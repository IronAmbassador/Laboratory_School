<template>
  <div class="app-container">
    <!--条件搜索表单-->
    <el-row>
      <el-form :inline="true">
        <el-form-item>
          <el-select v-model="searchObj.labSubId" placeholder="实验室分室" @change="getAll()">
            <el-option v-for="item in labSub" :key="item.id" :label="item.labName" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-input v-model="searchObj.name" placeholder="姓名" />
        </el-form-item>
        <el-form-item>
          <el-select v-model="searchObj.sex" placeholder="性别" @change="getAll()">
            <el-option value="男" label="男"></el-option>
            <el-option value="女" label="女"></el-option>
            <el-option value="未知" label="未知"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-input v-model="searchObj.address" placeholder="地址" />
        </el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="getAll()">查询</el-button>
        <el-button type="default" icon="el-icon-refresh" @click="reset()">清空</el-button>
      </el-form>
    </el-row>

    <!-- 工具栏 -->
    <el-row>
      <el-button v-if="hasRoles(1, 2)" type="primary" icon="el-icon-plus" @click="openEdit(null)">新增</el-button>
      <el-button
        v-if="hasRoles(1, 2)"
        type="danger"
        :disabled="ids.length < 1"
        icon="el-icon-delete"
        @click="batchDelete"
        >批量删除</el-button
      >
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
        <el-table-column prop="labName" fixed="left" label="实验室分室" min-width="100" show-overflow-tooltip />
        <el-table-column prop="name" fixed="left" label="姓名" show-overflow-tooltip />
        <el-table-column prop="sex" label="性别" show-overflow-tooltip>
          <template v-slot="scope">
            <el-tag type="success" v-if="scope.row.sex == '男'" effect="plain">男</el-tag>
            <el-tag type="danger" v-if="scope.row.sex == '女'" effect="plain">女</el-tag>
            <el-tag type="info" v-if="scope.row.sex == '未知'" effect="plain">未知</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="age" label="年龄" show-overflow-tooltip sortable />
        <el-table-column prop="address" label="地址" show-overflow-tooltip />
        <el-table-column prop="createAt" label="创建人" show-overflow-tooltip />
        <el-table-column prop="createTime" label="创建时间" min-width="120" show-overflow-tooltip sortable />
        <el-table-column prop="updateAt" min-width="100" label="最后修改人" show-overflow-tooltip />
        <el-table-column prop="updateTime" min-width="140" label="最后修改时间" show-overflow-tooltip sortable />

        <!-- 操作 -->
        <el-table-column label="操作" fixed="right" min-width="150" align="center">
          <template slot-scope="scope">
            <el-link type="success" icon="el-icon-edit" size="mini" @click="openEdit(scope.row.id)">编辑</el-link>
            <el-divider direction="vertical" />
            <el-popconfirm title="确定删除吗？" @confirm="remove(scope.row.id)">
              <template #reference>
                <el-link type="danger" icon="el-icon-remove" size="mini">删除</el-link>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </el-row>

    <!-- 分页导航栏 -->
    <el-row>
      <el-pagination
        @current-change="getAll"
        @size-change="handleSizeChange"
        :current-page="pIndex"
        :page-size="pSize"
        :page-sizes="[3, 6, 9, 12, 15]"
        background
        layout="total, sizes, ->, prev, pager, next, jumper"
        :total="page.total"
      >
      </el-pagination>
    </el-row>

    <!-- 表单 -->
    <el-dialog v-if="hasRoles(1, 2)" :title="dialogTitle" :visible.sync="showEdit" :before-close="close">
      <el-form ref="personForm" :model="person" :rules="rules" label-width="120px">
        <el-row :gutter="10">
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="ID" prop="id" :hidden="true">
              <el-input v-model="person.id" disabled />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="所属实验分室:" prop="labId">
              <el-select v-model="person.labId" style="width: 100%" placeholder="请选择实验分室">
                <el-option v-for="item in labSub" :key="item.id" :label="item.labName" :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="姓名:" prop="name">
              <el-input v-model.trim="person.name" placeholder="请输入姓名" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="性别:">
              <el-select v-model="person.sex" style="width: 100%" placeholder="请选择性别">
                <el-option value="男" label="男"></el-option>
                <el-option value="女" label="女"></el-option>
                <el-option value="未知" label="未知"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="年龄:" prop="age">
              <el-input-number v-model="person.age" placeholder="年龄" :min="16" :max="99" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="地址:" prop="address">
              <el-input v-model.trim="person.address" placeholder="请输入地址" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close">关 闭</el-button>
        <el-button @click="resetForm">重 置</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
