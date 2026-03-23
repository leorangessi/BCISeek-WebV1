<template>
  <div class="admin-layout">
    <div class="admin-sidebar">
      <div class="sidebar-header">
        <div class="logo">
          <el-icon><Setting /></el-icon>
          <span>管理后台</span>
        </div>
        <div class="user-info">
          <el-avatar :size="32" :icon="UserFilled" />
          <span class="username">{{ userStore.user?.username }}</span>
        </div>
      </div>

      <el-menu
        :default-active="activeMenu"
        class="sidebar-menu"
        @select="handleMenuSelect"
      >
        <el-menu-item index="posts">
          <el-icon><Document /></el-icon>
          <span>文章管理</span>
        </el-menu-item>
        <el-menu-item index="users">
          <el-icon><User /></el-icon>
          <span>用户管理</span>
        </el-menu-item>
        <el-menu-item index="comments">
          <el-icon><ChatDotRound /></el-icon>
          <span>评论管理</span>
        </el-menu-item>
        <el-menu-item index="permissions">
          <el-icon><Lock /></el-icon>
          <span>权限管理</span>
        </el-menu-item>
      </el-menu>

      <div class="sidebar-footer">
        <el-button type="danger" @click="handleLogout" text>
          <el-icon><SwitchButton /></el-icon>
          退出登录
        </el-button>
      </div>
    </div>

    <div class="admin-content">
      <div class="content-header">
        <h2 class="page-title">{{ getPageTitle(activeMenu) }}</h2>
        <div class="header-buttons">
          <el-button type="primary" @click="handleBack" v-if="activeMenu !== 'posts'">
            <el-icon><Back /></el-icon>
            返回
          </el-button>
          <el-button type="warning" @click="goBackHome">
            <el-icon><House /></el-icon>
            返回首页
          </el-button>
        </div>
      </div>

      <div class="content-body">
        <PostManagement v-if="activeMenu === 'posts'" />
        <div v-else class="coming-soon">
          <el-empty description="功能开发中..." />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Setting, UserFilled, Document, User, ChatDotRound, Lock, SwitchButton, Back, House } from '@element-plus/icons-vue'
import { useUserStore } from './store/user'
import PostManagement from './components/PostManagement.vue'

const router = useRouter()
const userStore = useUserStore()

const activeMenu = ref('posts')

const menuTitles: Record<string, string> = {
  'posts': '文章管理',
  'users': '用户管理',
  'comments': '评论管理',
  'permissions': '权限管理'
}

onMounted(() => {
  if (!userStore.isAdmin()) {
    ElMessage.error('无权访问管理后台')
    router.push('/visitor')
  }
})

const getPageTitle = (menu: string) => {
  return menuTitles[menu] || '管理后台'
}

const handleMenuSelect = (index: string) => {
  activeMenu.value = index
}

const handleBack = () => {
  activeMenu.value = 'posts'
}

const goBackHome = () => {
  router.push('/visitor')
}

const handleLogout = async () => {
  try {
    await ElMessageBox.confirm(
      '确定要退出登录吗？',
      '提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }
    )
    userStore.logout()
    ElMessage.success('已退出登录')
    router.push('/visitor')
  } catch {
    
  }
}
</script>

<style lang="scss" scoped>
.admin-layout {
  display: flex;
  min-height: 100vh;
  background: #f0f2f5;
}

.admin-sidebar {
  width: 260px;
  background: white;
  border-right: 1px solid #e4e7ed;
  display: flex;
  flex-direction: column;
  position: fixed;
  left: 0;
  top: 0;
  bottom: 0;
  z-index: 100;
}

.sidebar-header {
  padding: 1.5rem;
  border-bottom: 1px solid #e4e7ed;
}

.logo {
  display: flex;
  align-items: center;
  gap: 0.75rem;
  font-size: 1.25rem;
  font-weight: 700;
  color: #667eea;
  margin-bottom: 1.5rem;
  
  :deep(.el-icon) {
    font-size: 1.5rem;
  }
}

.user-info {
  display: flex;
  align-items: center;
  gap: 0.75rem;
  padding: 1rem;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border-radius: 8px;
  color: white;
}

.username {
  font-weight: 600;
  font-size: 0.95rem;
}

.sidebar-menu {
  flex: 1;
  border: none;
  
  :deep(.el-menu-item) {
    height: 50px;
    line-height: 50px;
    margin: 0;
    
    &:hover {
      background: #f0f0f0;
      color: #667eea;
    }
    
    &.is-active {
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      color: white;
      
      .el-icon {
        color: white;
      }
    }
  }
  
  :deep(.el-icon) {
    margin-right: 0.75rem;
    font-size: 1.1rem;
  }
}

.sidebar-footer {
  padding: 1.5rem;
  border-top: 1px solid #e4e7ed;
}

.admin-content {
  flex: 1;
  margin-left: 260px;
  display: flex;
  flex-direction: column;
}

.content-header {
  padding: 1.5rem 2rem;
  background: white;
  border-bottom: 1px solid #e4e7ed;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.header-buttons {
  display: flex;
  gap: 0.75rem;
}

.page-title {
  font-size: 1.5rem;
  font-weight: 700;
  color: #333;
  margin: 0;
}

.content-body {
  flex: 1;
  padding: 2rem;
  overflow-y: auto;
}

.coming-soon {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 400px;
}

@media (max-width: 768px) {
  .admin-sidebar {
    width: 60px;
    
    .logo span,
    .username,
    .sidebar-menu span,
    .sidebar-footer span {
      display: none;
    }
  }
  
  .admin-content {
    margin-left: 60px;
  }
}
</style>