import Layout from '@/layout'

// 管理员权限
const adminPer = [
  {
    path: '/sys',
    component: Layout,
    redirect: '/sys/user',
    meta: {
      title: '系统管理',
      icon: 'el-icon-setting'
    },
    children: [
      {
        path: 'user',
        name: 'user',
        component: () => import('@/views/sys/user/index'),
        meta: {
          title: '用户管理',
          icon: 'user'
        }
      },
      {
        path: 'log',
        component: () => import('@/views/sys/log/index'),
        meta: {
          title: '日志管理',
          icon: 'el-icon-info'
        }
      }
    ]
  },

  {
    path: '/lab',
    component: Layout,
    redirect: '/lab',
    meta: {
      title: '实验室管理',
      icon: 'el-icon-office-building'
    },
    children: [
      {
        path: '',
        component: () => import('@/views/lab/sub/index'),
        meta: {
          title: '实验室管理',
          icon: 'el-icon-school'
        }
      }
    ]
  },



  {
    path: '/device',
    component: Layout,
    redirect: '',
    meta: {
      title: '安全检查登记',
      icon: 'el-icon-s-check'
    },
    children: [
      {
        path: '',
        component: () => import('@/views/device/index.vue'),
        meta: {
          title: '日常检查',
          icon: 'el-icon-view'
        }
      },
      {
        path: 'repair',
        component: () => import('@/views/device/repair/index'),
        meta: {
          title: '异常处理',
          icon: 'el-icon-warning'
        }
      }
    ]
  }
]

// 教师权限
const teacherPer = [
  {
    path: '/lab',
    component: Layout,
    meta: {
      title: '实验室管理',
      icon: 'el-icon-office-building'
    },
    children: [
      {
        path: '',
        component: () => import('@/views/lab/sub/index'),
        meta: {
          title: '实验室查看',
          icon: 'el-icon-school'
        }
      }
    ]
  },



  {
    path: '/device',
    component: Layout,
    redirect: '',
    meta: {
      title: '安全检查登记',
      icon: 'el-icon-s-check'
    },
    children: [
      {
        path: '',
        component: () => import('@/views/device/index.vue'),
        meta: {
          title: '日常检查',
          icon: 'el-icon-view'
        }
      },
      {
        path: 'repair',
        component: () => import('@/views/device/repair/index'),
        meta: {
          title: '异常处理',
          icon: 'el-icon-warning'
        }
      }
    ]
  }
]

// 学生权限
const studentPer = [
  {
    path: '/lab',
    component: Layout,
    meta: {
      title: '实验室管理',
      icon: 'el-icon-office-building'
    },
    children: [
      {
        path: '',
        component: () => import('@/views/lab/sub/index'),
        meta: {
          title: '实验室查看',
          icon: 'el-icon-school'
        }
      }
    ]
  },



  {
    path: '/device',
    component: Layout,
    redirect: '',
    meta: {
      title: '安全检查登记',
      icon: 'el-icon-s-check'
    },
    children: [
      {
        path: '',
        component: () => import('@/views/device/repair/edit'),
        meta: {
          title: '安全检查',
          icon: 'el-icon-view'
        }
      }
    ]
  }
]

/**
 *根据角色ID获取用户权限
 * @param {Number} roleId 角色ID
 * @returns
 */
export function getPermission(roleId) {
  let menus = []

  if (roleId === 1) {
    menus = adminPer
  } else if (roleId === 2) {
    menus = teacherPer
  } else if (roleId === 3) {
    menus = studentPer
  }

  return menus
}
