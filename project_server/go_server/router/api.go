package router

import (
	"github.com/gin-gonic/gin"
	"go_server/controller"
)

func Init(r *gin.Engine) {
	r.GET("api/check", controller.Check.Info)

	r.POST("blog/articles/list", controller.Blog.ArticlesList)
	r.POST("blog/articles/detail", controller.Blog.ArticlesDetail)
	r.POST("blog/tags/list", controller.Blog.TagsList)
	r.POST("blog/leaveMessages/list", controller.Blog.LeaveMessagesList)
	r.POST("blog/leaveMessages/add", controller.Blog.LeaveMessagesAdd)

	r.POST("admin/users/login", controller.Users.Login)
	r.POST("admin/users/add", controller.Users.Add)
	r.POST("admin/users/info", controller.Users.Info)
	r.POST("admin/users/delete", controller.Users.Delete)
	r.POST("admin/users/list", controller.Users.List)

	r.POST("admin/tags/add", controller.Tags.Add)
	r.POST("admin/tags/info", controller.Tags.Info)
	r.POST("admin/tags/delete", controller.Tags.Delete)
	r.POST("admin/tags/update", controller.Tags.Update)
	r.POST("admin/tags/list", controller.Tags.List)

	r.POST("admin/leaveMessages/info", controller.LeaveMessages.Add)
	r.POST("admin/leaveMessages/delete", controller.LeaveMessages.Delete)
	r.POST("admin/leaveMessages/list", controller.LeaveMessages.List)

	r.POST("admin/articles/add", controller.Articles.Add)
	r.POST("admin/articles/info", controller.Articles.Info)
	r.POST("admin/articles/delete", controller.Articles.Delete)
	r.POST("admin/articles/update", controller.Articles.Update)
	r.POST("admin/articles/list", controller.Articles.List)
	r.POST("admin/articles/changeStatus", controller.Articles.ChangeStatus)
}
