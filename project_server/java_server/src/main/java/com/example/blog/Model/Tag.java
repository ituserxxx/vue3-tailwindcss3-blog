package com.example.blog.Model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tags")
public class Tag {

    private Integer id;

    private String name;

    private int articleSum;
}
