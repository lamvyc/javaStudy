# 数据库学习模块说明

## 🎯 学习目标

通过本模块，你将掌握：

### ⭐⭐⭐ SQL能力（最重要）
- **基础查询**：SELECT、WHERE条件、ORDER BY排序、LIMIT分页
- **聚合函数**：COUNT、SUM、AVG、MAX、MIN统计
- **分组查询**：GROUP BY分组、HAVING筛选
- **多表关联**：INNER JOIN、LEFT JOIN（⭐面试高频）
- **子查询**：WHERE子查询、IN子查询
- **增删改**：INSERT、UPDATE、DELETE操作

### ⭐⭐⭐ 表设计（非常重要）
- **实体识别**：电商系统需要哪些表
- **字段设计**：INT/VARCHAR/DECIMAL/TIMESTAMP类型选择
- **主键设计**：为什么用自增ID
- **外键关联**：如何实现表之间的关系
- **表关系类型**：一对多、多对多、一对一
- **数据库三范式**：1NF/2NF/3NF规范化设计

### ⭐⭐ MyBatis使用
- **MyBatis vs JDBC**：为什么用ORM框架
- **注解方式**：@Select、@Insert、@Update、@Delete
- **XML方式**：动态SQL、复杂查询配置
- **MyBatis-Plus**：自动CRUD，简化开发

### ⭐ 性能优化
- **索引优化**：创建索引提高查询速度
- **慢查询优化**：EXPLAIN分析、避免SELECT *
- **N+1问题**：避免循环查询
- **事务控制**：@Transactional注解使用

## 📚 学习文件

### 1️⃣ MySQLBasicsDemo.java（推荐学习）⭐⭐⭐

**重点内容：SQL能力 + 表设计**

这是核心学习文件，按照精简模板设计，包含8个章节：

1. **前端视角理解** - SQL = JS数组操作（建立认知桥梁）
2. **为什么存在** - 电商系统为什么需要数据库
3. **SQL核心能力（9个）** ⭐⭐⭐ - 最重要的部分
4. **表设计（6大原则）** ⭐⭐⭐ - 非常重要
5. **实战案例** - 电商系统多表JOIN查询
6. **MyBatis核心用法** - 注解/XML/MyBatis-Plus
7. **进阶技巧** - 索引/慢查询/事务/分页/批量
8. **常见坑+面试** - N+1查询、面试高频题

**适合人群**：
- ✅ 前端转全栈开发者
- ✅ 想快速掌握SQL和表设计
- ✅ 准备面试的求职者

**运行方式**：
```bash
./build.sh && ./run.sh mysql
```

### 2️⃣ DatabaseDemo.java（参考示例）

**重点内容：JDBC详细操作 + 学生选课系统**

这是详细的参考示例，包含完整的JDBC操作：

- H2内存数据库使用
- 学生-班级-课程-选课系统设计
- SQL基础到高级查询（8个部分）
- JDBC完整操作流程
- 事务演示（银行转账）
- 防止SQL注入

**适合人群**：
- ✅ 想深入了解JDBC底层操作
- ✅ 需要完整的数据库系统示例
- ✅ 想学习事务控制的细节

**运行方式**：
```bash
./build.sh && ./run.sh database
```

## 🗄️ 使用的数据库

本项目使用 **H2内存数据库**，优势如下：

✅ **无需安装MySQL** - 开箱即用  
✅ **语法兼容MySQL** - 学到的知识可直接应用到MySQL  
✅ **完全在内存中运行** - 速度快，适合学习  
✅ **IDEA可视化支持** - 可以在IDEA中查看数据库  

## 📊 MySQLBasicsDemo.java 的表结构

电商系统示例，包含3个核心表：

### 1. users（用户表）
```sql
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100),
    age INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 2. products（商品表）
```sql
CREATE TABLE products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2),
    stock INT DEFAULT 0,
    category VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 3. orders（订单表）
```sql
CREATE TABLE orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,                      -- 外键：关联用户
    product_id INT,                   -- 外键：关联商品
    quantity INT,
    total_price DECIMAL(10,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
```

## 🚀 学习路线（推荐）

### 第1-3天：SQL基础能力
运行 `MySQLBasicsDemo.java`，重点学习：
- SELECT基础查询
- WHERE条件筛选
- ORDER BY排序
- LIMIT分页

### 第4-5天：聚合和分组
- COUNT/SUM/AVG/MAX/MIN
- GROUP BY分组
- HAVING筛选

### 第6-8天：多表JOIN（⭐最重要）
- INNER JOIN内连接
- LEFT JOIN左连接
- 三表联查
- 实战：查询用户的所有订单及商品信息

### 第9-10天：表设计
- 实体识别：电商系统需要哪些表
- 主键外键设计
- 一对多、多对多关系
- 数据库三范式

### 第11-12天：MyBatis
- 注解方式（@Select/@Insert）
- XML配置（动态SQL）
- MyBatis-Plus简化开发

### 第13-14天：性能优化
- 创建索引
- 避免N+1查询
- 慢查询优化
- EXPLAIN分析SQL

