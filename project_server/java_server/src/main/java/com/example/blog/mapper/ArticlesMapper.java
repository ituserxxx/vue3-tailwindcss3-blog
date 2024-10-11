package com.example.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.Model.Articles;
import com.example.blog.dto.ArticleDetailResp;
import com.example.blog.dto.ArticleListResp;

import java.util.List;
import java.util.Map;

public interface ArticlesMapper extends BaseMapper<Articles> {
    public List<ArticleListResp> getArticleListByPageAndTag(Map<String, Integer> param);

    public Integer getArticleCountByTag(Map<String, Integer> param);

    public ArticleDetailResp getArticleById(int id);
}
