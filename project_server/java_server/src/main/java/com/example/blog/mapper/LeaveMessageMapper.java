package com.example.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.Model.LeaveMessages;

import java.util.List;

public interface LeaveMessageMapper extends BaseMapper<LeaveMessages> {
    public List<LeaveMessages> getListByPage(int offset);
}
