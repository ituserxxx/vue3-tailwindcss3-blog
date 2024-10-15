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
    private Integer id;

    private String title;

    private String content;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    private Integer commentsSum;

    private Integer viewSum;
}
