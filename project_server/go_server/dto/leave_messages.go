package dto

import "go_server/model"

type LeaveMessagesResp struct {
	List  []model.LeaveMessages `json:"list"`
	Total int64                 `json:"total"`
}

type LeaveMessagesAddReq struct {
	Name    string `json:"name" binding:"required"`
	Content string `json:"content" binding:"required"`
}
