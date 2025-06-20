<template>
  <div class="app-container">
    <!--条件搜索表单-->
    <el-row>
      <el-form ref="searchFrom" :inline="true" :model="searchObj">
        <el-form-item prop="labId">
          <el-select v-model="searchObj.labId" placeholder="请选择实验分室" @change="getAll()">
            <el-option v-for="item in labSub" :key="item.id" :label="item.labName" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item prop="courseName">
          <el-input v-model="searchObj.courseName" placeholder="课程名称" />
        </el-form-item>
        <el-form-item prop="teacher">
          <el-input v-model="searchObj.teacher" placeholder="任课教师" />
        </el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="getAll()">查询</el-button>
        <el-button icon="el-icon-refresh" @click="resetForm('searchFrom', 1)">清空</el-button>
      </el-form>
    </el-row>

    <el-row>
      <el-button v-if="hasRoles(1, 2)" type="primary" icon="el-icon-plus" @click="openEdit(null)">新增</el-button>
      <el-button
        v-if="hasRoles(1, 2)"
        type="danger"
        icon="el-icon-delete"
        :disabled="ids.length < 1"
        @click="batchDelete"
      >
        批量删除
      </el-button>
      <el-button type="primary" icon="el-icon-download" @click="exportData">批量导出</el-button>
      <el-button v-if="hasRoles(1, 2)" type="primary" icon="el-icon-upload2" @click="openImport">批量导入</el-button>
    </el-row>

    <el-row>
      <!-- banner列表 :data="list"表示遍历list数组 stripe条纹 -->
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
        <el-table-column prop="name" fixed="left" label="课程名称" show-overflow-tooltip />
        <el-table-column prop="labName" label="实验室分室" min-width="100" show-overflow-tooltip />
        <el-table-column prop="teacher" label="任课教师" show-overflow-tooltip />
        <el-table-column prop="upTime" label="上课时间" min-width="120" show-overflow-tooltip sortable />
        <el-table-column prop="downTime" label="下课时间" min-width="120" show-overflow-tooltip sortable />
        <el-table-column prop="createAt" label="创建人" show-overflow-tooltip />
        <el-table-column prop="createTime" label="创建时间" min-width="120" show-overflow-tooltip sortable />
        <el-table-column prop="updateAt" label="最后修改人" min-width="100" show-overflow-tooltip />
        <el-table-column prop="updateTime" label="最后修改时间" min-width="140" show-overflow-tooltip sortable />
        <!-- 操作 -->
        <el-table-column label="操作" fixed="right" min-width="150" v-if="hasRoles(1, 2)" align="center">
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
      <el-form ref="courseForm" :model="course" :rules="rules" label-width="120px">
        <el-row :gutter="10">
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="ID" prop="id" :hidden="true">
              <el-input v-model="course.id" disabled />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="所属实验分室:" prop="labId">
              <el-select v-model="course.labId" style="width: 100%" placeholder="请选择实验分室">
                <el-option v-for="item in labSub" :key="item.id" :label="item.labName" :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="课程名称:" prop="name">
              <el-input v-model.trim="course.name" placeholder="请输入课程名称" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="教师姓名:" prop="teacher">
              <el-input v-model.trim="course.teacher" placeholder="请输入教师姓名" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="上课时间:" prop="upTime">
              <div class="block">
                <el-date-picker
                  v-model.trim="course.upTime"
                  style="width: 100%"
                  type="datetime"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  placeholder="请选择上课时间"
                />
              </div>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="下课时间:" prop="downTime">
              <div class="block">
                <el-date-picker
                  v-model.trim="course.downTime"
                  style="width: 100%"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  type="datetime"
                  placeholder="请选择下课时间"
                />
              </div>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close">关 闭</el-button>
        <el-button @click="resetForm('courseForm', 2)">重 置</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog v-if="hasRoles(1, 2)" title="文件上传" :visible.sync="showUpload">
      <el-form label-position="right" label-width="170px">
        <el-form-item label="文件">
          <el-upload ref="uploadRef" :multiple="false" :on-success="onUploadSuccess" :action="uploadPath" name="file">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传xlsx文件</div>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="showUpload = false">取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
//引入api接口定义的js文件
import labSubApi from '@/api/lab/sub'
import courseApi from '@/api/course/index'
import { exportDataExcel } from '@/utils/index'
import { getToken } from '@/utils/auth'

export default {
  data() {
    // 定义变量和初始值
    return {
      loading: false,
      pIndex: 1, //当前页
      pSize: 6, //每页显示记录数
      searchObj: {}, //条件封装对象
      page: [], // 列表
      labSub: [],
      ids: [],
      showEdit: false,
      dialogTitle: '',
      showUpload: false,
      uploadPath: process.env.VUE_APP_BASE_API + '/file/upload',

      // 定义表单绑定对象
      course: {
        id: undefined,
        name: '',
        teacher: '',
        upTime: '',
        downTime: '',
        labId: ''
      },
      rules: {
        name: [{ required: true, message: '课程名不能为空', trigger: 'blur' }],
        teacher: [{ required: true, message: '老师不能为空', trigger: 'blur' }],
        upTime: [
          {
            required: true,
            message: '请选择上课时间',
            trigger: ['blur, change']
          }
        ],
        downTime: [
          {
            required: true,
            message: '请选择下课时间',
            trigger: ['blur, change']
          }
        ],
        labId: [{ required: true, message: '请选择实验室', trigger: ['blur, change'] }]
      }
    }
  },
  created() {
    //勾子函数，在页面渲染之前执行，一般调用methods定义的方法得到数据
    this.getAll() //调用methods中的函数
    this.getSub()
  },
  methods: {
    //查询所有分室信息
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
      courseApi
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
      this.dialogTitle = '新增课程信息'
      this.showEdit = true
      if (id != null) {
        this.dialogTitle = '编辑课程信息'
        this.getById(id)
      }
    },

    // 根据id查询
    getById(id) {
      courseApi.getById(id).then((res) => {
        this.course = res.data
      })
    },

    // 添加
    save() {
      this.$refs['courseForm'].validate((valid) => {
        if (valid) {
          const apiCall = this.course.id ? courseApi.update(this.course) : courseApi.save(this.course)

          apiCall.then((res) => {
            this.$message.success(res.message)
            this.close()
            this.getAll()
          })
        }
      })
    },

    // 关闭表单
    close() {
      this.showEdit = false
      this.$refs['courseForm'].resetFields()
    },

    // 重置
    resetForm(form, type) {
      if (type === 1) {
        this.getAll()
      }

      if (type === 2) {
        if (this.course.id) {
          this.getById(this.course.id)
        }
      }

      this.$refs[form].resetFields()
    },

    //删除实验室课程信息
    remove(id) {
      const loading = this.$loading({
        lock: true,
        text: '正在删除中...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      courseApi
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
        this.$confirm('此操作将永久删除课程信息, 是否继续?', '提示', {
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
          courseApi
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

    // 导出
    async exportData() {
      try {
        const res = await courseApi.exportData()
        exportDataExcel(res)
        this.$message.success('文件导出成功')
      } catch (error) {
        console.log('🚀 ~ courseApi.exportData ~ error:', error)
        this.$message.error('文件导出失败')
      }
    },

    // 打开导入弹窗
    openImport() {
      this.showUpload = true
    },

    // 文件上传成功后调用导入方法
    onUploadSuccess(res, file) {
      courseApi.importData(res.data).then((res) => {
        this.$refs.uploadRef.clearFiles()
        this.showUpload = false
        this.$message.success(res.data)
        this.getAll()
      })
    }
  }
}
</script>

<style></style>
