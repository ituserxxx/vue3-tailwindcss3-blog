package com.example.blog.Model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("users")
public class Users {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String passwd;
    private Date createTime;
}
