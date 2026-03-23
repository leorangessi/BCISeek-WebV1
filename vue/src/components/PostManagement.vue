<template>
  <div class="post-management">
    <div class="toolbar">
      <el-button type="primary" @click="handleCreate">
        <el-icon><Plus /></el-icon>
        新建文章
      </el-button>
      <el-select v-model="visibilityFilter" placeholder="筛选可见性" style="width: 200px" @change="loadPosts">
        <el-option label="全部" value="" />
        <el-option label="公开" value="visitor" />
        <el-option label="用户可见" value="user" />
        <el-option label="仅管理员" value="admin" />
      </el-select>
      <el-input
        v-model="searchKeyword"
        placeholder="搜索文章标题"
        style="width: 300px"
        clearable
        @clear="loadPosts"
        @keyup.enter="loadPosts"
      >
        <template #prefix>
          <el-icon><Search /></el-icon>
        </template>
      </el-input>
    </div>

    <el-table
      v-loading="loading"
      :data="posts"
      style="width: 100%"
      stripe
    >
      <el-table-column prop="id" label="ID" width="80" />
      <el-table-column prop="title" label="标题" min-width="100" />
      <el-table-column label="可见性" width="120">
        <template #default="{ row }">
          <el-tag :type="getVisibilityType(row.visibility)" size="small">
            {{ getVisibilityLabel(row.visibility) }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="180">
        <template #default="{ row }">
          {{ formatDate(row.createTime) }}
        </template>
      </el-table-column>
      <el-table-column label="更新时间" width="180">
        <template #default="{ row }">
          {{ formatDate(row.updateTime) }}
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200" fixed="right">
        <template #default="{ row }">
          <el-button type="primary" size="small" @click="handleEdit(row)">
            编辑
          </el-button>
          <el-button type="danger" size="small" @click="handleDelete(row)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      v-model:current-page="currentPage"
      v-model:page-size="pageSize"
      :total="total"
      :page-sizes="[10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="loadPosts"
      @current-change="loadPosts"
      style="margin-top: 20px; justify-content: flex-end;"
    />

    <el-dialog
      v-model="dialogVisible"
      :title="dialogTitle"
      width="600px"
      @close="handleDialogClose"
    >
      <el-form
        ref="formRef"
        :model="form"
        :rules="rules"
        label-width="100px"
      >
        <el-form-item label="文章标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入文章标题" maxlength="100" show-word-limit />
        </el-form-item>
        <el-form-item label="可见性" prop="visibility">
          <el-select v-model="form.visibility" placeholder="请选择可见性">
            <el-option label="公开" value="visitor" />
            <el-option label="用户可见" value="user" />
            <el-option label="仅管理员" value="admin" />
          </el-select>
        </el-form-item>
        <el-form-item label="文章内容" prop="content">
          <el-input
            v-model="form.content"
            type="textarea"
            :rows="10"
            placeholder="请输入文章内容"
            maxlength="5000"
            show-word-limit
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="handleDialogClose">取消</el-button>
        <el-button type="primary" @click="handleSubmit" :loading="submitting">
          确定
        </el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, computed, onMounted } from 'vue'
import { ElMessage, ElMessageBox, type FormInstance, type FormRules } from 'element-plus'
import { Plus, Search } from '@element-plus/icons-vue'
import { postApi } from '../api'
import { useUserStore } from '../store/user'

const userStore = useUserStore()

const posts = ref([])
const loading = ref(false)
const dialogVisible = ref(false)
const submitting = ref(false)
const dialogTitle = ref('新建文章')
const visibilityFilter = ref('')
const searchKeyword = ref('')
const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(0)
const formRef = ref<FormInstance>()

const form = reactive({
  id: 0,
  title: '',
  content: '',
  visibility: 'visitor'
})

const rules: FormRules = {
  title: [
    { required: true, message: '请输入文章标题', trigger: 'blur' }
  ],
  content: [
    { required: true, message: '请输入文章内容', trigger: 'blur' }
  ],
  visibility: [
    { required: true, message: '请选择可见性', trigger: 'change' }
  ]
}

const loadPosts = async () => {
  try {
    loading.value = true
    
    let data
    if (visibilityFilter.value) {
      data = await postApi.getByVisibility(visibilityFilter.value)
    } else {
      data = await postApi.getAll()
    }
    
    if (searchKeyword.value) {
      data = data.filter((post: any) => 
        post.title.toLowerCase().includes(searchKeyword.value.toLowerCase())
      )
    }
    
    posts.value = data
    total.value = data.length
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
  return date.toLocaleString('zh-CN', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit'
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

const handleCreate = () => {
  dialogTitle.value = '新建文章'
  form.id = 0
  form.title = ''
  form.content = ''
  form.visibility = 'visitor'
  dialogVisible.value = true
}

const handleEdit = (row: any) => {
  dialogTitle.value = '编辑文章'
  form.id = row.id
  form.title = row.title
  form.content = row.content
  form.visibility = row.visibility
  dialogVisible.value = true
}

const handleDelete = async (row: any) => {
  try {
    await ElMessageBox.confirm(
      `确定要删除文章"${row.title}"吗？`,
      '提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }
    )
    
    await postApi.delete(row.id)
    ElMessage.success('删除成功')
    await loadPosts()
  } catch (error: any) {
    console.error('删除文章失败:', error)
    ElMessage.error('删除失败')
  }
}

const handleSubmit = async () => {
  if (!formRef.value) return

  try {
    await formRef.value.validate()
    submitting.value = true

    if (form.id) {
      await postApi.update(form.id, {
        title: form.title,
        content: form.content,
        visibility: form.visibility
      })
      ElMessage.success('更新成功')
    } else {
      const authorId = userStore.user?.id
      await postApi.create(form.title, form.content, form.visibility, authorId)
      ElMessage.success('创建成功')
    }

    dialogVisible.value = false
    await loadPosts()
  } catch (error: any) {
    console.error('提交失败:', error)
    ElMessage.error('提交失败')
  } finally {
    submitting.value = false
  }
}

const handleDialogClose = () => {
  dialogVisible.value = false
  formRef.value?.resetFields()
}

onMounted(() => {
  loadPosts()
})
</script>

<style lang="scss" scoped>
.post-management {
  padding: 20px;
}

.toolbar {
  display: flex;
  gap: 1rem;
  margin-bottom: 20px;
  flex-wrap: wrap;
}
</style>