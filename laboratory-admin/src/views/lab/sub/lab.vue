<template>
  <div>
    <!--条件搜索表单-->
    <el-row>
      <el-form :inline="true">
        <el-form-item>
          <el-input v-model="searchObj.name" placeholder="实验室名称" clearable />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="getAll()">查询</el-button>
        </el-form-item>
      </el-form>
    </el-row>

    <!-- 工具栏 -->
    <el-row v-if="hasRoles(1)">
      <el-button type="primary" icon="el-icon-plus" @click="openEdit(null)">新增</el-button>
    </el-row>

    <el-row>
      <el-table
        style="width: 100%; margin: 15px 0px"
        v-loading="loading"
        element-loading-text="正在努力加载数据中..."
        stripe
        highlight-current-row
        :current-row-key="selectionId"
        :data="data.list"
        @row-click="handleRowClick"
      >
        <el-table-column fixed="left" width="35">
          <template slot-scope="scope">
            <el-radio v-model="selectionId" :label="scope.row.id"><span></span></el-radio>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="实验室名称" min-width="100" show-overflow-tooltip />

        <!-- 操作 -->
        <el-table-column label="操作" v-if="hasRoles(1)" width="140" align="center">
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
        layout="total, sizes, ->, prev, next"
        :total="data.total"
      >
      </el-pagination>
    </el-row>

    <!-- 表单 -->
    <el-dialog :title="dialogTitle" :visible.sync="visible" width="30" :before-close="close">
      <el-form ref="labForm" :model="lab" :rules="rules" label-width="100px">
        <el-form-item label="ID" prop="id" :hidden="true">
          <el-input v-model="lab.id" disabled />
        </el-form-item>
        <el-form-item label="实验室名称:" prop="name">
          <el-input v-model.trim="lab.name" placeholder="请输入实验室名称" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button @click="resetForm">重 置</el-button>
        <el-button type="primary" @click="save('labForm')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
//引入api接口定义的js文件
import labApi from '@/api/lab/index'

export default {
  data() {
    return {
      // 列表相关
      pIndex: 1,
      pSize: 6,
      searchObj: {},
      data: { list: [], total: 0 }, // 确保 data 是一个对象
      loading: false,
      selectionId: null, // 修正初始值
      dialogTitle: '',

      // 表单相关
      visible: false,
      lab: {
        id: undefined,
        name: ''
      },
      rules: {
        name: [{ required: true, message: '实验室名称不能为空', trigger: 'blur' }]
      }
    }
  },
  created() {
    //勾子函数，在页面渲染之前执行，一般调用methods定义的方法得到数据
    this.getAll() //调用methods中的函数
  },
  methods: {
    //实验室课程信息
    getAll(current = 1) {
      this.loading = true
      // 加载列表数据
      this.pIndex = current //添加当前页参数
      // axios异步获取远程数据（ajax），通过api
      labApi
        .getPage(this.pIndex, this.pSize, this.searchObj)
        .then((response) => {
          //请求成功response是接口返回数据
          //返回集合赋值list
          this.data = response.data
          // 设置默认选中项（假设默认选中第一行）
          if (this.data.list.length > 0) {
            this.selectionId = this.data.list[0].id
            this.$emit('getLabId', this.selectionId)
          }
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

    // 单击选中
    handleRowClick(row) {
      this.selectionId = row.id // 修正为 row.id
      this.$emit('getLabId', row.id) // 修正为 row.id
    },

    //删除
    remove(id) {
      const loading = this.$loading({
        lock: true,
        text: '正在删除中...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      //确定执行then方法
      //调用接口
      labApi
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

    // 清空
    reset() {
      this.searchObj = {}
      this.getAll()
    },

    // 打开表单
    openEdit(id) {
      this.dialogTitle = '新增实验室'
      if (id != null) {
        this.dialogTitle = '编辑实验室'
        this.getById(id)
      }
      this.visible = true
    },

    // 根据Id查询
    getById(id) {
      labApi.getById(id).then((res) => {
        this.lab = res.data
      })
    },

    // 关闭表单
    close() {
      this.visible = false
      this.$refs['labForm'].resetFields()
    },

    // 重置表单
    resetForm() {
      if (this.lab.id) {
        this.getById(this.lab.id)
      }
      this.$refs['labForm'].resetFields()
    },

    // 保存
    save(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          const apiCall = this.lab.id ? labApi.update(this.lab) : labApi.save(this.lab)
          apiCall.then(() => {
            this.$message.success('操作成功')
            this.close()
            this.getAll()
          })
        }
      })
    }
  }
}
</script>
