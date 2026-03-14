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
      // 原有：实验室管理
      {
        path: '',
        component: () => import('@/views/lab/sub/index'),
        meta: {
          title: '实验室管理',
          icon: 'el-icon-school'
        }
      },
       // 补充：实验室预约
      {
        path: 'booking',
        name: 'LabBooking',
        component: () => import('@/views/lab/booking/index'),
        meta: {
          title: '实验室预约管理',
          icon: 'el-icon-date'
        }
      },
      // 补充：实验室开放管理
      {
        path: 'open',
        name: 'LabOpen',
        component: () => import('@/views/lab/open/index'),
        meta: {
          title: '实验室开放管理',
          icon: 'el-icon-s-data'
        }
      },
      // 补充：实验室人员管理
      {
        path: 'person',
        name: 'LabPerson',
        component: () => import('@/views/lab/person/index'),
        meta: {
          title: '实验室人员管理',
          icon: 'el-icon-document-copy'
        }
      }
    ]
  },

   // 课程管理模块（管理员完整权限）
  {
    path: '/course',
    component: Layout,
    redirect: '/course/index',
    meta: {
      title: '课程管理',
      icon: 'el-icon-notebook-2'
    },
    children: [
      // 课程列表
      {
        path: 'index',
        name: 'CourseIndex',
        component: () => import('@/views/course/index'),
        meta: {
          title: '课程列表',
          icon: 'el-icon-menu'
        }
      },
      // 项目管理
      {
        path: 'project/index',
        name: 'ProjectIndex',
        component: () => import('@/views/course/project/index'),
        meta: {
          title: '项目管理',
          icon: 'el-icon-location-outline'
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
      },
      // 个人预约记录（教师：仅操作自身数据）
      {
        path: 'booking',
        name: 'LabBooking',
        component: () => import('@/views/lab/booking/index'),
        meta: {
          title: '个人预约记录',
          icon: 'el-icon-document-copy'
        }
      },
      // 补充：实验室开放查看
      {
        path: 'open',
        name: 'LabOpen',
        component: () => import('@/views/lab/open/index'),
        meta: {
          title: '实验室开放查看',
          icon: 'el-icon-s-data'
        }
      },
      //实验室人员管理
      {
        path: 'person',
        name: 'LabPerson',
        component: () => import('@/views/lab/person/index'),
        meta: {
          title: '实验室人员管理',
          icon: 'el-icon-document-copy'
        }
      }
    ]
  },

   // 课程管理模块（教师完整权限）
  {
    path: '/course',
    component: Layout,
    redirect: '/course/index',
    meta: {
      title: '课程管理',
      icon: 'el-icon-notebook-2'
    },
    children: [
      // 课程列表
      {
        path: 'index',
        name: 'CourseIndex',
        component: () => import('@/views/course/index'),
        meta: {
          title: '课程列表',
          icon: 'el-icon-menu'
        }
      },
      // 项目管理
      {
        path: 'project/index',
        name: 'ProjectIndex',
        component: () => import('@/views/course/project/index'),
        meta: {
          title: '项目管理',
          icon: 'el-icon-location-outline'
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
      },
      // 个人预约记录（学生：仅操作自身数据）
      {
        path: 'booking',
        name: 'LabBooking',
        component: () => import('@/views/lab/booking/index'),
        meta: {
          title: '个人预约记录',
          icon: 'el-icon-document-copy'
        }
      },
      // 补充：实验室开放查看
      {
        path: 'open',
        name: 'LabOpen',
        component: () => import('@/views/lab/open/index'),
        meta: {
          title: '实验室开放查看',
          icon: 'el-icon-s-data'
        }
      }
    ]
  },

 // 课程管理（学生仅查看）
  {
    path: '/course',
    component: Layout,
    redirect: '/course/index',
    meta: {
      title: '课程查看',
      icon: 'el-icon-notebook-2'
    },
    children: [
      {
        path: 'index',
        name: 'CourseIndex',
        component: () => import('@/views/course/index'),
        meta: {
          title: '课程列表',
          icon: 'el-icon-menu'
        }
      }
    ]
  },

  {
    path: '/device',
    component: Layout,
    redirect: '',
    meta: {
      title: '设备报修',
      icon: 'el-icon-s-check'
    },
    children: [
      {
        path: '',
        component: () => import('@/views/device/repair/edit'),
        meta: {
          title: '设备报修登记',
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
