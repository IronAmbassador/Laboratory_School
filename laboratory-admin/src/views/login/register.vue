<template>
  <div class="login-container">
    <el-form ref="userForm" :model="userForm" :rules="userRules" class="login-form">
      <el-form-item>
        <div class="login-title">实验室辅助管理系统注册</div>
      </el-form-item>

      <el-form-item prop="username">
        <el-input v-model="userForm.username" placeholder="请输入账号" type="text" clearable />
      </el-form-item>

      <el-form-item prop="password">
        <el-input
          ref="password"
          v-model="userForm.password"
          :key="passwordType"
          :type="passwordType"
          placeholder="请输入密码"
        >
          <template #suffix>
            <span style="margin-right: 10px" @click="showPwd">
              <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
            </span>
          </template>
        </el-input>
      </el-form-item>

      <el-form-item prop="nickname">
        <el-input v-model="userForm.nickname" placeholder="请输入昵称" clearable />
      </el-form-item>

      <el-form-item prop="userEmail">
        <el-input v-model="userForm.userEmail" placeholder="请输入邮箱" />
      </el-form-item>

      <el-form-item prop="roleId">
        <el-select v-model="userForm.roleId" placeholder="请选择身份" style="width: 100%">
          <el-option v-for="(item, index) in roleOptions" :key="index" :value="item.id" :label="item.roleName" />
        </el-select>
      </el-form-item>

      <div style="margin-bottom: 10px">已有账号？<el-button type="text" @click="openLogin">点击登录</el-button></div>

      <el-form-item>
        <el-button style="width: 100%" :loading="loading" type="primary" @click="handleSumbit">注册 </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import userApi from '@/api/sys/user'

export default {
  name: 'Register',
  data() {
    return {
      showCode: false,

      // 定义表单绑定对象
      userForm: {
        username: undefined,
        nickname: undefined,
        password: '123456',
        userSex: '未知',
        userEmail: undefined,
        roleId: 3
      },
      roleOptions: [
        { id: 1, roleName: '管理员' },
        { id: 2, roleName: '教师' },
        { id: 3, roleName: '学生' }
      ],
      userRules: {
        username: [
          { required: true, message: '账号不能为空', trigger: 'blur' },
          { pattern: /^[a-zA-Z0-9]+$/, message: '账号只能是大小写字母数字组合', trigger: 'blur' },
          { min: 5, max: 20, message: '账号长度在5-20个字符', trigger: 'blur' }
        ],
        nickname: [{ required: true, message: '昵称不能为空', trigger: 'blur' }],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { pattern: /^[^\u4e00-\u9fa5]+$/, message: '密码不能包含中文字符', trigger: 'blur' },
          { min: 6, max: 20, message: '密码长度在6到20个字符', trigger: 'blur' }
        ],
        userSex: [{ required: true, message: '请选择性别', trigger: 'change' }],
        userEmail: [
          { required: true, message: '邮箱不能为空', trigger: 'blur' },
          { type: 'email', message: '邮箱格式错误', trigger: 'blur' }
        ],
        roleId: [{ required: true, message: '请选择用户身份', trigger: 'change' }]
      },
      loading: false,
      passwordType: 'password'
    }
  },
  methods: {
    // 显示密码
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },

    // 提交
    handleSumbit() {
      this.$refs.userForm.validate((valid) => {
        if (valid) {
          this.loading = true
          const loading = this.$loading({
            lock: true,
            text: '正在注册中...',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)'
          })
          userApi
            .save(this.userForm)
            .then(() => {
              // 提示
              this.$message.success('注册成功!')
              // 跳转列表页面，使用路由跳转方式实现
              this.$router.push({
                path: '/login'
              })
            })
            .finally(() => {
              this.loading = false
              loading.close()
            })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },

    // 打开登录
    openLogin() {
      this.$router.push('login')
    }
  }
}
</script>

<styel lang="scss">
$bg: #283443;

.login-container {
  min-height: 100vh;
  width: 100%;
  // background-color: $bg;
  overflow: hidden;
  background-image: url(../../assets/login-bg.jpeg);
  background-repeat: no-repeat;
  background-size: cover;
  display: flex;
  justify-content: center;
  align-items: center;

  .login-title {
    font-size: 26px;
    color: black;
    text-align: center;
    font-weight: bold;
  }

  .login-form {
    position: relative;
    width: 100%;
    max-width: 450px;
    padding: 40px 35px;
    margin: 0 auto;
    overflow: hidden;
    background-color: white;
    border-radius: 10px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  }
}
</styel>
