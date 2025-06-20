<script>
import Lab from './lab.vue'
import LabSub from './lab-sub.vue'

export default {
  components: { Lab, LabSub },
  data() {
    return {
      labId: undefined
    }
  },
  methods: {
    handleGetLabId(val) {
      this.labId = val
    }
  }
}
</script>

<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="7">
        <Lab @getLabId="handleGetLabId" />
      </el-col>
      <el-col :span="17">
        <LabSub :lab-id="labId" />
      </el-col>
    </el-row>
  </div>
</template>
