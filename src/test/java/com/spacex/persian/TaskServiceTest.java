package com.spacex.persian;

import com.spacex.persian.dto.TaskDTO;
import com.spacex.persian.enums.TaskStatusEnum;
import com.spacex.persian.service.TaskService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest {
    @Resource
    private TaskService taskService;


    private Long taskId;

    @Before
    public void init() {
        taskId = 1L;
    }

    @Test
    public void getById() {
        TaskDTO taskDTO = taskService.getById(taskId);
        Assert.assertNotNull(taskDTO);
    }

    @Test
    @Transactional
    public void delete() {
        taskService.delete(taskId);
        TaskDTO taskDTO = taskService.getById(taskId);
        Assert.assertNull(taskDTO);
    }

    @Test
    @Transactional
    public void updateStatus() {
        taskService.updateStatus(taskId, TaskStatusEnum.PROCESSING);
        TaskDTO taskDTO = taskService.getById(taskId);
        Assert.assertNotNull(taskDTO);
        Assert.assertEquals(taskDTO.getTaskStatus(), TaskStatusEnum.PROCESSING.getCode());
    }
}