//引入api接口定义的js文件
import labSubApi from '@/api/lab/sub'
import personApi from '@/api/lab/person'

export default {
  data() {
    // 定义变量和初始值
    return {
      pIndex: 1, //当前页
      pSize: 6, //每页显示记录数
      searchObj: {}, //条件封装对象
      page: [], // 列表
      loading: false,
      ids: [],
      showEdit: false,
      dialogTitle: '',

      // 定义表单绑定对象
      person: {
        id: undefined,
        name: '',
        sex: '男',
        age: '',
        address: '',
        labId: ''
      },
      labSub: [],
      isEdit: false,
      rules: {
        name: [{ required: true, message: '姓名不能为空', trigger: 'blur' }],
        age: [{ required: true, message: '年龄不能为空', trigger: 'blur' }],
        address: [{ required: true, message: '地址不能为空', trigger: 'blur' }],
        labId: [
          {
            required: true,
            message: '请选择实验室',
            trigger: ['blur', 'change']
          }
        ]
      }
    }
  },
  created() {
    //勾子函数，在页面渲染之前执行，一般调用methods定义的方法得到数据
    this.getAll() //调用methods中的函数
    this.getSub()
  },
  methods: {
    // 查询所有分室信息
    getSub() {
      labSubApi.getList().then((response) => {
        this.labSub = response.data
      })
    },

    //实验室人员信息
    getAll(current = 1) {
      this.loading = true
      // 加载列表数据
      this.pIndex = current //添加当前页参数
      // axios异步获取远程数据（ajax），通过api
      personApi
        .getPage(this.pIndex, this.pSize, this.searchObj)
        .then((response) => {
          //请求成功response是接口返回数据
          this.page = response.data
        })
        .finally(() => {
          this.loading = false
        })
    },

    // 打开表单
    openEdit(id) {
      this.dialogTitle = '新增实验室人员'
      this.showEdit = true
      if (id != null) {
        this.dialogTitle = '编辑实验室人员'
        this.getById(id)
      }
    },

    // 关闭表单
    close() {
      this.showEdit = false
      this.$refs['personForm'].resetFields()
    },

    // 根据id获取
    getById(id) {
      personApi.getById(id).then((res) => {
        this.person = res.data
      })
    },

    // 添加
    save() {
      this.$refs['personForm'].validate((valid) => {
        if (valid) {
          const apiCall = this.person.id ? personApi.update(this.person) : personApi.save(this.person)

          apiCall.then((res) => {
            this.$message.success(res.message)
            this.close()
            this.getAll()
          })
        }
      })
    },

    // 重置
    resetForm() {
      if (this.person.id) {
        this.getById(this.person.id)
      }

      this.$refs['personForm'].resetFields()
    },

    // 改变页码
    handleSizeChange(pageSize) {
      this.pSize = pageSize
      //重新调用数据加载方法 --> 方法中已经加入的查询参数
      this.getAll()
    },

    // 复选框选中
    handleSelectionChange(r) {
      // 得到选中的id
      this.ids = r.map((v) => v.id)
    },

    //删除实验室人员信息
    remove(id) {
      const loading = this.$loading({
        lock: true,
        text: '正在删除中...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      personApi
        .remove(id)
        .then((resp) => {
          //提示
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          //刷新页面
          this.getAll()
        })
        .finally(() => {
          loading.close()
        })
    },

    // 批量删除
    batchDelete() {
      if (this.ids.length > 0) {
        this.$confirm('此操作将永久删除人员信息, 是否继续?', '提示', {
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
          // 调用接口
          personApi
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
      } else {
        this.$message.warning('请至少选择一条数据')
      }
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
