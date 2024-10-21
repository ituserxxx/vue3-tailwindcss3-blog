package com.example.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.example.blog.Model.LeaveMessages;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface LeaveMessagesMapper extends BaseMapper<LeaveMessages> {
    public List<LeaveMessages> getListByPage(int offset);

    @Select({
            "<script>",
            "select * from leave_messages order by id desc limit #{offset},#{limit}",
            "</script>"
    })
    public List<LeaveMessages> GetList(@Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select({
            "<script>",
            "select count(1) from leave_messages",
            "</script>"
    })
    public int GetListCount();
}
