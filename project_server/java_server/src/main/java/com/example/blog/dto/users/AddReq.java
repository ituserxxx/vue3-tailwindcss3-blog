package com.example.blog.dto.users;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class AddReq {
    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @NotEmpty(message = "passwd cannot be empty")
    private String passwd;
}
