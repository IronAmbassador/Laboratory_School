<script>
import LoginLog from './loginLog.vue'
import OperateLog from './operateLog.vue'

export default {
  components: {
    LoginLog,
    OperateLog
  },

  data() {
    return {
      activeName: 'loginLog'
    }
  }
}
</script>
<template>
  <div class="app-container">
    <el-tabs v-model="activeName">
      <el-tab-pane label="登录日志" name="loginLog">
        <LoginLog :is-load="activeName === 'loginLog'" />
      </el-tab-pane>
      <el-tab-pane label="操作日志" name="operationLog">
        <OperateLog :is-load="activeName === 'operationLog'" />
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
