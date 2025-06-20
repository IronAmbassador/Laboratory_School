<template>
  <div>
    <!--条件搜索表单-->
    <el-row>
      <el-form :inline="true">
        <el-form-item>
          <el-input v-model="searchObj.LabName" placeholder="分室名称" />
        </el-form-item>
        <el-form-item>
          <el-select v-model="searchObj.labType" placeholder="学院" clearable @change="getAll()">
            <el-option label="计算机学院" value="计算机学院" />
            <el-option label="电子信息学院" value="电子信息学院" />
            <el-option label="机械工程学院" value="机械工程学院" />
            <el-option label="化学工程学院" value="化学工程学院" />
            <el-option label="生物医学学院" value="生物医学学院" />
            <el-option label="物理学院" value="物理学院" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-input v-model="searchObj.labAddr" placeholder="地点" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="getAll()">查询</el-button>
          <el-button icon="el-icon-refresh" @click="reset()">清空</el-button>
        </el-form-item>
      </el-form>
    </el-row>

    <el-row>
      <el-button v-if="hasRoles(1)" type="primary" icon="el-icon-plus" @click="openEdit(null)">新增</el-button>
      <el-button v-if="hasRoles(1)" type="danger" icon="el-icon-delete" :disabled="ids.length < 1" @click="batchDelete">
        批量删除
      </el-button>
      <el-button v-if="hasRoles(1, 2)" type="primary" icon="el-icon-download" @click="exportData">批量导出</el-button>
      <el-button v-if="hasRoles(1)" type="primary" icon="el-icon-upload2" @click="openImport">批量导入</el-button>
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
        <el-table-column type="selection" width="55" fixed="left" />
        <el-table-column type="index" width="80" label="序号" fixed="left" />
        <el-table-column prop="labName" label="分室名称" fixed="left" min-width="120" show-overflow-tooltip />
        <el-table-column prop="labType" label="学院" min-width="100" show-overflow-tooltip />
        <el-table-column prop="labAddr" label="地点" min-width="100" show-overflow-tooltip />
        <el-table-column prop="createAt" label="创建人" show-overflow-tooltip />
        <el-table-column prop="createTime" label="学期年份" min-width="120" show-overflow-tooltip sortable />
        <el-table-column prop="updateAt" label="最后修改人" min-width="100" show-overflow-tooltip />
        <el-table-column prop="updateTime" label="最后修改时间" min-width="140" show-overflow-tooltip sortable />

        <!-- 操作 -->
        <el-table-column label="操作" width="200" align="center" fixed="right">
          <template slot-scope="scope">
            <el-link v-if="hasRoles(1)" type="primary" icon="el-icon-edit" @click="openEdit(scope.row.id)"
              >编辑
            </el-link>
            <el-divider v-if="hasRoles(1)" direction="vertical" />
            <el-popconfirm title="确定删除吗？" @confirm="remove(scope.row.id)">
              <template #reference>
                <el-link type="danger" v-if="hasRoles(1)" icon="el-icon-remove" size="mini">删除</el-link>
              </template>
            </el-popconfirm>
            <el-divider v-if="hasRoles(1)" direction="vertical" />
            <el-link type="info" icon="el-icon-info" @click="openDetail(scope.row)">详情</el-link>
            <el-divider v-if="hasRoles(2, 3) && scope.row.labStatus == 0" direction="vertical" />
            <el-link
              v-if="hasRoles(2, 3) && scope.row.labStatus == 0"
              type="success"
              icon="el-icon-s-claim"
              @click="openBooking(scope.row)"
              >预约
            </el-link>
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
    <el-dialog :title="dialogTitle" :visible.sync="visible" @close="close('labSubForm', 1)">
      <el-form ref="labSubForm" :model="labSub" :rules="rules" label-width="100px">
        <el-row :gutter="10">
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="ID" prop="id" :hidden="true">
              <el-input v-model="labSub.id" disabled />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="所属实验室:" prop="labId">
              <el-select v-model="labSub.labId" style="width: 100%" placeholder="请选择实验室">
                <el-option v-for="item in lab" :key="item.id" :label="item.name" :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="分室名称:" prop="labName">
              <el-input v-model.trim="labSub.labName" placeholder="请输入分室名称" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="学院:" prop="labType">
              <el-select v-model="labSub.labType" style="width: 100%" placeholder="请选择学院">
                <el-option label="计算机学院" value="计算机学院" />
                <el-option label="电子信息学院" value="电子信息学院" />
                <el-option label="机械工程学院" value="机械工程学院" />
                <el-option label="化学工程学院" value="化学工程学院" />
                <el-option label="生物医学学院" value="生物医学学院" />
                <el-option label="物理学院" value="物理学院" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="学期年份:" prop="labCreateYear">
              <div class="block">
                <el-date-picker
                  v-model="labSub.labCreateYear"
                  style="width: 100%"
                  type="year"
                  :default-value="labSub.labCreateYear"
                  placeholder="请选择学期年份"
                />
              </div>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="地点:" prop="labAddr">
              <el-input v-model.trim="labSub.labAddr" placeholder="请输入地点" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <el-form-item label="使用专业:" prop="labMajor1">
              <el-checkbox-group v-model="labSub.labMajor1">
                <el-checkbox v-for="item in labMajor" :key="item" :label="item">{{ item }}</el-checkbox>
              </el-checkbox-group>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close('labSubForm', 1)">取 消</el-button>
        <el-button @click="resetForm">重 置</el-button>
        <el-button type="primary" @click="save('labSubForm')">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="分室详情" :visible.sync="detailVisible" @close="close(null, 2)">
      <el-form label-width="120px">
        <el-row :gutter="10">
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="所属实验室:"> {{ labSub.name }}</el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="分室名称:">
              {{ labSub.labName }}
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="学院:">
              {{ labSub.labType }}
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="学期年份:"> {{ labSub.labCreateYear }} 年</el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="地点:">
              {{ labSub.labAddr }}
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <el-form-item label="使用专业:">
              {{ labSub.labMajor }}
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="创建人:">
              {{ labSub.createAt }}
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="创建时间:">
              {{ labSub.createTime }}
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="最后修改人:">
              {{ labSub.updateAt }}
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="最后修改时间:">
              {{ labSub.updateTime }}
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close(null, 2)">关 闭</el-button>
      </div>
    </el-dialog>

    <!-- 实验室预约 -->
    <el-dialog title="实验室预约" :visible.sync="bookingVisible" @close="close('labBookingForm', 3)">
      <el-form ref="labBookingForm" :model="labBooking" label-width="150px">
        <el-row :gutter="10">
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
            <el-form-item label="实验室" prop="labId">
              <el-select v-model="labBooking.labId" style="width: 100%" placeholder="请选择实验室" disabled>
                <el-option v-for="item in page.list" :key="item.id" :label="item.labName" :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
            <el-form-item label="实验室类型:" prop="labType">
              <el-select v-model="labBooking.labType" style="width: 100%" placeholder="请选择类型" disabled>
                <el-option label="生物实验室" value="生物实验室" />
                <el-option label="计算机实验室" value="计算机实验室" />
                <el-option label="物理实验室" value="物理实验室" />
                <el-option label="医学实验室" value="医学实验室" />
                <el-option label="化学实验室" value="化学实验室" />
                <el-option label="工程实验室" value="工程实验室" />
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
                align="left"
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
                align="left"
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
        <el-button @click="close('labBookingForm', 3)">取 消</el-button>
        <el-button type="primary" @click="saveBooking('labBookingForm')">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="文件上传" :visible.sync="showUpload">
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
import labApi from '@/api/lab/index'
import labBookingApi from '@/api/lab/booking'
import { exportDataExcel } from '@/utils/index'

