# Repository 层开发规范

## 职责
- 负责数据访问
- 封装数据库操作
- 实现查询逻辑

## 命名规范
- 接口: `XxxRepository` (如 `UserRepository`)
- 查询方法: 使用findBy/getBy/queryBy前缀

## 性能考虑
- 避免N+1查询问题
- 合理使用@Transactional
- 考虑使用JPA投影减少数据传输