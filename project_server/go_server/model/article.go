package model

import "time"

// Articles represents the articles table in the database
type Articles struct {
	ID          uint       `gorm:"primaryKey;autoIncrement"`                              // 主键，自增
	Title       string     `gorm:"type:varchar(255);not null;collate:utf8mb4_general_ci"` // 文章标题，非空
	Content     string     `gorm:"type:text;not null;collate:utf8mb4_general_ci"`         // 文章内容，非空
	CreateTime  time.Time  `gorm:"type:datetime;not null"`                                // 创建时间，非空
	UpdateTime  *time.Time `gorm:"type:datetime"`                                         // 更新时间，允许为空
	Status      int        `gorm:"type:tinyint;default:1"`                                // 文章状态，默认为1
	CommentsSum int        `gorm:"type:int;default:0"`                                    // 评论数量，默认为0
	ViewSum     int        `gorm:"type:int;default:0"`                                    // 浏览数量，默认为0
}
