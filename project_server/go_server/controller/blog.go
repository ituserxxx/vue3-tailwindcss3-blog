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
	var params dto.BlogArticleListReq

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
		CreateTime: time.Now().Format("2006-01-02 15:04:05"),
		IP:         "",
		IPAddr:     "",
	}
	Config.Dao.Model(model.LeaveMessages{}).Create(&newRecord)
	dto.ReturnRes.Succ(c, newRecord)
}
func (blog) Search(c *gin.Context) {
	var params dto.SearchReq
	if err := c.ShouldBind(&params); err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}

	dto.ReturnRes.Succ(c, "还在开发中啊~~~")
}
func (blog) ShootsList(c *gin.Context) {
	var params dto.PageReq
	if err := c.ShouldBind(&params); err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	var data = make([]model.Shoots, 0)
	data = append(data, model.Shoots{
		ID:         0,
		Alt:        "aa",
		ImgUrl:     "https://images.unsplash.com/photo-1432462770865-65b70566d673?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&amp;ixlib=rb-1.2.1&amp;auto=format&amp;fit=crop&amp;w=1950&amp;q=80",
		CreateTime: "",
	})
	data = append(data, model.Shoots{
		ID:         0,
		Alt:        "",
		ImgUrl:     "https://images.unsplash.com/photo-1552960562-daf630e9278b?ixlib=rb-4.0.3&amp;ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&amp;auto=format&amp;fit=crop&amp;w=687&amp;q=80",
		CreateTime: "",
	})
	data = append(data, model.Shoots{
		ID:         0,
		Alt:        "",
		ImgUrl:     "https://images.unsplash.com/photo-1493246507139-91e8fad9978e?ixlib=rb-4.0.3&amp;ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&amp;auto=format&amp;fit=crop&amp;w=2940&amp;q=80",
		CreateTime: "",
	})
	data = append(data, model.Shoots{
		ID:         0,
		Alt:        "",
		ImgUrl:     "https://images.unsplash.com/photo-1552960562-daf630e9278b?ixlib=rb-4.0.3&amp;ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&amp;auto=format&amp;fit=crop&amp;w=687&amp;q=80",
		CreateTime: "",
	})
	dto.ReturnRes.Succ(c, data)
}
