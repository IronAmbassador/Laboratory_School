<template>
  <div class="app-container">
    <el-form ref="deviceForm" :model="repair" :rules="rules" label-width="120px">
      <el-form-item label="所属设备" prop="deviceId">
        <el-select v-model="repair.deviceId" placeholder="请选择设备" style="width: 100%">
          <el-option v-for="item in deviceList" :key="item.id" :label="item.deviceName" :value="item.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="报修时间" prop="repairDate">
        <el-date-picker
          type="date"
          value-format="yyyy-MM-dd"
          v-model="repair.repairDate"
          placeholder="请选择报修时间"
          style="width: 100%"
        />
      </el-form-item>
      <el-form-item label="故障描述" prop="description">
        <el-input type="textarea" :rows="4" placeholder="请简单描述故障" v-model="repair.description" />
      </el-form-item>
      <el-form-item>
        <el-button type="info" @click="reset">重置</el-button>
        <el-button type="primary" @click="save">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
// 引入api接口定义的js文件
import repairApi from '@/api/device/repair'
import deviceApi from '@/api/device/index'

export default {
  data() {
    return {
      // 表单相关
      visible: false,
      repair: {
        id: undefined,
        deviceId: undefined,
        description: '',
        repairDate: undefined,
        technicianName: '',
        status: undefined
      },
      rules: {
        deviceId: [{ required: true, message: '请选择设备', trigger: 'change' }],
        repairDate: [{ required: true, message: '请选择报修时间', trigger: 'change' }],
        technicianName: [{ required: true, message: '请输入维修技术员名称', trigger: 'blur' }],
        status: [{ required: true, message: '请选择维修状态', trigger: 'change' }],
        description: [{ required: true, message: '请输入故障描述', trigger: 'blur' }]
      },
      deviceList: []
    }
  },
  created() {
    this.getDeviceList()
  },
  methods: {
    // 获取设备
    getDeviceList() {
      deviceApi.getList().then((res) => {
        this.deviceList = res.data
      })
    },

    // 添加
    save() {
      this.$refs['deviceForm'].validate((valid) => {
        if (valid) {
          repairApi.save(this.repair).then((res) => {
            this.$message.success(res.message)
            this.reset()
          })
        }
      })
    },

    // 重置
    reset() {
      this.$refs['deviceForm'].resetFields()
    }
  }
}
</script>

<style scoped></style>
