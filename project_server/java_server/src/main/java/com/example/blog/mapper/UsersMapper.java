package com.example.blog.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.Model.Users;

public interface UsersMapper extends BaseMapper<Users> {
    public Users GetOneByUsernameAndPasswd(String username, String passwd);

    public List<Users> GetList(Integer page, Integer pageSize);
}
