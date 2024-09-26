package com.example.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.blog.Model.LeaveMessage;
import com.example.blog.mapper.LeaveMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveMessageService {
    @Autowired
    private LeaveMessageMapper leaveMessageMapper;

    public List<LeaveMessage> list(int offset){
        return leaveMessageMapper.getListByPage(offset);
    }
    public Integer add(LeaveMessage leaveMessage){
        return leaveMessageMapper.insert(leaveMessage);
    }
}
