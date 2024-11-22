package dto

type PageReq struct {
	Page     int `form:"page,default=1"`
	PageSize int `form:"pageSize,default=10"`
}

type IdReq struct {
	ID int `form:"id" json:"id"  binding:"required"`
}
