package dto

import "go_server/model"

type LeaveMessagesResp struct {
	List  []model.LeaveMessages `json:"list"`
	Total int64                 `json:"total"`
}

type LeaveMessagesAddReq struct {
	Name    string `form:"name" binding:"required"`
	Content string `form:"content" binding:"required"`
}
type SearchReq struct {
	Content string `form:"content"`
}
