package req

type PageReq struct {
	Page int `form:"page" json:"page" default:"1"`
}

type IdReq struct {
	ID int `form:"id" json:"id"  binding:"required"`
}
