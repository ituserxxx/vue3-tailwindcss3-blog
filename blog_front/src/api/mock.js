var http = require('http')
var querystring = require('querystring')
http.createServer(function(request, response) {
  response.setHeader('Access-Control-Allow-Origin', '*')
  // 允许的header类型
  response.setHeader('Access-Control-Allow-Headers', 'content-type')
  // 跨域允许的请求方式
  response.setHeader('Access-Control-Allow-Methods', 'DELETE,PUT,POST,GET,OPTIONS')
  const url = request.url
  let data = {}

  // 2.注册data事件接收数据（每当收到一段表单提交的数据，该方法会执行一次）
  request.on('data', function(chunk) {
    data += chunk
  })

  request.on('end', function() {
    data = decodeURI(data)
    console.log(data)
    var dataObject = querystring.parse(data)
    console.log(dataObject)
  })

  console.log('请求url->' + request.url)
  if (url === '/admin/user/login') {
    data = userLoginAPi()
  }
  if (url.indexOf('/admin/user/info') !== -1) {
    data = userInfoAPi()
  }
  if (url.indexOf('/admin/table/list') !== -1) {
    data = tableListApi()
  }
  if (url.indexOf('/admin/tag/list') !== -1) {
    data = tagListApi()
  }
  if (url.indexOf('/admin/article/create') !== -1) {
    data = articleCreateApi()
  }
  if (url.indexOf('/admin/tag/edit') !== -1) {
    data = editTagApi()
  }
  if (url.indexOf('/admin/article/edit') !== -1) {
    data = addTagApi()
  }
  if (url.indexOf('/admin/article/edit') !== -1) {
    data = delTagApi()
  }
  if (url.indexOf('/admin/article/detail') !== -1) {
    data = detailArticleApi()
  }
  if (url.indexOf('/visitor/visitorList') !== -1) {
    data = visitorListApi()
  }
  if (url.indexOf('/visitor/edit') !== -1) {
    data = editVisitor()
  }
  if (url.indexOf('/visitor/delete') !== -1) {
    data = editVisitor()
  }
  // 内容类型: text/plain
  response.writeHead(200,
    { 'Content-Type': 'application/json; charset=UTF-8' })
  // 发送响应数据
  response.end(JSON.stringify({
    code: 20000,
    msg: true,
    data: data
  }))
}).listen(1002)

function userLoginAPi() {
  return {
    user_id: 1
  }
}
function userInfoAPi() {
  return {
    name: '张三',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    introduction: 'I am a super administrator'
  }
}
// https://github1s.com/crank220/vue_iview_admin
function tableListApi() {
  return [
    {
      id: 1,
      name: 'redis',
      create_time: '2016-05-02',
      update_time: '2016-05-02'
    },
    {
      id: 2,
      name: 'mysql',
      address: '上海市普陀区金沙江路 1517 弄',
      create_time: '2016-05-02',
      update_time: '2016-05-02'
    }
  ]
}

function articleCreateApi() {
  return {
    id: 1
  }
}
function detailArticleApi() {
  return {
    id: 1,
    title: 'redis',
    create_time: '2020-09-09',
    update_time: '2020-09-09',
    tag: ['redis'],
    draft: '1',
    desc: '描述',
    markdown_content: '## asdfasdf'
  }
}
function delTagApi() {
  return {
    id: 1
  }
}
function editTagApi() {
  return {
    id: 1
  }
}
function addTagApi() {
  return {
    id: 1
  }
}
function tagListApi() {
  return [
    {
      name: 'mysql',
      id: 1
    },
    {
      name: 'redis',
      id: 2
    },
    {
      name: 'redis',
      id: 3
    },
    {
      name: 'php',
      id: 4
    }
  ]
}
function visitorListApi() {
  return [
    {
      name: '访客一',
      id: 1
    },
    {
      name: '访客二',
      id: 2
    },
    {
      name: '访客三',
      id: 3
    }
  ]
}
function editVisitor() {
  return {
    id: 1
  }
}