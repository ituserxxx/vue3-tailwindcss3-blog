package model

// Shoots represents the articles table in the database
type Shoots struct {
	ID         uint   `json:"id" gorm:"primaryKey;autoIncrement"`                              // 主键，自增
	Alt        string `json:"alt" gorm:"type:varchar(20);not null;collate:utf8mb4_general_ci"` // 标题
	ImgUrl     string `json:"imgUrl" gorm:"type:text;not null;collate:utf8mb4_general_ci"`     // ImgUrl，非空
	CreateTime string `json:"createTime" gorm:"type:datetime;not null"`                        // 创建时间，非空
}
