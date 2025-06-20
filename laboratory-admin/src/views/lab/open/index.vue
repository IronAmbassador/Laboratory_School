<template>
  <div class="app-container">
    <!--条件搜索表单-->
    <el-row>
      <el-form :inline="true">
        <el-form-item>
          <el-select v-model="searchObj.labSubId" placeholder="请选择实验分室" @change="getAll()">
            <el-option v-for="item in labSub" :key="item.id" :label="item.labName" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-input v-model="searchObj.name" placeholder="负责人姓名" />
        </el-form-item>
        <el-form-item>
          <el-input v-model="searchObj.phone" placeholder="负责人电话" />
        </el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="getAll()">查询</el-button>
        <el-button type="default" icon="el-icon-refresh" @click="reset()">清空</el-button>
      </el-form>
    </el-row>

    <!-- 工具栏 -->
    <el-row v-if="hasRoles(1)">
      <el-button type="primary" icon="el-icon-plus" @click="openEdit(null)">新增</el-button>
      <el-button type="danger" icon="el-icon-delete" :disabled="ids.length < 1" @click="batchDelete"
        >批量删除</el-button
      >
    </el-row>

    <el-row>
      <!-- banner列表 :data="list"表示遍历list数组 stripe条纹 -->
      <el-table
        :data="data.list"
        :border="false"
        v-loading="loading"
        element-loading-text="正在努力加载数据中..."
        stripe
        style="width: 100%; margin: 15px 0px"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" fixed="left" width="55" />
        <el-table-column type="index" fixed="left" width="80" label="序号" />
        <el-table-column prop="labName" fixed="left" label="实验室名称" min-width="100" show-overflow-tooltip />
        <el-table-column prop="startTime" label="开放时间" min-width="120" show-overflow-tooltip sortable />
        <el-table-column prop="stopTime" label="关闭时间" min-width="120" show-overflow-tooltip sortable />
        <el-table-column prop="name" label="负责人姓名" min-width="100" show-overflow-tooltip />
        <el-table-column prop="sex" label="负责人性别" min-width="100" show-overflow-tooltip>
          <template v-slot="scope">
            <el-tag type="success" v-if="scope.row.sex == '男'" effect="plain">男</el-tag>
            <el-tag type="danger" v-if="scope.row.sex == '女'" effect="plain">女</el-tag>
            <el-tag type="info" v-if="scope.row.sex == '未知'" effect="plain">未知</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="phone" label="负责人电话" min-width="120" show-overflow-tooltip sortable />
        <el-table-column prop="createAt" label="创建人" show-overflow-tooltip />
        <el-table-column prop="createTime" label="创建时间" min-width="120" show-overflow-tooltip sortable />
        <el-table-column prop="updateAt" label="最后修改人" min-width="100" show-overflow-tooltip />
        <el-table-column prop="updateTime" label="最后修改时间" min-width="140" show-overflow-tooltip sortable />

        <!-- 操作 -->
        <el-table-column label="操作" v-if="hasRoles(1)" fixed="right" min-width="150" align="center">
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
        :total="data.total"
      >
      </el-pagination>
    </el-row>

    <!-- 表单 -->
    <el-dialog :title="dialogTitle" :visible.sync="showEdit" :before-close="close">
      <el-form ref="openForm" :model="open" :rules="rules" label-width="150px">
        <el-row :gutter="10">
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="ID" prop="id" :hidden="true">
              <el-input v-model="open.id" disabled />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
            <el-form-item label="所属实验分室:" prop="labId">
              <el-select v-model="open.labId" placeholder="请选择实验分室" style="width: 100%">
                <el-option v-for="item in labSub" :key="item.id" :label="item.labName" :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
            <el-form-item label="负责人姓名:" prop="name">
              <el-input v-model.trim="open.name" placeholder="请输入负责人姓名" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
            <el-form-item label="负责人性别">
              <el-select v-model="open.sex" style="width: 100%" placeholder="请选择负责人性别">
                <el-option value="男" label="男" />
                <el-option value="女" label="女" />
                <el-option value="未知" label="未知" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
            <el-form-item label="负责人电话:" prop="phone">
              <el-input v-model.trim="open.phone" placeholder="请输入负责人电话" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
            <el-form-item label="实验室开放时间:" prop="startTime">
              <div class="block">
                <el-date-picker
                  v-model.trim="open.startTime"
                  style="width: 100%"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  type="datetime"
                  placeholder="请选择实验室开放时间"
                />
              </div>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
            <el-form-item label="实验室关闭时间:" prop="stopTime">
              <div class="block">
                <el-date-picker
                  v-model.trim="open.stopTime"
                  style="width: 100%"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  type="datetime"
                  placeholder="请选择实验室关闭时间"
                />
              </div>
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
import openApi from '@/api/lab/open'
import labSubApi from '@/api/lab/sub'

