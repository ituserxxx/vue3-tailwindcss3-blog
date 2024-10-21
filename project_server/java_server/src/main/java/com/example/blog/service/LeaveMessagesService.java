package com.example.blog.service;

import com.example.blog.Model.LeaveMessages;
import com.example.blog.mapper.LeaveMessagesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveMessagesService {
    @Autowired
    private LeaveMessagesMapper leaveMessageMapper;

    public List<LeaveMessages> list(int offset){
        return leaveMessageMapper.getListByPage(offset);
    }
    public Integer add(LeaveMessages leaveMessage){
        return leaveMessageMapper.insert(leaveMessage);
    }
}
