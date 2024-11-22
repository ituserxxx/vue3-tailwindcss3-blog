package router

import (
	"github.com/gin-gonic/gin"
	"go_server/controller"
)

func Init(r *gin.Engine) {
	r.GET("api/check", controller.Check.Info)

	blog := r.Group("blog")
	blog.POST("/articles/list", controller.Blog.ArticlesList)
	blog.POST("/articles/detail", controller.Blog.ArticlesDetail)
	blog.POST("/tags/list", controller.Blog.TagsList)
	blog.POST("/leaveMessages/list", controller.Blog.LeaveMessagesList)
	blog.POST("/leaveMessages/add", controller.Blog.LeaveMessagesAdd)
	blog.POST("/search", controller.Blog.Search)

	admin := r.Group("admin")

	users := admin.Group("users")
	users.POST("/login", controller.Users.Login)
	users.POST("/add", controller.Users.Add)
	users.POST("/update", controller.Users.Update)
	users.POST("/info", controller.Users.Info)
	users.POST("/delete", controller.Users.Delete)
	users.POST("/list", controller.Users.List)

	tags := admin.Group("tags")
	tags.POST("/add", controller.Tags.Add)
	tags.POST("/info", controller.Tags.Info)
	tags.POST("/delete", controller.Tags.Delete)
	tags.POST("/update", controller.Tags.Update)
	tags.POST("/list", controller.Tags.List)

	leaveMessages := admin.Group("leaveMessages")
	leaveMessages.POST("/delete", controller.LeaveMessages.Delete)
	leaveMessages.POST("/list", controller.LeaveMessages.List)

	articles := admin.Group("articles")
	articles.POST("/add", controller.Articles.Add)
	articles.POST("/info", controller.Articles.Info)
	articles.POST("/delete", controller.Articles.Delete)
	articles.POST("/update", controller.Articles.Update)
	articles.POST("/list", controller.Articles.List)
	articles.POST("/changeStatus", controller.Articles.ChangeStatus)
}
