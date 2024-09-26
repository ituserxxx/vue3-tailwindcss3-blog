package com.example.blog.dto;

import com.example.blog.Model.Tag;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ArticleDetailResp {
    private int id;
    private int view;
    private Date date;
    private String title;

    private String content;

    private List<Tag> tags;
}
