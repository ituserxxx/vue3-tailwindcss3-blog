package com.example.blog.controller.admin;

import com.example.blog.common.Result;
import com.example.blog.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("admin/tags")
public class TagsController {
    @Autowired
    private TagsService tagService;

    @PostMapping("list")
    public Result list(String page, String tagId) {
        Result result = new Result();
        Map<String, Object> map = new HashMap<>();
        map.put("list", tagService.list());
        result.setData(map);
        return result;
    }
}
