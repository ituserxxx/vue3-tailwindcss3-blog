package com.example.blog.dto.tags;

import java.util.List;

import com.example.blog.Model.Tags;

import lombok.Data;

@Data
public class ListResp {
    private List<Tags> list;
    private Integer total;

    public void setTotal(Integer total) {
        this.total = total;
    }
}
