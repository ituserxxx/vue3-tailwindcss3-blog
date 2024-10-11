package com.example.blog.service;

import com.example.blog.Model.Tags;
import com.example.blog.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsService {
    @Autowired
    private TagMapper tagMapper;
    public List<Tags> list(){
        return tagMapper.selectList(null);
    }

}
