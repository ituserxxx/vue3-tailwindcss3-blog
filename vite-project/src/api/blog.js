import http from '../utils/http'
export function articleDetailApi (data) {
    return http.post('/article/detail',data)
}

export function ApiArticleList (data) {
    return http.post('/article/list',data)
}

export function ApiTagList (data) {
    return http.post('/tag/list',data)
}

export function ApiLeaveMessageList (data) {
    return http.post('/leaveMessage/list',data)
}

export function leaveMessageAddApi (data) {
    return http.post('/leaveMessage/add',data)
}