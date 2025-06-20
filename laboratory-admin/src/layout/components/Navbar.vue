<template>
  <div class="navbar">
    <hamburger :is-active="sidebar.opened" class="hamburger-container" @toggleClick="toggleSideBar" />

    <breadcrumb class="breadcrumb-container" />

    <div class="right-menu">
      <el-dropdown class="avatar-container" trigger="click">
        <div class="avatar-wrapper">
          <img :src="avatar + '?imageView2/1/w/80/h/80'" class="user-avatar" />
          <span class="username">{{ name }}</span>
          <i class="el-icon-caret-bottom" />
        </div>
        <el-dropdown-menu slot="dropdown" class="user-dropdown">
          <router-link to="/">
            <el-dropdown-item> 主页 </el-dropdown-item>
          </router-link>
          <router-link to="/profile">
            <el-dropdown-item> 个人中心 </el-dropdown-item>
          </router-link>
          <el-dropdown-item @click.native="cleanCache"> 清除缓存 </el-dropdown-item>
          <el-dropdown-item divided @click.native="logout">
            <span style="display: block">退出登录</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'

export default {
  components: {
    Breadcrumb,
    Hamburger
  },
  computed: {
    ...mapGetters(['sidebar', 'avatar', 'name'])
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    logout() {
      this.$confirm('此操作将退出登录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        beforeClose: (action, instance, done) => {
          if (action === 'confirm') {
            instance.confirmButtonLoading = true
            instance.confirmButtonText = '正在退出登录中...'
            setTimeout(() => {
              instance.confirmButtonLoading = false
              done()
            }, 1000)
          } else {
            done()
          }
        }
      }).then(async () => {
        await this.$store.dispatch('user/logout')
        this.$router.push(`/login`)
        this.$message.success('成功退出登录')
      })
    },
    // 清除缓存
    cleanCache() {
      const loading = this.$loading({
        lock: true,
        text: '正在清理缓存中...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      setTimeout(() => {
        loading.close()
        this.$router.push(`/dashboard`)
        this.$message.success('缓存清理完毕！')
      }, 2000)
    }
  }
}
</script>

<style lang="scss" scoped>
.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
  cursor: pointer;

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background 0.3s;
    -webkit-tap-highlight-color: transparent;

    &:hover {
      background: rgba(0, 0, 0, 0.025);
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background 0.3s;

        &:hover {
          background: rgba(0, 0, 0, 0.025);
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        display: flex;
        align-items: center; /* 垂直居中对齐 */
        gap: 10px; /* 元素之间的间距 */

        .user-avatar {
          width: 40px;
          height: 40px;
          border-radius: 50%; /* 圆形头像 */
          object-fit: cover; /* 保持图片比例并裁剪 */
        }

        .user-name {
          font-size: 16px; /* 设置字体大小 */
          color: #333; /* 设置字体颜色 */
        }

        .el-icon-caret-bottom {
          font-size: 16px; /* 设置字体大小 */
          color: #333; /* 设置字体颜色 */
        }
      }
    }
  }
}
</style>
