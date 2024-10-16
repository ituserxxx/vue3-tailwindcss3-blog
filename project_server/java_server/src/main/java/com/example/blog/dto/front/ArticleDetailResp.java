package com.example.blog.dto.front;

import lombok.Data;
import java.util.Date;

import com.example.blog.Model.Tags;

@Data
public class ArticleDetailResp {
    private int id;

    private String title;

    private String content;

    private Date createTime;

    private Date updateTime;

    private int status;
    private int commentsSum;

    private int viewSum;
    private Tags[] tagsList;

    public void setTagsIdList(Tags[] tt) {
        this.tagsList = tt;
    }

}
