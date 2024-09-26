import http from '../utils/http'

export function ApiTagsAdd(data) {
    return http.post('/tags/add', data)
}

export function ApiTagsList(data) {
    return http.post('/tags/list', data)
}

export function ApiTagsInfo(data) {
    return http.post('/tags/info', data)
}

export function ApiTagsUpdate(data) {
    return http.post('/tags/update', data)
}

export function ApiTagsDelete(data) {
    return http.post('/tags/delete', data)
}