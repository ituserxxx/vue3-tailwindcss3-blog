package controller

import (
	"github.com/gin-gonic/gin"
	"go_server/dto/resp"
)

type check struct {
}

var Check = &check{}

func (check) Info(c *gin.Context) {
	resp.ReturnRes.Succ(c, "v1")
}
