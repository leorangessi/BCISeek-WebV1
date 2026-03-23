<template>
  <div class="visitor-home">
    <VisitorNavbar />
    
    <div class="hero-section">
      <div class="hero-content">
        <h1 class="hero-title">ExpandableBCI - 可扩展脑机接口系统</h1>
        <p class="hero-subtitle">市面最低价格 · 最高性价比 · 自动扩展 · 无需烧录代码</p>
        <div class="hero-buttons">
          <el-button type="primary" size="large" @click="handleGetStarted">了解更多</el-button>
          <el-button size="large" @click="handleDownload">产品对比</el-button>
        </div>
      </div>
    </div>

    <ProductComparison />
    <CoreTechnology />
    <ProductFeatures />
    <PartnersSection />
    <ContactSection />
  </div>
</template>

<script setup lang="ts">
import VisitorNavbar from './components/VisitorNavbar.vue'
import ProductComparison from './components/ProductComparison.vue'
import CoreTechnology from './components/CoreTechnology.vue'
import ProductFeatures from './components/ProductFeatures.vue'
import PartnersSection from './components/PartnersSection.vue'
import ContactSection from './components/ContactSection.vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'

const router = useRouter()

const handleGetStarted = () => {
  router.push('/visitor')
}

const handleDownload = () => {
  ElMessage.info('产品对比功能开发中...')
}
</script>

<style lang="scss" scoped>
.visitor-home {
  min-height: 100vh;
  background: #fff;
}

.hero-section {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 120px 20px;
  text-align: center;
  color: white;
}

.hero-content {
  max-width: 1200px;
  margin: 0 auto;
}

.hero-title {
  font-size: 3rem;
  font-weight: 700;
  margin-bottom: 1.5rem;
  line-height: 1.2;
}

.hero-subtitle {
  font-size: 1.25rem;
  margin-bottom: 2.5rem;
  opacity: 0.9;
  line-height: 1.6;
}

.hero-buttons {
  display: flex;
  gap: 1rem;
  justify-content: center;
  flex-wrap: wrap;
}

@media (max-width: 768px) {
  .hero-title {
    font-size: 2rem;
  }
  
  .hero-subtitle {
    font-size: 1rem;
  }
}
</style>