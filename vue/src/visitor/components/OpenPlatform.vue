<template>
  <div class="open-platform-section">
    <div class="section-container">
      <h2 class="section-title">开放平台</h2>
      <p class="section-subtitle">完全开放，由你创造</p>
      
      <div class="platform-grid">
        <div v-for="feature in platformFeatures" :key="feature.id" class="platform-card">
          <div class="platform-icon">
            <el-icon :size="48">
              <component :is="feature.icon" />
            </el-icon>
          </div>
          <h3 class="platform-title">{{ feature.title }}</h3>
          <p class="platform-description">{{ feature.description }}</p>
          <div class="platform-details">
            <div v-for="detail in feature.details" :key="detail" class="detail-item">
              {{ detail }}
            </div>
          </div>
        </div>
      </div>
      
      <div class="platform-code">
        <div class="code-header">
          <span class="code-title">快速开始</span>
          <el-button type="primary" size="small" @click="copyCode">复制</el-button>
        </div>
        <pre class="code-content"><code>pip install neuradock
from neuradock import NeuraDock

device = NeuraDock()
device.connect()

while True:
    data = device.get_data()
    print(f"EEG Data: {data}")</code></pre>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { 
  DataBoard, 
  Tools, 
  Document, 
  ChatDotRound 
} from '@element-plus/icons-vue'

const platformFeatures = [
  {
    id: 1,
    icon: DataBoard,
    title: '原始数据开放',
    description: '完整的原始EEG数据流，无任何预处理或过滤，让你完全掌控数据处理流程',
    details: [
      '原始数据流实时获取',
      '自定义采样率配置',
      '多通道数据同步采集'
    ]
  },
  {
    id: 2,
    icon: Tools,
    title: '硬件端口开放',
    description: '直接访问硬件寄存器，自定义采样率、增益、滤波器等参数',
    details: [
      'UART · GPIO · I2C 接口',
      '硬件寄存器直接访问',
      '自定义滤波器配置'
    ]
  },
  {
    id: 3,
    icon: Document,
    title: '开源 SDK & API',
    description: '跨平台SDK支持，Python、JavaScript、C++全覆盖，详尽的开发文档',
    details: [
      'Python SDK',
      'JavaScript SDK',
      'C++ SDK',
      '完整API文档'
    ]
  },
  {
    id: 4,
    icon: ChatDotRound,
    title: '开发者中心',
    description: '完整稳定示例项目、视频教程、开发者论坛、工具库、技术支持',
    details: [
      '20+ 开源示例项目',
      '系统化学习路径',
      '开发者社区论坛',
      '24小时技术支持'
    ]
  }
]

const copyCode = () => {
  const code = `pip install neuradock
from neuradock import NeuraDock

device = NeuraDock()
device.connect()

while True:
    data = device.get_data()
    print(f"EEG Data: {data}")`
  
  navigator.clipboard.writeText(code)
}
</script>

<style lang="scss" scoped>
.open-platform-section {
  padding: 80px 20px;
  background: white;
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

.platform-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 2rem;
  margin-bottom: 4rem;
}

.platform-card {
  background: #f8f9fa;
  padding: 2.5rem;
  border-radius: 12px;
  transition: all 0.3s;
  
  &:hover {
    background: white;
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
    transform: translateY(-4px);
  }
}

.platform-icon {
  width: 80px;
  height: 80px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 1.5rem;
  
  :deep(.el-icon) {
    color: white;
  }
}

.platform-title {
  font-size: 1.5rem;
  font-weight: 600;
  margin-bottom: 1rem;
  color: #333;
}

.platform-description {
  font-size: 1rem;
  color: #666;
  line-height: 1.6;
  margin-bottom: 1.5rem;
}

.platform-details {
  display: flex;
  flex-direction: column;
  gap: 0.75rem;
}

.detail-item {
  padding: 0.75rem 1rem;
  background: white;
  border-radius: 6px;
  font-size: 0.9rem;
  color: #666;
  border-left: 3px solid #667eea;
}

.platform-code {
  background: #1e1e1e;
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.code-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 1.5rem;
  background: #2d2d2d;
  border-bottom: 1px solid #3d3d3d;
}

.code-title {
  color: #fff;
  font-weight: 500;
}

.code-content {
  padding: 1.5rem;
  margin: 0;
  overflow-x: auto;
  
  code {
    color: #d4d4d4;
    font-family: 'Courier New', monospace;
    font-size: 0.9rem;
    line-height: 1.6;
  }
}

@media (max-width: 768px) {
  .section-title {
    font-size: 2rem;
  }
  
  .platform-grid {
    grid-template-columns: 1fr;
  }
  
  .code-content {
    padding: 1rem;
    
    code {
      font-size: 0.8rem;
    }
  }
}
</style>