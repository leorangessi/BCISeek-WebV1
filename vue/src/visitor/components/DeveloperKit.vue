<template>
  <div class="developer-kit-section">
    <div class="section-container">
      <h2 class="section-title">EEG 开发者套件</h2>
      <p class="section-subtitle">科研级信噪比 · 开发者级便捷</p>
      
      <div class="kit-content">
        <div class="kit-features">
          <div v-for="feature in features" :key="feature.id" class="feature-item">
            <div class="feature-icon">
              <el-icon :size="32">
                <component :is="feature.icon" />
              </el-icon>
            </div>
            <div class="feature-content">
              <h3 class="feature-title">{{ feature.title }}</h3>
              <p class="feature-description">{{ feature.description }}</p>
            </div>
          </div>
        </div>
        
        <div class="kit-specs">
          <h3 class="specs-title">硬件参数</h3>
          <div class="specs-list">
            <div v-for="spec in specs" :key="spec.label" class="spec-item">
              <span class="spec-label">{{ spec.label }}</span>
              <span class="spec-value">{{ spec.value }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { 
  Timer, 
  CircleCheck, 
  Connection, 
  Tools,
  Monitor,
  TrendCharts
} from '@element-plus/icons-vue'

const features = [
  {
    id: 1,
    icon: Timer,
    title: '长时佩戴零感设计',
    description: '持续佩戴 2-3 小时无不适感，专注你的开发工作'
  },
  {
    id: 2,
    icon: CircleCheck,
    title: '100% 适配率',
    description: '独立调节每条头带，精确适配你的头型'
  },
  {
    id: 3,
    icon: Connection,
    title: '实时质量检测',
    description: '毫秒级刷新，随时掌握每个电极的接触状态和信号质量'
  },
  {
    id: 4,
    icon: Tools,
    title: '精准问题定位',
    description: '清晰标识接触良好、接触不良、肌电干扰、无信号四种状态'
  },
  {
    id: 5,
    icon: Monitor,
    title: '即时反馈调整',
    description: '调整头带位置后立即看到效果，无需重启设备或软件'
  },
  {
    id: 6,
    icon: TrendCharts,
    title: '质量评分系统',
    description: '自动计算整体信号质量评分，确保数据采集达到最佳状态'
  }
]

const specs = [
  { label: '通道数', value: '7通道枕颞叶EEG数据记录' },
  { label: '采样率', value: '250Hz' },
  { label: '分辨率（ADC）', value: '24bit' },
  { label: '输入阻抗', value: '1TΩ' },
  { label: '带宽', value: '0.3-150Hz' },
  { label: '最小输入范围', value: '0.5μVrms' },
  { label: '硬件', value: 'ADS1299' }
]
</script>

<style lang="scss" scoped>
.developer-kit-section {
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

.kit-content {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 3rem;
}

.kit-features {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}

.feature-item {
  display: flex;
  gap: 1.5rem;
  background: white;
  padding: 1.5rem;
  border-radius: 12px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
  transition: all 0.3s;
  
  &:hover {
    transform: translateX(8px);
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }
}

.feature-icon {
  width: 60px;
  height: 60px;
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

.feature-content {
  flex: 1;
}

.feature-title {
  font-size: 1.25rem;
  font-weight: 600;
  margin-bottom: 0.5rem;
  color: #333;
}

.feature-description {
  font-size: 0.95rem;
  color: #666;
  line-height: 1.6;
}

.kit-specs {
  background: white;
  padding: 2.5rem;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.05);
}

.specs-title {
  font-size: 1.5rem;
  font-weight: 600;
  margin-bottom: 2rem;
  color: #333;
  padding-bottom: 1rem;
  border-bottom: 2px solid #667eea;
}

.specs-list {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.spec-item {
  display: flex;
  justify-content: space-between;
  padding: 1rem;
  background: #f8f9fa;
  border-radius: 8px;
  transition: all 0.3s;
  
  &:hover {
    background: #f0f1f3;
  }
}

.spec-label {
  font-weight: 500;
  color: #333;
}

.spec-value {
  font-weight: 600;
  color: #667eea;
}

@media (max-width: 968px) {
  .kit-content {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 768px) {
  .section-title {
    font-size: 2rem;
  }
  
  .feature-item {
    flex-direction: column;
    text-align: center;
  }
  
  .spec-item {
    flex-direction: column;
    gap: 0.5rem;
    text-align: center;
  }
}
</style>