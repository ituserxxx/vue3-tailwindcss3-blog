package dto

import "go_server/model"

type LoginReq struct {
	Username string `json:"username"  binding:"required"`
	Passwd   string `json:"passwd"  binding:"required"`
}
type UserListResp struct {
	List  []model.Users `json:"list"`
	Total int64         `json:"total"`
}
