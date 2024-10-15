package com.example.blog.dto.articles;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import java.util.Date;;

@Data
public class UpdateReq {
    @Min(value = 0, message = "id cannot be empty")
    private Integer id;
    @NotEmpty(message = "title cannot be empty")
    private String title;
    @NotEmpty(message = "title cannot be empty")
    private String content;
    @Min(value = 0, message = "status cannot be empty")
    private Integer status;
    private Date createTime;
    private Date updateTime;
    private Integer[] tagsId;

}