export default {
  data() {
    // 定义变量和初始值
    return {
      pIndex: 1, // 当前页
      pSize: 6, // 每页显示记录数
      searchObj: {}, // 条件封装对象
      data: {}, // 数据
      loading: false,
      ids: [],
      showEdit: false,
      dialogTitle: '',

      // 定义表单绑定对象
      open: {
        id: undefined,
        name: '',
        sex: '男',
        phone: '',
        startTime: '',
        stopTime: '',
        labId: ''
      },
      labSub: [],
      isEdit: false,
      rules: {
        name: [{ required: true, message: '负责人姓名不能为空', trigger: 'blur' }],
        phone: [
          { required: true, message: '负责人电话不能为空', trigger: 'blur' },
          {
            pattern: /^1[3456789]\d{9}$/,
            message: '手机号码格式错误',
            trigger: ['blur', 'change']
          }
        ],
        startTime: [{ required: true, message: '请选择实验室开放时间', trigger: 'change' }],
        stopTime: [{ required: true, message: '请选择实验室关闭时间', trigger: 'change' }],
        labId: [{ required: true, message: '请选择实验室', trigger: 'change' }]
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

    //实验室课程信息
    getAll(current = 1) {
      this.loading = true
      // 加载列表数据
      this.pIndex = current //添加当前页参数
      // axios异步获取远程数据（ajax），通过api
      openApi
        .getPage(this.pIndex, this.pSize, this.searchObj)
        .then((response) => {
          this.data = response.data
        })
        .finally(() => {
          this.loading = false
        })
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

    // 打开表单
    openEdit(id) {
      this.dialogTitle = '新增实验室开放信息'
      this.showEdit = true
      if (id != null) {
        this.dialogTitle = '编辑实验室开放信息'
        this.getById(id)
      }
    },

    //删除实验室开放信息
    remove(id) {
      const loading = this.$loading({
        lock: true,
        text: '正在删除中...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      openApi
        .remove(id)
        .then((resp) => {
          //提示
          this.$message.success('删除成功!')
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
        this.$confirm('此操作将永久删除实验室开放信息, 是否继续?', '提示', {
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
          openApi
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
    },

    // 根据id获取
    getById(id) {
      openApi.getById(id).then((res) => {
        this.open = res.data
      })
    },

    // 关闭表单
    close() {
      this.showEdit = false
      this.$refs['openForm'].resetFields()
    },

    // 重置表单
    resetForm() {
      if (this.open.id) {
        this.getById(this.open.id)
      }
      this.$refs['openForm'].resetFields()
    },

    // 添加
    save() {
      this.$refs['openForm'].validate((valid) => {
        if (valid) {
          const apiCall = this.open.id ? openApi.update(this.open) : openApi.save(this.open)

          apiCall.then((res) => {
            this.$message.success(res.message)
            this.close()
            this.getAll()
          })
        }
      })
    }
  }
}
</script>

<style></style>
