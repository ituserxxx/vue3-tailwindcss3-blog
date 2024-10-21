import http from '../utils/http'

export function ApiArticlesAdd(data) {
    return http.post('/articles/add', data)
}

export function ApiArticlesList(data) {
    return http.post('/articles/list', data)
}

export function ApiArticlesInfo(data) {
    return http.post('/articles/info', data)
}

export function ApiArticlesUpdate(data) {
    return http.post('/articles/update', data)
}
export function ApiArticlesChangeStatus(data) {
    return http.post('/articles/changeStatus', data)
}
export function ApiArticlesDelete(data) {
    return http.post('/articles/delete', data)
}