# 数据库学习模块说明

## 🎯 学习目标

通过本模块，你将掌握：
- SQL基础查询（SELECT、WHERE、ORDER BY、LIMIT）
- 多表连接（INNER JOIN、LEFT JOIN）
- 分组聚合（GROUP BY、COUNT、SUM、AVG）
- 子查询（WHERE子查询、FROM子查询）
- 数据库设计（主键、外键、索引）
- 事务管理（ACID特性、提交、回滚）
- JDBC编程（Connection、Statement、PreparedStatement）

## 🗄️ 使用的数据库

本项目使用 **H2内存数据库**，优势如下：

✅ **无需安装MySQL** - 开箱即用  
✅ **语法兼容MySQL** - 学到的知识可直接应用到MySQL  
✅ **完全在内存中运行** - 速度快，适合学习  
✅ **IDEA可视化支持** - 可以在IDEA中查看数据库  

## 📊 数据库表结构

本示例使用学生选课系统，包含4个表：

### 1. students（学生表）
| 字段 | 类型 | 说明 |
|------|------|------|
| id | INT | 主键，自增 |
| name | VARCHAR(50) | 学生姓名 |
| age | INT | 年龄 |
| class_id | INT | 班级ID（外键） |
| score | DECIMAL(5,2) | 成绩 |

### 2. classes（班级表）
| 字段 | 类型 | 说明 |
|------|------|------|
| id | INT | 主键，自增 |
| name | VARCHAR(50) | 班级名称 |
| teacher | VARCHAR(50) | 班主任 |

### 3. courses（课程表）
| 字段 | 类型 | 说明 |
|------|------|------|
| id | INT | 主键，自增 |
| name | VARCHAR(50) | 课程名称 |
| credit | INT | 学分 |

### 4. enrollments（选课表）
| 字段 | 类型 | 说明 |
|------|------|------|
| id | INT | 主键，自增 |
| student_id | INT | 学生ID（外键） |
| course_id | INT | 课程ID（外键） |
| score | DECIMAL(5,2) | 课程成绩 |

## 🚀 运行示例

```bash
# 编译项目
./build.sh

# 运行数据库示例
./run.sh database
```

## 📖 学习内容

### 1. SQL基础查询
- SELECT 查询所有数据
- WHERE 条件过滤
- ORDER BY 排序
- LIMIT 限制结果数量
- LIKE 模糊查询
- IN 范围查询
- BETWEEN 区间查询

### 2. JOIN 连接查询
- INNER JOIN 内连接
- LEFT JOIN 左连接
- 多表连接

### 3. GROUP BY 分组
- COUNT 计数
- SUM 求和
- AVG 平均值
- MAX/MIN 最大最小值
- HAVING 分组后筛选

### 4. 子查询
- WHERE 中的子查询
- FROM 中的子查询
- EXISTS 子查询

### 5. 数据库设计
- 主键：唯一标识
- 外键：引用完整性
- 索引：加快查询速度
- 三范式：规范化设计

### 6. 事务
- ACID特性
- 事务提交
- 事务回滚
- 转账示例

### 7. JDBC操作
- Connection 数据库连接
- Statement 执行SQL
- PreparedStatement 预编译SQL
- ResultSet 结果集处理
- 防止SQL注入

## 💡 在IDEA中查看H2数据库

1. 打开IDEA的Database面板（View -> Tool Windows -> Database）
2. 点击 + 号，选择 Data Source -> H2
3. 配置连接信息：
   - URL: `jdbc:h2:mem:testdb;MODE=MySQL;DB_CLOSE_DELAY=-1`
   - User: `sa`
   - Password: (留空)
4. 点击 Test Connection
5. **注意**：必须在DatabaseDemo程序运行时才能连接（因为是内存数据库）

## 🔄 切换到MySQL

如果想使用真实的MySQL：

1. 安装MySQL
2. 下载MySQL驱动jar包
3. 修改DatabaseDemo.java中的连接信息：
   ```java
   String URL = "jdbc:mysql://localhost:3306/testdb";
   String USER = "root";
   String PASSWORD = "your_password";
   ```

## 📚 推荐资源

- SQL教程：https://www.runoob.com/sql/
- MySQL官方文档：https://dev.mysql.com/doc/
- JDBC教程：https://www.runoob.com/jdbc/
