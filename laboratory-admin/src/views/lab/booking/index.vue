<template>
  <div class="app-container">
    <!--条件搜索表单-->
    <el-row>
      <el-form :inline="true">
        <el-form-item>
          <el-select v-model="searchObj.labId" placeholder="实验室分室" filterable clearable @change="getAll()">
            <el-option v-for="item in labSub" :key="item.id" :label="item.labName" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-select v-model="searchObj.bookingStatus" placeholder="预约状态" clearable @change="getAll()">
            <el-option v-for="item in bookingStateOptions" :key="item.value" :label="item.label" :value="item.value" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-date-picker
            v-model="searchObj.bookingTimes"
            type="datetimerange"
            value-format="yyyy-MM-dd HH:mm:ss"
            range-separator="至"
            start-placeholder="预约开始时间"
            end-placeholder="预约结束时间"
            @change="getAll()"
          />
        </el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="getAll()">查询</el-button>
        <el-button type="default" icon="el-icon-refresh" @click="reset()">清空</el-button>
      </el-form>
    </el-row>

    <el-row>
      <el-button type="danger" icon="el-icon-delete" :disabled="ids.length < 1" @click="batchDelete"
        >批量删除</el-button
      >
      <el-button v-if="hasRoles(1)" type="primary" icon="el-icon-download" @click="exportData">批量导出</el-button>
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
        <el-table-column type="selection" width="55" fixed="left" />
        <el-table-column type="index" width="80" label="序号" fixed="left" />
        <el-table-column prop="labName" min-width="100" label="预约实验室" fixed="left" show-overflow-tooltip />
        <el-table-column v-if="hasRoles(1)" prop="username" label="预约用户" min-width="130" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-tag v-if="scope.row.roleId === 2" type="primary" effect="plain">
              {{ scope.row.username }}
              <el-divider direction="vertical" />
              教师
            </el-tag>
            <el-tag v-if="scope.row.roleId === 3" type="info" effect="plain">
              {{ scope.row.username }}
              <el-divider direction="vertical" />
              学生
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="bookingStatus" label="预约状态" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-tag v-if="scope.row.bookingStatus === 0" type="warning" effect="plain">审核中</el-tag>
            <el-tag v-if="scope.row.bookingStatus === 1" type="success" effect="plain">已预约</el-tag>
            <el-tag v-if="scope.row.bookingStatus === 2" type="danger" effect="plain">未通过</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="bookingTime" label="预约时间" min-width="120" show-overflow-tooltip sortable />
        <el-table-column prop="remark" label="备注" show-overflow-tooltip />
        <el-table-column prop="createAt" label="创建人" show-overflow-tooltip />
        <el-table-column prop="createTime" label="创建时间" min-width="120" show-overflow-tooltip sortable />
        <el-table-column prop="updateAt" min-width="100" label="最后修改人" show-overflow-tooltip />
        <el-table-column prop="updateTime" min-width="140" label="最后修改时间" show-overflow-tooltip sortable />

        <!-- 操作 -->
        <el-table-column label="操作" width="250" align="center" fixed="right">
          <template slot-scope="scope">
            <el-link type="primary" icon="el-icon-edit" @click="openEdit(scope.row.id)">编辑</el-link>
            <el-divider direction="vertical" />
            <el-popconfirm title="确定删除吗？" @confirm="remove(scope.row.id)">
              <template #reference>
                <el-link type="danger" icon="el-icon-remove" size="mini">删除</el-link>
              </template>
            </el-popconfirm>
            <el-divider v-if="hasRoles(1)" direction="vertical" />
            <el-dropdown v-if="hasRoles(1)">
              <el-link type="primary" icon="el-icon-more">更多</el-link>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>
                  <el-link
                    type="success"
                    icon="el-icon-success"
                    @click="process(scope.row.id, 1)"
                    :disabled="scope.row.bookingStatus == 1 || scope.row.bookingStatus == 2"
                    >审核通过</el-link
                  >
                </el-dropdown-item>
                <el-dropdown-item>
                  <el-link
                    type="danger"
                    icon="el-icon-error"
                    @click="process(scope.row.id, 2)"
                    :disabled="scope.row.bookingStatus == 1 || scope.row.bookingStatus == 2"
                    >审核不通过</el-link
                  >
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
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
    <el-dialog :title="dialogTitle" :visible.sync="visible" :before-close="close">
      <el-form ref="labBookingForm" :model="labBooking" :rules="rules" label-width="150px">
        <el-row :gutter="10">
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
            <el-form-item label="ID" prop="id" :hidden="true">
              <el-input v-model="labBooking.id" disabled />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
            <el-form-item label="所属实验室:" prop="labId">
              <el-select v-model="labBooking.labId" style="width: 100%" placeholder="请选择实验室">
                <el-option v-for="item in labSub" :key="item.id" :label="item.labName" :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
            <el-form-item label="预约开始时间:" prop="startTime" required>
              <el-date-picker
                style="width: 100%"
                v-model.trim="labBooking.startTime"
                value-format="yyyy-MM-dd HH:mm:ss"
                type="datetime"
                placeholder="请选择预约开始时间"
              />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
            <el-form-item label="预约结束时间:" prop="endTime" required>
              <el-date-picker
                style="width: 100%"
                v-model.trim="labBooking.endTime"
                value-format="yyyy-MM-dd HH:mm:ss"
                type="datetime"
                placeholder="请选择预约结束时间"
              />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="备注" prop="remark">
              <el-input
                v-model="labBooking.remark"
                :rows="3"
                type="textarea"
                placeholder="请输入备注"
                maxlength="255"
                show-word-limit
              />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button @click="resetForm">重 置</el-button>
        <el-button type="primary" @click="save('labBookingForm')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
