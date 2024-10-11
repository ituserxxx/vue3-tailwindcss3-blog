package com.example.blog.dto.users;

import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class DelReq {
    @Min(value = 0, message = "id cannot be empty")
    private Integer id;
}
