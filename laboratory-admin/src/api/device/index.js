import ApiClient from '../base-api'

//对应后台接口请求
const deviceApi = new ApiClient('/admin/device')

// 获取设备总数
deviceApi.getTotal = () => {
  return deviceApi.get('total')
}

// 获取设备维修数
deviceApi.getRepairTotal = () => {
  return deviceApi.get('repairTotal')
}

// 获取设备维修率
deviceApi.getRepairCount = () => {
  return deviceApi.get('getRepairCount')
}

export default deviceApi
