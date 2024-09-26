package com.example.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.Model.LeaveMessage;

import java.util.List;

public interface LeaveMessageMapper extends BaseMapper<LeaveMessage> {
    public List<LeaveMessage> getListByPage(int offset);
}
