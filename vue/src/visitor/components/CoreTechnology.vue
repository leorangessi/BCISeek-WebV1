<template>
  <div class="core-technology-section">
    <div class="section-container">
      <h2 class="section-title">技术参数</h2>
      <p class="section-subtitle">ExpandableBCI 专业技术指标</p>
      
      <div class="tech-grid">
        <div v-for="tech in technologies" :key="tech.id" class="tech-card">
          <div class="tech-icon">
            <el-icon :size="48">
              <component :is="tech.icon" />
            </el-icon>
          </div>
          <h3 class="tech-title">{{ tech.title }}</h3>
          <p class="tech-value">{{ tech.value }}</p>
          <p class="tech-description">{{ tech.description }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { 
  Cpu, 
  DataLine, 
  Connection, 
  Microphone,
  Filter,
  Monitor
} from '@element-plus/icons-vue'

const technologies = [
  {
    id: 1,
    icon: Cpu,
    title: '采集精度',
    value: '24bit',
    description: '高精度ADC，确保数据采集质量'
  },
  {
    id: 2,
    icon: DataLine,
    title: '采集频率',
    value: '最高1000Hz',
    description: 'WiFi模式下支持250/500/1000Hz'
  },
  {
    id: 3,
    icon: Connection,
    title: '通信方式',
    value: '蓝牙/WiFi',
    description: '双模通信，配置工具一键切换'
  },
  {
    id: 4,
    icon: Microphone,
    title: '噪声控制',
    value: '≤1uVpp',
    description: '低噪声设计，媲美OpenBCI'
  },
  {
    id: 5,
    icon: Filter,
    title: '共模抑制比',
    value: '≥110dB',
    description: '优秀的共模抑制能力'
  },
  {
    id: 6,
    icon: Monitor,
    title: '上位机支持',
    value: 'OpenBCI GUI',
    description: '兼容主流脑电软件生态'
  }
]
</script>

<style lang="scss" scoped>
.core-technology-section {
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

.tech-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 2rem;
}

.tech-card {
  background: white;
  padding: 2.5rem;
  border-radius: 12px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
  transition: all 0.3s;
  text-align: center;
  
  &:hover {
    transform: translateY(-8px);
    box-shadow: 0 12px 24px rgba(0, 0, 0, 0.1);
  }
}

.tech-icon {
  width: 80px;
  height: 80px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0 auto 1.5rem;
  
  :deep(.el-icon) {
    color: white;
  }
}

.tech-title {
  font-size: 1.25rem;
  font-weight: 600;
  margin-bottom: 0.5rem;
  color: #333;
}

.tech-value {
  font-size: 1.5rem;
  font-weight: 700;
  color: #667eea;
  margin-bottom: 0.5rem;
}

.tech-description {
  font-size: 0.95rem;
  color: #666;
  line-height: 1.6;
}

@media (max-width: 768px) {
  .section-title {
    font-size: 2rem;
  }
  
  .tech-grid {
    grid-template-columns: 1fr;
  }
}
</style>