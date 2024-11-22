package dto

import (
	"go_server/model"
)

type ArticleAddReq struct {
	Title   string `json:"title"  binding:"required"`
	Content string `json:"content"  binding:"required"`
	Status  int    `json:"status"  binding:"required"`
	TagsId  []uint `json:"tags_id"`
}
type ArticleUpdateReq struct {
	Id         uint   `json:"id" binding:"required"`
	CreateTime string `json:"createTime" binding:"required"`
	UpdateTime string `json:"updateTime"`
	Title      string `json:"title"  binding:"required"`
	Content    string `json:"content"  binding:"required"`
	Status     int    `json:"status"  binding:"required"`
	TagsId     []uint `json:"tags_id"`
}
type ArticlesListResp struct {
	List  []model.Articles `json:"list"`
	Total int64            `json:"total"`
}

type ChangeStatusReq struct {
	ID     int `json:"id"  binding:"required"`
	Status int `json:"status" binding:"required" min:"1" max:"3"`
}

type ArticlesInfoResp struct {
	model.Articles
	TagsIdList []int64 `json:"tagsIdList"`
}
