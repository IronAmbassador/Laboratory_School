<template>
  <div class="app-container">
    <el-form ref="projectForm" :model="project" :rules="rules" label-width="120px">
      <el-form-item label="项目类型:" prop="type">
        <el-input v-model.trim="project.type" placeholder="请输入项目类型" />
      </el-form-item>
      <el-form-item label="项目名称:" prop="name">
        <el-input v-model.trim="project.name" placeholder="请输入项目名称" />
      </el-form-item>
      <el-form-item label="项目启动时间:" prop="startTime">
        <div class="block">
          <el-date-picker
            v-model.trim="project.startTime"
            style="width: 100%"
            value-format="yyyy-MM-dd HH:mm:ss"
            type="datetime"
            placeholder="请选择项目启动时间"
            align="left"
          />
        </div>
      </el-form-item>
      <el-form-item label="项目结束时间:" prop="stopTime">
        <div class="block">
          <el-date-picker
            v-model.trim="project.stopTime"
            style="width: 100%"
            value-format="yyyy-MM-dd HH:mm:ss"
            type="datetime"
            placeholder="请选择项目结束时间"
            align="left"
          />
        </div>
      </el-form-item>
      <el-form-item label="项目介绍:" prop="descript">
        <el-input v-model="project.descript" type="textarea" placeholder="请输入项目介绍" />
      </el-form-item>
      <el-form-item label="项目成员:" prop="member">
        <el-input v-model="project.member" type="textarea" placeholder="请输入项目成员" />
      </el-form-item>
      <el-form-item>
        <el-button type="default" @click="$router.go(-1)">返回</el-button>
        <el-button type="info" @click="reset">重置</el-button>
        <el-button type="primary" @click="saveProject">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
// 引入api接口定义的js文件
import projectApi from '@/api/course/project'
export default {
  data() {
    return {
      // 定义表单绑定对象
      project: {
        type: '',
        name: '',
        startTime: '',
        stopTime: '',
        descript: '',
        member: ''
      },
      isEdit: false,
      rules: {
        type: [{ required: true, message: '项目类型不能为空', trigger: 'blur' }],
        name: [{ required: true, message: '项目名称不能为空', trigger: 'blur' }],
        startTime: [{ required: true, message: '请选择项目启动时间', trigger: 'change' }],
        stopTime: [{ required: true, message: '请选择项目结束时间', trigger: 'change' }],
        member: [{ required: true, message: '项目成员不能为空', trigger: 'blur' }]
      }
    }
  },
  created() {
    const id = this.$route.params.id
    this.isEdit = !!id

    if (this.isEdit) {
      this.getById(id)
    }
  },
  methods: {
    // 根据id查询
    getById(id) {
      projectApi.getById(id).then((res) => {
        this.project = res.data
      })
    },

    // 添加
    saveProject() {
      this.$refs['projectForm'].validate((valid) => {
        if (valid) {
          const apiCall = this.isEdit ? projectApi.update(this.project) : projectApi.save(this.project)

          apiCall.then((res) => {
            const message = this.isEdit ? '修改成功' : '添加成功'
            this.$message.success(message)
            this.$router.push({ path: '/project/index' })
          })
        }
      })
    },

    // 重置
    reset() {
      if (this.isEdit) {
        this.getById(this.$route.params.id)
      }

      this.$refs['projectForm'].resetFields()
    }
  }
}
</script>

<style scoped></style>
