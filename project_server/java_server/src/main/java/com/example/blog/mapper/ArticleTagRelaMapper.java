package com.example.blog.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.example.blog.Model.ArticleTagRela;

public interface ArticleTagRelaMapper extends BaseMapper<ArticleTagRela> {
    @Insert({
            "<script>",
            "INSERT INTO article_tag_rela (tag_id, article_id) VALUES ",
            "<foreach collection='tagsIdArr' item='v' separator=','>",
            "(#{v}, #{articleId})",
            "</foreach>",
            "</script>"
    })
    int InsertBatch(@Param("tagsIdArr") Integer[] tagsIdArr, @Param("articleId") Integer articleId);

    @Delete({
            "<script>",
            "DELETE FROM article_tag_rela ",
            "WHERE article_id=#{articleId} ",
            "</script>"
    })
    int DeleteByArticleId(@Param("articleId") Integer articleId);
}
