package com.spacex.persian.job;

import lombok.Data;

@Data
public abstract class Worker implements Runnable {

    private Integer id;

    public Worker(Integer id) {
        this.id = id;
    }
}
