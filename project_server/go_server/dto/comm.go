package dto

type PageReq struct {
	Page     int `form:"page" json:"page" default:"1"`
	PageSize int `form:"page_size" json:"page_size" default:"10"`
}

type IdReq struct {
	ID int `form:"id" json:"id"  binding:"required"`
}
