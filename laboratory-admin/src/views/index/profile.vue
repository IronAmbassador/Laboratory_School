<template>
  <div style="padding: 20px">
    <el-row :gutter="20">
      <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="6">
        <el-card shadow="hover" style="margin-top: 10px; text-align: center">
          <el-form>
            <el-form-item>
              <el-upload
                action="http://localhost:8070/file/upload"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload"
                :show-file-list="false"
              >
                <el-avatar shape="square" alt="点击上传头像" :size="150" :src="userForm.userImg" />
              </el-upload>
            </el-form-item>
            <el-form-item label="用户名称">
              {{ userForm.username }}
            </el-form-item>
            <el-form-item label="用户角色">
              {{ initRole(userForm.roleId) }}
            </el-form-item>
            <el-form-item label="创建时间">
              {{ userForm.createTime }}
            </el-form-item>
            <el-form-item label="介绍">
              {{ userForm.remark }}
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="14" :md="16" :lg="18" :xl="18">
        <el-card style="margin-top: 10px">
          <el-tabs value="first">
            <el-tab-pane label="修改信息" name="first">
              <el-form ref="userForm" :model="userForm" :rules="userRules" label-width="100px">
                <el-form-item label="用户昵称" prop="nickname">
                  <el-input v-model="userForm.nickname" placeholder="请输入昵称" />
                </el-form-item>
                <el-form-item label="用户邮箱" prop="userEmail">
                  <el-input v-model="userForm.userEmail" placeholder="请输入邮箱" />
                </el-form-item>
                <el-form-item label="用户性别" prop="userSex">
                  <el-select v-model="userForm.userSex" style="width: 100%" placeholder="请选择性别">
                    <el-option v-for="item in sexOptions" :key="item.value" :label="item.label" :value="item.value" />
                  </el-select>
                </el-form-item>
                <el-form-item label="备注" prop="remark">
                  <el-input type="textarea" :rows="3" placeholder="请输入备注" v-model="userForm.remark" />
                </el-form-item>
                <el-form-item>
                  <el-button @click="$router.go(-1)">返回</el-button>
                  <el-button @click="resetForm('userForm', 1)">重置</el-button>
                  <el-button type="primary" @click="submitForm('userForm')">保存</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>
            <el-tab-pane label="修改密码" name="second">
              <el-form ref="userPasswordForm" :model="userPwd" :rules="pwdRules" label-width="100px">
                <el-form-item label="旧密码" prop="oldPwd">
                  <el-input v-model="userPwd.oldPwd" show-password placeholder="请输入旧密码" />
                </el-form-item>
                <el-form-item label="新密码" prop="newPwd">
                  <el-input v-model="userPwd.newPwd" show-password placeholder="请输入新密码" />
                </el-form-item>
                <el-form-item label="确认密码" prop="confirmPassword">
                  <el-input v-model="userPwd.confirmPassword" show-password placeholder="请再次输入新密码" />
                </el-form-item>
                <el-form-item>
                  <el-button @click="$router.go(-1)">返回</el-button>
                  <el-button @click="resetForm('userPasswordForm', 2)">重置</el-button>
                  <el-button type="primary" @click="updatePwd('userPasswordForm')">保存</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import userApi from '@/api/sys/user'
import { getInfo } from '@/api/login'
import { getToken } from '@/utils/auth'
import store from '@/store'

