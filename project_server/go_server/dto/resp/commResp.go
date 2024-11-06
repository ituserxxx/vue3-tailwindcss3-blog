package resp

import (
	"github.com/gin-gonic/gin"
	"net/http"
)

type CommResp struct {
	Data interface{} `json:"data"`
	Code int         `json:"code"`
	Msg  string      `json:"msg"`
}

var ReturnRes = &CommResp{}

func (*CommResp) Succ(c *gin.Context, data interface{}) {
	resp := CommResp{
		Code: 0,
		Msg:  "OK",
		Data: data,
	}
	c.Set("succ_response", resp)
	c.JSON(http.StatusOK, resp)
}

func (*CommResp) Err(c *gin.Context, ErrCode int, messge string) {
	resp := CommResp{
		Code: ErrCode,
		Msg:  messge,
		Data: "",
	}
	c.Set("err_response", resp)
	c.JSON(http.StatusOK, resp)
}
