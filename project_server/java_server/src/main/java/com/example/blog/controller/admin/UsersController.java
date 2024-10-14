package com.example.blog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;

import com.example.blog.Model.Users;
import com.example.blog.common.Result;
import com.example.blog.dto.PageReq;
import com.example.blog.dto.users.DelReq;
import com.example.blog.dto.users.LoginReq;
import com.example.blog.mapper.UsersMapper;
import java.util.Date;

@RestController
@RequestMapping("admin/users")
public class UsersController {

    @Autowired
    private UsersMapper usersMapper;

    @PostMapping("/login")
    public Result Login(@Validated @RequestBody LoginReq req, BindingResult err) {
        Result result = new Result();
        try {
            if (err.hasErrors()) {
                throw new Exception(err.getAllErrors().get(0).getDefaultMessage());
            }
            Users info = usersMapper.GetOneByUsernameAndPasswd(req.getUsername(), req.getPasswd());
            if (info == null) {
                throw new Exception("账号或者密码错误");
            }
            result.setData(info.getId());
        } catch (Exception e) {
            result.setCode(2300);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @PostMapping("/add")
    public Result Add(@Validated @RequestBody LoginReq req, BindingResult err) {
        Result result = new Result();
        try {
            if (err.hasErrors()) {
                throw new Exception(err.getAllErrors().get(0).getDefaultMessage());
            }
            Users isExist = usersMapper.GetOneByUsernameAndPasswd(req.getUsername(), req.getPasswd());
            if (isExist != null) {
                throw new Exception("名称已存在，不可重复");
            }
            Users info = new Users();

            info.setName(req.getUsername());
            info.setPasswd(req.getPasswd());
            info.setCreateTime(new Date());
            usersMapper.insert(info);

            result.setData(info.getId());
        } catch (Exception e) {
            result.setCode(2300);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @PostMapping("/del")
    public Result Del(@Validated @RequestBody DelReq req, BindingResult err) {
        Result result = new Result();
        try {
            if (err.hasErrors()) {
                throw new Exception(err.getAllErrors().get(0).getDefaultMessage());
            }
            int row = usersMapper.deleteById(req.getId());
            if (row == 0) {
                throw new Exception("操作失败~");
            }
        } catch (Exception e) {
            result.setCode(2300);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @PostMapping("/list")
    public Result List(@Validated @RequestBody PageReq req, BindingResult err) {
        Result result = new Result();
        try {
            if (err.hasErrors()) {
                throw new Exception(err.getAllErrors().get(0).getDefaultMessage());
            }
            // todo
        } catch (Exception e) {
            result.setCode(2300);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
