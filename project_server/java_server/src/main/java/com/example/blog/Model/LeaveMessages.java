package com.example.blog.Model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("leave_messages")
public class LeaveMessages {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String content;

    private Date createTime;

    private String ip;

    private String ipAddr;

}
