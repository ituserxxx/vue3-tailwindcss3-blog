package com.example.blog.dto.articles;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class ChangeStatusReq {
    @Min(value = 0, message = "id cannot be empty")
    private Integer id;
    @Min(value = 1, message = "status cannot be than main")
    @Max(value = 3, message = "status cannot be than max")
    private Integer status;

}
