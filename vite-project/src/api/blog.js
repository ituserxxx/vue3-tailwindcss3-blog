import http from '../utils/http'
export function ApiUserAdd (data) {
    return http.post('/user/add',data)
}

export function ApiUserList (data) {
    return http.post('/user/list',data)
}
export function ApiUserInfo(data) {
    return http.post('/user/info',data)
}
export function ApiUserUpdate(data) {
    return http.post('/user/update',data)
}
export function ApiUserDelete(data) {
    return http.post('/user/delete',data)
}