## 💡 学习建议

### ⭐ 重点中的重点
1. **多表JOIN查询** - 面试高频，必须熟练
2. **表设计能力** - 能设计出合理的表结构
3. **SQL性能优化** - 知道为什么慢，如何优化

### ⭐ 学习技巧
- ✅ 先学 MySQLBasicsDemo.java（重点是SQL和表设计）
- ✅ 再看 DatabaseDemo.java（了解JDBC细节）
- ✅ 多练习JOIN查询（LeetCode数据库题）
- ✅ 画ER图理解表关系
- ✅ 使用EXPLAIN分析SQL

### ⭐ 避免的误区
- ❌ 不要一开始就深入JDBC底层
- ❌ 不要忽视表设计的重要性
- ❌ 不要只会单表查询，不会JOIN
- ❌ 不要记忆SQL语法，要理解应用场景

## 💻 在IDEA中查看H2数据库

1. 打开IDEA的Database面板（View -> Tool Windows -> Database）
2. 点击 + 号，选择 Data Source -> H2
3. 配置连接信息：
   - URL: `jdbc:h2:mem:testdb;MODE=MySQL;DB_CLOSE_DELAY=-1`
   - User: `sa`
   - Password: (留空)
4. 点击 Test Connection
5. **注意**：必须在程序运行时才能连接（因为是内存数据库）

## 🔄 切换到MySQL

如果想使用真实的MySQL：

1. 安装MySQL
2. 下载MySQL驱动jar包：`mysql-connector-java-8.0.33.jar`
3. 修改代码中的连接信息：
   ```java
   String url = "jdbc:mysql://localhost:3306/mydb?useSSL=false&serverTimezone=UTC";
   String user = "root";
   String password = "your_password";
   ```

## 🎯 学习成果检验

完成本模块后，你应该能够：

### SQL能力
- ✅ 写出复杂的多表JOIN查询
- ✅ 使用GROUP BY统计分析数据
- ✅ 理解子查询的应用场景
- ✅ 能够优化慢查询

### 表设计能力
- ✅ 能设计电商/博客/社交等系统的表结构
- ✅ 理解主键外键的作用
- ✅ 能识别一对多/多对多关系
- ✅ 遵循数据库三范式

### MyBatis使用
- ✅ 能用注解方式写CRUD
- ✅ 理解MyBatis比JDBC的优势
- ✅ 会用MyBatis-Plus简化开发

### 面试准备
- ✅ 能回答索引原理和作用
- ✅ 能说出INNER JOIN和LEFT JOIN区别
- ✅ 能解释数据库三范式
- ✅ 能举例说明如何优化慢查询

## 📚 推荐资源

### 在线练习
- **LeetCode数据库题**：https://leetcode.cn/problemset/database/
  - 推荐刷50道题巩固SQL能力
- **牛客SQL练习**：https://www.nowcoder.com/activity/oj
- **SQLZoo**：https://sqlzoo.net/

### 文档教程
- SQL教程：https://www.runoob.com/sql/
- MySQL官方文档：https://dev.mysql.com/doc/
- MyBatis官方文档：https://mybatis.org/mybatis-3/zh/

### 推荐书籍
- 《MySQL必知必会》 - 入门首选
- 《高性能MySQL（第3版）》 - 进阶必读
- 《SQL基础教程（第2版）》 - MICK著

## 🎓 下一步学习

完成本模块后，推荐学习：

1. **Spring Boot + MyBatis 实战**
   - 搭建完整的后端项目
   - 实现用户管理系统CRUD
   - 前后端分离开发

2. **数据库进阶**
   - Redis缓存
   - 数据库索引深入
   - 分库分表
   - 读写分离

3. **实战项目**
   - 电商系统（用户、商品、订单）
   - 博客系统（用户、文章、评论）
   - 社交系统（用户、关注、动态）

## 💬 常见问题

**Q: 为什么用H2数据库而不是MySQL？**  
A: H2是内存数据库，无需安装，语法与MySQL兼容。学完后可以无缝切换到MySQL。

**Q: MySQLBasicsDemo和DatabaseDemo有什么区别？**  
A: MySQLBasicsDemo重点是SQL能力和表设计，适合快速学习；DatabaseDemo是详细的JDBC操作示例，适合深入了解。

**Q: 我应该先学哪个？**  
A: 推荐先学MySQLBasicsDemo，掌握SQL和表设计后，再看DatabaseDemo了解JDBC细节。

**Q: 学完需要多久？**  
A: 建议10-14天，每天2-3小时。SQL和表设计是重点，多练习。

**Q: 如何检验学习效果？**  
A: 到LeetCode刷数据库题，能独立完成50道中等难度题就算掌握了。

## 🚀 开始学习

```bash
# 编译项目
./build.sh

# 运行MySQLBasicsDemo（推荐）
./run.sh mysql

# 运行DatabaseDemo（参考）
./run.sh database
```

记住：**SQL能力 > 表设计 > MyBatis > JDBC**

重点学习多表JOIN查询和表设计，这是数据库开发的核心！💪
