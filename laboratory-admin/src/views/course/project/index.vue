<template>
  <div class="app-container">
    <!--条件搜索表单-->
    <el-row>
      <el-form :inline="true">
        <el-form-item>
          <el-select v-model="searchObj.courseId" placeholder="课程" @change="getAll()">
            <el-option v-for="item in courseList" :key="item.id" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-input v-model="searchObj.name" placeholder="项目名称" />
        </el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="getAll()">查询</el-button>
        <el-button icon="el-icon-refresh" @click="reset()">清空</el-button>
      </el-form>
    </el-row>

    <!-- 工具栏 -->
    <el-row v-if="hasRoles(1, 2)">
      <el-button type="primary" icon="el-icon-plus" @click="openEdit(null)">新增</el-button>
      <el-button type="danger" icon="el-icon-delete" :disabled="ids.length < 1" @click="batchDelete">
        批量删除
      </el-button>
    </el-row>

    <!-- banner列表 :data="list"表示遍历list数组 stripe条纹 -->
    <el-row>
      <el-table
        :data="page.list"
        :border="false"
        v-loading="loading"
        element-loading-text="正在努力加载数据中..."
        stripe
        style="width: 100%; margin: 15px 0px"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" fixed="left" width="55" />
        <el-table-column type="index" fixed="left" width="80" label="序号" />
        <el-table-column prop="courseName" fixed="left" label="项目课程" show-overflow-tooltip />
        <el-table-column prop="name" fixed="left" label="项目名称" show-overflow-tooltip />
        <el-table-column prop="personNames" label="项目成员" show-overflow-tooltip />
        <el-table-column prop="startTime" min-width="140" label="项目启动日期" show-overflow-tooltip sortable />
        <el-table-column prop="stopTime" min-width="140" label="项目结束日期" show-overflow-tooltip sortable />
        <el-table-column prop="description" label="项目介绍" show-overflow-tooltip />
        <el-table-column prop="createAt" label="创建人" show-overflow-tooltip />
        <el-table-column prop="createTime" label="创建时间" min-width="120" show-overflow-tooltip sortable />
        <el-table-column prop="updateAt" min-width="100" label="最后修改人" show-overflow-tooltip />
        <el-table-column prop="updateTime" min-width="140" label="最后修改时间" show-overflow-tooltip sortable />

        <!-- 操作 -->
        <el-table-column label="操作" v-if="hasRoles(1, 2)" fixed="right" min-width="150" align="center">
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
      <el-form ref="projectForm" :model="project" :rules="rules" label-width="120px">
        <el-row :gutter="10">
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="ID" prop="id" :hidden="true">
              <el-input v-model="project.id" disabled />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="所属课程:" prop="courseId">
              <el-select v-model="project.courseId" placeholder="请选择课程" @change="getAll()">
                <el-option v-for="item in courseList" :key="item.id" :label="item.name" :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="项目名称:" prop="name">
              <el-input v-model.trim="project.name" placeholder="请输入项目名称" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="项目启动时间:" prop="startTime">
              <div class="block">
                <el-date-picker
                  v-model.trim="project.startTime"
                  style="width: 100%"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  type="datetime"
                  placeholder="请选择项目启动时间"
                />
              </div>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="项目结束时间:" prop="stopTime">
              <div class="block">
                <el-date-picker
                  v-model.trim="project.stopTime"
                  style="width: 100%"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  type="datetime"
                  placeholder="请选择项目结束时间"
                />
              </div>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="12">
            <el-form-item label="项目成员:" prop="personIds1">
              <el-select
                v-model="project.personIds1"
                multiple
                :multiple-limit="8"
                placeholder="请选择项目成员"
                style="width: 100%"
                filterable
                clearable
                @change="$forceUpdate()"
              >
                <el-option v-for="item in personList" :key="item.id" :label="item.name" :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="项目介绍:" prop="description">
              <el-input
                v-model="project.description"
                type="textarea"
                placeholder="请输入项目介绍"
                :autosize="{ minRows: 3, maxRows: 5 }"
                maxlength="150"
                show-word-limit
              />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close">关 闭</el-button>
        <el-button @click="resetForm('projectForm')">重 置</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
//引入api接口定义的js文件
import projectApi from '@/api/course/project'
import courseApi from '@/api/course/index'
import personApi from '@/api/lab/person'

export default {
  data() {
    // 定义变量和初始值
    return {
      loading: false,
      pIndex: 1, //当前页
      pSize: 6, //每页显示记录数
      searchObj: {}, //条件封装对象
      page: {}, // 列表
      ids: [],
      showEdit: false,
      courseList: [],
      personList: [],
      dialogTitle: '',

      // 定义表单绑定对象
      project: {
        id: undefined,
        courseId: undefined,
        name: '',
        startTime: '',
        stopTime: '',
        description: '',
        personIds: '',
        personIds1: []
      },
      rules: {
        courseId: [{ required: true, message: '所属课程不能为空', trigger: 'change' }],
        name: [{ required: true, message: '项目名称不能为空', trigger: 'blur' }],
        startTime: [{ required: true, message: '请选择项目启动时间', trigger: 'change' }],
        stopTime: [{ required: true, message: '请选择项目结束时间', trigger: 'change' }],
        personIds: [{ required: true, message: '项目成员不能为空', trigger: 'blur' }]
      }
    }
  },
  created() {
    //勾子函数，在页面渲染之前执行，一般调用methods定义的方法得到数据
    this.getAll() //调用methods中的函数
    this.getCourseList()
    this.getPersonList()
  },
  methods: {
    // 查询所有课程
    getCourseList() {
      courseApi.getList().then((res) => {
        this.courseList = res.data
      })
    },

    // 查询所有人员
    getPersonList() {
      personApi.getList().then((res) => {
        this.personList = res.data
      })
    },

    //实验室课程项目
    getAll(current = 1) {
      this.loading = true
      // 加载列表数据
      this.pIndex = current //添加当前页参数
      // axios异步获取远程数据（ajax），通过api
      projectApi
        .getPage(this.pIndex, this.pSize, this.searchObj)
        .then((response) => {
          //请求成功response是接口返回数据
          this.page = response.data
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
      this.dialogTitle = '新增课程项目'
      this.showEdit = true
      if (id != null) {
        this.dialogTitle = '编辑课程项目'
        this.getById(id)
      }
    },

    // 关闭表单
    close() {
      this.showEdit = false
      this.$refs['projectForm'].resetFields()
    },

    // 根据id查询
    async getById(id) {
      await projectApi.getById(id).then((res) => {
        this.project = res.data
        // 将字符串切割成数组并转换成数字
        this.project.personIds1 = this.project.personIds.split(',').map(Number)
      })
    },

    // 添加
    save() {
      this.$refs['projectForm'].validate((valid) => {
        if (valid) {
          this.project.personIds = this.project.personIds1.join(',')

          const apiCall = this.project.id ? projectApi.update(this.project) : projectApi.save(this.project)

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
      if (this.project.id) {
        this.getById(this.project.id)
      }

      this.$refs['projectForm'].resetFields()
    },

    //删除实验室课程项目
    remove(id) {
      const loading = this.$loading({
        lock: true,
        text: '正在删除中...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      projectApi
        .remove(id)
        .then((resp) => {
          //提示
          this.$message({
            message: '删除成功!',
            type: 'success'
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
        this.$confirm('此操作将永久删除项目信息, 是否继续?', '提示', {
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
          projectApi
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
