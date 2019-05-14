package com.spacex.persian.dto;

import lombok.Data;

@Data
public class TaskDTO {
    private Long id;
    private Long shopId;
    private String name;
    private Integer taskStatus;
    private Integer deleted;
}
