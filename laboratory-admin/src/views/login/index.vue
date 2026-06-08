<template>
  <div class="login-container">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form">
      <el-form-item>
        <div class="login-title">实验室辅助管理系统登录</div>
      </el-form-item>

      <el-form-item prop="name">
        <el-input v-model="loginForm.name" placeholder="账号" type="text" prefix-icon="el-icon-user" clearable />
      </el-form-item>

      <el-form-item prop="pwd">
        <el-input
          ref="password"
          v-model="loginForm.pwd"
          :key="passwordType"
          :type="passwordType"
          placeholder="密码"
          prefix-icon="el-icon-lock"
          clearable
        >
          <template #suffix>
            <span style="margin-right: 10px" @click="showPwd">
              <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
            </span>
          </template>
        </el-input>
      </el-form-item>

      <el-form-item prop="code">
        <div style="display: flex; line-height: 10px">
          <el-input
            ref="code"
            style="width: 75%"
            v-model="loginForm.code"
            placeholder="请输入验证码"
            prefix-icon="el-icon-check"
            clearable
            @keyup.enter.native="handleLogin"
          />
          <div class="login-code">
            <Code ref="captcha" />
          </div>
        </div>
      </el-form-item>

      <div style="margin-bottom: 10px">没有账号？<el-button type="text" @click="openRegister">点击注册</el-button></div>

      <el-form-item>
        <el-button style="width: 100%" :loading="loading" type="primary" @click="handleLogin">登录 </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import Code from '@/components/code/index'

export default {
  name: 'Login',
  components: {
    Code
  },
  data() {
    return {
      showCode: false,
      loginForm: {
        name: 'admin',
        pwd: '123456',
        code: ''
      },
      loginRules: {
        name: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 5, message: '用户名长度不能少于5位', trigger: 'blur' }
        ],
        pwd: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, message: '密码长度不能少于6位', trigger: 'blur' }
        ],
        code: [
          { required: true, message: '请输入验证码', trigger: 'blur' },
          { validator: this.validateCode, trigger: 'blur' }
        ]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined
    }
  },
  watch: {
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  methods: {
    // 校验验证码
    validateCode(rule, value, callback) {
      if (this.loginForm.code.toLowerCase() !== this.$refs.captcha.currentCaptcha.toLowerCase()) {
        // this.$refs.captcha.refreshCaptcha()
        callback(new Error('请填写正确验证码'))
      } else {
        callback()
      }
    },

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


    // 登录
    handleLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.loading = true
          const loading = this.$loading({
            lock: true,
            text: '正在登录中...',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)'
          })
          this.$store
            .dispatch('user/login', this.loginForm)
            .then(() => {
              this.$router.push('/')
              this.loading = false
              this.$message.success('登陆成功！')
            })
            .catch(() => {})
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

    // 注册
    openRegister() {
      this.$router.push('register')
    }
  }
}
</script>

<style lang="scss">
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

.login-code {
  margin-left: 10px;
  flex-shrink: 0;
}
</style>
