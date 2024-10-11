package com.example.blog.controller.admin;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import com.example.blog.common.Result;
import com.example.blog.dto.users.LoginReq;

@RestController
@RequestMapping("admin/user")
public class UsersController {
    @PostMapping("/login")
    public Result Login(@Validated @RequestBody LoginReq req, BindingResult err) {
        Result result = new Result();
        try {

            if (err.hasErrors()) {
                throw new Exception(err.getAllErrors().get(0).getDefaultMessage());
            }

            result.setData(2);
        } catch (Exception e) {
            result.setCode(2300);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
