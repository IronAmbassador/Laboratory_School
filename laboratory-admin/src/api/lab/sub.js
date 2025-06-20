import ApiClient from '../base-api'

//对应后台接口请求
const labSubApi = new ApiClient('/admin/labSub')

// 获取实验室总数
labSubApi.getTotal = () => {
  return labSubApi.get('total')
}

// 获取实验室空闲数
labSubApi.getFreeTotal = () => {
  return labSubApi.get('freeTotal')
}

// 获取实验室使用率
labSubApi.getWeekCount = () => {
  return labSubApi.get('getUseCount')
}

export default labSubApi
