package com.example.blog.Model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("articles")
public class Articles {
    @TableId(type = IdType.AUTO)
    private int id;

    private String title;

    private String content;

    private Date createTime;

    private Date updateTime;

    // 	状态：1发布，2草稿，3删除	
    private int status;

    private int commentsSum;

    private int viewSum;
}
