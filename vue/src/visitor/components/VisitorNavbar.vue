<template>
  <div class="visitor-navbar">
    <div class="navbar-container">
      <div class="navbar-brand">
        <img src="/images/partners/log.svg" alt="探脑科技" class="brand-logo" />
        <span class="brand-text">探脑科技 BCISeek</span>
      </div>
      
      <div class="navbar-menu">
        <a 
          v-for="item in menuItems" 
          :key="item.path"
          class="menu-item"
          :class="{ active: activePath === item.path }"
          @click="handleMenuClick(item)"
        >
          {{ item.label }}
        </a>
      </div>
      
      <div class="navbar-actions">
        <template v-if="!userStore.isLoggedIn()">
          <el-button type="primary" @click="handleLogin">用户登录</el-button>
        </template>
        <template v-else>
          <div class="user-info">
            <el-dropdown @command="handleUserCommand">
              <span class="user-name">
                <el-icon><User /></el-icon>
                {{ userStore.user?.username }}
              </span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item v-if="userStore.isAdmin()" command="admin">
                    <el-icon><Setting /></el-icon>
                    管理后台
                  </el-dropdown-item>
                  <el-dropdown-item command="profile">
                    <el-icon><User /></el-icon>
                    个人中心
                  </el-dropdown-item>
                  <el-dropdown-item divided command="logout">
                    <el-icon><SwitchButton /></el-icon>
                    退出登录
                  </el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </div>
        </template>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import { User, Setting, SwitchButton } from '@element-plus/icons-vue'
import { useUserStore } from '../../store/user'

const router = useRouter()
const route = useRoute()
const userStore = useUserStore()

const activePath = ref('')

const menuItems = [
  { label: '首页', path: '/visitor' },
  { label: '开发者社区', path: '/visitor/community' },
  { label: '快速上手指南', path: '/guide' },
  { label: '下载', path: '/download' },
  { label: '联系我们', path: '/contact' }
]

onMounted(() => {
  activePath.value = route.path
  userStore.initUser()
})

onUnmounted(() => {
  
})

const handleMenuClick = (item: any) => {
  activePath.value = item.path
  if (item.path === '/visitor' || item.path === '/visitor/community' || item.path === '/guide' || item.path === '/contact') {
    router.push(item.path)
  } else {
    ElMessage.info(`${item.label}页面开发中...`)
  }
}

const handleLogin = () => {
  router.push('/login')
}

const handleUserCommand = async (command: string) => {
  switch (command) {
    case 'admin':
      router.push('/admin')
      break
    case 'profile':
      ElMessage.info('个人中心功能开发中...')
      break
    case 'logout':
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
      break
  }
}
</script>

<style lang="scss" scoped>
.visitor-navbar {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(10px);
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  z-index: 1000;
}

.navbar-container {
  max-width: 1400px;
  margin: 0 auto;
  padding: 0 2rem;
  height: 70px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.navbar-brand {
    display: flex;
    align-items: center;
    gap: 0.75rem;
    
    .brand-logo {
      height: 40px;
      width: auto;
      object-fit: contain;
    }
    
    .brand-text {
      font-size: 1.5rem;
      font-weight: 700;
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      -webkit-background-clip: text;
      -webkit-text-fill-color: transparent;
      background-clip: text;
    }
  }

.navbar-menu {
  display: flex;
  gap: 2rem;
}

.menu-item {
  color: #333;
  text-decoration: none;
  font-size: 1rem;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.3s;
  position: relative;
  
  &:hover {
    color: #667eea;
  }
  
  &.active {
    color: #667eea;
    
    &::after {
      content: '';
      position: absolute;
      bottom: -8px;
      left: 0;
      right: 0;
      height: 2px;
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      border-radius: 1px;
    }
  }
}

.navbar-actions {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.user-info {
  display: flex;
  align-items: center;
}

.user-name {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  cursor: pointer;
  padding: 0.5rem 1rem;
  border-radius: 8px;
  transition: all 0.3s;
  font-size: 1rem;
  font-weight: 500;
  
  &:hover {
    background: #f0f0f0;
    color: #667eea;
  }
  
  :deep(.el-icon) {
    font-size: 1.1rem;
  }
}

@media (max-width: 768px) {
  .navbar-container {
    padding: 0 1rem;
  }
  
  .navbar-menu {
    display: none;
  }
  
  .navbar-brand .brand-text {
    font-size: 1.2rem;
  }
}

</style>