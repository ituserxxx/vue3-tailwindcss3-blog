package com.example.blog.dto.articles;

import lombok.Data;
import java.util.Date;

@Data
public class InfoResp {
    private Integer id;

    private String title;

    private String content;

    private Date createTime;

    private Date updateTime;

    private Integer status;
    private Integer[] tagsIdList;

}
