package com.example.blog.dto.tags;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class AddReq {
    @NotEmpty(message = "tag name cannot be empty")
    private String name;
}
