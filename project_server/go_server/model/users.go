package model

// Users represents the user table in the database
type Users struct {
	ID         uint   `json:"id" gorm:"primaryKey;autoIncrement"`            // 主键，自增
	Name       string `json:"name" gorm:"type:varchar(20);not null"`         // 用户名，非空
	Passwd     string `json:"passwd" gorm:"type:varchar(100);not null"`      // 密码，非空
	Status     int8   `json:"status" gorm:"type:tinyint;default:1;not null"` // 状态：1正常，0禁用
	CreateTime string `json:"createTime" gorm:"type:datetime;not null"`      // 创建时间，非空
}
