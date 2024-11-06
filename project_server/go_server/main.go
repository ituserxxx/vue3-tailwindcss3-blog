package main

import (
	"github.com/gin-gonic/gin"
	"github.com/joho/godotenv"
	"go_server/db"
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
	// 加载数据库
	db.Init()
	println(os.Getenv("Mysql"))

	// 加载路由
	router.Init(r)

	err = r.Run(":6008")
	if err != nil {
		return
	}
}
