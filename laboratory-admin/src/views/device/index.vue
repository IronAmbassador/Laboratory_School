<template>
  <div class="app-container">
    <!--条件搜索表单-->
    <el-row>
      <el-form :model="searchObj" :inline="true">
        <el-form-item>
          <el-select v-model="searchObj.guarantee" placeholder="学期" clearable @change="getAll()">
            <el-option label="2024-2025第一学期" value="2024-2025第一学期" />
            <el-option label="2024-2025第二学期" value="2024-2025第二学期" />
            <el-option label="2025-2026第一学期" value="2025-2026第一学期" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-select v-model="searchObj.labId" placeholder="实验室" clearable @change="getAll()">
            <el-option v-for="item in labSub" :key="item.id" :label="item.labName" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-select v-model="searchObj.deviceStatus" placeholder="检查状态" clearable @change="getAll()">
            <el-option v-for="item in deviceStatusOptions" :key="item.value" :value="item.value" :label="item.text" />
          </el-select>
        </el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="getAll()">查询</el-button>
        <el-button icon="el-icon-refresh" @click="reset()">清空</el-button>
      </el-form>
    </el-row>

    <el-row v-if="hasRoles(1, 2)">
      <el-button type="primary" icon="el-icon-plus" @click="openEdit(null)">新增</el-button>
      <el-button type="danger" icon="el-icon-delete" :disabled="ids.length < 1" @click="batchDelete"
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
        <el-table-column prop="labName" fixed="left" label="实验室" min-width="100" show-overflow-tooltip />
        <el-table-column prop="deviceName" label="检查项" show-overflow-tooltip />
        <el-table-column prop="deviceDate" label="检查日期" min-width="120" show-overflow-tooltip sortable />
        <el-table-column prop="guarantee" label="学期" min-width="120" show-overflow-tooltip sortable />
        <el-table-column prop="deviceFactory" label="检查人员" show-overflow-tooltip />
        <el-table-column prop="deviceStatus" label="检查状态">
          <template v-slot="scope">
            <el-tag
              v-for="item in deviceStatusOptions"
              :key="item.value"
              :type="item.type"
              v-if="scope.row.deviceStatus == item.value"
              effect="plain"
              >{{ item.text }}</el-tag
            >
          </template>
        </el-table-column>
        <el-table-column prop="createAt" label="创建人" show-overflow-tooltip />
        <el-table-column prop="createTime" label="创建时间" min-width="120" show-overflow-tooltip sortable />
        <el-table-column prop="updateAt" label="最后修改人" min-width="100" show-overflow-tooltip />
        <el-table-column prop="updateTime" label="最后修改时间" min-width="140" show-overflow-tooltip sortable />

        <!-- 操作 -->
        <el-table-column label="操作" fixed="right" min-width="150" align="center" v-if="hasRoles(1, 2)">
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
    <el-dialog v-if="hasRoles(1, 2)" :title="dialogTitle" :visible.sync="visible" :before-close="close">
      <el-form ref="deviceForm" :model="device" :rules="rules" label-width="120px">
        <el-row :gutter="10">
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="ID" prop="id" :hidden="true">
              <el-input v-model="device.id" disabled />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="所属实验分室:" prop="labId">
              <el-select v-model="device.labId" placeholder="请选择实验分室" style="width: 100%">
                <el-option v-for="item in labSub" :key="item.id" :label="item.labName" :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="检查项:" prop="deviceName">
              <el-select v-model="device.deviceName" placeholder="请选择检查项" style="width: 100%">
                <el-option value="门窗检查">门窗检查</el-option>
                <el-option value="电器检查">电器检查</el-option>
                <el-option value="线路检查">线路检查</el-option>
                <el-option value="消防检查">消防检查</el-option>
                <el-option value="设备检查">设备检查</el-option>
                <el-option value="卫生检查">卫生检查</el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="检查日期:" prop="deviceDate">
              <div class="block">
                <el-date-picker
                  v-model.trim="device.deviceDate"
                  style="width: 100%"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  type="date"
                  placeholder="请选择检查日期"
                  align="left"
                />
              </div>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="学期:" prop="guarantee">
              <el-select v-model="device.guarantee" placeholder="请选择学期" style="width: 100%">
                <el-option value="2024-2025第一学期">2024-2025第一学期</el-option>
                <el-option value="2024-2025第二学期">2024-2025第二学期</el-option>
                <el-option value="2025-2026第一学期">2025-2026第一学期</el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="检查人员:" prop="deviceFactory">
              <el-input v-model.trim="device.deviceFactory" placeholder="请输入检查人员" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="8">
            <el-form-item label="检查状态:" prop="deviceStatus">
              <el-select v-model="device.deviceStatus" placeholder="请选择检查状态" style="width: 100%">
                <el-option
                  v-for="item in deviceStatusOptions"
                  :key="item.value"
                  :value="item.value"
                  :label="item.text"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button @click="resetForm">重 置</el-button>
        <el-button type="primary" @click="save('deviceForm')">确 定</el-button>
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
import deviceApi from '@/api/device/index'
import labSubApi from '@/api/lab/sub'
import { exportDataExcel } from '@/utils/index'

