package com.example.blog.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.blog.dto.ArticleDetailResp;
import com.example.blog.dto.ArticleListResp;
import com.example.blog.mapper.ArticlesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticlesService {
    @Autowired
    private ArticlesMapper articleMapper;

    public List<ArticleListResp> list(Map<String, Integer> param) {
        return articleMapper.getArticleListByPageAndTag(param);
    }

    public Integer count(Map<String, Integer> param) {
        return articleMapper.getArticleCountByTag(param);
    }

    public ArticleDetailResp detail(int id) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id", id);
        updateWrapper.setSql("view_sum = view_sum + 1");
        articleMapper.update(null, updateWrapper);
        return articleMapper.getArticleById(id);
    }
}
