package com.example.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.Model.Users;

public interface UsersMapper extends BaseMapper<Users> {

    public Users GetOneByUsernameAndPasswd(@Param("username") String username, @Param("passwd") String passwd);

    public List<Users> GetList(@Param("offset") Integer offset, @Param("limit") Integer limit);

    public Integer GetListCount();
}
