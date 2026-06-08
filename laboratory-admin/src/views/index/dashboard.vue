<template>
  <div class="dashboard-container">
    <!-- 欢迎卡片 -->
    <el-row :gutter="10">
      <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
        <el-card class="box-card" style="height: 140px; padding-top: 10px" shadow="hover">
          <div style="float: left">
            <el-image style="width: 80px; height: 80px" :src="avatar" :preview-src-list="srcList" />
          </div>
          <div style="padding-left: 120px">
            <p style="font-weight: bold">
              {{ initTime }}尊敬的
              <span v-if="hasRoles(1)">超级管理员</span>
              <span v-if="hasRoles(2)">管理员</span>
              <span v-if="hasRoles(3)">管理员</span>
              : {{ name }}
            </p>
            <p>愿您的每一天都充满创新与发现！</p>
          </div>
        </el-card>
      </el-col>

      <!-- 实验室统计 -->
      <el-col :xs="24" :sm="24" :md="6" :lg="4" :xl="4">
        <el-card class="box-card statistics" shadow="hover">
          <div @click="$router.push('/lab')">
            <div class="left">
              <p class="count">{{ labSubTotal }}</p>
              <p>实验室总数</p>
            </div>
            <div class="right">
              <i class="el-icon-office-building"></i>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="6" :lg="4" :xl="4">
        <el-card class="box-card statistics" shadow="hover">
          <div @click="$router.push('/lab/lab-sub')">
            <div class="left">
              <p class="count">{{ labSubFreeTotal }}</p>
              <p>实验空闲数</p>
            </div>
            <div class="right">
              <i class="el-icon-office-building"></i>
            </div>
          </div>
        </el-card>
      </el-col>

      <!-- 设备统计（仅管理员和超级管理员可见） -->
      <el-col :xs="24" :sm="24" :md="6" :lg="4" :xl="4" v-if="hasRoles(1, 2)">
        <el-card class="box-card statistics" shadow="hover">
          <div @click="$router.push('/device')">
            <div class="left">
              <p class="count">{{ deviceTotal }}</p>
              <p>设备总数</p>
            </div>
            <div class="right">
              <i class="el-icon-monitor"></i>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="6" :lg="4" :xl="4" v-if="hasRoles(1, 2)">
        <el-card class="box-card statistics" shadow="hover">
          <div @click="$router.push('/device/repair')">
            <div class="left">
              <p class="count">{{ deviceRepairTotal }}</p>
              <p>设备维修数</p>
            </div>
            <div class="right">
              <i class="el-icon-document-delete"></i>
            </div>
          </div>
        </el-card>
      </el-col>

      <!-- 折线图展示：近一周实验室使用率 -->
      <el-col :xs="24" :sm="24" :md="24" :lg="16" :xl="16" v-if="hasRoles(1, 2)">
        <el-card class="box-card" shadow="hover">
          <div ref="lineChart" style="width: 100%; height: 380px"></div>
        </el-card>
      </el-col>

      <!-- 快捷导航模块（仅超级管理员可见） -->
      <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8" v-if="hasRoles(1)">
        <el-card header="快捷导航" class="box-card" shadow="hover">
          <div class="box-container">
            <el-card v-for="item in navList" :key="item.title" shadow="hover" class="icon-title-box">
              <div @click="navTo(item.path)">
                <i :class="item.icon" :style="item.style" />
                <h2 class="title">{{ item.title }}</h2>
              </div>
            </el-card>
          </div>
        </el-card>
      </el-col>

      <!-- 柱状图展示：设备报修率 -->
      <el-col :xs="24" :sm="24" :md="24" :lg="16" :xl="16" v-if="hasRoles(1, 2)">
        <el-card class="box-card" shadow="hover">
          <div ref="barChart" style="width: 100%; height: 380px"></div>
        </el-card>
      </el-col>

      <!-- 系统消息时间线（仅超级管理员可见） -->
      <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8" v-if="hasRoles(1)">
        <el-card header="系统消息" class="box-card" shadow="hover">
          <el-timeline :reverse="false">
            <el-timeline-item
              v-for="(item, index) in activities"
              :type="item.loginState == 1 ? 'success' : 'danger'"
              :key="index"
              :timestamp="item.loginTime"
            >
              {{ item.username }} - {{ item.loginResult }} - {{ item.loginIp }}
            </el-timeline-item>
          </el-timeline>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import * as echarts from 'echarts'
import deviceApi from '@/api/device'
import labSubApi from '@/api/lab/sub'
import { initTime } from '@/utils/index'
import logApi from '@/api/sys/login-log'

