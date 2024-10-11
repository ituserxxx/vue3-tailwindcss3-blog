package com.example.blog.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleListResp {
    private int id;
    private Date date;
    private String title;
    private String body;
}
