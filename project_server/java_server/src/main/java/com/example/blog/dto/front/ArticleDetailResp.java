package com.example.blog.dto.front;

import lombok.Data;
import java.util.Date;

import com.example.blog.Model.Tags;

@Data
public class ArticleDetailResp {
    private Integer id;

    private String title;

    private String content;

    private Date createTime;

    private Date updateTime;

    private Integer status;
    private Integer commentsSum;

    private Integer viewSum;
    private Tags[] tagsList;

    public void setTagsIdList(Tags[] tt) {
        this.tagsList = tt;
    }

}
