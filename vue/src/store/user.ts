import { defineStore } from 'pinia'
import { ref } from 'vue'

export interface User {
  id: number
  username: string
  role: 'visitor' | 'user' | 'admin'
  status: 'online' | 'offline'
  createTime: string
  updateTime: string
}

export const useUserStore = defineStore('user', () => {
  const user = ref<User | null>(null)
  const token = ref<string | null>(localStorage.getItem('token'))

  const setUser = (userData: User) => {
    user.value = userData
    localStorage.setItem('user', JSON.stringify(userData))
  }

  const setToken = (newToken: string) => {
    token.value = newToken
    localStorage.setItem('token', newToken)
  }

  const logout = () => {
    user.value = null
    token.value = null
    localStorage.removeItem('user')
    localStorage.removeItem('token')
  }

  const initUser = () => {
    const savedUser = localStorage.getItem('user')
    if (savedUser) {
      user.value = JSON.parse(savedUser)
    }
  }

  const isLoggedIn = () => {
    return !!user.value
  }

  const isAdmin = () => {
    return user.value?.role === 'admin'
  }

  const isUser = () => {
    return user.value?.role === 'user'
  }

  const isVisitor = () => {
    return !user.value || user.value?.role === 'visitor'
  }

  return {
    user,
    token,
    setUser,
    setToken,
    logout,
    initUser,
    isLoggedIn,
    isAdmin,
    isUser,
    isVisitor
  }
})