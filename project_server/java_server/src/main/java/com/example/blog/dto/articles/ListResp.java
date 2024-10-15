package com.example.blog.dto.articles;

import com.example.blog.Model.Articles;

import lombok.Data;

import java.util.List;
@Data
public class ListResp {
    private List<Articles> list;
    private Integer total;
}
