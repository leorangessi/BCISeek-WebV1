<template>
  <div class="product-comparison-section">
    <div class="section-container">
      <h2 class="section-title">产品对比</h2>
      <p class="section-subtitle">ExpandableBCI 对比市场主流产品，性价比之选</p>
      
      <div class="comparison-table-wrapper">
        <table class="comparison-table">
          <thead>
            <tr>
              <th class="feature-col">特性</th>
              <th class="product-col">OpenBCI</th>
              <th class="product-col">Brduino</th>
              <th class="product-col highlight">ExpandableBCI</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td class="feature-name">通道数</td>
              <td>支持8、16通道</td>
              <td>8通道</td>
              <!-- <td class="highlight">支持8、16、32通道，自动扩展，无需烧录代码</td> -->
               <td class="highlight" style="font-weight: bold;">支持8、16、32通道，自动扩展，无需烧录代码</td>
            </tr>
            <tr>
              <td class="feature-name">采集精度</td>
              <td>24bit</td>
              <td>24bit</td>
              <td class="highlight">24bit</td>
            </tr>
            <tr>
              <td class="feature-name">通信方式</td>
              <td>蓝牙/WiFi/串口，需要烧录不同代码，购买不同配件</td>
              <td>蓝牙</td>
              <td class="highlight" style="font-weight: bold;">蓝牙/WiFi，无需烧录代码，使用配置工具配置通信模式</td>
            </tr>
            <tr>
              <td class="feature-name">采集频率</td>
              <td>WiFi：250/500/1000Hz<br>蓝牙：250Hz</td>
              <td>500Hz</td>
              <td class="highlight">WiFi：250/500/1000Hz<br>蓝牙：250Hz</td>
            </tr>
            <tr>
              <td class="feature-name">噪声</td>
              <td>≤1uVpp</td>
              <td>≤1.4uVpp</td>
              <td class="highlight">≤1uVpp</td>
            </tr>
            <tr>
              <td class="feature-name">共模抑制比</td>
              <td>≥110dB</td>
              <td>≥110dB</td>
              <td class="highlight">≥110dB</td>
            </tr>
            <tr>
              <td class="feature-name">上位机</td>
              <td>OpenBCI GUI</td>
              <td>OpenBCI GUI</td>
              <td class="highlight">OpenBCI GUI</td>
            </tr>
            <tr>
              <td class="feature-name">支持离线采集</td>
              <td>否，依赖电脑</td>
              <td>否，依赖电脑</td>
              <td class="highlight" style="font-weight: bold;">是，可不依赖电脑</td>
            </tr>
            <tr>
              <td class="feature-name">电源管理功能</td>
              <td>否，需外接电池</td>
              <td>是</td>
              <td class="highlight">是，自动提示电量低</td>
            </tr>
            <tr>
              <td class="feature-name">充电使用</td>
              <td>/</td>
              <td>充电2h，续航6h</td>
              <td class="highlight">充电2h，续航10h</td>
            </tr>
            <tr>
              <td class="feature-name">脑电帽搭配</td>
              <td>主推干电极，较重</td>
              <td>湿电极，轻便</td>
              <td class="highlight">干湿兼具，可选配弹簧电极、柔性电极，佩戴更为舒适</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
</script>

<style lang="scss" scoped>
.product-comparison-section {
  padding: 80px 20px;
  background: #f8f9fa;
}

.section-container {
  max-width: 1400px;
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
  margin-bottom: 3rem;
}

.comparison-table-wrapper {
  overflow-x: auto;
  background: white;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.comparison-table {
  width: 100%;
  border-collapse: collapse;
  font-size: 0.9rem;
  
  th, td {
    padding: 1rem 1.5rem;
    text-align: left;
    border-bottom: 1px solid #e0e0e0;
  }
  
  th {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    color: white;
    font-weight: 600;
    font-size: 1rem;
  }
  
  .feature-col {
    width: 15%;
    min-width: 120px;
  }
  
  .product-col {
    width: 28%;
    min-width: 200px;
    
    &.highlight {
      border-left: 3px solid #fff;
      border-right: 3px solid #fff;
      font-weight: 600;
      font-size: 1.1rem;
    }
  }
  
  td.highlight {
    background: rgba(102, 126, 234, 0.08);
    font-weight: 500;
  }
  
  .feature-name {
    font-weight: 600;
    color: #333;
    background: #fafafa;
  }
  
  tbody tr:hover {
    background: rgba(102, 126, 234, 0.02);
  }
  

}

@media (max-width: 968px) {
  .comparison-table {
    font-size: 0.8rem;
    
    th, td {
      padding: 0.75rem 1rem;
    }
  }
}
</style>
