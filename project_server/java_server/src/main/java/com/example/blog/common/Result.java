package com.example.blog.common;

import lombok.Data;

@Data
public class Result {
    private Object data;

    private boolean msg;

    private int code;

    public Result() {
        this.msg = true;
        this.code = 0;
    }
}
