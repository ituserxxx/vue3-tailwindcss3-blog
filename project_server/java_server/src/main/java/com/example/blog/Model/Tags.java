package com.example.blog.Model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tags")
public class Tags {

    private Integer id;

    private String name;

    private int articleSum;
}
