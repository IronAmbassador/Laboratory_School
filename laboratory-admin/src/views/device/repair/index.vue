<template>
  <div class="app-container">
    <!--条件搜索表单-->
    <el-row>
      <el-form :model="searchObj" :inline="true">
        <el-form-item>
          <el-select v-model="searchObj.deviceId" placeholder="设备" @change="getAll">
            <el-option v-for="item in deviceList" :key="item.id" :label="item.deviceName" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-select v-model="searchObj.status" placeholder="状态" @change="getAll">
            <el-option v-for="item in statusOptions" :key="item.value" :label="item.label" :value="item.value" />
          </el-select>
        </el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="getAll()">查询</el-button>
        <el-button type="default" icon="el-icon-refresh" @click="reset()">清空</el-button>
      </el-form>
    </el-row>

    <el-row>
      <el-button type="primary" icon="el-icon-plus" @click="openEdit(null)">新增</el-button>
      <el-button v-if="hasRoles(1)" type="danger" icon="el-icon-delete" :disabled="ids.length < 1" @click="batchDelete"
        >批量删除</el-button
      >
      <el-button type="primary" icon="el-icon-download" @click="exportData">批量导出</el-button>
      <el-button type="primary" icon="el-icon-upload2" @click="openImport">批量导入</el-button>
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
        <el-table-column type="index" width="80" fixed="left" label="序号" />
        <el-table-column prop="deviceName" label="设备名称" show-overflow-tooltip />
        <el-table-column prop="repairDate" label="报修时间" min-width="120" show-overflow-tooltip sortable />
        <el-table-column prop="description" label="故障描述" show-overflow-tooltip />
        <el-table-column prop="technicianName" label="维修技术员" min-width="100" show-overflow-tooltip />
        <el-table-column prop="status" label="维修状态" min-width="120" show-overflow-tooltip sortable>
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status === 1" type="success" effect="plain">已维修</el-tag>
            <el-tag v-if="scope.row.status === 2" type="danger" effect="plain">未维修</el-tag>
            <el-tag v-if="scope.row.status === 3" type="wanring" effect="plain">维修中</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createAt" label="创建人" show-overflow-tooltip />
        <el-table-column prop="createTime" label="创建时间" min-width="120" show-overflow-tooltip sortable />
        <el-table-column prop="updateAt" label="最后修改人" min-width="100" show-overflow-tooltip />
        <el-table-column prop="updateTime" label="最后修改时间" min-width="140" show-overflow-tooltip sortable />

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
        :page-sizes="[3, 6, 9]"
        background
        layout="total, sizes, ->, prev, pager, next, jumper"
        :total="data.total"
      >
      </el-pagination>
    </el-row>

    <!-- 表单 -->
    <el-dialog :title="repair.id ? '设备报修修改' : '设备报修新增'" :visible.sync="visible" :before-close="close">
      <el-form ref="repairForm" :model="repair" :rules="rules" label-width="120px">
        <el-row :gutter="10">
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="ID" prop="id" :hidden="true">
              <el-input v-model="repair.id" disabled />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="所属设备" prop="deviceId">
              <el-select v-model="repair.deviceId" placeholder="请选择设备" style="width: 100%">
                <el-option v-for="item in deviceList" :key="item.id" :label="item.deviceName" :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="报修时间" prop="repairDate">
              <el-date-picker
                type="date"
                value-format="yyyy-MM-dd"
                v-model="repair.repairDate"
                placeholder="请选择报修时间"
                style="width: 100%"
              />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="维修技术员" prop="">
              <el-input v-model.trim="repair.technicianName" placeholder="请输入维修技术员名称" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="维修状态" prop="status">
              <el-select v-model="repair.status" placeholder="请选择维修状态" style="width: 100%">
                <el-option v-for="item in statusOptions" :key="item.value" :label="item.label" :value="item.value" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="故障描述" prop="description">
              <el-input type="textarea" :rows="4" placeholder="请简单描述故障" v-model="repair.description" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button @click="resetForm">重 置</el-button>
        <el-button type="primary" @click="save('repairForm')">确 定</el-button>
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
import repairApi from '@/api/device/repair'
import deviceApi from '@/api/device/index'
import { exportDataExcel } from '@/utils/index'

