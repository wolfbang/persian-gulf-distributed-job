package com.spacex.persian.dto;

import lombok.Data;

@Data
public class TaskUpdateDTO extends TaskCreateDTO {
    private Long id;
    private Integer taskStatus;
    private Integer deleted;
}
