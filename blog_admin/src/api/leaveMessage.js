import http from '../utils/http'

export function ApiLeaveMessageAdd(data) {
    return http.post('/leaveMessages/add', data)
}

export function ApiLeaveMessageList(data) {
    return http.post('/leaveMessages/list', data)
}

export function ApiLeaveMessageDelete(data) {
    return http.post('/leaveMessages/delete', data)
}