export default {
  data() {
    // 定义变量和初始值
    return {
      // 列表相关
      pIndex: 1, // 当前页
      pSize: 6, // 每页显示记录数
      searchObj: {}, // 条件封装对象
      data: [], // 数据
      loading: false,
      ids: [],
      deviceList: [],
      statusOptions: [
        { value: 1, label: '已维修' },
        { value: 2, label: '未维修' },
        { value: 3, label: '维修中' }
      ],
      dialogTitle: '',
      showUpload: false,
      uploadPath: process.env.VUE_APP_BASE_API + '/file/upload',

      // 表单相关
      visible: false,
      repair: {
        id: undefined,
        deviceId: undefined,
        description: '',
        repairDate: undefined,
        technicianName: '',
        status: undefined
      },
      rules: {
        deviceId: [{ required: true, message: '请选择设备', trigger: 'change' }],
        repairDate: [{ required: true, message: '请选择报修时间', trigger: 'change' }],
        technicianName: [{ required: true, message: '请输入维修技术员名称', trigger: 'blur' }],
        status: [{ required: true, message: '请选择维修状态', trigger: 'change' }],
        description: [{ required: true, message: '请输入故障描述', trigger: 'blur' }]
      }
    }
  },
  created() {
    //勾子函数，在页面渲染之前执行，一般调用methods定义的方法得到数据
    this.getAll() //调用methods中的函数
    this.getDeviceList()
  },
  methods: {
    // 获取设备
    getDeviceList() {
      deviceApi.getList().then((res) => {
        this.deviceList = res.data
      })
    },

    //实验室课程信息
    getAll(current = 1) {
      this.loading = true
      // 加载列表数据
      this.pIndex = current //添加当前页参数
      // axios异步获取远程数据（ajax），通过api
      repairApi
        .getPage(this.pIndex, this.pSize, this.searchObj)
        .then((response) => {
          //请求成功response是接口返回数据
          //返回集合赋值list
          this.data = response.data
        })
        .finally(() => {
          this.loading = false
        })
    },

    handleSizeChange(pageSize) {
      // 改变页码
      this.pSize = pageSize
      //重新调用数据加载方法 --> 方法中已经加入的查询参数
      this.getAll()
    },
    remove(id) {
      const loading = this.$loading({
        lock: true,
        text: '正在删除中...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      repairApi
        .remove(id)
        .then((resp) => {
          this.$message.success('删除成功!')
          this.getAll()
        })
        .finally(() => {
          loading.close()
        })
    },

    // 复选框选中
    handleSelectionChange(r) {
      // 得到选中的id
      this.ids = r.map((v) => v.id)
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
          repairApi
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

    // 打开表单
    openEdit(id) {
      this.dialogTitle = '新增设备报修信息'
      if (id != null) {
        this.dialogTitle = '编辑设备报修信息'
        this.getById(id)
      }

      this.visible = true
    },

    // 根据Id查询
    getById(id) {
      repairApi.getById(id).then((res) => {
        this.repair = res.data
      })
    },

    // 关闭表单
    close() {
      this.visible = false
      this.$refs['repairForm'].resetFields()
    },

    // 重置表单
    resetForm() {
      if (this.repair.id) {
        this.getById(this.repair.id)
      }

      this.$refs['repairForm'].resetFields()
    },

    // 保存
    save(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          const apiCall = this.repair.id ? repairApi.update(this.repair) : repairApi.save(this.repair)
          apiCall.then(() => {
            this.$message.success('操作成功')
            this.close()
            this.getAll()
          })
        }
      })
    },

    // 清空
    reset() {
      this.searchObj = {}
      this.getAll()
    },

    // 导出
    async exportData() {
      try {
        const res = await repairApi.exportData()
        exportDataExcel(res)
        this.$message.success('文件导出成功')
      } catch (error) {
        console.log('🚀 ~ repairApi.exportData ~ error:', error)
        this.$message.error('文件导出失败')
      }
    },

    // 打开导入弹窗
    openImport() {
      this.showUpload = true
    },

    // 文件上传成功后调用导入方法
    onUploadSuccess(res, file) {
      repairApi.importData(res.data).then((res) => {
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
