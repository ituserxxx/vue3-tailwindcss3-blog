package controller

import (
	"github.com/gin-gonic/gin"
	Config "go_server/config"
	"go_server/dto"
	"go_server/model"
	"time"
)

type users struct {
}

var Users = users{}

func (users) Login(c *gin.Context) {
	var params dto.LoginReq
	if err := c.ShouldBind(&params); err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	var uInfo *model.Users
	Config.Dao.Model(model.Users{}).
		Where("name=?", params.Username).
		Where("passwd=?", params.Passwd).
		Scan(&uInfo)
	if uInfo == nil {
		dto.ReturnRes.Err(c, 10001, "not find record")
		return
	}
	if uInfo.Status == 0 {
		dto.ReturnRes.Err(c, 10001, "状态异常")
		return
	}
	dto.ReturnRes.Succ(c, uInfo.ID)
}

func (users) Add(c *gin.Context) {
	var params dto.LoginReq
	if err := c.ShouldBind(&params); err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	var isExistName *model.Users
	Config.Dao.Model(model.Users{}).
		Where("name=?", params.Username).
		Scan(&isExistName)
	if isExistName != nil {
		dto.ReturnRes.Err(c, 10001, "find exist record")
		return
	}

	var newRecord = model.Users{
		Name:       params.Username,
		Passwd:     params.Passwd,
		Status:     1,
		CreateTime: time.Now().Format("2006-01-02 15:04:05"),
	}
	Config.Dao.Model(model.Users{}).Create(&newRecord)
	dto.ReturnRes.Succ(c, newRecord.ID)
}

func (users) Update(c *gin.Context) {
	var params dto.UsersUpdateReq
	if err := c.ShouldBind(&params); err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}

	Config.Dao.Model(model.Users{}).Updates(model.Users{
		ID:     params.Id,
		Name:   params.Name,
		Passwd: params.Passwd,
	})
	dto.ReturnRes.Succ(c, "")
}

func (users) Info(c *gin.Context) {
	var params dto.IdReq
	if err := c.ShouldBind(&params); err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	var uInfo *model.Users
	Config.Dao.Model(model.Users{}).
		Where("id=?", params.ID).
		Scan(&uInfo)
	if uInfo == nil {
		dto.ReturnRes.Err(c, 10001, "not find record")
		return
	}
	dto.ReturnRes.Succ(c, uInfo)
}

func (users) Delete(c *gin.Context) {
	var params dto.IdReq
	if err := c.ShouldBind(&params); err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	Config.Dao.Model(model.Users{}).
		Where("id=?", params.ID).
		Unscoped().
		Delete(model.Users{})
	dto.ReturnRes.Succ(c, "")
}

func (users) List(c *gin.Context) {
	var params dto.PageReq
	if err := c.ShouldBind(&params); err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	var data = dto.UserListResp{
		List: make([]model.Users, 0),
	}
	Config.Dao.Model(model.Users{}).
		Offset((params.Page - 1) * params.PageSize).
		Limit(params.PageSize).
		Order("id desc").
		Scan(&data.List)
	Config.Dao.Model(model.Users{}).Count(&data.Total)
	dto.ReturnRes.Succ(c, data)
}
