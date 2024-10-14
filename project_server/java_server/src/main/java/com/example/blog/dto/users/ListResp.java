package com.example.blog.dto.users;

import java.util.List;

import com.example.blog.Model.Users;

import lombok.Data;

@Data
public class ListResp {
    private List<Users> list;
    private Integer total;
}