//引入api接口定义的js文件
import labBookingApi from '@/api/lab/booking'
import labSubApi from '@/api/lab/sub'
import { exportDataExcel } from '@/utils/index'
import { mapGetters } from 'vuex'

export default {
  computed: {
    ...mapGetters(['userId', 'name', 'avatar', 'roleId'])
  },
  data() {
    return {
      // 列表相关
      pIndex: 1, //当前页
      pSize: 6, //每页显示记录数
      searchObj: {}, //条件封装对象
      page: {},
      loading: false,
      ids: [],
      labSub: [],
      bookingStateOptions: [
        { value: 0, label: '审核中' },
        { value: 1, label: '已预约' },
        { value: 2, label: '未通过' }
      ],
      dialogTitle: '',

      // 表单相关
      visible: false,
      labBooking: {
        id: undefined,
        labId: undefined,
        userId: undefined,
        bookingTime: '',
        remark: ''
      },

      rules: {
        labId: [{ required: true, message: '请选择实验室', trigger: 'change' }],
        bookingTime: [{ required: true, message: '请选择预约时间', trigger: 'change' }]
      }
    }
  },
  //勾子函数，在页面渲染之前执行，一般调用methods定义的方法得到数据
  created() {
    this.getAll() //调用methods中的函数
    this.getSub()
  },
  methods: {
    getSub() {
      labSubApi.getList().then((res) => {
        this.labSub = res.data
      })
    },
    //实验室信息
    getAll(current = 1) {
      this.searchObj.userId = this.userId
      this.searchObj.roleId = this.roleId

      this.loading = true
      // 加载列表数据
      this.pIndex = current //添加当前页参数
      // axios异步获取远程数据（ajax），通过api
      labBookingApi
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
      //重新调用数据加载方法 --> 方法中已经加入的查询参数
      this.getAll()
    },

    // 复选框选中
    handleSelectionChange(r) {
      // 得到选中的id
      this.ids = r.map((v) => v.id)
    },

    //删除实验室信息
    remove(id) {
      const loading = this.$loading({
        lock: true,
        text: '正在删除中...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      //确定执行then方法
      //调用接口
      labBookingApi
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
        this.$confirm('此操作将永久删除实验室信息, 是否继续?', '提示', {
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
          labBookingApi
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

    // 打开表单
    openEdit(id) {
      this.dialogTitle = '新增实验室预约'
      if (id != null) {
        this.dialogTitle = '编辑实验室预约'
        this.getById(id)
      }

      this.visible = true
    },

    // 根据Id查询
    getById(id) {
      labBookingApi.getById(id).then((res) => {
        this.labBooking = res.data
      })
    },

    // 关闭表单
    close() {
      this.visible = false
      this.$refs['labBookingForm'].resetFields()
    },

    // 重置表单
    resetForm() {
      if (this.labBooking.id) {
        this.getById(this.labBooking.id)
      }
      this.$refs['labBookingForm'].resetFields()
    },

    // 保存
    save(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          const apiCall = this.labBooking.id
            ? labBookingApi.update(this.labBooking)
            : labBookingApi.save(this.labBooking)

          apiCall.then((res) => {
            this.$message.success('操作成功')
            this.close()
            this.getAll()
          })
        }
      })
    },

    // 导出
    async exportData() {
      try {
        const res = await labBookingApi.exportData()
        exportDataExcel(res)
        this.$message.success('文件导出成功')
      } catch (error) {
        console.log('🚀 ~ labBookingApi.exportData ~ error:', error)
        this.$message.error('文件导出失败')
      }
    },

    // 审核
    process(id, bookingStatus) {
      let text = bookingStatus == 1 ? '通过' : '不通过'
      let color = bookingStatus == 1 ? 'success' : 'error'
      this.$confirm(`你确定要标记该预约记录为${text}吗?`, `审核${text}`, {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: color
      }).then(() => {
        // 调用接口
        labBookingApi.process({ id, bookingStatus }).then((resp) => {
          // 提示
          this.$message.success('操作成功!')
          // 刷新页面
          this.getAll()
        })
      })
    }
  }
}
</script>

<style></style>
