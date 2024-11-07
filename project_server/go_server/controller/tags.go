package controller

import (
	"github.com/gin-gonic/gin"
	Config "go_server/config"
	"go_server/dto"
	"go_server/model"
)

type tags struct {
}

var Tags = tags{}

func (tags) List(c *gin.Context) {
	var params dto.PageReq
	if err := c.ShouldBind(&params); err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	var data = &dto.TagsListResp{
		List:  make([]model.Tags, 0),
		Total: 0,
	}
	Config.Dao.Model(model.Tags{}).
		Offset((params.Page - 1) * params.PageSize).
		Limit(params.PageSize).
		Scan(data.List)
	Config.Dao.Model(model.Tags{}).Count(&data.Total)
	dto.ReturnRes.Succ(c, data)
}

func (tags) Add(c *gin.Context) {
	var params dto.TagsAddReq
	if err := c.ShouldBind(&params); err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	var isExistName *model.Tags
	Config.Dao.Model(model.Tags{}).
		Where("name=?", params.Name).
		Scan(&isExistName)
	if isExistName != nil {
		dto.ReturnRes.Err(c, 10001, "find exist record")
		return
	}
	var newRecord = model.Tags{
		Name: params.Name,
	}
	Config.Dao.Model(model.Tags{}).Create(&newRecord)
	dto.ReturnRes.Succ(c, newRecord.ID)
	dto.ReturnRes.Succ(c, "")
}

func (tags) Update(c *gin.Context) {
	var params dto.TagsUpdateReq
	if err := c.ShouldBind(&params); err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	var isExistName *model.Tags
	Config.Dao.Model(model.Tags{}).
		Where("name=?", params.Name).
		Scan(&isExistName)
	if isExistName != nil {
		dto.ReturnRes.Err(c, 10001, "find exist record")
		return
	}
	Config.Dao.Model(model.Tags{}).
		Where("id=?", params.ID).
		Update("name", params.Name)
	dto.ReturnRes.Succ(c, "")
}

func (tags) Delete(c *gin.Context) {
	var params dto.IdReq
	if err := c.ShouldBind(&params); err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	Config.Dao.Model(model.Tags{}).
		Where("id=?", params.ID).
		Unscoped().
		Delete(model.Tags{})
	dto.ReturnRes.Succ(c, "")
}

func (tags) Info(c *gin.Context) {
	var params dto.IdReq
	if err := c.ShouldBind(&params); err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	var info *model.Tags
	Config.Dao.Model(model.Tags{}).Where("id=?", params.ID).Scan(&info)
	dto.ReturnRes.Succ(c, info)
}
