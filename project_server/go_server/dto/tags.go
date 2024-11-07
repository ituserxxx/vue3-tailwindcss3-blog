package dto

import "go_server/model"

type TagsListResp struct {
	List  []model.Tags `json:"list"`
	Total int64        `json:"total"`
}

type TagsAddReq struct {
	Name string `json:"name" binding:"required"`
}

type TagsUpdateReq struct {
	ID   int    `json:"id"  binding:"required"`
	Name string `json:"name" binding:"required"`
}
