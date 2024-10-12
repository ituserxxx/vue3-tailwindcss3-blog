import http from '../utils/http'

export function ApiUserLogin(data) {
    return http.post('/users/login', data)
}
export function ApiUserAdd(data) {
    return http.post('/users/add', data)
}

export function ApiUserList(data) {
    return http.post('/users/list', data)
}

export function ApiUserInfo(data) {
    return http.post('/users/info', data)
}

export function ApiUserUpdate(data) {
    return http.post('/users/update', data)
}

export function ApiUserDelete(data) {
    return http.post('/users/delete', data)
}