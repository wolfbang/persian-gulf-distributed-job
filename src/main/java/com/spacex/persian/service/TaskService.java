package com.spacex.persian.service;

import com.spacex.persian.dto.TaskDTO;
import com.spacex.persian.dto.TaskUpdateDTO;
import com.spacex.persian.enums.TaskStatusEnum;
import com.spacex.persian.repository.mapper.TaskMapper;
import com.spacex.persian.repository.po.TaskPO;
import com.spacex.persian.util.BeanCopyUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TaskService {

    private Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Resource
    private TaskMapper taskMapper;

    public TaskDTO getNextTask() {
        Example example = new Example(TaskPO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", TaskStatusEnum.WAITING.getCode());
        example.setOrderByClause("id asc");
        List<TaskPO> taskPOs = taskMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(taskPOs)) {
            return null;
        }
        TaskPO taskPO = taskPOs.get(0);
        TaskDTO taskDTO = BeanCopyUtil.map(taskPO, TaskDTO.class);
        return taskDTO;
    }

    public TaskDTO getById(Long taskId) {
        Example example = new Example(TaskPO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", taskId);
        example.setOrderByClause("id asc");
        List<TaskPO> taskPOs = taskMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(taskPOs)) {
            return null;
        }
        TaskPO taskPO = taskPOs.get(0);
        TaskDTO taskDTO = BeanCopyUtil.map(taskPO, TaskDTO.class);
        return taskDTO;
    }

    public TaskDTO update(TaskUpdateDTO taskUpdateDTO) {
        Long taskId = taskUpdateDTO.getId();
        Example example = new Example(TaskPO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", taskId);
        TaskPO taskPO = BeanCopyUtil.map(taskUpdateDTO, TaskPO.class);
        taskPO.setId(null);
        taskMapper.updateByExampleSelective(taskPO, example);
        return getById(taskId);
    }

    public TaskDTO updateStatus(Long taskId, TaskStatusEnum taskStatusEnum) {
        if (taskId == null || taskStatusEnum == null) {
            return null;
        }

        Example example = new Example(TaskPO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", taskId);
        TaskPO taskPO = new TaskPO();
        taskPO.setTaskStatus(taskStatusEnum.getCode());
        taskMapper.updateByExampleSelective(taskPO, example);
        return getById(taskId);
    }

}
