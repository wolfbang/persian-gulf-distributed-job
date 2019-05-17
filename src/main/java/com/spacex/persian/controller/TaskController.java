package com.spacex.persian.controller;

import com.spacex.persian.dto.TaskCreateDTO;
import com.spacex.persian.dto.TaskDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("task")
public class TaskController {

    @PostMapping
    public TaskDTO create(@RequestBody TaskCreateDTO taskCreateDTO) {
        return null;
    }
}
