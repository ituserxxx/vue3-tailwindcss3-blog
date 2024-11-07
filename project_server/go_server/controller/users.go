package controller

import (
	"github.com/gin-gonic/gin"
	Config "go_server/config"
	"go_server/dto/req"
	"go_server/dto/resp"
	"go_server/model"
)

type users struct {
}

var Users = users{}

func (users) Login(c *gin.Context) {
	var params req.LoginReq
	if err := c.ShouldBind(&params); err != nil {
		resp.ReturnRes.Err(c, 10001, err.Error())
		return
	}
	var uInfo *model.Users
	Config.Dao.Model(model.Users{}).
		Where("name=?", params.Username).
		Where("passwd=?", params.Passwd).
		Scan(&uInfo)
	if uInfo == nil {
		resp.ReturnRes.Err(c, 10001, "not find record")
		return
	}
	if uInfo.Status == 0 {
		resp.ReturnRes.Err(c, 10001, "状态异常")
		return
	}
	resp.ReturnRes.Succ(c, uInfo.ID)
}
