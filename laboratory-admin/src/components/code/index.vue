vue
<template>
  <div class="code" @click="refreshCaptcha" title="看不清换一张">
    <canvas id="captcha" width="100" height="45"></canvas>
  </div>
</template>

<script>
export default {
  data() {
    return {
      captchaLength: 5, // 验证码长度
      currentCaptcha: ''
    }
  },
  mounted() {
    this.currentCaptcha = this.generateCaptcha()
  },
  methods: {
    getRandomColor() {
      const letters = '0123456789ABCDEF'
      let color = '#'
      for (let i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)]
      }
      return color
    },
    generateCaptcha() {
      const canvas = document.getElementById('captcha')
      const ctx = canvas.getContext('2d')
      const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789'
      let captcha = ''
      // 清空画布
      ctx.clearRect(0, 0, canvas.width, canvas.height)
      // 生成随机验证码
      for (let i = 0; i < this.captchaLength; i++) {
        const char = chars.charAt(Math.floor(Math.random() * chars.length))
        captcha += char
        // 设置字体样式和随机颜色
        ctx.font = '30px Arial'
        ctx.fillStyle = this.getRandomColor() // 获取随机颜色
        ctx.textAlign = 'center'
        ctx.textBaseline = 'middle'
        // 绘制验证码字符
        ctx.fillText(char, (canvas.width / this.captchaLength) * (i + 0.5), canvas.height / 2)
      }
      // 添加彩色干扰线
      for (let i = 0; i < 5; i++) {
        ctx.beginPath()
        ctx.moveTo(Math.random() * canvas.width, Math.random() * canvas.height)
        ctx.lineTo(Math.random() * canvas.width, Math.random() * canvas.height)
        ctx.strokeStyle = this.getRandomColor() // 获取随机颜色
        ctx.lineWidth = 2
        ctx.stroke()
      }
      return captcha
    },
    refreshCaptcha() {
      this.currentCaptcha = this.generateCaptcha()
    }
  }
}
</script>

<style scoped>
.code {
  background-color: #fff;
  cursor: pointer; /* 鼠标悬停时显示手型光标 */
}
</style>
