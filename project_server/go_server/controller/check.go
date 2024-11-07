package controller

import (
	"github.com/gin-gonic/gin"
	"go_server/dto"
)

type check struct {
}

var Check = &check{}

func (check) Info(c *gin.Context) {
	dto.ReturnRes.Succ(c, "v1")
}
