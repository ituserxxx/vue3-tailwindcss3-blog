import http from '../utils/http'

export function ApiLeaveMessageAdd(data) {
    return http.post('/leaveMessage/add', data)
}

export function ApiLeaveMessageList(data) {
    return http.post('/leaveMessage/list', data)
}

export function ApiLeaveMessageDelete(data) {
    return http.post('/leaveMessage/delete', data)
}