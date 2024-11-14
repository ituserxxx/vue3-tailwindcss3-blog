package controller

import (
	"github.com/gin-gonic/gin"
	Config "go_server/config"
	"go_server/dto"
	"go_server/model"
)

type leaveMessages struct {
}

var LeaveMessages = &leaveMessages{}

func (leaveMessages) List(c *gin.Context) {
	var params dto.PageReq
	if err := c.ShouldBind(&params); err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	var data = dto.LeaveMessagesResp{
		List: make([]model.LeaveMessages, 0),
	}
	Config.Dao.Model(model.LeaveMessages{}).
		Offset((params.Page - 1) * params.PageSize).
		Limit(params.PageSize).
		Order("id desc").
		Scan(data.List)
	Config.Dao.Model(model.LeaveMessages{}).Count(&data.Total)
	dto.ReturnRes.Succ(c, data)
}

func (leaveMessages) Delete(c *gin.Context) {
	var params dto.IdReq
	if err := c.ShouldBind(&params); err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	Config.Dao.Model(model.LeaveMessages{}).
		Where("id=?", params.ID).
		Unscoped().
		Delete(model.LeaveMessages{})
	dto.ReturnRes.Succ(c, "")
}
