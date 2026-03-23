const BASE_URL = 'http://localhost:8080/api'

interface Post {
  id: number
  title: string
  content: string
  visibility: 'visitor' | 'user' | 'admin'
  createTime: string
  updateTime: string
}

interface User {
  id: number
  username: string
  password: string
  role: 'visitor' | 'user' | 'admin'
  status: 'online' | 'offline'
  createTime: string
  updateTime: string
}

export const api = {
  async get<T>(url: string, options?: RequestInit): Promise<T> {
    const response = await fetch(`${BASE_URL}${url}`, {
      ...options,
      headers: {
        'Content-Type': 'application/json',
        ...options?.headers
      }
    })
    
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`)
    }
    
    return response.json()
  },

  async post<T>(url: string, data: any): Promise<T> {
    return this.get<T>(url, {
      method: 'POST',
      body: JSON.stringify(data)
    })
  },

  async put<T>(url: string, data: any): Promise<T> {
    return this.get<T>(url, {
      method: 'PUT',
      body: JSON.stringify(data)
    })
  },

  async delete(url: string): Promise<void> {
    const response = await fetch(`${BASE_URL}${url}`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json'
      }
    })
    
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`)
    }
    
    // 204 No Content 不需要解析响应体
    return
  }
}

export const userApi = {
  login: async (username: string, password: string) => {
    const users = await api.get<User[]>('/users')
    const user = users.find(u => u.username === username && u.password === password)
    if (user) {
      return user
    }
    throw new Error('用户名或密码错误')
  },

  register: async (username: string, password: string) => {
    return api.post<User>('/users', { username, password, role: 'user', status: 'offline' })
  },

  getAllUsers: async () => {
    return api.get<User[]>('/users')
  }
}

export const postApi = {
  getAll: async () => {
    return api.get<Post[]>('/posts')
  },

  getByVisibility: async (visibility: string) => {
    return api.get<Post[]>(`/posts/visibility/${visibility}`)
  },

  create: async (title: string, content: string, visibility: string = 'visitor', authorId?: number) => {
    return api.post<Post>('/posts', { title, content, visibility, authorId })
  },

  update: async (id: number, data: Partial<Post>) => {
    return api.put<Post>(`/posts/${id}`, data)
  },

  delete: async (id: number) => {
    return api.delete(`/posts/${id}`)
  }
}

export const commentApi = {
  getAll: async () => {
    return api.get('/comments')
  },

  getByPostId: async (postId: number) => {
    return api.get(`/comments/post/${postId}`)
  },

  create: async (content: string, postId: number) => {
    return api.post('/comments', { content })
  }
}