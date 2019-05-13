package com.spacex.persian.repository.po;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "task")
public class TaskPO {
    @Id
    private Long id;
    private Long shopId;
    private String name;
    private Integer taskStatus;
    private Integer deleted;
}
