var http = require('http')
// const http = require('http');

// import http from 'http';
var querystring = require('querystring')
http.createServer(function (request, response) {
  response.setHeader('Access-Control-Allow-Origin', '*')
  // 允许的header类型
  response.setHeader('Access-Control-Allow-Headers', 'content-type')
  // 跨域允许的请求方式
  response.setHeader('Access-Control-Allow-Methods', 'DELETE,PUT,POST,GET,OPTIONS')
  const url = request.url
  let data = {}

  // 2.注册data事件接收数据（每当收到一段表单提交的数据，该方法会执行一次）
  request.on('data', function (chunk) {
    data += chunk
  })

  request.on('end', function () {
    data = decodeURI(data)
    console.log(data)
    var dataObject = querystring.parse(data)
    console.log(dataObject)
  })

  console.log('请求url->' + request.url)
  
  /*------------- blog front -------------*/
  if (url === '/blog/article/list') {
    data = articleList()
  }
  if (url === '/blog/article/detail') {
    data = articleDetail()
  }
  if (url === '/blog/tag/list') {
    data = tagList()
  }
  if (url === '/blog/leaveMessage/list') {
    data = leaveMessageList()
  }
  if (url === '/blog/leaveMessage/add') {
    data = leaveMessageAdd()
  }
  /*------------- blog admin -----user--------*/
  if (url === '/admin/user/list') {
    data = adminUserList()
  }
  if (url === '/admin/user/add') {
    data = adminUserAdd()
  }
  if (url === '/admin/user/info') {
    data = adminUserInfo()
  }
  if (url === '/admin/user/update') {
    data = adminUserUpdate()
  }
  if (url === '/admin/user/delte') {
    data = adminUserDelete()
  }

/*------------- blog admin ----tags---------*/
if (url === '/admin/tags/list') {
  data = adminTagsList()
}
if (url === '/admin/tags/add') {
  data = adminTagsAdd()
}
if (url === '/admin/tags/info') {
  data = adminTagsInfo()
}
if (url === '/admin/tags/update') {
  data = adminTagsUpdate()
}
if (url === '/admin/tags/delte') {
  data = adminTagsDelete()
}

/*------------- blog admin ----LeaveMessage---------*/
if (url === '/admin/leaveMessage/list') {
  data = adminLeaveMessageList()
}
if (url === '/admin/leaveMessage/add') {
  data = adminLeaveMessageAdd()
}
if (url === '/admin/leaveMessage/delete') {
  data = adminLeaveMessageDelete()
}


  // 内容类型: text/plain
  response.writeHead(200,
    { 'Content-Type': 'application/json; charset=UTF-8' })
  // 发送响应数据
  response.end(JSON.stringify({
    code: 0,
    msg: true,
    data: data
  }))
}).listen(6008)
/*------------- blog tags -------------*/
function adminLeaveMessageList() {
  return {
    list: [
      {
        id: 1,
        name: 'vvvvue',
        content:"aaaaadfadf",
        dateStr: "2020-05-05 15:00:00",
      },
      {
        id: 2,
        name: 'jssssssssss',
        content:"aaaaadfadf",
        dateStr: "2020-05-05 15:00:00",
      },
    ],
    total: 10
  }
}
function adminLeaveMessageAdd() {
  return {
    id: 1,
  }
}

function adminLeaveMessageDelete() {
  return {

  }
}

/*------------- blog tags -------------*/
function adminTagsList() {
  return {
    list: [
      {
        id: 1,
        name: 'vvvvue',
        num: 1,
      },
      {
        id: 2,
        name: 'jssssssssss',
        num: 1,
      },
    ],
    total: 10
  }
}
function adminTagsAdd() {
  return {
    id: 1,
  }
}
function adminTagsInfo() {
  return {
    id: 1,
    name: 'vvvvue',
    num: 1,
  }
}
function adminTagsUpdate() {
  return {

  }
}
function adminTagsDelete() {
  return {

  }
}

/*------------- blog admin -------------*/
function adminUserList() {
  return {
    list: [
      {
        id: 1,
        name: 'xxx0',
        passwd: 'sssss',
        status: 1,
      },
      {
        id: 2,
        name: 'xxx1',
        passwd: 'sssss',
        status: 2,
      },
    ],
    total: 10
  }
}
function adminUserAdd() {
  return {
    id: 1,
  }
}
function adminUserInfo() {
  return {
    id: 1,
    name: 'xxx0',
    passwd: 'sssss',
    status: 2,
  }
}
function adminUserUpdate() {
  return {

  }
}
function adminUserDelete() {
  return {

  }
}

/* ---------------- blog front -------------*/
function leaveMessageAdd() {
  return {
    id: 998,
    name: "Michael Gough1111",
    content: `   Very straight-to-point article. Really worth time reading. Thank
            you! But tools are just the
            instruments for the UX designers. The knowledge of the design tools are as important as the
            creation of the design strategy.`,
    date: "2022-03-04 15:12:33",
  }
}
function leaveMessageList() {
  return {
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
}
function tagList() {
  return {
    list: [
      { name: 'Country Names', id: 1 },
      { name: 'Chemistry', id: 2 },
      { name: 'File Type', id: 3 },
      { name: 'Cryptocurrency', id: 4 },
      { name: 'Academic', id: 5 },
      { name: 'Softwares', id: 6 },
      { name: 'General', id: 7 },
      { name: 'Web Technology', id: 8 },
      { name: 'Business', id: 9 },
      { name: 'Technology', id: 10 },
      { name: 'Sports', id: 11 },
      { name: 'Law', id: 12 },
      { name: 'Internet Slangs', id: 13 },
      { name: 'Insurance', id: 14 },
      { name: 'Space Science', id: 15 },
      { name: 'Jobs', id: 16 },
      { name: 'Certifications', id: 17 },
      { name: 'Electronics', id: 18 },
    ]
  }
}
function articleDetail() {
  return {
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
}
function articleList() {
  return {
    articleList: [
      {
        id: 1,
        date: 'Jun 1, 2024',
        title: 'Build Your New Idea with Vue 3 Framework.',
        body: 'Lorem ipsum dolor sit, amet consectetur adipisicing elit. Tempora expedita dicta totam aspernatur doloremque. Excepturi iste iusto eos enim reprehenderit nisi, accusamus delectus nihil quis facere in modi ratione libero!',
      },
      {
        id: 2,
        date: 'Jul 15, 2024',
        title: 'Explore the Latest Features of Vue 3',
        body: 'Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
      },
      {
        id: 2,
        date: 'Jul 15, 2024',
        title: 'Explore the Latest Features of Vue 3',
        body: 'Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
      },
      {
        id: 2,
        date: 'Jul 15, 2024',
        title: 'Explore the Latest Features of Vue 3',
        body: 'Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
      }
    ],
    total: 10
  }
}

