import store from '@/store'

/**
 * 判断按钮是否有权限
 *
 * @param  {...any} role 角色
 */
export function hasRoles(...roles) {
  const role = store.getters.roleId
  return roles.includes(role)
}
