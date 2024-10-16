package com.example.blog.dto.front;

import com.example.blog.Model.Articles;

import lombok.Data;

import java.util.List;
@Data
public class ArticlesListResp {
    private List<Articles> list;
    private int total;
}
