package req

type LoginReq struct {
	Username string `json:"username"  binding:"required"`
	Passwd   string `json:"passwd"  binding:"required"`
}
