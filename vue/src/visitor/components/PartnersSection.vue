<template>
  <div class="partners-section">
    <div class="section-container">
      <h2 class="section-title">合作伙伴</h2>
      <p class="section-subtitle">与顶尖高校和研究机构携手共进</p>
      
      <div class="partners-grid">
        <div v-for="partner in partners" :key="partner.id" class="partner-card">
          <div class="partner-logo">
            <img 
              :src="partner.logo" 
              :alt="partner.name" 
              class="logo-image"
            />
          </div>
          <h3 class="partner-name">{{ partner.name }}</h3>
          <p class="partner-description">{{ partner.description }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
const partners = [
  {
    id: 1,
    name: '四川大学',
    logo: '/images/partners/R-C.png',
    description: '教育部直属全国重点大学，国家"双一流"建设高校'
  },
  {
    id: 2,
    name: '西南交通大学',
    logo: '/images/partners/OIP-C.jpg',
    description: '教育部直属全国重点大学，"211工程"重点建设高校'
  },
  {
    id: 3,
    name: 'Simon Fraser University',
    logo: '/images/partners/OIP-C.pnd.webp',
    description: '加拿大顶尖脑科学研究机构，专注于脑磁图技术研究'
  }
]
</script>

<style lang="scss" scoped>
.partners-section {
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

.partners-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 2rem;
}

.partner-card {
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

.partner-logo {
  margin-bottom: 1.5rem;
  height: 100px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.logo-image {
  max-width: 120px;
  max-height: 80px;
  object-fit: contain;
}

.partner-name {
  font-size: 1.5rem;
  font-weight: 600;
  margin-bottom: 1rem;
  color: #333;
}

.partner-description {
  font-size: 0.95rem;
  color: #666;
  line-height: 1.6;
}

@media (max-width: 768px) {
  .section-title {
    font-size: 2rem;
  }
  
  .partners-grid {
    grid-template-columns: 1fr;
  }
  
  .logo-image {
    max-width: 100px;
    max-height: 60px;
  }
}
</style>
