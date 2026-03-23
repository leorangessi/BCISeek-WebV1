<template>
  <div class="contact-section">
    <div class="section-container">
      <h2 class="section-title">联系我们</h2>
      <p class="section-subtitle">期待与您的合作</p>
      
      <div class="contact-grid">
        <div class="contact-info">
          <!-- 公司地址 -->
          <div class="info-card">
            <div class="info-icon">
              <el-icon :size="40">
                <Location />
              </el-icon>
            </div>
            <div class="info-content">
              <h3 class="info-title">公司地址</h3>
              <p class="info-value">四川省成都市青羊区同诚路333号</p>
            </div>
          </div>
          
          <!-- 联系电话 -->
          <!-- <div class="info-card">
            <div class="info-icon">
              <el-icon :size="40">
                <Phone />
              </el-icon>
            </div>
            <div class="info-content">
              <h3 class="info-title">联系电话</h3>
              <p class="info-value">400-888-8888</p>
            </div>
          </div> -->
          
          <!-- 电子邮箱 -->
          <div class="info-card">
            <div class="info-icon">
              <el-icon :size="40">
                <Message />
              </el-icon>
            </div>
            <div class="info-content">
              <h3 class="info-title">电子邮箱</h3>
              <p class="info-value">2673755910@qq.com</p>
            </div>
          </div>
          
          <!-- 企业微信 -->
          <!-- <div class="info-card">
            <div class="info-icon">
              <el-icon :size="40">
                <ChatDotRound />
              </el-icon>
            </div>
            <div class="info-content">
              <h3 class="info-title">企业微信</h3>
              <p class="info-value">扫码添加企业微信</p>
            </div>
          </div> -->
        </div>
        
        <!-- <div class="contact-form">
          <el-form :model="form" label-width="80px">
            <el-form-item label="姓名">
              <el-input v-model="form.name" placeholder="请输入您的姓名" />
            </el-form-item>
            <el-form-item label="邮箱">
              <el-input v-model="form.email" placeholder="请输入您的邮箱" />
            </el-form-item>
            <el-form-item label="电话">
              <el-input v-model="form.phone" placeholder="请输入您的电话" />
            </el-form-item>
            <el-form-item label="留言">
              <el-input 
                v-model="form.message" 
                type="textarea" 
                :rows="4"
                placeholder="请输入您的留言"
              />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="handleSubmit">提交</el-button>
            </el-form-item>
          </el-form>
        </div> -->
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { reactive } from 'vue'
import { ElMessage } from 'element-plus'
import { 
  Location, 
  Phone, 
  Message, 
  ChatDotRound 
} from '@element-plus/icons-vue'

const contactInfos = [
  {
    id: 1,
    icon: Location,
    title: '公司地址',
    value: '北京市海淀区中关村科技园'
  },
  {
    id: 2,
    icon: Phone,
    title: '联系电话',
    value: '400-888-8888'
  },
  {
    id: 3,
    icon: Message,
    title: '电子邮箱',
    value: 'contact@tanbrain.com'
  },
  {
    id: 4,
    icon: ChatDotRound,
    title: '企业微信',
    value: '扫码添加企业微信'
  }
]

const form = reactive({
  name: '',
  email: '',
  phone: '',
  message: ''
})

const handleSubmit = () => {
  ElMessage.success('提交成功，我们会尽快联系您！')
  form.name = ''
  form.email = ''
  form.phone = ''
  form.message = ''
}
</script>

<style lang="scss" scoped>
.contact-section {
  padding: 80px 20px;
  background: #f8f9fa;
}

.section-container {
  max-width: 1200px;
  margin: 0 auto;
}

.section-title {
  font-size: 2.5rem;
  font-weight: 700;
  text-align: center;
  margin-bottom: 1rem;
  color: #333;
}

.section-subtitle {
  font-size: 1.125rem;
  text-align: center;
  color: #666;
  margin-bottom: 4rem;
}

.contact-grid {
  display: grid;
  grid-template-columns: 1fr;
  gap: 3rem;
}

.contact-info {
  display: flex;
  flex-direction: row;
  gap: 1.5rem;
  flex-wrap: wrap;
  justify-content: center;
}

.info-card {
  display: flex;
  gap: 1.5rem;
  background: white;
  padding: 2rem;
  border-radius: 12px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
  transition: all 0.3s;
  
  &:hover {
    transform: translateY(-4px);
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
  }
}

.info-icon {
  width: 70px;
  height: 70px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
  
  :deep(.el-icon) {
    color: white;
  }
}

.info-content {
  flex: 1;
}

.info-title {
  font-size: 1.25rem;
  font-weight: 600;
  margin-bottom: 0.5rem;
  color: #333;
}

.info-value {
  font-size: 1rem;
  color: #666;
  line-height: 1.6;
}

.contact-form {
  background: white;
  padding: 2.5rem;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.05);
}

@media (max-width: 968px) {
  .contact-grid {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 768px) {
  .section-title {
    font-size: 2rem;
  }
  
  .info-card {
    flex-direction: column;
    text-align: center;
  }
}
</style>