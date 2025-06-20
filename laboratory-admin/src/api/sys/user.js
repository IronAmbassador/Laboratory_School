import ApiClient from '../base-api'

//对应后台接口请求
const userApi = new ApiClient('/admin/user')

// 修改个人信息
userApi.updateProfile = (form) => {
  return userApi.put('profile', form)
}

// 修改密码
userApi.updatePwd = (form) => {
  return userApi.put('updPwd', form)
}

// 重置密码
userApi.resetPwd = (id) => {
  return userApi.get(`resetPwd/${id}`)
}

export default userApi
