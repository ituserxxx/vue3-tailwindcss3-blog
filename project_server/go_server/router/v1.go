package router

import (
	"github.com/gin-gonic/gin"
	"go_server/controller"
)

func Init(r *gin.Engine) {
	r.GET("api/check", controller.Check.Info)
}
