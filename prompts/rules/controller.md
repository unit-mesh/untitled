# Spring Boot Controller 开发规范

## 基本原则
1. 保持Controller简洁，只处理HTTP请求和响应
2. 业务逻辑应放在Service层
3. 每个Controller方法应只做一件事

## 命名规范
- 类名: `XxxController` (如 `UserController`)
- 路径: 使用复数形式 (`/api/users`)
