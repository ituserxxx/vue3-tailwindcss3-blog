package com.example.blog.Model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tags")
public class Tags {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer articleSum;
}
