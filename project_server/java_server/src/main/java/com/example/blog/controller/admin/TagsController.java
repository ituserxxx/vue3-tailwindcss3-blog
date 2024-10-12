package com.example.blog.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.blog.Model.Tags;
import com.example.blog.common.Result;
import com.example.blog.dto.tags.AddReq;
import com.example.blog.dto.tags.UpdateReq;
import com.example.blog.mapper.TagsMapper;
import com.example.blog.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import javax.validation.OverridesAttribute.List;

@RestController
@RequestMapping("admin/tags")
public class TagsController {
    @Autowired
    private TagsService tagService;

    @Autowired
    private TagsMapper tagsMapper;

    @PostMapping("list")
    public Result list(String page, String tagId) {
        Result result = new Result();
        Map<String, Object> map = new HashMap<>();
        // List<Tags> list = tagsMapper.selectPage(null, null)
        map.put("list", tagService.list());
        result.setData(map);
        return result;
    }

    @PostMapping("add")
    public Result add(@Validated @RequestBody AddReq req, BindingResult err) {
        Result result = new Result();
        try {
            if (err.hasErrors()) {
                throw new Exception(err.getAllErrors().get(0).getDefaultMessage());
            }
            QueryWrapper<Tags> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name", req.getName());
            boolean exists = tagsMapper.exists(queryWrapper);
            if (exists) {
                throw new Exception("名称已存在");
            }
            result.setData("2222");
        } catch (Exception e) {
            result.setCode(2401);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @PostMapping("update")
    public Result update(@Validated @RequestBody UpdateReq req, BindingResult err) {
        Result result = new Result();
        try {
            if (err.hasErrors()) {
                throw new Exception(err.getAllErrors().get(0).getDefaultMessage());
            }
            QueryWrapper<Tags> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name", req.getName());
            boolean exists = tagsMapper.exists(queryWrapper);
            if (exists) {
                throw new Exception("名称已存在");
            }

            UpdateWrapper updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", req.getId());
            updateWrapper.set("name", req.getName());
            int updateRows = tagsMapper.update(null, updateWrapper);
            if (updateRows != 1) {
                throw new Exception("更新失败");
            }

        } catch (Exception e) {
            result.setCode(2401);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
