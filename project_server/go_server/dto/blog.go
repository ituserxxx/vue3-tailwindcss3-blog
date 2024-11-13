package dto

import (
	"go_server/model"
	"time"
)

type BlogArticleListReq struct {
	TagId int `json:"tagId"`
	PageReq
}
type BlogArticleListResp struct {
	List  []model.Articles `json:"list"`
	Total int64            `json:"total"`
}
type BlogTagsListResp struct {
	List []model.Tags `json:"list"`
}
type BlogArticlesDetailResp struct {
	ID          uint       `json:"id" gorm:"primaryKey;autoIncrement"`                                 // 主键，自增
	Title       string     `json:"title" gorm:"type:varchar(255);not null;collate:utf8mb4_general_ci"` // 文章标题，非空
	Content     string     `json:"content" gorm:"type:text;not null;collate:utf8mb4_general_ci"`       // 文章内容，非空
	CreateTime  time.Time  `json:"createTime" gorm:"type:datetime;not null"`                           // 创建时间，非空
	UpdateTime  *time.Time `json:"updateTime" gorm:"type:datetime"`                                    // 更新时间，允许为空
	Status      int        `json:"status" gorm:"type:tinyint;default:1"`                               // 文章状态，默认为1
	CommentsSum int        `json:"commentsSum" gorm:"type:int;default:0"`                              // 评论数量，默认为0
	ViewSum     int        `json:"viewSum" gorm:"type:int;default:0"`                                  // 浏览数量，默认为0
	TagsList    []int      `json:"tagsList"`
}
type BlogLeaveMessagesListResp struct {
	List []model.LeaveMessages `json:"list"`
}
