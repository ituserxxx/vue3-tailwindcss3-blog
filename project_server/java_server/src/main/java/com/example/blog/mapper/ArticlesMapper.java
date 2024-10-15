package com.example.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.Model.Articles;
import com.example.blog.dto.ArticleDetailResp;
import com.example.blog.dto.ArticleListResp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ArticlesMapper extends BaseMapper<Articles> {
    public List<ArticleListResp> getArticleListByPageAndTag(Map<String, Integer> param);

    public Integer getArticleCountByTag(Map<String, Integer> param);

    public ArticleDetailResp getArticleById(int id);

    @Select({
            "<script>",
            "select * from articles order by id desc limit #{offset},#{limit}",
            "</script>"
    })
    public List<Articles> GetList(@Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select({
        "<script>",
        "select count(1) from articles",
        "</script>",
    })
    public Integer GetListCount();
}