export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters(['name', 'avatar', 'roleId'])
  },
  data() {
    return {
      // 页面初始化时间
      initTime: initTime(),
      // 实验室统计数据
      labSubTotal: 388,
      labSubFreeTotal: 268,
      // 设备统计数据
      deviceTotal: 299,
      deviceRepairTotal: 168,

      // 头像预览图片列表
      srcList: [
        'https://img0.baidu.com/it/u=2123036823,827931345&fm=253&fmt=auto&app=120&f=JPEG?w=889&h=500',
        'https://img0.baidu.com/it/u=3429084431,3824519370&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500'
      ],
      // 快捷导航配置
      navList: [
        { icon: 'el-icon-setting', title: '系统管理', path: '/sys', style: { color: '#67C23A' } },
        { icon: 'el-icon-s-tools', title: '实验室管理', path: '/lab', style: { color: '#F56C6C' } },

        { icon: 'el-icon-s-check', title: '安全检查登记', path: '/device', style: { color: '#764755' } }
      ],
  
      // 系统登录日志
      activities: [],

      // 图表数据缓存
      labSubData: [],
      deviceRepairData: []
    }
  },
  mounted() {
    if (true) {
      // 初始化图表
      deviceApi.getRepairCount().then((res) => {
        this.initBarChart(res.data)
      })

      labSubApi.getWeekCount().then((res) => {
        this.initLineChart(res.data)
      })

      // 获取统计数据
      this.getStatistic()

      // 获取系统消息
      this.getSystemMsg()
    }

    // 监听窗口变化以调整图表尺寸
    window.addEventListener('resize', this.handleResize)
  },
  beforeDestroy() {
    // 组件销毁时释放 ECharts 实例
    if (this.lineChart) {
      this.lineChart.dispose()
      this.lineChart = null
    }
    if (this.barChart) {
      this.barChart.dispose()
      this.barChart = null
    }
  },
  methods: {

     hasRoles(...roles) {
    const userRole = this.roleId
    if (!userRole) return false
    return roles.includes(userRole)
  },
    // 获取最近登录记录
    getSystemMsg() {
      logApi.getPage(1, 6, {}).then((res) => {
        this.activities = res.data.list
      })
    },

    // 获取统计数据
    getStatistic() {
      labSubApi.getTotal().then((res) => {
        this.labSubTotal = res.data
      })

      labSubApi.getFreeTotal().then((res) => {
        this.labSubFreeTotal = res.data
      })

      deviceApi.getTotal().then((res) => {
        this.deviceTotal = res.data
      })

      deviceApi.getRepairTotal().then((res) => {
        this.deviceRepairTotal = res.data
      })
    },

    // 页面跳转
    navTo(path) {
      this.$router.push(path)
    },

    // 初始化折线图
    initLineChart(data) {
      this.lineChart = echarts.init(this.$refs.lineChart)
      const lineOptions = {
        title: {
          text: '近一周实验室使用率'
        },
        tooltip: {
          trigger: 'axis'
        },
        grid: {
          left: '1%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          show: true,
          feature: {
            dataZoom: {
              yAxisIndex: 'none'
            },
            dataView: { readOnly: false },
            magicType: { type: ['bar', 'line'] },
            restore: {},
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
        },
        yAxis: {},
        series: [
          {
            name: '使用量',
            type: 'line',
            data: data
          }
        ]
      }
      this.lineChart.setOption(lineOptions)
    },

    // 初始化柱状图
    initBarChart(data) {
      this.barChart = echarts.init(this.$refs.barChart)
      const barOptions = {
        title: {
          text: '设备报修率'
        },
        tooltip: {
          trigger: 'axis'
        },
        grid: {
          left: '1%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          show: true,
          feature: {
            dataZoom: {
              yAxisIndex: 'none'
            },
            dataView: { readOnly: false },
            magicType: { type: ['line', 'bar'] },
            restore: {},
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
        },
        yAxis: {},
        series: [
          {
            name: '报修数',
            type: 'bar',
            data: data
          }
        ]
      }
      this.barChart.setOption(barOptions)
    },

    // 调整图表大小
    handleResize() {
      if (this.lineChart) {
        this.lineChart.resize()
      }

      if (this.barChart) {
        this.barChart.resize()
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin-left: 20px;
    margin-top: 10px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}

.box-container {
  display: flex;
  flex-direction: row; /* 设置为横向排列 */
  padding: 0; /* 移除外边距 */
  margin: 0; /* 移除外边距 */
  cursor: pointer;
}

.icon-title-box {
  width: 120px;
  margin: 5px;
  height: 80px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border: 1px solid #cbced1; /* 边框 */
}

.icon-title-box i {
  padding-left: 10px;
  font-size: 24px; /* 图标大小 */
}

.title {
  margin-top: 5px; /* 图标和标题之间的间距 */
  font-size: 10px; /* 标题字体大小 */
}

.box-card {
  margin-top: 10px;
  cursor: pointer;

  .right {
    line-height: 90px;
    font-size: 36px; /* 图标大小 */
    color: #409eff; /* 图标颜色 */
    float: right;
  }

  .left {
    text-align: center;
    float: left;

    .count {
      font-size: 18px; /* 字体大小 */
      font-weight: bold; /* 字体加粗 */
      color: #333; /* 字体颜色 */
    }
  }
}

.statistics {
  height: 140px;
}
</style>
