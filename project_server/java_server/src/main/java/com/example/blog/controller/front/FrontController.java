package com.example.blog.controller.front;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.Model.Articles;
import com.example.blog.common.Result;
import com.example.blog.dto.front.ArticleDetailResp;
import com.example.blog.dto.front.ArticlesListResp;
import com.example.blog.mapper.ArticlesMapper;
import com.example.blog.mapper.TagsMapper;

@RestController
@RequestMapping("blog/")
public class FrontController {

    @Autowired
    private ArticlesMapper articlesMapper;

    @Autowired
    private TagsMapper tagsMapper;

    @PostMapping("articles/list")
    public Result ArticleList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "tagId", defaultValue = "0") Integer tagId) {
        Result result = new Result();
        try {
            ArticlesListResp data = new ArticlesListResp();
            data.setList(articlesMapper.GetArticlesListByTagId((page - 1) * 8, 8, tagId));
            data.setTotal(articlesMapper.GetArticlesListByTagIdCount(tagId));
            result.setData(data);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            result.setCode(9001);
        }
        return result;
    }

    @PostMapping("articles/detail")
    public Result ArticleDetail(@RequestParam(value = "id", required = true) Integer id) {
        Result result = new Result();
        try {
            ArticleDetailResp data = new ArticleDetailResp();
            Articles articles = articlesMapper.selectById(id);
            data.setContent(articles.getContent());
            data.setId(articles.getId());
            data.setCreateTime(articles.getCreateTime());
            data.setStatus(articles.getStatus());
            data.setTitle(articles.getTitle());
            data.setUpdateTime(articles.getUpdateTime());
            data.setTagsIdList(tagsMapper.GetAllByArticleId(id));
            result.setData(data);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            result.setCode(9001);
        }
        return result;
    }

    @PostMapping("tags/list")
    public Result TagsList() {
        Result result = new Result();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("list", tagsMapper.GetAll());
            result.setData(map);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            result.setCode(9001);
        }
        return result;
    }
}
