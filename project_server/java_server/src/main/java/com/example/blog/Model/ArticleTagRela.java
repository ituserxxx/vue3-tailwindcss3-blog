package com.example.blog.Model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("article_tag_rela")
public class ArticleTagRela {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer tagId;
    private Integer articleId;
}
