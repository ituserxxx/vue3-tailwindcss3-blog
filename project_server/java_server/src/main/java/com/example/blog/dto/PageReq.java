package com.example.blog.dto;


import lombok.Data;

@Data
public class PageReq {
    private int page;
    private int pageSize;

    // 默认构造函数
    public PageReq() {
        this.page = 1;
        this.pageSize = 10;
    }
}
