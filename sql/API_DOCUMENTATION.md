# 论坛系统后端 API 接口文档

## 基础信息

- **Base URL**: `http://localhost:8080`
- **Content-Type**: `application/json`
- **跨域支持**: 已启用 CORS

---

## 1. 用户管理接口

### 1.1 获取所有用户

- **URL**: `/api/users`
- **Method**: `GET`
- **Response**: 用户列表

**成功响应 (200)**:
```json
[
  {
    "id": 1,
    "username": "gen",
    "password": "12345",
    "role": "admin",
    "status": "offline",
    "createTime": "2026-03-09T12:59:46",
    "updateTime": "2026-03-09T12:59:46"
  }
]
```

---

### 1.2 根据ID获取用户

- **URL**: `/api/users/{id}`
- **Method**: `GET`
- **Path Variable**: `id` - 用户ID

**成功响应 (200)**:
```json
{
  "id": 1,
  "username": "gen",
  "password": "12345",
  "role": "admin",
  "status": "offline",
  "createTime": "2026-03-09T12:59:46",
  "updateTime": "2026-03-09T12:59:46"
}
```

**失败响应 (404)**: 用户不存在

---

### 1.3 创建用户

- **URL**: `/api/users`
- **Method**: `POST`
- **Request Body**:

```json
{
  "username": "newuser",
  "password": "password123",
  "role": "user",
  "status": "offline"
}
```

**字段说明**:
| 字段 | 类型 | 必填 | 说明 |
|------|------|------|------|
| username | String | 是 | 用户名，唯一 |
| password | String | 是 | 密码 |
| role | String | 否 | 角色：visitor/user/admin，默认visitor |
| status | String | 否 | 状态：online/offline，默认offline |

**成功响应 (201)**:
```json
{
  "id": 2,
  "username": "newuser",
  "password": "password123",
  "role": "user",
  "status": "offline",
  "createTime": "2026-03-09T13:00:00",
  "updateTime": "2026-03-09T13:00:00"
}
```

**失败响应 (409)**: 用户名已存在

---

### 1.4 更新用户

- **URL**: `/api/users/{id}`
- **Method**: `PUT`
- **Path Variable**: `id` - 用户ID
- **Request Body**: 同创建用户

**成功响应 (200)**: 更新后的用户信息

**失败响应 (404)**: 用户不存在

---

### 1.5 删除用户

- **URL**: `/api/users/{id}`
- **Method**: `DELETE`
- **Path Variable**: `id` - 用户ID

**成功响应 (204)**: 无内容

**失败响应 (404)**: 用户不存在

---

## 2. 文章管理接口

### 2.1 获取所有文章

- **URL**: `/api/posts`
- **Method**: `GET`

**成功响应 (200)**:
```json
[
  {
    "id": 1,
    "title": "文章标题",
    "content": "文章内容...",
    "visibility": "visitor",
    "createTime": "2026-03-09T13:00:00",
    "updateTime": "2026-03-09T13:00:00"
  }
]
```

---

### 2.2 根据ID获取文章

- **URL**: `/api/posts/{id}`
- **Method**: `GET`
- **Path Variable**: `id` - 文章ID

**成功响应 (200)**: 文章详情

**失败响应 (404)**: 文章不存在

---

### 2.3 获取作者的文章

- **URL**: `/api/posts/author/{authorId}`
- **Method**: `GET`
- **Path Variable**: `authorId` - 作者ID

**成功响应 (200)**: 该作者的所有文章列表

---

### 2.4 根据可见性获取文章

- **URL**: `/api/posts/visibility/{visibility}`
- **Method**: `GET`
- **Path Variable**: `visibility` - 可见性级别
  - `visitor` - 对所有人可见
  - `user` - 对登录用户可见
  - `admin` - 仅对管理员可见

**成功响应 (200)**: 符合条件的文章列表

---

### 2.5 创建文章

- **URL**: `/api/posts`
- **Method**: `POST`
- **Request Body**:

```json
{
  "title": "文章标题",
  "content": "文章内容...",
  "visibility": "visitor"
}
```

**字段说明**:
| 字段 | 类型 | 必填 | 说明 |
|------|------|------|------|
| title | String | 是 | 文章标题，最大100字符 |
| content | String | 是 | 文章内容 |
| visibility | String | 否 | 可见性：visitor/user/admin，默认visitor |

