package com.example.blog.controller;

import com.example.blog.common.Result;
import com.example.blog.dto.ArticleDetailResp;
import com.example.blog.mapper.ArticleMapper;
import com.example.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("blog/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("list")
    public Result list(String page, String tagId) {
        Result result = new Result();
        try {
            int pageNum = 0;
            Map<String, Integer> param = new HashMap<>();
            if(null != tagId && tagId != ""){
                int tag = Integer.parseUnsignedInt(tagId);
                param.put("tagId", tag);
            }
            if(null != page && page != ""){
                pageNum = Integer.parseUnsignedInt(page);
            }
            int offset = pageNum * 10;
            param.put("offset", offset);
            Map<String,Object> map=new HashMap<>();
            map.put("articleList",articleService.list(param));
            map.put("total",articleService.count(param));
            result.setData(map);
        } catch (NumberFormatException e) {
            result.setData("page或tagId格式错误");
            result.setMsg(false);
            result.setCode(1);
        }

        return result;
    }

    @PostMapping("detail")
    public Result detail(String id) {
        Result result = new Result();
        try {
            int articleId = Integer.parseInt(id);
            ArticleDetailResp articleDetailResp = articleService.detail(articleId);
            if (null == articleDetailResp) {
                result.setData("文章不存在");
            } else {
                result.setData(articleDetailResp);
            }
        } catch (NumberFormatException e) {
            result.setData("id格式错误");
            result.setMsg(false);
            result.setCode(1);
        }
        return result;
    }


}
