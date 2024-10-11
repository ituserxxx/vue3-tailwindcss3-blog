package com.example.blog.Model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("leave_messages")
public class LeaveMessages {

    private Integer id;

    private String name;

    private String content;

    private Date createTime;

    private String ip;

    private String ipAddr;

}