export default {
  data() {
    // 定义变量和初始值
    return {
      pIndex: 1, // 当前页
      pSize: 6, // 每页显示记录数
      searchObj: {}, // 条件封装对象
      data: [], // 数据
      loading: false,
      ids: [],
      dialogTitle: '',
      showUpload: false,
      uploadPath: process.env.VUE_APP_BASE_API + '/file/upload',

      // 表单相关
      visible: false,
      device: {
        id: undefined,
        deviceName: '',
        deviceDate: '',
        devicePrice: '',
        guarantee: '',
        deviceFactory: '',
        deviceStatus: 0,
        labId: ''
      },
      labSub: [],
      rules: {
        deviceName: [{ required: true, message: '请选择检查项', trigger: 'change' }],
        deviceDate: [{ required: true, message: '请选择检查日期', trigger: 'change' }],
        guarantee: [{ required: true, message: '请选择学期', trigger: 'change' }],
        deviceFactory: [{ required: true, message: '检查人员不能为空', trigger: 'blur' }],
        labId: [{ required: true, message: '请选择实验室', trigger: 'change' }]
      },
      deviceStatusOptions: [
        { value: 0, text: '正常', type: 'success' },
        { value: 1, text: '异常', type: 'danger' },
        { value: 2, text: '已处理', type: 'info' }
      ]
    }
  },
  created() {
    //勾子函数，在页面渲染之前执行，一般调用methods定义的方法得到数据
    this.getAll() //调用methods中的函数
    this.getSub() //获取实验室列表用于搜索
  },
  methods: {
    //实验室课程信息
    getAll(current = 1) {
      this.loading = true
      // 加载列表数据
      this.pIndex = current //添加当前页参数
      // axios异步获取远程数据（ajax），通过api
      deviceApi
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
      //删除实验室设备信息
      deviceApi
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
          deviceApi
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
      this.dialogTitle = '新增检查记录'
      if (id != null) {
        this.dialogTitle = '编辑检查记录'
        this.getById(id)
      }

      this.getSub()

      this.visible = true
    },

    // 查询实验室
    getSub() {
      labSubApi.getList().then((res) => {
        this.labSub = res.data
      })
    },

    // 根据Id查询
    getById(id) {
      deviceApi.getById(id).then((res) => {
        this.device = res.data
      })
    },

    // 关闭表单
    close() {
      this.visible = false
      this.$refs['deviceForm'].resetFields()
    },

    // 重置表单
    resetForm() {
      if (this.device.id) {
        this.getById(this.device.id)
      }

      this.$refs['deviceForm'].resetFields()
    },

    // 保存
    save(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          const apiCall = this.device.id ? deviceApi.update(this.device) : deviceApi.save(this.device)
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
        const res = await deviceApi.exportData()
        exportDataExcel(res)
        this.$message.success('文件导出成功')
      } catch (error) {
        console.log('🚀 ~ deviceApi.exportData ~ error:', error)
        this.$message.error('文件导出失败')
      }
    },

    // 打开导入弹窗
    openImport() {
      this.showUpload = true
    },

    // 文件上传成功后调用导入方法
    onUploadSuccess(res, file) {
      deviceApi.importData(res.data).then((res) => {
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
