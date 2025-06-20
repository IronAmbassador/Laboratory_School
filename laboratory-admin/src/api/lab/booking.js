import ApiClient from '../base-api'

//对应后台接口请求
const labBookingApi = new ApiClient('/admin/lab-booking')

// 审核
labBookingApi.process = (data) => {
  return labBookingApi.put("process", data)
}
export default labBookingApi
