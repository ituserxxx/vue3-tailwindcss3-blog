package main

import (
	"github.com/joho/godotenv"
	"go_server/config"
	"go_server/model"
	"log"
	"testing"
)

func TestDBTest(t *testing.T) {
	// 加载配置文件
	err := godotenv.Load(".dev.env") // 加载.env文件
	if err != nil {
		log.Fatal("Error loading .env file", err)
	}
	Config.RunModuleServer()
	var uInfo *model.Users
	Config.Dao.Model(model.Users{}).
		Where("name=?", "xxx3").
		Where("passwd=?", "sfsdf").
		Scan(&uInfo)
	if uInfo == nil {
		print("is nil")
	} else if uInfo.ID == 0 {
		print("id ==0")
	}

}
