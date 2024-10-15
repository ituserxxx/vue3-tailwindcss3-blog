package com.example.blog.dto;

import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class IdReq {
    @Min(value = 0, message = "id cannot be empty")
    private Integer id;
}
