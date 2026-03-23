<template>
  <div class="community-page">
    <VisitorNavbar />
    
    <div class="hero-section">
      <div class="hero-content">
        <h1 class="hero-title">开发者社区</h1>
        <p class="hero-subtitle">加入探脑科技开发者社区，分享经验，共同成长</p>
      </div>
    </div>

    <div class="content-section">
      <div class="container">
        <div class="community-content">
          <div class="main-content">
            <div class="section">
              <div class="section-header">
                <h2 class="section-title">热门文章</h2>
              </div>
              <div class="articles-list">
                <el-skeleton v-if="loading" :rows="3" animated />
                <el-empty v-else-if="articles.length === 0" description="暂无文章" />
                <div v-else v-for="article in articles" :key="article.id" class="article-card" @click="handleReadArticle(article)">
                  <div class="article-header">
                    <h3 class="article-title">{{ article.title }}</h3>
                    <span class="article-date">{{ formatDate(article.createTime) }}</span>
                  </div>
                  <p class="article-excerpt">{{ article.content.substring(0, 100) }}...</p>
                  <div class="article-footer">
                    <el-tag :type="getVisibilityType(article.visibility)" size="small">
                      {{ getVisibilityLabel(article.visibility) }}
                    </el-tag>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="sidebar">
            <div class="sidebar-section">
              <h3 class="sidebar-title">社区导航</h3>
              <ul class="nav-list">
                <li class="nav-item active">
                  <el-icon><House /></el-icon>
                  社区首页
                </li>
                <li class="nav-item">
                  <el-icon><Document /></el-icon>
                  技术文档
                </li>
                <li class="nav-item">
                  <el-icon><ChatDotRound /></el-icon>
                  论坛讨论
                </li>
                <li class="nav-item">
                  <el-icon><Edit /></el-icon>
                  代码示例
                </li>
                <li class="nav-item">
                  <el-icon><VideoPlay /></el-icon>
                  视频教程
                </li>
              </ul>
            </div>

            <div class="sidebar-section">
              <h3 class="sidebar-title">热门标签</h3>
              <div class="tags-list">
                <el-tag v-for="tag in hotTags" :key="tag" size="small" effect="plain">
                  {{ tag }}
                </el-tag>
              </div>
            </div>

            <div class="sidebar-section">
              <h3 class="sidebar-title">快速操作</h3>
              <div class="quick-actions">
                <el-button type="primary" @click="handleCreateArticle" v-if="userStore.isLoggedIn()">
                  发布文章
                </el-button>
                <el-button @click="handleLogin" v-else>
                  登录后发布
                </el-button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <ContactSection />
    
    <!-- 文章详情浮窗 -->
    <ElDialog
      v-model="showArticleDialog"
      :title="currentArticle?.title || '文章详情'"
      width="800px"
      destroy-on-close
    >
      <div v-if="currentArticle" class="article-detail">
        <div class="article-meta">
          <span class="article-date">{{ formatDate(currentArticle.createTime) }}</span>
          <el-tag :type="getVisibilityType(currentArticle.visibility)" size="small" style="margin-left: 1rem;">
            {{ getVisibilityLabel(currentArticle.visibility) }}
          </el-tag>
        </div>
        <div class="article-content">
          {{ currentArticle.content }}
        </div>
      </div>
      <div v-else>
        文章加载中...
      </div>
    </ElDialog>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import VisitorNavbar from './VisitorNavbar.vue'
import ContactSection from './ContactSection.vue'
import { House, Document, ChatDotRound, Edit, VideoPlay, Close } from '@element-plus/icons-vue'
import { ElMessage, ElDialog } from 'element-plus'
import { postApi } from '../../api'
import { useUserStore } from '../../store/user'

const router = useRouter()
const userStore = useUserStore()

const articles = ref([])
const loading = ref(true)
const hotTags = ref(['脑机接口', 'Python', '信号处理', '医疗应用', '人工智能'])

// 文章详情浮窗
const showArticleDialog = ref(false)
const currentArticle = ref(null)

const loadArticles = async () => {
  try {
    loading.value = true
    
    const data = await postApi.getAll()
    
    // 根据用户角色过滤文章
    articles.value = data.filter((article: any) => {
      const visibility = article.visibility
      if (userStore.isAdmin()) {
        // 管理员可以看到所有文章
        return true
      } else if (userStore.isUser()) {
        // 用户可以看到公开和用户可见的文章
        return visibility === 'visitor' || visibility === 'user'
      } else {
        // 游客只能看到公开文章
        return visibility === 'visitor'
      }
    })
  } catch (error: any) {
    console.error('加载文章失败:', error)
    ElMessage.error('加载文章失败')
  } finally {
    loading.value = false
  }
}

