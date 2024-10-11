页面调试可启动 node /project_server/mock.js 进行调试

## api doc

### Required reading
request address : http://loclhost:6008

request router profix: admin

request method is POST

example reqeust home api is : http://loclhost:6008/admin/article/list

response data 
```
{
    code: 0, // eq 0 is ok
    msg: true,
    data: data // api data
}
```
response err data 
```
{
    code: 0, 
    msg: "baocuo err is asdf",
    data: null // api data
}
```

下面的接口返回的数据都是 data 的值

#### 文章列表 admin/users/login

require
```
{
    username:"xxxx",        // required
    passwd:"adsfasdf",      // required
}
```
response