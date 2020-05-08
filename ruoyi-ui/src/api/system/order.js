import request from '@/utils/request'

// 查询预览表列表
export function listOrder(query) {
  return request({
    url: '/system/order/list',
    method: 'get',
    params: query
  })
}

// 查询预览表详细
export function getOrder(id) {
  return request({
    url: '/system/order/' + id,
    method: 'get'
  })
}

// 新增预览表
export function addOrder(data) {
  return request({
    url: '/system/order',
    method: 'post',
    data: data
  })
}

// 修改预览表
export function updateOrder(data) {
  return request({
    url: '/system/order',
    method: 'put',
    data: data
  })
}

// 删除预览表
export function delOrder(id) {
  return request({
    url: '/system/order/' + id,
    method: 'delete'
  })
}

// 导出预览表
export function exportOrder(query) {
  return request({
    url: '/system/order/export',
    method: 'get',
    params: query
  })
}