export default {
  props: {
    labId: { type: Number, required: true, default: 0 }
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
      dialogTitle: '',
      showUpload: false,
      uploadPath: process.env.VUE_APP_BASE_API + '/file/upload',

      // 表单相关
      visible: false,
      labSub: {
        id: undefined,
        labName: '',
        labType: '',
        labCreateYear: '2020',
        labArea: 0,
        labAddr: '',
        labSeat: 0,
        labStatus: 0,
        labMajor: '',
        labId: '',
        labMajor1: []
      },
      labStatusOptions: [
        { value: 0, text: '可预约', type: 'success' },
        { value: 1, text: '维护中', type: 'info' },
        { value: 2, text: '使用中' },
        { value: 3, text: '待检中', type: 'warning' }
      ],
      labMajor: [
        '计算机科学与技术',
        '软件工程',
        '物联网工程',
        '电子信息工程',
        '数学与应用数学',
        '数据科学与大数据技术',
        '其他'
      ],
      lab: [],
      rules: {
        labName: [{ required: true, message: '分室名称不能为空', trigger: 'blur' }],
        labType: [{ required: true, message: '学院不能为空', trigger: 'change' }],
        labCreateYear: [{ required: true, message: '学期年份不能为空', trigger: 'blur' }],
        labAddr: [{ required: true, message: '地址不能为空', trigger: 'blur' }],
        labMajor1: [{ required: true, message: '请选择使用专业', trigger: 'blur' }],
        labId: [{ required: true, message: '请选择实验室', trigger: 'change' }]
      },

      // 详情
      detailVisible: false,

      // 实验室预约
      labBooking: {
        labId: undefined,
        startTime: undefined,
        endTime: undefined,
        remark: ''
      },
      bookingVisible: false
    }
  },
  created() {
    this.getListLab()

    this.$watch('labId', (newVal, oldVal) => {
      if (newVal != oldVal) {
        this.searchObj.labId = newVal
        this.labSub.labId = newVal
        this.getAll()
      }
    })
  },
  methods: {
    //实验室信息
    getAll(current = 1) {
      this.loading = true
      // 加载列表数据
      this.pIndex = current //添加当前页参数
      // axios异步获取远程数据（ajax），通过api
      labSubApi
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
      labSubApi
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
        this.$confirm('此操作将永久删除分室信息, 是否继续?', '提示', {
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
          labSubApi
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
      this.dialogTitle = '新增分室'
      if (id != null) {
        this.dialogTitle = '编辑分室'
        this.getById(id)
      }
      this.visible = true
    },

    // 查询所有实验室
    getListLab() {
      labApi.getList().then((res) => {
        this.lab = res.data
      })
    },

    // 根据Id查询
    getById(id) {
      labSubApi.getById(id).then((res) => {
        this.labSub = res.data
        this.labSub.labMajor1 = this.labSub.labMajor.split(',')
      })
    },

    // 关闭表单
    close(form, type) {
      switch (type) {
        case 1:
          this.visible = false
          break
        case 2:
          this.detailVisible = false
          break
        case 3:
          this.bookingVisible = false
          break
      }

      if (form) {
        this.$refs[form].resetFields()
      }
    },

    // 重置表单
    resetForm() {
      if (this.labSub.id) {
        this.getById(this.labSub.id)
      }
      this.$refs['labSubForm'].resetFields()
    },

    // 保存
    save(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          this.labSub.labMajor = this.labSub.labMajor1.join(',')
          const apiCall = this.labSub.id ? labSubApi.update(this.labSub) : labSubApi.save(this.labSub)

          apiCall.then((res) => {
            this.$message.success('操作成功')
            this.close(form, 1)
            this.getAll()
          })
        }
      })
    },

    // 导出
    async exportData() {
      try {
        const res = await labSubApi.exportData()
        exportDataExcel(res)
        this.$message.success('文件导出成功')
      } catch (error) {
        console.log('🚀 ~ labSubApi.exportData ~ error:', error)
        this.$message.error('文件导出失败')
      }
    },

    // 打开实验室预约表单
    openBooking(r) {
      this.bookingVisible = true
      this.labBooking.labId = r.id
      this.labBooking.labType = r.labType
    },

    // 预约实验室
    saveBooking(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          labBookingApi.save(this.labBooking).then((res) => {
            this.$message.success('预约成功')
            this.close(form, 3)
          })
        }
      })
    },

    // 打开详情
    openDetail(row) {
      this.detailVisible = true
      this.labSub = row
    },

    // 打开导入弹窗
    openImport() {
      this.showUpload = true
    },

    // 文件上传成功后调用导入方法
    onUploadSuccess(res, file) {
      labSubApi.importData(res.data).then((res) => {
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
