package controller

import (
	"github.com/gin-gonic/gin"
	Config "go_server/config"
	"go_server/dto"
	"go_server/model"
	"gorm.io/gorm"
	"time"
)

type articles struct {
}

var Articles = &articles{}

func (articles) Add(c *gin.Context) {
	var params dto.ArticleAddReq
	if err := c.ShouldBind(&params); err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	var id uint
	err := Config.Dao.Transaction(func(tx *gorm.DB) error {
		var recode = model.Articles{
			Title:      params.Title,
			Content:    params.Content,
			CreateTime: time.Now(),
			Status:     params.Status,
		}
		err := tx.Model(model.Articles{}).Create(&recode).Error
		if err != nil {
			return err
		}
		id = recode.ID
		if len(params.TagsId) > 0 {
			var tagL = make([]model.ArticleTagRela, 0)
			for _, tid := range params.TagsId {
				tagL = append(tagL, model.ArticleTagRela{
					TagID:     tid,
					ArticleID: id,
				})
			}
			// 新增文章标签关系记录
			err = tx.Model(model.ArticleTagRela{}).Create(&tagL).Error
			if err != nil {
				return err
			}
			// 更新标签文章数量
			err = tx.Model(model.Tags{}).
				Where("id in (?)", tagL).
				UpdateColumn("article_sum", gorm.Expr("ArticleSum + ?", 1)).
				Error
			if err != nil {
				return err
			}
		}
		// 返回 nil 提交事务
		return nil
	})
	if err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	dto.ReturnRes.Succ(c, id)
}
func (articles) Update(c *gin.Context) {
	var params dto.ArticleUpdateReq
	if err := c.ShouldBind(&params); err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	err := Config.Dao.Transaction(func(tx *gorm.DB) error {
		var recode = model.Articles{
			Title:      params.Title,
			Content:    params.Content,
			CreateTime: time.Now(),
			Status:     params.Status,
		}

		err := tx.Model(model.Articles{}).Updates(&recode).Error
		if err != nil {
			return err
		}
		var oldTagIdList = make([]int, 0)

		tx.Model(model.ArticleTagRela{}).
			Where("article_id=?", params.Id).
			Select("tag_id").
			Scan(&oldTagIdList)

		// // 先查旧的文章标签id，要 减掉article_sum
		if len(oldTagIdList) > 0 {
			err = tx.Model(model.ArticleTagRela{}).
				Where("article_id=?", params.Id).
				Unscoped().
				Delete(model.ArticleTagRela{}).Error
			if err != nil {
				return err
			}
			// 更新标签文章数量
			err = tx.Model(model.Tags{}).
				Where("id in (?)", oldTagIdList).
				Where("article_sum >= 1 ").
				UpdateColumn("article_sum", gorm.Expr("ArticleSum -1 ?", 1)).Error
			if err != nil {
				return err
			}
		}

		// 新增文章标签关系记录  && 更新标签文章数量
		if len(params.TagsId) > 0 {
			var tagL = make([]model.ArticleTagRela, 0)
			for _, tid := range params.TagsId {
				tagL = append(tagL, model.ArticleTagRela{
					TagID:     tid,
					ArticleID: params.Id,
				})
			}
			// 新增文章标签关系记录
			err = tx.Model(model.ArticleTagRela{}).Create(&tagL).Error
			if err != nil {
				return err
			}
			// 更新标签文章数量
			err = tx.Model(model.Tags{}).
				Where("id in (?)", tagL).
				Where("article_sum >= 1 ").
				UpdateColumn("article_sum", gorm.Expr("ArticleSum + ?", 1)).Error
			if err != nil {
				return err
			}
		}
		// 返回 nil 提交事务
		return nil
	})
	if err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	dto.ReturnRes.Succ(c, "")
}
func (articles) List(c *gin.Context) {
	var params dto.PageReq
	if err := c.ShouldBind(&params); err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	var data = dto.ArticlesListResp{
		List: make([]model.Articles, 0),
	}
	Config.Dao.Model(model.Articles{}).
		Offset((params.Page - 1) * params.PageSize).
		Limit(params.PageSize).
		Order("id desc").
		Scan(&data.List)
	Config.Dao.Model(model.Articles{}).Count(&data.Total)
	dto.ReturnRes.Succ(c, data)
}
func (articles) Delete(c *gin.Context) {
	var params dto.IdReq
	if err := c.ShouldBind(&params); err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	err := Config.Dao.Transaction(func(tx *gorm.DB) error {

		err := tx.Unscoped().Delete(model.Articles{}, params.ID).Error
		if err != nil {
			return err
		}
		err = tx.Unscoped().Where("article_id=?", params.ID).Delete(model.ArticleTagRela{}).Error
		if err != nil {
			return err
		}
		// 返回 nil 提交事务
		return nil
	})
	if err != nil {
		dto.ReturnRes.Err(c, 20001, err.Error())
		return
	}
	dto.ReturnRes.Succ(c, "")
}
func (articles) Info(c *gin.Context) {
	var params dto.IdReq
	if err := c.ShouldBind(&params); err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	var data = &dto.ArticlesInfoResp{
		TagsIdList: make([]int64, 0),
	}
	Config.Dao.Model(model.Articles{}).Where("id=?", params.ID).Scan(&data)
	Config.Dao.Model(model.ArticleTagRela{}).
		Where("article_id=?", params.ID).
		Select("tag_id").
		Scan(&data.TagsIdList)
	dto.ReturnRes.Succ(c, data)
}

func (articles) ChangeStatus(c *gin.Context) {
	var params dto.ChangeStatusReq
	if err := c.ShouldBind(&params); err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	err := Config.Dao.Model(model.Articles{}).Where("id=?", params.ID).Update("status", params.Status).Error
	if err != nil {
		dto.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	dto.ReturnRes.Succ(c, "")
}
