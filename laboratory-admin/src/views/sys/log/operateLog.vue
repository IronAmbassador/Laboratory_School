<template>
  <div>
    <!--条件搜索表单-->
    <el-row>
      <el-form :inline="true">
        <el-form-item>
          <el-input v-model="searchObj.username" placeholder="操作用户" />
        </el-form-item>
        <el-form-item>
          <el-input v-model="searchObj.operationModule" placeholder="操作模块" />
        </el-form-item>
        <el-form-item>
          <el-select v-model="searchObj.operationState" placeholder="操作状态" @change="getAll">
            <el-option value="0" label="失败" />
            <el-option value="1" label="成功" />
          </el-select>
        </el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="getAll()">查询</el-button>
        <el-button icon="el-icon-refresh" @click="reset()">清空</el-button>
      </el-form>
    </el-row>

    <el-row>
      <el-button type="danger" icon="el-icon-delete" :disabled="ids.length < 1" @click="batchDelete"
        >批量删除</el-button
      >
      <el-button type="primary" icon="el-icon-download" @click="exportData">批量导出</el-button>
    </el-row>

    <!-- banner列表 :data="list"表示遍历list数组 stripe条纹 -->
    <el-row>
      <el-table
        :data="pageList.list"
        :border="false"
        v-loading="loading"
        element-loading-text="正在努力加载数据中..."
        stripe
        style="width: 100%; margin: 15px 0"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" fixed="left" width="55" />
        <el-table-column type="index" fixed="left" width="80" label="序号" />
        <el-table-column prop="username" fixed="left" label="操作用户" show-overflow-tooltip sortable />
        <el-table-column prop="operationModule" label="操作模块" show-overflow-tooltip />
        <el-table-column prop="operation" label="操作类型" show-overflow-tooltip />
        <el-table-column prop="operationIp" label="操作IP" show-overflow-tooltip sortable />
        <el-table-column prop="operationAddr" label="操作地址" show-overflow-tooltip />
        <el-table-column prop="operationState" label="操作状态" show-overflow-tooltip sortable>
          <template slot-scope="scope">
            <el-tag v-if="scope.row.operationState === 1" type="success" effect="plain">成功</el-tag>
            <el-tag v-if="scope.row.operationState === 0" type="danger" effect="plain">失败</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="operationTime" label="操作时间" show-overflow-tooltip sortable />
        <el-table-column prop="operationResult" label="操作结果" show-overflow-tooltip />
      </el-table>
    </el-row>

    <!-- 分页导航栏 -->
    <el-row>
      <el-pagination
        background
        :current-page="pIndex"
        :page-size="pSize"
        :total="pageList.total"
        :page-sizes="[3, 6, 9, 12, 15, 20]"
        layout="total, sizes, ->, prev, pager, next, jumper"
        @current-change="getAll"
        @size-change="handleSizeChange"
      />
    </el-row>
  </div>
</template>

<script>
// 引入api接口定义的js文件
import logApi from '@/api/sys/operation-log'
import { exportDataExcel } from '@/utils/index'

export default {
  props: {
    isLoad: { type: Boolean, required: true, default: false }
  },
  data() {
    // 定义变量和初始值
    return {
      pIndex: 1, // 当前页
      pSize: 6, // 每页显示记录数
      searchObj: {}, // 条件封装对象
      ids: [],
      pageList: {},
      loading: false
    }
  },
  watch: {
    isLoad: {
      immediate: true,
      handler(newVal) {
        if (newVal) {
          this.getAll()
        }
      }
    }
  },
  methods: {
    getAll(current = 1) {
      this.loading = true
      // 加载列表数据
      this.pIndex = current // 添加当前页参数
      // axios异步获取远程数据（ajax），通过api
      logApi
        .getPage(this.pIndex, this.pSize, this.searchObj)
        .then((response) => {
          this.pageList = response.data
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

    // 批量删除
    batchDelete() {
      this.$confirm('此操作将永久删除登录日志信息, 是否继续?', '提示', {
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
        logApi
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
    },

    // 导出
    async exportData() {
      try {
        const res = await logApi.exportData()
        exportDataExcel(res)
        this.$message.success('文件导出成功')
      } catch (error) {
        console.log('🚀 ~ logApi.exportData ~ error:', error)
        this.$message.error('文件导出失败')
      }
    },

    // 重置
    reset() {
      // 清空
      this.searchObj = {}
      this.getAll()
    }
  }
}
</script>

<style></style>
