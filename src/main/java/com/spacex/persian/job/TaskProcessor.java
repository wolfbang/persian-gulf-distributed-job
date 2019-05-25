package com.spacex.persian.job;

import com.spacex.persian.dto.TaskDTO;
import com.spacex.persian.enums.TaskStatusEnum;
import com.spacex.persian.service.DistributedLockService;
import com.spacex.persian.service.TaskService;
import com.spacex.persian.util.threads.ThreadPoolExecutor;
import com.spacex.persian.util.threads.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

@Component
public class TaskProcessor {

    private Logger logger = LoggerFactory.getLogger(TaskProcessor.class);

    private final int capacity = 100;
    private final int totalWorker = 50;

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 2, TimeUnit.MINUTES, new ArrayBlockingQueue<>(capacity));

    @Resource
    private TaskService taskService;

    @Resource
    private DistributedLockService distributedLockService;

    @Value("active.worker.total:50")
    private Integer activeWorkerNumber;

    @PostConstruct
    public void doJob() {
        logger.info(String.format("TaskProcessor#process args:%s", null));

        for (int i = 0; i < totalWorker; i++) {
            Worker worker = new Worker(i) {
                @Override
                public void run() {

                    if (getId() > activeWorkerNumber) {
                        logger.info(String.format("TaskProcessor#process  worker id:%s not activated!", getId()));
                        ThreadUtil.sleep(30000L);
                        return;
                    }

                    TaskDTO taskDTO = taskService.getNextTask();
                    if (taskDTO == null) {
                        ThreadUtil.sleep(30000L);
                        return;
                    }

                    if (TaskStatusEnum.WAITING.getCode() != taskDTO.getTaskStatus()) {
                        return;
                    }

                    Long taskId = taskDTO.getId();
                    String requestId = UUID.randomUUID().toString();
                    String key = "" + taskId;
                    boolean acquireLock = distributedLockService.acquire(key, 1L, 50000L);

                    if (!acquireLock) {
                        logger.info("get lock failed and discard task...");
                        return;
                    }

                    try {
                        updateTaskStatus(taskDTO.getId(), TaskStatusEnum.PROCESSING);
                        process(taskDTO);
                        updateTaskStatus(taskDTO.getId(), TaskStatusEnum.FINISHED);
                    } catch (Exception ex) {
                        updateTaskStatus(taskDTO.getId(), TaskStatusEnum.FAILED);
                        logger.warn("TaskProcessor#doJob error:%s", ex);
                    } finally {
                        distributedLockService.releaseDistributedLock(key, requestId);
                    }
                }
            };

            threadPoolExecutor.execute(worker);
        }
    }

    public void process(TaskDTO taskDTO) {
        logger.info("[Thread-Worker] job now!");
    }

    public void updateTaskStatus(Long taskId, TaskStatusEnum taskStatusEnum) {
        taskService.updateStatus(taskId, taskStatusEnum);
    }
}
