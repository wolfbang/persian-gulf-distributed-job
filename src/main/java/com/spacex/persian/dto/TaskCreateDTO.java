package com.spacex.persian.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class TaskCreateDTO {

    @NotNull(message = "shopId不能为空")
    private Long shopId;

    @NotBlank(message = "shopId不能为空")
    private String name;
}
