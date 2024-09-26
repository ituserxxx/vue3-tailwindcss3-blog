package com.example.blog.Model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@TableName("articles")
public class Article {

    private Integer id;

    private String title;

    private String content;

    private Date createTime;

    private Date updateTime;

    private int status;

    private int comments_sum;

    private int view_sum;
}
