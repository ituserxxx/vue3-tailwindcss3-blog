package com.example.blog.dto.articles;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class AddReq {
    @NotEmpty(message = "title cannot be empty")
    private String title;
    @NotEmpty(message = "title cannot be empty")
    private String content;
    @Min(value = 0, message = "status cannot be empty")
    private Integer status;
    private Integer[] tagsId;
}
