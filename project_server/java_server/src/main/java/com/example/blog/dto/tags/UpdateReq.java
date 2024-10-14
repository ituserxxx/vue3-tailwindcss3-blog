package com.example.blog.dto.tags;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UpdateReq {
    @Min(value = 0, message = "id cannot be empty")
    private Integer id;
    @NotEmpty(message = "tag name cannot be empty")
    private String name;
}
