package com.spacex.persian.controller;

import com.spacex.persian.dto.TaskCreateDTO;
import com.spacex.persian.dto.TaskDTO;
import com.spacex.persian.dto.TaskUpdateDTO;
import com.spacex.persian.service.TaskService;
import com.spacex.persian.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("task")
public class TaskController {

    private Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Resource
    private TaskService taskService;

    @PostMapping
    public TaskDTO create(@RequestBody TaskCreateDTO taskCreateDTO) {
        logger.info(String.format("TaskController#create taskCreateDTO:%s", JsonUtil.toJson(taskCreateDTO)));
        TaskDTO taskDTO = taskService.create(taskCreateDTO);
        return taskDTO;
    }

    @PutMapping
    public TaskDTO update(@RequestBody TaskUpdateDTO taskUpdateDTO) {
        logger.info(String.format("TaskController#update taskUpdateDTO:%s", JsonUtil.toJson(taskUpdateDTO)));
        TaskDTO taskDTO = taskService.update(taskUpdateDTO);
        return taskDTO;
    }
}
