package com.example.blog.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.Model.Tags;

public interface TagsMapper extends BaseMapper<Tags> {
    public List<Tags> GetList(Integer page, Integer pageSize);
}
