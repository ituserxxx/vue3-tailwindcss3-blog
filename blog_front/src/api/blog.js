import http from '../utils/http'
export function articleDetailApi (data) {
    return http.post('/article/detail',data)
}

export function articleListApi (data) {
    return http.post('/article/list',data)
}

export function tagListApi (data) {
    return http.post('/tag/list',data)
}

export function leaveMessageListApi (data) {
    return http.post('/leaveMessage/list',data)
}

export function leaveMessageAddApi (data) {
    return http.post('/leaveMessage/add',data)
}