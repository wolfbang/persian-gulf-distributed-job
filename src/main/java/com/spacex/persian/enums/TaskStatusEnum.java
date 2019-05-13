package com.spacex.persian.enums;

public enum TaskStatusEnum {
    WAITING(0), PROCESSING(1), FINISHED(2), FAILED(3);
    private Integer code;

    TaskStatusEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public TaskStatusEnum getEnumByCode(int code) {
        for (TaskStatusEnum statusEnum : TaskStatusEnum.values()) {
            if (statusEnum.getCode() == code) {
                return statusEnum;
            }
        }
        return null;
    }
}