export default {
  data() {
    return {
      sexOptions: [
        { value: '男', label: '男' },
        { value: '女', label: '女' },
        { value: '未知', label: '未知' }
      ],
      userForm: {
        id: undefined,
        nickname: '',
        userImg: '',
        userEmail: '',
        userSex: '',
        createTime: ''
      },
      userRules: {
        nickname: [{ required: true, message: '用户昵称不能为空', trigger: 'blur' }],
        userEmail: [
          { required: true, message: '邮箱不能为空', trigger: 'blur' },
          {
            type: 'email',
            message: '邮箱格式错误',
            trigger: ['blur', 'change']
          }
        ]
      },

      // 修改密码表单
      userPwd: {
        oldPwd: '',
        newPwd: '',
        confirmPassword: ''
      },
      pwdRules: {
        oldPwd: [{ required: true, message: '旧密码不能为空', trigger: 'blur' }],
        newPwd: [
          { required: true, message: '新密码不能为空', trigger: 'blur' },
          { min: 6, message: '密码不能少于6位', trigger: 'blur' },
          { validator: this.validateNewPwd, trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, message: '确认密码不能为空', trigger: 'blur' },
          { validator: this.validateConfirmPassword, trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.getUserInfo()
  },
  methods: {
    // 新密码
    validateNewPwd(rule, value, callback) {
      if (value == this.userPwd.oldPwd) {
        callback(new Error('新密码不能和旧密码相同'))
      } else {
        callback()
      }
    },
    // 确认密码
    validateConfirmPassword(rule, value, callback) {
      if (value !== this.userPwd.newPwd) {
        callback(new Error('两次输入的密码不一致'))
      } else {
        callback()
      }
    },

    // 修改密码
    updatePwd(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          this.loading = true
          const loading = this.$loading({
            lock: true,
            text: '正在修改密码中...',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)'
          })
          userApi
            .updatePwd(this.userPwd)
            .then(() => {
              this.$message.success('密码修改成功')
              setTimeout(() => {
                this.$confirm('您的密码已经修改请重新登录, 是否继续?', '提示', {
                  confirmButtonText: '确定',
                  cancelButtonText: '取消',
                  type: 'warning',
                  beforeClose: (action, instance, done) => {
                    if (action === 'confirm') {
                      instance.confirmButtonLoading = true
                      instance.confirmButtonText = '正在退出登录中...'
                      setTimeout(() => {
                        done()
                        setTimeout(() => {
                          instance.confirmButtonLoading = false
                        }, 200)
                      }, 2000)
                    } else {
                      done()
                    }
                  }
                })
                  .then(async () => {
                    await this.$store.dispatch('user/logout')
                    this.$router.push(`/login?redirect=${this.$route.fullPath}`)
                    this.$message.success('成功退出登录')
                  })
                  .catch(async () => {
                    await this.$store.dispatch('user/logout')
                    this.$router.push(`/login?redirect=${this.$route.fullPath}`)
                    this.$message.success('成功退出登录')
                  })
              }, 2000)
            })
            .finally(() => {
              this.loading = false
              loading.close()
            })
        }
      })
    },

    initRole(roleId) {
      if (roleId === 1) {
        return '管理员'
      } else if (roleId === 2) {
        return '教师'
      } else if (roleId === 3) {
        return '学生'
      } else {
        return '未知'
      }
    },
    // 获取用户信息
    getUserInfo() {
      getInfo(getToken()).then((res) => {
        this.userForm = res.data
      })
    },

    // 提交
    submitForm(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          const loading = this.$loading({
            lock: true,
            text: '正在修改中...',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)'
          })
          userApi
            .updateProfile(this.userForm)
            .then(() => {
              this.$message.success('修改成功')
            })
            .finally(() => {
              loading.close()
            })
        }
      })
    },

    // 重置
    resetForm(form, type) {
      if (type === 1) {
        this.getUserInfo()
      }
      this.$refs[form].resetFields()
    },

    // 头像上传成功
    handleAvatarSuccess(res) {
      if (res.code === 200) {
        this.$message.success('头像上传成功')
        this.userForm.userImg = res.data
        this.$store.dispatch('user/updateUserImg', res.data)
      }
    },

    // 头像上传前
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg' || file.type === 'image/jpg' || file.type === 'image/png'
      const isLt2M = file.size / 1024 / 1024 < 3

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG|PNG|JPEG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 3MB!')
      }

      return isJPG && isLt2M
    }
  }
}
</script>
