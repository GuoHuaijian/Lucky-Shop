import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/cfg/list',
    method: 'get',
    params
  })
}
export function getByPrefix(params) {
  return request({
    url: '/cfg/getByPrefix',
    method: 'get',
    params
  })
}

export function saveGroup(params){
  return request({
    url: '/cfg/saveGroup',
    method: 'post',
    params
  })
}

export function exportXls(params) {
  return request({
    url: '/cfg/export',
    method: 'get',
    params
  })
}


export function save(params) {
  return request({
    url: '/cfg',
    method: 'post',
    params
  })
}

export function remove(id) {
  return request({
    url: '/cfg',
    method: 'delete',
    params: {
      id: id
    }
  })
}
