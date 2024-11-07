package main

import (
	"github.com/gin-gonic/gin"
	"github.com/joho/godotenv"
	Config "go_server/config"
	"go_server/router"
	"log"
	"os"
)

func main() {
	r := gin.Default()
	// 加载配置文件
	err := godotenv.Load(".dev.env") // 加载.env文件
	if err != nil {
		log.Fatal("Error loading .env file", err)
	}
	// 加载组件
	Config.RunModuleServer()
	println(os.Getenv("Mysql"))

	// 加载路由
	router.Init(r)

	err = r.Run(":6008")
	if err != nil {
		return
	}
}
