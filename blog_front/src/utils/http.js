
import request from './request.js'
const http = {
    post(url, params) {
        const config = {
            method: 'post',
            url: url
        }
        if (params) config.data = params
        return request(config)
    },
}
export default http
