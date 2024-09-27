
# blog front doc

页面调试可启动 node /project_server/mock.js 进行调试

## api doc

### Required reading
request address : http://loclhost:6008

request router profix: blog

request method is POST

example reqeust home api is : http://loclhost:6008/blog/article/list

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

#### 文章列表 blog/article/list

require
```
{
    page:1,// option
    tagId:1,// option
}
```
response
```
{
    articleList: [
      {
        id: 1,
        date: 'Jun 1, 2024',
        title: 'Build Your New Idea with Vue 3 Framework.',
        body: 'Lorem ipsum dolor sit, amet consectetur adipisicing elit. Tempora expedita dicta totam aspernatur doloremque. Excepturi iste iusto eos enim reprehenderit nisi, accusamus delectus nihil quis facere in modi ratione libero!',
      },
      {},
      {}
    ],
    total: 10
  }
```

#### 文章相亲 blog/article/detail

require
```
{
    id:1 // required
}
```
response
```
{
    id: 1,
    date: "2022-05-06 23:59:59",
    view: 312,
    title: "Portrait Photography In Early Days",
    content: "编辑文件：vim /etc/docker/daemon.json \n\n输入以下内容\n```go\n\n{\n  \"registry-mirrors\": [\n    \"https://ustc-edu-cn.mirror.aliyuncs.com\",\n    \"https://mirror.baidubce.com\",\n     \"https://docker.996.ninja\",\n    \"https://quay.996.ninja\",\n    \"https://k8s.996.ninja\",\n    \"https://k8s-gcr.996.ninja\",\n    \"https://ghcr.996.ninja\",\n    \"https://cloudsmith.996.ninja\"\n  ]\n}\n\n```\n然后重启docker\n```\nsystemctl restart docker\n```",
    tags: [
      {
        id: 1,
        name: "Joe"
      }, {
        id: 2,
        name: "Election2020"
      },
    ]
  }
```
#### 标签列表 blog/tag/list

require
```
{}
```
response
```
{
    list: [
      { name: 'Country Names', id: 1 },
      { name: 'Chemistry', id: 2 },
      { name: 'File Type', id: 3 },
    ]
  }
```

#### 留言列表 blog/leaveMessage/list

require
```
{
    page:1,// option
}
```
response
```
{
    list: [
      {
        id: 1,
        name: "Michael Gough",
        content: `   Very straight-to-point article. Really worth time reading. Thank
            you! But tools are just the
            instruments for the UX designers. The knowledge of the design tools are as important as the
            creation of the design strategy.`,
        date: "2022-03-04 15:12:33",
      },
      {
        id: 2,
        name: "Michael Gough2",
        content: `   Very straight-to-point article. Really worth time reading. Thank you!  `,
        date: "2024-03-04 15:11:33",
      }
    ]
  }
```

#### 新增留言 blog/leaveMessage/add

require
```
{
    name: "Michael Gough1111", // option 
    content: `   Very straight-to-point artic`, //required
}
```
response
```
{
    id: 998,
    name: "Michael Gough1111",
    content: `   Very straight-to-point article. Really worth time reading. Thank
            you! But tools are just the
            instruments for the UX designers. The knowledge of the design tools are as important as the
            creation of the design strategy.`,
    date: "2022-03-04 15:12:33",
  }
```