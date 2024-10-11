package com.example.blog.controller.admin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.common.Result;

@RestController
@RequestMapping("admin/user")
public class UsersController {
    @PostMapping("/login")
    public Result Login(){
        Result result = new Result();
        result.setData(2);
        return result;
    }
}
