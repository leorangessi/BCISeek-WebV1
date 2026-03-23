<template>
  <div class="product-features-section">
    <div class="section-container">
      <h2 class="section-title">ExpandableBCI 核心优势</h2>
      <p class="section-subtitle">为什么选择我们的产品</p>
      
      <div class="features-grid">
        <div v-for="feature in features" :key="feature.id" class="feature-card">
          <div class="feature-icon">
            <el-icon :size="40">
              <component :is="feature.icon" />
            </el-icon>
          </div>
          <h3 class="feature-title">{{ feature.title }}</h3>
          <p class="feature-description">{{ feature.description }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { 
  Money, 
  Expand, 
  Tools, 
  Lightning,
  Timer,
  User
} from '@element-plus/icons-vue'

const features = [
  {
    id: 1,
    icon: Expand,
    title: '自动扩展通道',
    description: '支持8、16、32通道自动扩展，无需烧录代码，即插即用'
  },
   {
    id: 2,
    icon: Timer,
    title: '离线采集',
    description: '支持离线采集，可不依赖电脑，使用场景更加灵活'
  },
  {
    id: 3,
    icon: Tools,
    title: '配置工具支持',
    description: '无需编程，使用配置工具即可配置通信模式，降低使用门槛'
  },
  {
    id: 4,
    icon: User,
    title: '舒适佩戴',
    description: '干湿电极兼具，可选配弹簧电极、柔性电极，佩戴舒适不失精度'
  },
   {
    id: 5,
    icon: Lightning,
    title: '超长续航',
    description: '充电2小时，续航10小时，比Brduino多4小时，自动提示电量低'
  },
  {
    id: 6,
    icon: Money,
    title: '性价比高',
    description: '打造千元级设备，性价比之王'
  }
  
]
</script>

<style lang="scss" scoped>
.product-features-section {
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

.features-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(350px, 1fr));
  gap: 2rem;
}

.feature-card {
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

.feature-icon {
  width: 70px;
  height: 70px;
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

.feature-title {
  font-size: 1.5rem;
  font-weight: 600;
  margin-bottom: 1rem;
  color: #333;
}

.feature-description {
  font-size: 1rem;
  color: #666;
  line-height: 1.6;
}

@media (max-width: 768px) {
  .section-title {
    font-size: 2rem;
  }
  
  .features-grid {
    grid-template-columns: 1fr;
  }
}
</style>