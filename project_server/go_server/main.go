package main

import (
	"fmt"
	"github.com/gin-gonic/gin"
	"github.com/joho/godotenv"
	Config "go_server/config"
	"go_server/router"
	"log"
	"os"
)

func main() {
	// 加载配置文件
	err := godotenv.Load(".env") // 加载.env文件
	if err != nil {
		log.Fatal("Error loading .env file", err)
	}
	// 加载组件
	Config.RunModuleServer()

	r := gin.Default()
	gin.SetMode(os.Getenv("GIN_MODE"))
	// 加载路由
	router.Init(r)

	err = r.Run(fmt.Sprintf(":%s", os.Getenv("ServerPort")))
	if err != nil {
		return
	}
}
