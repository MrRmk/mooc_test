import request from '@/utils/request'

// 查询知识点功能列表
export function listKnowledge(query) {
  return request({
    url: '/system/knowledge/list',
    method: 'get',
    params: query
  })
}

// 查询知识点功能详细
export function getKnowledge(id) {
  return request({
    url: '/system/knowledge/' + id,
    method: 'get'
  })
}

// 新增知识点功能
export function addKnowledge(data) {
  return request({
    url: '/system/knowledge',
    method: 'post',
    data: data
  })
}

// 修改知识点功能
export function updateKnowledge(data) {
  return request({
    url: '/system/knowledge',
    method: 'put',
    data: data
  })
}

// 删除知识点功能
export function delKnowledge(id) {
  return request({
    url: '/system/knowledge/' + id,
    method: 'delete'
  })
}

// 导出知识点功能
export function exportKnowledge(query) {
  return request({
    url: '/system/knowledge/export',
    method: 'get',
    params: query
  })
}