const formatDate = (dateString: string) => {
  if (!dateString) return ''
  const date = new Date(dateString)
  return date.toLocaleDateString('zh-CN', {
    year: 'numeric',
    month: 'long',
    day: 'numeric'
  })
}

const getVisibilityLabel = (visibility: string) => {
  const labels: Record<string, string> = {
    'visitor': '公开',
    'user': '用户可见',
    'admin': '仅管理员'
  }
  return labels[visibility] || '未知'
}

const getVisibilityType = (visibility: string) => {
  const types: Record<string, string> = {
    'visitor': 'success',
    'user': 'warning',
    'admin': 'danger'
  }
  return types[visibility] || 'info'
}

const handleReadArticle = (article: any) => {
  currentArticle.value = article
  showArticleDialog.value = true
}

const handleCreateArticle = () => {
  ElMessage.info('文章发布功能开发中...')
}

const handleLogin = () => {
  router.push('/login')
}

onMounted(() => {
  userStore.initUser()
  loadArticles()
})
</script>

<style lang="scss" scoped>
.community-page {
  min-height: 100vh;
  background: #fff;
}

.hero-section {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 100px 20px;
  text-align: center;
  color: white;
}

.hero-content {
  max-width: 1200px;
  margin: 0 auto;
}

.hero-title {
  font-size: 2.5rem;
  font-weight: 700;
  margin-bottom: 1rem;
  line-height: 1.2;
}

.hero-subtitle {
  font-size: 1.125rem;
  margin-bottom: 0.1rem;
  opacity: 0.9;
  line-height: 1.6;
}

.content-section {
  padding: 80px 20px;
  background: #f8f9fa;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
}

.community-content {
  display: grid;
  grid-template-columns: 1fr 320px;
  gap: 2rem;
}

.main-content {
  display: flex;
  flex-direction: column;
  gap: 3rem;
}

.section {
  background: white;
  padding: 2rem;
  border-radius: 12px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
}

.section-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.5rem;
}

.section-title {
  font-size: 1.5rem;
  font-weight: 700;
  color: #333;
  border-bottom: 2px solid #667eea;
  padding-bottom: 0.5rem;
}

.articles-list {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}

.article-card {
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  padding: 1.5rem;
  transition: all 0.3s;
  cursor: pointer;
  
  &:hover {
    border-color: #667eea;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.08);
  }
}

.article-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 1rem;
}

.article-title {
  font-size: 1.1rem;
  font-weight: 600;
  color: #333;
  margin: 0;
  flex: 1;
  
  &:hover {
    color: #667eea;
  }
}

.article-date {
  font-size: 0.85rem;
  color: #999;
  white-space: nowrap;
  margin-left: 1rem;
}

.article-excerpt {
  font-size: 0.95rem;
  color: #666;
  line-height: 1.6;
  margin-bottom: 1rem;
}

.article-footer {
  display: flex;
  align-items: center;
}

.sidebar {
  display: flex;
  flex-direction: column;
  gap: 2rem;
}

.sidebar-section {
  background: white;
  padding: 1.5rem;
  border-radius: 12px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
}

.sidebar-title {
  font-size: 1.1rem;
  font-weight: 600;
  margin-bottom: 1rem;
  color: #333;
  border-bottom: 1px solid #e0e0e0;
  padding-bottom: 0.5rem;
}

.nav-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.nav-item {
  display: flex;
  align-items: center;
  padding: 0.75rem 1rem;
  margin-bottom: 0.5rem;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.3s;
  
  &:hover {
    background: #f0f0f0;
    color: #667eea;
  }
  
  &.active {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    color: white;
  }
  
  :deep(.el-icon) {
    margin-right: 0.75rem;
  }
}

.tags-list {
  display: flex;
  flex-wrap: wrap;
  gap: 0.5rem;
}

.quick-actions {
  display: flex;
  flex-direction: column;
  gap: 0.75rem;
}

@media (max-width: 768px) {
  .community-content {
    grid-template-columns: 1fr;
  }
  
  .hero-title {
    font-size: 2rem;
  }
  
  .content-section {
    padding: 40px 20px;
  }
}

/* 文章详情浮窗样式 */
.article-detail {
  padding: 1rem 0;
}

.article-meta {
  margin-bottom: 1.5rem;
  padding-bottom: 1rem;
  border-bottom: 1px solid #e0e0e0;
  display: flex;
  align-items: center;
}

.article-content {
  font-size: 1rem;
  line-height: 1.8;
  color: #333;
  white-space: pre-wrap;
  word-break: break-word;
}

/* 响应式调整 */
@media (max-width: 768px) {
  :deep(.el-dialog) {
    width: 90% !important;
    margin: 20px auto !important;
  }
}
</style>