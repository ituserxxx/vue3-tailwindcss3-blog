package controller

import (
	"github.com/gin-gonic/gin"
	Config "go_server/config"
	"go_server/dto"
	"go_server/model"
	"time"
)

type blog struct {
}

var Blog = &blog{}

func (blog) ArticlesList(c *gin.Context) {
	var params = dto.BlogArticleListReq{}

	if err := c.ShouldBind(&params); err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	var data = &dto.BlogArticleListResp{}
	db := Config.Dao.Model(model.Articles{}).Where("status=1")

	if params.TagId > 0 {
		db = db.Joins("left join article_tag_rela as atr on atr.article_id=articles.id").
			Where("atr.tag_id =?", params.TagId)
	}
	db.Limit(params.PageSize).
		Select("articles.*").
		Offset((params.Page - 1) * params.PageSize).
		Order("id desc").
		Scan(&data.List)
	Config.Dao.Model(model.Articles{}).Count(&data.Total)

	dto.ReturnRes.Succ(c, data)
}
func (blog) ArticlesDetail(c *gin.Context) {
	var params dto.IdReq
	if err := c.ShouldBind(&params); err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	var data = &dto.BlogArticlesDetailResp{
		TagsList: make([]model.Tags, 0),
	}
	Config.Dao.Model(model.Articles{}).Where("id=?", params.ID).Scan(&data)
	Config.Dao.Model(model.Tags{}).
		Where("id in (?)", Config.Dao.Model(model.ArticleTagRela{}).Select("tag_id").Where("id=?", params.ID)).
		Scan(&data.TagsList)

	dto.ReturnRes.Succ(c, data)
}
func (blog) TagsList(c *gin.Context) {
	var data dto.BlogTagsListResp
	Config.Dao.Model(model.Tags{}).Scan(&data.List)
	dto.ReturnRes.Succ(c, data)
}
func (blog) LeaveMessagesList(c *gin.Context) {
	var params dto.PageReq
	if err := c.ShouldBind(&params); err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	var data = &dto.BlogLeaveMessagesListResp{
		List: make([]model.LeaveMessages, 0),
	}
	Config.Dao.Model(model.LeaveMessages{}).
		Offset((params.Page - 1) * params.PageSize).
		Order("id desc").
		Scan(&data.List)
	dto.ReturnRes.Succ(c, data)

}
func (blog) LeaveMessagesAdd(c *gin.Context) {
	var params dto.LeaveMessagesAddReq
	if err := c.ShouldBind(&params); err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	var newRecord = model.LeaveMessages{
		Name:       params.Name,
		Content:    params.Content,
		CreateTime: time.Now(),
		IP:         nil,
		IPAddr:     nil,
	}
	Config.Dao.Model(model.LeaveMessages{}).Create(&newRecord)
	dto.ReturnRes.Succ(c, newRecord.ID)
}
