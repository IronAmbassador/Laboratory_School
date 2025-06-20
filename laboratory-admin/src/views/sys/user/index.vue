<template>
  <div class="app-container">
    <!--条件搜索表单-->
    <el-row>
      <el-form :inline="true">
        <el-form-item>
          <el-input v-model="searchObj.name" placeholder="用户名" />
        </el-form-item>
        <el-form-item>
          <el-input v-model="searchObj.email" placeholder="邮箱" />
        </el-form-item>
        <el-form-item>
          <el-select v-model="searchObj.sex" placeholder="性别" clearable @change="getAll()">
            <el-option value="男" label="男" />
            <el-option value="女" label="女" />
            <el-option value="未知" label="未知" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-select v-model="searchObj.roleId" placeholder="身份" clearable @change="getAll()">
            <el-option value="1" label="超级管理员" />
            <el-option value="2" label="管理员" />
            <el-option value="3" label="管理员" />
          </el-select>
        </el-form-item>
        <el-form-item prop="status">
          <el-select v-model="searchObj.status" placeholder="用户状态" clearable @change="getAll()">
            <el-option :value="0" label="正常" />
            <el-option :value="1" label="停用" />
          </el-select>
        </el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="getAll()">查询</el-button>
        <el-button icon="el-icon-refresh" @click="reset()">清空</el-button>
      </el-form>
    </el-row>

    <!-- 工具栏 -->
    <el-row>
      <el-button type="primary" icon="el-icon-plus" @click="openEdit(null)">新增</el-button>
      <el-button type="danger" icon="el-icon-delete" :disabled="ids.length < 1" @click="batchDelete"
        >批量删除
      </el-button>
    </el-row>

    <el-row>
      <!-- banner列表 :data="list"表示遍历list数组 stripe条纹 -->
      <el-table
        :data="page.list"
        :border="false"
        v-loading="loading"
        element-loading-text="正在努力加载数据中..."
        stripe
        style="width: 100%; margin: 15px 0"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" fixed="left" width="55" />
        <el-table-column type="index" fixed="left" width="80" label="序号" />
        <el-table-column prop="username" fixed="left" label="用户名称" min-width="120" sortable />
        <el-table-column prop="nickname" label="用户昵称" show-overflow-tooltip />
        <el-table-column prop="userSex" label="用户性别" min-width="120" sortable show-overflow-tooltip>
          <template v-slot="scope">
            <el-tag v-if="scope.row.userSex === '男'" type="success" effect="plain">{{ scope.row.userSex }}</el-tag>
            <el-tag v-if="scope.row.userSex === '女'" type="danger" effect="plain">{{ scope.row.userSex }}</el-tag>
            <el-tag v-if="scope.row.userSex === '未知'" type="info" effect="plain">{{ scope.row.userSex }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="userEmail" label="用户邮箱" min-width="120" sortable show-overflow-tooltip />
        <el-table-column prop="roleId" label="用户身份" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-tag v-if="scope.row.roleId === 1" effect="plain">超级管理员</el-tag>
            <el-tag v-if="scope.row.roleId === 2" type="success" effect="plain">管理员</el-tag>
            <el-tag v-if="scope.row.roleId === 3" type="info" effect="plain">管理员</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="status" label="用户状态">
          <template v-slot="scope">
            <el-tag v-if="scope.row.status == 0" type="success" effect="plain">正常</el-tag>
            <el-tag v-if="scope.row.status == 1" type="danger" effect="plain">停用</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="remark" label="备注" show-overflow-tooltip />
        <el-table-column prop="createAt" label="创建人" show-overflow-tooltip />
        <el-table-column prop="createTime" label="创建时间" min-width="120" sortable show-overflow-tooltip />
        <el-table-column prop="updateAt" min-width="100" label="最后修改人" show-overflow-tooltip />
        <el-table-column prop="updateTime" min-width="140" label="最后修改时间" sortable show-overflow-tooltip />

        <!-- 操作 -->
        <el-table-column label="操作" fixed="right" width="250" align="center">
          <template slot-scope="scope">
            <template v-if="scope.row.id !== 1">
              <el-link type="success" icon="el-icon-edit" size="mini" @click="openEdit(scope.row.id)">编辑</el-link>
              <el-divider direction="vertical" />
              <el-popconfirm title="确定删除吗？" @confirm="remove(scope.row.id)">
                <template #reference>
                  <el-link type="danger" icon="el-icon-remove" size="mini">删除</el-link>
                </template>
              </el-popconfirm>
              <el-divider direction="vertical" />
              <el-link type="primary" icon="el-icon-refresh" size="mini" @click="resetPwd(scope.row)">重置密码</el-link>
            </template>
          </template>
        </el-table-column>
      </el-table>
    </el-row>

    <!-- 分页导航栏 -->
    <el-row>
      <el-pagination
        background
        :current-page="pIndex"
        :page-size="pSize"
        :total="page.total"
        :page-sizes="[3, 6, 9, 12, 15]"
        layout="total, sizes, ->, prev, pager, next, jumper"
        @current-change="getAll"
        @size-change="handleSizeChange"
      />
    </el-row>

    <!-- 表单 -->
    <el-dialog :title="dialogTitle" :visible.sync="visible" :before-close="close">
      <el-form ref="userForm" label-width="100px" :model="user" :rules="rules">
        <el-row :gutter="10">
          <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="8">
            <el-form-item label="ID" prop="id" :hidden="true">
              <el-input v-model="user.id" disabled />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="8">
            <el-form-item label="用户账号:" prop="username">
              <el-input v-model.trim="user.username" placeholder="请输入账号" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="8">
            <el-form-item label="用户昵称:" prop="nickname">
              <el-input v-model.trim="user.nickname" placeholder="请输入昵称" />
            </el-form-item>
          </el-col>
          <el-col v-if="!user.id" :xs="24" :sm="24" :md="12" :lg="12" :xl="8">
            <el-form-item label="用户密码:" prop="password">
              <el-input v-model.trim="user.password" type="password" placeholder="请输入密码" show-password />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="8">
            <el-form-item label="用户性别:" prop="userSex">
              <el-select v-model="user.userSex" style="width: 100%" placeholder="性别">
                <el-option value="男" label="男" />
                <el-option value="女" label="女" />
                <el-option value="未知" label="未知" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="8">
            <el-form-item label="用户邮箱:" prop="userEmail">
              <el-input v-model.trim="user.userEmail" placeholder="请输入邮箱" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="8">
            <el-form-item label="用户身份:" prop="roleId">
              <el-select v-model="user.roleId" placeholder="请选择身份" style="width: 100%">
                <el-option v-for="item in role" :key="item.id" :label="item.roleName" :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="8">
            <el-form-item label="用户状态:" prop="status">
              <el-select v-model="user.status" placeholder="请选择用户状态" style="width: 100%">
                <el-option :value="0" label="正常" />
                <el-option :value="1" label="停用" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="备注" prop="remark">
              <el-input type="textarea" :rows="3" placeholder="请输入备注" v-model="user.remark" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button @click="resetForm">重 置</el-button>
        <el-button type="primary" @click="save('userForm')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// 引入api接口定义的js文件
import userApi from '@/api/sys/user'
import { mapGetters } from 'vuex'

export default {
  computed: {
    ...mapGetters(['userId'])
  },
  data() {
    return {
      // 列表相关
      pIndex: 1,
      pSize: 6,
      searchObj: {},
      loading: true,
      page: [],
      ids: [],
      dialogTitle: '',

      // 表单相关
      visible: false,
      user: {
        id: undefined,
        username: '',
        nickname: '',
        password: '123456',
        userSex: '未知',
        userEmail: '',
        roleId: 1,
        status: 0,
        remark: ''
      },
      role: [
        { id: 1, roleName: '管理员' },
        { id: 2, roleName: '教师' },
        { id: 3, roleName: '学生' }
      ],
      rules: {
        username: [
          { required: true, message: '账号不能为空', trigger: 'blur' },
          { pattern: /^[a-zA-Z]+$/, message: '账号只能是大小写字母', trigger: 'blur' },
          { min: 5, max: 20, message: '账号长度在5-20个字符', trigger: 'blur' }
        ],
        nickname: [{ required: true, message: '昵称不能为空', trigger: 'blur' }],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { min: 6, max: 20, message: '密码长度在6到20个字符', trigger: 'blur' }
        ],
        userEmail: [
          { required: true, message: '邮箱不能为空', trigger: 'blur' },
          {
            type: 'email',
            message: '请输入有效的邮箱地址',
            trigger: ['blur', 'change']
          }
        ],
        roleId: [{ required: true, message: '请选择身份', trigger: 'change' }]
      }
    }
  },
  created() {
    // 勾子函数，在页面渲染之前执行，一般调用methods定义的方法得到数据
    this.getAll() // 调用methods中的函数
  },
  methods: {
    // 用户信息
    getAll(current = 1) {
      this.loading = true
      // 加载列表数据
      this.pIndex = current //添加当前页参数
      // axios异步获取远程数据（ajax），通过api
      userApi
        .getPage(this.pIndex, this.pSize, this.searchObj)
        .then((response) => {
          this.page = response.data
        })
        .finally(() => {
          this.loading = false
        })
    },

    // 改变页码
    handleSizeChange(pageSize) {
      this.pSize = pageSize
      // 重新调用数据加载方法 --> 方法中已经加入的查询参数
      this.getAll()
    },

    // 复选框选中
    handleSelectionChange(r) {
      // 得到选中的id
      this.ids = r.map((v) => v.id)
    },

    // 删除用户信息
    remove(id) {
      if (id === this.userId) {
        this.$message.warning('无法删除当前登录用户!')
        return
      }
      const loading = this.$loading({
        lock: true,
        text: '正在删除中...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      // 调用接口
      userApi
        .remove(id)
        .then((resp) => {
          // 提示
          this.$message.success('删除成功!')
          // 刷新页面
          this.getAll()
        })
        .finally(() => {
          loading.close()
        })
    },

    // 打开表单
    async openEdit(id) {
      this.dialogTitle = '用户新增'
      if (id != null) {
        this.dialogTitle = '用户编辑'
        this.getById(id)
      }
      this.user.password = '123456'
      this.visible = true
    },

    // 根据Id查询用户
    getById(id) {
      userApi.getById(id).then((res) => {
        this.user = res.data
      })
    },

    // 关闭表单
    close() {
      this.visible = false
      this.$refs['userForm'].resetFields()
    },

    // 批量删除
    batchDelete() {
      if (this.ids.length > 0) {
        if (this.ids.includes(this.userId)) {
          this.$message.warning('不能删除当前登录用户')
        } else {
          this.$confirm('此操作将永久删除用户信息, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            const loading = this.$loading({
              lock: true,
              text: '正在删除中...',
              spinner: 'el-icon-loading',
              background: 'rgba(0, 0, 0, 0.7)'
            })
            // 确定执行then方法
            // 调用接口
            userApi
              .batchRemove(this.ids)
              .then((resp) => {
                // 提示
                this.$message.success('删除成功!')
                // 刷新页面
                this.getAll()
              })
              .finally(() => {
                loading.close()
              })
          })
        }
      } else {
        this.$message.warning('请至少选择一条数据')
      }
    },

    // 保存
    save(form) {
      // 表单校验
      this.$refs[form].validate((valid) => {
        if (valid) {
          const apiCall = this.user.id ? userApi.update(this.user) : userApi.save(this.user)
          apiCall.then(() => {
            this.$message.success('操作成功')
            this.close()
            this.getAll()
          })
        }
      })
    },

    // 重置密码
    resetPwd(row) {
      this.$confirm(`此操作将重置 ${row.nickname} 的密码, 是否继续?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const loading = this.$loading({
          lock: true,
          text: '正在重置密码中...',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        })
        userApi
          .resetPwd(row.id)
          .then((res) => {
            this.$message.success('操作成功')
          })
          .finally(() => {
            loading.close()
          })
      })
    },

    // 重置表单
    resetForm() {
      if (this.user.id) {
        this.getById(this.user.id)
      }
      this.$refs['userForm'].resetFields()
    },

    // 清空
    reset() {
      this.searchObj = {}
      this.getAll()
    }
  }
}
</script>

<style></style>
