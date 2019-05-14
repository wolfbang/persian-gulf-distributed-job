package com.spacex.persian.service;

import com.spacex.persian.dto.TaskDTO;
import com.spacex.persian.dto.TaskUpdateDTO;
import com.spacex.persian.enums.TaskStatusEnum;
import com.spacex.persian.repository.mapper.TaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TaskService {

    private Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Resource
    private TaskMapper taskMapper;

    public TaskDTO getNextTask() {
        return null;
    }

    public TaskDTO getById(Long taskId) {
        return null;
    }

    public TaskDTO update(TaskUpdateDTO taskUpdateDTO) {
        return null;
    }

    public TaskDTO updateStatus(Long taskId, TaskStatusEnum taskStatusEnum) {
        return null;
    }

}
