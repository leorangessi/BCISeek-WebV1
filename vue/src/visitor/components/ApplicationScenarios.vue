<template>
  <div class="application-scenarios-section">
    <div class="section-container">
      <h2 class="section-title">应用场景</h2>
      <p class="section-subtitle">从科研到商业，从实验室到产品，全方位覆盖脑电应用需求</p>
      
      <div class="scenarios-grid">
        <div v-for="scenario in scenarios" :key="scenario.id" class="scenario-card">
          <div class="scenario-icon">
            <el-icon :size="40">
              <component :is="scenario.icon" />
            </el-icon>
          </div>
          <h3 class="scenario-title">{{ scenario.title }}</h3>
          <ul class="scenario-list">
            <li v-for="item in scenario.items" :key="item">{{ item }}</li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { 
  DataBoard, 
  Monitor, 
  MagicStick, 
  VideoPlay 
} from '@element-plus/icons-vue'

const scenarios = [
  {
    id: 1,
    icon: DataBoard,
    title: 'AI算法训练',
    items: [
      '高质量数据驱动深度学习模型',
      '专注度检测、情绪识别等实时AI应用',
      '大规模数据集采集支持模型训练与优化',
      '支持边缘计算与云端协同的混合架构'
    ]
  },
  {
    id: 2,
    icon: Monitor,
    title: '辅助医疗诊断',
    items: [
      '癫痫发作监测与预警系统',
      '睡眠障碍评估（失眠、睡眠呼吸暂停）',
      '认知功能评估（阿尔茨海默早筛）',
      '康复训练反馈（神经反馈治疗、脑卒中康复）'
    ]
  },
  {
    id: 3,
    icon: MagicStick,
    title: '创新应用',
    items: [
      '快速验证开放平台加速产品原型开发',
      '游戏与VR中的情绪自适应体验',
      '教育场景下的注意力追踪与反馈',
      '完整开发工具链，从概念到产品仅需数周'
    ]
  },
  {
    id: 4,
    icon: VideoPlay,
    title: '神经科学范式',
    items: [
      '事件相关电位（ERP): P300、N400、MMN等',
      'SSVEP稳态视觉诱发、运动想象（MI)范式',
      '睡眠分期、Alpha波调制等基础研究',
      '高时间精度保证，满足科研级数据质量要求'
    ]
  }
]
</script>

<style lang="scss" scoped>
.application-scenarios-section {
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

.scenarios-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 2rem;
}

.scenario-card {
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

.scenario-icon {
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

.scenario-title {
  font-size: 1.5rem;
  font-weight: 600;
  margin-bottom: 1.5rem;
  color: #333;
}

.scenario-list {
  list-style: none;
  padding: 0;
  margin: 0;
  
  li {
    padding: 0.5rem 0;
    padding-left: 1.5rem;
    position: relative;
    color: #666;
    font-size: 0.95rem;
    line-height: 1.6;
    
    &::before {
      content: '•';
      position: absolute;
      left: 0;
      color: #667eea;
      font-weight: bold;
    }
  }
}

@media (max-width: 768px) {
  .section-title {
    font-size: 2rem;
  }
  
  .scenarios-grid {
    grid-template-columns: 1fr;
  }
}
</style>