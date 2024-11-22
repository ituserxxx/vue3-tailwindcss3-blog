
import request from './request.js'
const http = {
    post(url, params) {
        const config = {
            method: 'post',
            url: url,
            // headers: {
            //     'Content-Type': 'application/json', // 默认请求体类型为 JSON
            // },
        }
        if (params) config.data = params
        return request(config)
    },
}
export default http
