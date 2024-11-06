package model

// ArticleTagRela represents the article_tag_rela table in the database
type ArticleTagRela struct {
	ID        int `gorm:"primaryKey;autoIncrement"` // 主键，自增
	TagID     int `gorm:"not null"`                 // 标签ID，非空
	ArticleID int `gorm:"not null"`                 // 文章ID，非空
}

// TableName overrides the default table name
func (ArticleTagRela) TableName() string {
	return "article_tag_rela" // 表名
}
