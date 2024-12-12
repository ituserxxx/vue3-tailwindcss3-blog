import http from '../utils/http'
export function articleDetailApi (data) {
    return http.post('/articles/detail',data)
}

export function articleListApi (data) {
    return http.post('/articles/list',data)
}

export function tagListApi (data) {
    return http.post('/tags/list',data)
}

export function leaveMessageListApi (data) {
    return http.post('/leaveMessages/list',data)
}

export function leaveMessageAddApi (data) {
    return http.post('/leaveMessages/add',data)
}

export function searchApi (data) {
    return http.post('/search',data)
}

export function shootListApi (data) {
    return http.post('/shoots/list',data)
}