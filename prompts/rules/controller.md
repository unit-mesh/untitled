# Spring Boot Controller 开发规范

## 基本原则
1. 保持Controller简洁，只处理HTTP请求和响应
2. 业务逻辑应放在Service层
3. 每个Controller方法应只做一件事

## 命名规范
- 类名: `XxxController` (如 `UserController`)
- 路径: 使用复数形式 (`/api/users`)

/write:prompts/rules/service.md
```markdown
# Spring Boot Service 开发规范

## 职责
- 封装业务逻辑
- 协调多个Repository操作
- 处理事务边界

## 命名规范
- 接口: `XxxService` (如 `UserService`)
- 实现类: `XxxServiceImpl` (如 `UserServiceImpl`)