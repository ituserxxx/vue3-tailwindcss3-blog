var http = require('http')
// const http = require('http');

// import http from 'http';
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
  if (url === '/blog/article/list') {
    data = articleList()
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
}).listen(6008)


function articleList() {
  return{
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
    total:10
}
}

