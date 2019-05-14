package com.spacex.persian.enums;

public enum DeletedStatus {
    NOT_DELETED(0), DELETED(1);
    private Integer code;

    DeletedStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
