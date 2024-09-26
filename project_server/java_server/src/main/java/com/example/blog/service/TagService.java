package com.example.blog.service;

import com.example.blog.Model.Tag;
import com.example.blog.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagMapper tagMapper;
    public List<Tag> list(){
        return tagMapper.selectList(null);
    }

}
