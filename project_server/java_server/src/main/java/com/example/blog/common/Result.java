package com.example.blog.common;

import lombok.Data;

@Data
public class Result {
    private Object data;

    private String msg;

    private int code;

    public Result() {
        this.msg = "ok";
        this.code = 0;
    }
}
