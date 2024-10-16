import Axios from 'axios'
// import { message } from 'ant-design-vue';
const request = Axios.create({
    headers: {
        'Content-Type': 'application/json'
    },
    timeout: 60000, // 超时
    // baseURL: `http://${window.location.hostname}:6008/admin` // 请求接口地址，这里使用本项目地址，因为我们是前后端分离，后面需要在vue.config.js里面配置代理，实际请求得地址不是这个。
    baseURL: `/api/admin` // 请求接口地址，这里使用本项目地址，因为我们是前后端分离，后面需要在vue.config.js里面配置代理，实际请求得地址不是这个。
})

// 请求拦截
request.interceptors.request.use(req => {
    // if (req.method === 'get') {
    //     const url = req.url
    //     const t = new Date().getTime()
    //     if (url.indexOf('?') >= 0) {
    //         req.url = `${url}&t=${t}`
    //     } else {
    //         req.url = `${url}?t=${t}`
    //     }
    // }
    return req
})

// 响应拦截
request.interceptors.response.use(
    response => {
        // console.log(response)
        const res = response
        // if (res.data.code !== 0) {
        //     message.error(res.data.msg);
        //     return
        // }
        return res.data
    },
    error => {
        // 响应失败统一处理
        const { response } = error
        if (response) {
            switch (response.status) {
                case 400:
                    window.$vm.$message.error('请求无效')
                    break
                case 401:
                    window.$vm.$message.error({ message: '尚未登录请重新登录' })
                    break
                case 403:
                    window.$vm.$message.error('您没有权限这样做，请联系管理员')
                    break
                case 404:
                    window.$vm.$message.error('请求未找到')
                    break
                case 500:
                    window.$vm.$message.error('系统异常')
                    break
                case 504:
                    window.$vm.$message.error('请求超时，请稍后再试')
                    break
                default:
                    window.$vm.$message.error('系统异常')
                    break
            }
        }
        return Promise.reject(error)
    }
)
export default request