**成功响应 (201)**: 创建的文章信息

---

### 2.6 更新文章

- **URL**: `/api/posts/{id}`
- **Method**: `PUT`
- **Path Variable**: `id` - 文章ID
- **Request Body**: 同创建文章

**成功响应 (200)**: 更新后的文章信息

**失败响应 (404)**: 文章不存在

---

### 2.7 删除文章

- **URL**: `/api/posts/{id}`
- **Method**: `DELETE`
- **Path Variable**: `id` - 文章ID

**成功响应 (204)**: 无内容

**失败响应 (404)**: 文章不存在

---

## 3. 评论管理接口

### 3.1 获取所有评论

- **URL**: `/api/comments`
- **Method**: `GET`

**成功响应 (200)**:
```json
[
  {
    "id": 1,
    "content": "评论内容...",
    "createTime": "2026-03-09T13:00:00",
    "updateTime": "2026-03-09T13:00:00"
  }
]
```

---

### 3.2 根据ID获取评论

- **URL**: `/api/comments/{id}`
- **Method**: `GET`
- **Path Variable**: `id` - 评论ID

**成功响应 (200)**: 评论详情

**失败响应 (404)**: 评论不存在

---

### 3.3 获取文章的评论

- **URL**: `/api/comments/post/{postId}`
- **Method**: `GET`
- **Path Variable**: `postId` - 文章ID

**成功响应 (200)**: 该文章的所有评论列表（按时间倒序）

---

### 3.4 获取作者的评论

- **URL**: `/api/comments/author/{authorId}`
- **Method**: `GET`
- **Path Variable**: `authorId` - 作者ID

**成功响应 (200)**: 该作者的所有评论列表

---

### 3.5 创建评论

- **URL**: `/api/comments`
- **Method**: `POST`
- **Request Body**:

```json
{
  "content": "评论内容..."
}
```

**字段说明**:
| 字段 | 类型 | 必填 | 说明 |
|------|------|------|------|
| content | String | 是 | 评论内容 |

**成功响应 (201)**: 创建的评论信息

---

### 3.6 更新评论

- **URL**: `/api/comments/{id}`
- **Method**: `PUT`
- **Path Variable**: `id` - 评论ID
- **Request Body**: 同创建评论

**成功响应 (200)**: 更新后的评论信息

**失败响应 (404)**: 评论不存在

---

### 3.7 删除评论

- **URL**: `/api/comments/{id}`
- **Method**: `DELETE`
- **Path Variable**: `id` - 评论ID

**成功响应 (204)**: 无内容

**失败响应 (404)**: 评论不存在

---

## 4. 权限管理接口

### 4.1 获取所有权限规则

- **URL**: `/api/permissions`
- **Method**: `GET`

**成功响应 (200)**:
```json
[
  {
    "id": 1,
    "role": "visitor",
    "resourceType": "post",
    "operations": "query",
    "visibleRoles": "visitor",
    "description": "游客仅可查看对游客可见的文章"
  },
  {
    "id": 2,
    "role": "visitor",
    "resourceType": "comment",
    "operations": "query",
    "visibleRoles": "visitor",
    "description": "游客仅可查看对游客可见文章的评论"
  },
  {
    "id": 3,
    "role": "user",
    "resourceType": "post",
    "operations": "query,add,delete_self,update_self",
    "visibleRoles": "visitor,user",
    "description": "普通用户可查看非仅管理员可见的文章，增删改自己的文章"
  },
  {
    "id": 4,
    "role": "user",
    "resourceType": "comment",
    "operations": "query,add,delete_self,update_self",
    "visibleRoles": "visitor,user",
    "description": "普通用户可查看非仅管理员可见文章的评论，增删改自己的评论"
  },
  {
    "id": 5,
    "role": "admin",
    "resourceType": "post",
    "operations": "query,add,delete,update",
    "visibleRoles": "visitor,user,admin",
    "description": "管理员可查看所有文章，增删改所有文章，可修改可见范围"
  },
  {
    "id": 6,
    "role": "admin",
    "resourceType": "comment",
    "operations": "query,add,delete,update",
    "visibleRoles": "visitor,user,admin",
    "description": "管理员可查看所有评论，增删改所有评论"
  }
]
```

---

### 4.2 根据ID获取权限规则

