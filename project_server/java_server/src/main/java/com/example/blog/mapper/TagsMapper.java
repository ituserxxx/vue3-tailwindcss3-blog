package com.example.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.Model.Tags;

public interface TagsMapper extends BaseMapper<Tags> {
    public List<Tags> GetList(Integer page, Integer pageSize);

    @Update({
            "<script>",
            "UPDATE tags SET article_sum = article_sum + 1 WHERE id IN",
            "<foreach item='id' collection='idArr' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    void IncrementArticleSumByIdArr(@Param("idArr") Integer[] idArr);

    @Select({
            "<script>",
            "SELECT t.id FROM tags as t ",
            "LEFT JOIN article_tag_rela as atr ON atr.tag_id=t.id ",
            "WHERE atr.article_id=#{articleId} ",
            "</script>"
    })
    public Integer[] GetListByArticleId(@Param("articleId") Integer articleId);
}
