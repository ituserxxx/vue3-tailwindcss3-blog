package model

// ArticleTagRela represents the article_tag_rela table in the database
type ArticleTagRela struct {
	ID        uint `json:"id" gorm:"primaryKey;autoIncrement"` // 主键，自增
	TagID     uint `json:"tagId" gorm:"not null"`              // 标签ID，非空
	ArticleID uint `json:"articleId" gorm:"not null"`          // 文章ID，非空
}

// TableName overrides the default table name
func (ArticleTagRela) TableName() string {
	return "article_tag_rela" // 表名
}
