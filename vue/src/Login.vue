<template>
  
  <div class="login-page">
    
    <div class="login-container">
      
      <div class="login-card">
        <div class="back-button">
          <el-button type="primary" plain @click="goBack">
            <el-icon><ArrowLeft /></el-icon>
            返回
          </el-button>
        </div>
        <div class="login-header">
          <h1 class="login-title">用户登录</h1>
          <p class="login-subtitle">欢迎回到探脑科技</p>
        </div>

        <el-form
          ref="loginFormRef"
          :model="loginForm"
          :rules="rules"
          label-width="0"
          class="login-form"
          @submit.prevent="handleLogin"
        >
          <el-form-item prop="username">
            <el-input
              v-model="loginForm.username"
              placeholder="请输入用户名"
              size="large"
              :prefix-icon="User"
            />
          </el-form-item>

          <el-form-item prop="password">
            <el-input
              v-model="loginForm.password"
              type="password"
              placeholder="请输入密码"
              size="large"
              :prefix-icon="Lock"
              show-password
              @keyup.enter="handleLogin"
            />
          </el-form-item>

          <el-form-item>
            <el-button
              type="primary"
              size="large"
              :loading="loading"
              class="login-button"
              @click="handleLogin"
            >
              登录
            </el-button>
          </el-form-item>

          <div class="login-footer">
            <span class="footer-text">还没有账号？</span>
            <el-link type="primary" @click="handleRegister">立即注册</el-link>
          </div>


        </el-form>
      </div>
    </div>
    
    <!-- 滑块验证码组件 -->
    <Vcode
      :show="showVcode"
      @success="handleVcodeSuccess"
      @close="handleVcodeClose"
    />
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage, type FormInstance, type FormRules } from 'element-plus'
import { User, Lock, ArrowLeft } from '@element-plus/icons-vue'
import Vcode from 'vue3-puzzle-vcode'
import { userApi } from './api'
import { useUserStore } from './store/user'

const router = useRouter()
const userStore = useUserStore()

const loginFormRef = ref<FormInstance>()
const loading = ref(false)
const showVcode = ref(false)

const loginForm = reactive({
  username: '',
  password: ''
})

const rules: FormRules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' }
  ]
}

const handleLogin = async () => {
  if (!loginFormRef.value) return

  try {
    await loginFormRef.value.validate()
    loading.value = true

    // 显示滑块验证码
    showVcode.value = true
  } catch (error: any) {
    ElMessage.error(error.message || '请检查输入信息')
  } finally {
    loading.value = false
  }
}

const handleVcodeSuccess = async () => {
  try {
    loading.value = true

    const user = await userApi.login(loginForm.username, loginForm.password)
    
    userStore.setUser(user)
    userStore.setToken('demo-token-' + Date.now())
    
    ElMessage.success('登录成功')
    
    if (user.role === 'admin') {
      router.push('/admin')
    } else {
      router.push('/visitor')
    }
  } catch (error: any) {
    ElMessage.error(error.message || '登录失败，请检查用户名和密码')
  } finally {
    loading.value = false
    showVcode.value = false
  }
}

const handleVcodeClose = () => {
  showVcode.value = false
}

const handleRegister = () => {
  ElMessage.info('注册功能开发中...')
}

const fillDemo = (username: string, password: string, role: string) => {
  loginForm.username = username
  loginForm.password = password
  ElMessage.info(`已填充${role === 'admin' ? '管理员' : '普通用户'}测试账号`)
}

const goBack = () => {
  router.push('/visitor')
}
</script>

<style lang="scss" scoped>
.login-page {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 20px;
}

.login-container {
  width: 100%;
  max-width: 420px;
}

.login-card {
  background: white;
  border-radius: 16px;
  padding: 2.5rem;
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.2);
}

.back-button {
  margin-top: 0rem;
  margin-left: -0.5rem;
  margin-bottom: 1.5rem;
  text-align: left;
}



.login-header {
  text-align: center;
  margin-bottom: 2rem;
}

.login-title {
  font-size: 2rem;
  font-weight: 700;
  color: #333;
  margin: 0 0 0.5rem 0;
}

.login-subtitle {
  font-size: 1rem;
  color: #666;
  margin: 0;
}

.login-form {
  :deep(.el-form-item) {
    margin-bottom: 1.5rem;
  }
}

.login-button {
  width: 100%;
  height: 44px;
  font-size: 1rem;
  font-weight: 600;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border: none;
  
  &:hover {
    opacity: 0.9;
  }
}

.login-footer {
  text-align: center;
  margin-top: 1.5rem;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.5rem;
}

.footer-text {
  color: #666;
  font-size: 0.9rem;
}

.demo-accounts {
  margin-top: 2rem;
  padding: 1rem;
  background: #f8f9fa;
  border-radius: 8px;
}

.demo-title {
  font-size: 0.85rem;
  color: #666;
  margin: 0 0 0.75rem 0;
}

.demo-buttons {
  display: flex;
  gap: 0.5rem;
}

@media (max-width: 480px) {
  .login-card {
    padding: 1.5rem;
  }
  
  .login-title {
    font-size: 1.5rem;
  }
}
</style>