- **URL**: `/api/permissions/{id}`
- **Method**: `GET`
- **Path Variable**: `id` - 权限ID

**成功响应 (200)**: 权限规则详情

**失败响应 (404)**: 权限规则不存在

---

### 4.3 根据角色获取权限规则

- **URL**: `/api/permissions/role/{role}`
- **Method**: `GET`
- **Path Variable**: `role` - 角色名称
  - `visitor` - 游客
  - `user` - 普通用户
  - `admin` - 管理员

**成功响应 (200)**: 该角色的所有权限规则

---

### 4.4 根据角色和资源类型获取权限规则

- **URL**: `/api/permissions/role/{role}/resource/{resourceType}`
- **Method**: `GET`
- **Path Variables**:
  - `role` - 角色名称
  - `resourceType` - 资源类型（post/comment）

**成功响应 (200)**: 对应的权限规则

**失败响应 (404)**: 权限规则不存在

---

### 4.5 创建权限规则

- **URL**: `/api/permissions`
- **Method**: `POST`
- **Request Body**:

```json
{
  "role": "user",
  "resourceType": "post",
  "operations": "query,add",
  "visibleRoles": "visitor,user",
  "description": "权限描述"
}
```

**字段说明**:
| 字段 | 类型 | 必填 | 说明 |
|------|------|------|------|
| role | String | 是 | 角色：visitor/user/admin |
| resourceType | String | 是 | 资源类型：post/comment |
| operations | String | 是 | 允许的操作，逗号分隔 |
| visibleRoles | String | 是 | 可见角色，逗号分隔 |
| description | String | 否 | 权限描述 |

**成功响应 (201)**: 创建的权限规则

---

### 4.6 更新权限规则

- **URL**: `/api/permissions/{id}`
- **Method**: `PUT`
- **Path Variable**: `id` - 权限ID
- **Request Body**: 同创建权限规则

**成功响应 (200)**: 更新后的权限规则

**失败响应 (404)**: 权限规则不存在

---

### 4.7 删除权限规则

- **URL**: `/api/permissions/{id}`
- **Method**: `DELETE`
- **Path Variable**: `id` - 权限ID

**成功响应 (204)**: 无内容

**失败响应 (404)**: 权限规则不存在

---

## 5. 角色权限说明

### 5.1 游客 (visitor)
- **可查看**: visibility = visitor 的文章和评论
- **可操作**: 仅查询 (query)
- **不可操作**: 创建、修改、删除

### 5.2 普通用户 (user)
- **可查看**: visibility = visitor 或 user 的文章和评论
- **可操作**: 
  - 查询 (query)
  - 创建自己的文章/评论 (add)
  - 删除自己的文章/评论 (delete_self)
  - 修改自己的文章/评论 (update_self)
- **不可操作**: 修改他人内容、修改可见范围

### 5.3 管理员 (admin)
- **可查看**: 所有文章和评论（visibility = visitor/user/admin）
- **可操作**:
  - 查询 (query)
  - 创建 (add)
  - 删除任意内容 (delete)
  - 修改任意内容 (update)
  - 修改文章可见范围

---

## 6. 通用响应状态码

| 状态码 | 说明 |
|--------|------|
| 200 | 请求成功 |
| 201 | 创建成功 |
| 204 | 删除成功，无返回内容 |
| 400 | 请求参数错误 |
| 404 | 资源不存在 |
| 409 | 资源冲突（如用户名已存在） |
| 500 | 服务器内部错误 |

---

## 7. 测试示例

### 使用 curl 测试

```bash
# 获取所有用户
curl http://localhost:8080/api/users

# 创建用户
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"username":"test","password":"123456","role":"user"}'

# 获取所有权限
curl http://localhost:8080/api/permissions

# 获取所有文章
curl http://localhost:8080/api/posts

# 获取所有评论
curl http://localhost:8080/api/comments
```

### 使用 PowerShell 测试

```powershell
# 获取所有用户
Invoke-WebRequest -Uri "http://localhost:8080/api/users" -UseBasicParsing

# 创建用户
Invoke-WebRequest -Uri "http://localhost:8080/api/users" -Method POST `
  -ContentType "application/json" `
  -Body '{"username":"test","password":"123456","role":"user"}'
```

---

**文档生成时间**: 2026-03-09
**版本**: 1.0.0
