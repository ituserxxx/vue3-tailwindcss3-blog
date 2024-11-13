package model

// Tags represents the tags table in the database
type Tags struct {
	ID         uint   `json:"id" gorm:"primaryKey;autoIncrement"`                               // 主键，自增
	Name       string `json:"name" gorm:"type:varchar(50);not null;collate:utf8mb4_general_ci"` // 标签名称，非空，字符集utf8mb4
	ArticleSum int    `json:"articleSum" gorm:"type:int;default:0"`                             // 文章数量，默认为0
}
