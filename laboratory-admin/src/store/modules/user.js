import { login, logout, getInfo } from '@/api/login'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'
import { getPermission } from './user-per'

const getDefaultState = () => {
  return {
    token: getToken(),
    userId: undefined,
    name: '',
    avatar: '',
    roleId: undefined,
    menus: '' // 菜单
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_USER_ID: (state, userId) => {
    state.userId = userId
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_ROLE_ID: (state, roleId) => {
    state.roleId = roleId
  },
  SET_MENUS: (state, menus) => {
    state.menus = menus
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { name, pwd } = userInfo
    return new Promise((resolve, reject) => {
      login({
        name: name.trim(),
        pwd: pwd
      })
        .then((response) => {
          const { data } = response
          commit('SET_TOKEN', data.token)
          setToken(data.token)
          resolve()
        })
        .catch((error) => {
          reject(error)
        })
    })
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token)
        .then((response) => {
          const { data } = response

          if (!data) {
            return reject('验证失败,请重新登录.')
          }

          const { id, nickname, userImg, roleId } = data

          // 添加用户菜单
          const menus = getPermission(data.roleId)

          commit('SET_USER_ID', id)
          commit('SET_NAME', nickname)
          commit('SET_AVATAR', userImg)
          commit('SET_ROLE_ID', roleId)
          commit('SET_MENUS', menus)
          resolve(data)
        })
        .catch((error) => {
          reject(error)
        })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token)
        .then(() => {
          removeToken() // must remove  token  first
          resetRouter()
          commit('RESET_STATE')
          resolve()
        })
        .catch((error) => {
          reject(error)
        })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise((resolve) => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  },

  // update avatar
  updateUserImg({ commit }, userImg) {
    return new Promise((resolve) => {
      commit('SET_AVATAR', userImg)
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
