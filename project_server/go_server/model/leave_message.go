package model

import "time"

// LeaveMessages represents the leave_messages table in the database
type LeaveMessages struct {
	ID         uint      `json:"id" gorm:"primaryKey;autoIncrement"`                               // 主键，自增
	Name       string    `json:"name" gorm:"type:varchar(50);not null;collate:utf8mb4_general_ci"` // 姓名，非空，字符集utf8mb4
	Content    string    `json:"content" gorm:"type:text;collate:utf8mb4_general_ci;not null"`     // 内容，非空
	CreateTime time.Time `json:"createTime" gorm:"type:datetime;not null"`                         // 创建时间，非空
	IP         *string   `json:"ip" gorm:"type:varchar(20);collate:utf8mb4_general_ci"`            // IP 地址，可为空
	IPAddr     *string   `json:"ipAddr" gorm:"type:varchar(20);collate:utf8mb4_general_ci"`        // IP 地址详细信息，可为空
}
