package dto

import "go_server/model"

type LeaveMessagesResp struct {
	List  []model.LeaveMessages `json:"list"`
	Total int64                 `json:"total"`
}

type LeaveMessagesAddReq struct {
	Name    string `json:"name" from:"name" binding:"required"`
	Content string `json:"content" from:"content" binding:"required"`
}
type SearchReq struct {
	Content string `json:"content"`
}
