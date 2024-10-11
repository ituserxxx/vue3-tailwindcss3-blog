package com.example.blog.service;

import com.example.blog.Model.Tags;
import com.example.blog.mapper.TagsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsService {
    @Autowired
    private TagsMapper tagMapper;
    public List<Tags> list(){
        return tagMapper.selectList(null);
    }

}
