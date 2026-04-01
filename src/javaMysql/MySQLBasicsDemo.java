package javaMysql;

import java.sql.*;
import static javaBasic.utils.PrintUtil.*;

/**
 * MySQL与MyBatis学习【精简通用模板】
 * 
 * 目标读者：前端开发者转全栈
 * 核心内容：SQL能力 + 表设计（非常重要）
 */
public class MySQLBasicsDemo {
    
    private static Connection conn;
    
    public static void main(String[] args) {
        
        try {
            // 连接数据库（H2内存数据库，兼容MySQL语法）
            conn = DriverManager.getConnection("jdbc:h2:mem:testdb;MODE=MySQL", "sa", "");
            success("✓ 数据库连接成功！");
            println();
            
            
            // ==================== 1. 前端视角理解 ⭐ ====================
            title("1. 前端视角理解 - SQL的本质");
            
            subtitle("【一句话讲清本质】");
            println("  SQL = 数据库的查询语言，就像前端操作DOM一样操作数据");
            println("  SELECT = document.querySelector（查）");
            println("  INSERT = element.appendChild（增）");
            println("  UPDATE = element.textContent =（改）");
            println("  DELETE = element.remove（删）");
            println();
            
            subtitle("【前端思维映射（类比 JS）】");
            println();
            println("JavaScript 数组操作        →  SQL 数据库操作");
            println("─────────────────────────────────────────────────");
            println("const users = []           →  CREATE TABLE users");
            println("users.push({name:'张三'})   →  INSERT INTO users");
            println("users.filter(u=>u.age>18)  →  SELECT * WHERE age>18");
            println("users.map(u=>u.name)       →  SELECT name FROM users");
            println("users.sort()               →  ORDER BY");
            println("users.slice(0, 10)         →  LIMIT 10");
            println();
            
            subtitle("【JS vs SQL 核心对比】");
            println();
            println("┌────────────────────────────────────────────────────────┐");
            println("│ JavaScript              │ SQL                          │");
            println("├────────────────────────────────────────────────────────┤");
            println("│ users.filter(u=>u.age>18)│ SELECT * FROM users         │");
            println("│                          │ WHERE age > 18              │");
            println("│                          │                             │");
            println("│ users.find(u=>u.id===1)  │ SELECT * FROM users         │");
            println("│                          │ WHERE id = 1                │");
            println("│                          │                             │");
            println("│ users.map(u=>u.name)     │ SELECT name FROM users      │");
            println("│                          │                             │");
            println("│ users.sort((a,b)=>       │ SELECT * FROM users         │");
            println("│   b.score - a.score)     │ ORDER BY score DESC         │");
            println("│                          │                             │");
            println("│ users.slice(0, 10)       │ SELECT * FROM users LIMIT 10│");
            println("└────────────────────────────────────────────────────────┘");
            println();
            println("💡 目的：先建立认知桥梁，而不是直接学语法");
            
            line();
            
            
            // ==================== 2. 为什么存在 ====================
            title("2. 为什么存在 - 解决什么问题");
            
            subtitle("【痛点场景：电商系统】");
            println();
            println("前端需求：");
            println("  - 显示商品列表（带筛选、排序、分页）");
            println("  - 用户下单（减库存、创建订单、生成物流单）");
            println("  - 查看订单历史（关联用户、商品、物流信息）");
            println();
            
            println("❌ 如果只用前端数组：");
            println("  1. 数据刷新就丢失");
            println("  2. 多个用户之间数据不同步");
            println("  3. 无法做复杂的多表关联");
            println("  4. 性能差（上万条数据前端卡死）");
            println();
            
            println("✅ 有了SQL和表设计：");
            println("  1. 数据持久化存储（MySQL）");
            println("  2. 多用户共享数据");
            println("  3. 表之间通过外键关联（用户表、订单表、商品表）");
            println("  4. 数据库索引加速查询（百万数据秒查）");
            println();
            
            subtitle("【前后端协作中的作用】");
            println();
            println("前端发起请求                后端SQL查询");
            println("───────────────────────    ──────────────────────");
            println("GET /api/products?page=1   SELECT * FROM products");
            println("                           LIMIT 10 OFFSET 0");
            println();
            println("GET /api/products?sort=    SELECT * FROM products");
            println("    price_desc             ORDER BY price DESC");
            println();
            println("GET /api/orders/user/1     SELECT o.*, p.name");
            println("                           FROM orders o");
            println("                           JOIN products p");
            println("                           ON o.product_id = p.id");
            println("                           WHERE o.user_id = 1");
            println();
            println("💡 目的：避免\"学了但不知道用在哪\"");
            
            line();
            
            
            // ==================== 3. SQL核心能力 ⭐（重点） ====================
            title("3. SQL核心能力 ⭐（非常重要）");
            
            // 初始化数据
            initDatabase();
            
            subtitle("【SQL能力1：基础查询（SELECT）】");
            println();
            println("// 场景：商品列表页");
            println("SELECT * FROM products;");
            println();
            executeSQL("SELECT * FROM products");
            println();
            
            println("// 场景：只查询需要的字段（性能优化）");
            println("SELECT id, name, price FROM products;");
            println();
            executeSQL("SELECT id, name, price FROM products");
            println();
            
            subtitle("【SQL能力2：条件查询（WHERE）】");
            println();
            println("// 场景：筛选价格大于5000的商品");
            println("SELECT name, price FROM products WHERE price > 5000;");
            println();
            executeSQL("SELECT name, price FROM products WHERE price > 5000");
            println();
            
            println("// 场景：多条件查询（价格在2000-10000之间且库存>10）");
            println("SELECT name, price, stock");
            println("FROM products");
            println("WHERE price BETWEEN 2000 AND 10000 AND stock > 10;");
            println();
            executeSQL("SELECT name, price, stock FROM products WHERE price BETWEEN 2000 AND 10000 AND stock > 10");
            println();
            
            println("// 场景：模糊搜索（搜索包含'Pro'的商品）");
            println("SELECT name, price FROM products WHERE name LIKE '%Pro%';");
            println();
            executeSQL("SELECT name, price FROM products WHERE name LIKE '%Pro%'");
            println();
            
            subtitle("【SQL能力3：排序（ORDER BY）】");
            println();
            println("// 场景：按价格从高到低排序");
            println("SELECT name, price FROM products ORDER BY price DESC;");
            println();
            executeSQL("SELECT name, price FROM products ORDER BY price DESC");
            println();
            
            println("// 场景：多字段排序（先按库存降序，再按价格升序）");
            println("SELECT name, price, stock FROM products");
            println("ORDER BY stock DESC, price ASC;");
            println();
            executeSQL("SELECT name, price, stock FROM products ORDER BY stock DESC, price ASC");
            println();
            
            subtitle("【SQL能力4：分页（LIMIT）】");
            println();
            println("// 场景：每页显示3条，查询第1页");
            println("SELECT name, price FROM products LIMIT 3 OFFSET 0;");
            println();
            executeSQL("SELECT name, price FROM products LIMIT 3 OFFSET 0");
            println();
            
            println("// 场景：查询第2页");
            println("SELECT name, price FROM products LIMIT 3 OFFSET 3;");
            println();
            executeSQL("SELECT name, price FROM products LIMIT 3 OFFSET 3");
            println();
            
            subtitle("【SQL能力5：聚合函数（COUNT/SUM/AVG/MAX/MIN）】");
            println();
            println("// 场景：统计商品总数");
            println("SELECT COUNT(*) AS total FROM products;");
            println();
            executeSQL("SELECT COUNT(*) AS total FROM products");
            println();
            
            println("// 场景：计算商品平均价格");
            println("SELECT AVG(price) AS avg_price FROM products;");
            println();
            executeSQL("SELECT AVG(price) AS avg_price FROM products");
            println();
            
            println("// 场景：统计总库存和最高价格");
            println("SELECT SUM(stock) AS total_stock, MAX(price) AS max_price FROM products;");
            println();
            executeSQL("SELECT SUM(stock) AS total_stock, MAX(price) AS max_price FROM products");
            println();
            
            subtitle("【SQL能力6：分组查询（GROUP BY）】");
            println();
            println("// 场景：统计每个分类的商品数量");
            println("SELECT category, COUNT(*) AS count");
            println("FROM products");
            println("GROUP BY category;");
            println();
            executeSQL("SELECT category, COUNT(*) AS count FROM products GROUP BY category");
            println();
            
            println("// 场景：统计每个分类的平均价格");
            println("SELECT category, AVG(price) AS avg_price");
            println("FROM products");
            println("GROUP BY category;");
            println();
            executeSQL("SELECT category, AVG(price) AS avg_price FROM products GROUP BY category");
            println();
            
            println("// 场景：筛选平均价格>5000的分类（HAVING）");
            println("SELECT category, AVG(price) AS avg_price");
            println("FROM products");
            println("GROUP BY category");
            println("HAVING AVG(price) > 5000;");
            println();
            executeSQL("SELECT category, AVG(price) AS avg_price FROM products GROUP BY category HAVING AVG(price) > 5000");
            println();
            
            subtitle("【SQL能力7：多表关联（JOIN）⭐⭐⭐】");
            println();
            println("// 场景：查询订单及对应的商品名称");
            println("SELECT o.id AS order_id, o.quantity, p.name AS product_name, p.price");
            println("FROM orders o");
            println("INNER JOIN products p ON o.product_id = p.id;");
            println();
            executeSQL(
                "SELECT o.id AS order_id, o.quantity, p.name AS product_name, p.price " +
                "FROM orders o " +
                "INNER JOIN products p ON o.product_id = p.id"
            );
            println();
            
            println("// 场景：查询用户的所有订单（包括商品信息）");
            println("SELECT u.username, o.id AS order_id, p.name AS product_name, o.quantity");
            println("FROM users u");
            println("INNER JOIN orders o ON u.id = o.user_id");
            println("INNER JOIN products p ON o.product_id = p.id");
            println("WHERE u.id = 1;");
            println();
            executeSQL(
                "SELECT u.username, o.id AS order_id, p.name AS product_name, o.quantity " +
                "FROM users u " +
                "INNER JOIN orders o ON u.id = o.user_id " +
                "INNER JOIN products p ON o.product_id = p.id " +
                "WHERE u.id = 1"
            );
            println();
            
            println("// 场景：LEFT JOIN（显示所有用户，即使没有订单）");
            println("SELECT u.username, COUNT(o.id) AS order_count");
            println("FROM users u");
            println("LEFT JOIN orders o ON u.id = o.user_id");
            println("GROUP BY u.id, u.username;");
            println();
            executeSQL(
                "SELECT u.username, COUNT(o.id) AS order_count " +
                "FROM users u " +
                "LEFT JOIN orders o ON u.id = o.user_id " +
                "GROUP BY u.id, u.username"
            );
            println();
            
            subtitle("【SQL能力8：子查询】");
            println();
            println("// 场景：查询价格高于平均价格的商品");
            println("SELECT name, price");
            println("FROM products");
            println("WHERE price > (SELECT AVG(price) FROM products);");
            println();
            executeSQL("SELECT name, price FROM products WHERE price > (SELECT AVG(price) FROM products)");
            println();
            
            println("// 场景：查询买过商品的用户");
            println("SELECT username FROM users");
            println("WHERE id IN (SELECT DISTINCT user_id FROM orders);");
            println();
            executeSQL("SELECT username FROM users WHERE id IN (SELECT DISTINCT user_id FROM orders)");
            println();
            
            subtitle("【SQL能力9：增删改（INSERT/UPDATE/DELETE）】");
            println();
            println("// 场景：新增商品");
            println("INSERT INTO products (name, price, stock, category)");
            println("VALUES ('新商品', 999.00, 50, '手机');");
            println();
            Statement stmt = conn.createStatement();
            int rows = stmt.executeUpdate("INSERT INTO products (name, price, stock, category) VALUES ('新商品', 999.00, 50, '手机')");
            success("  ✓ 插入成功，影响行数: " + rows);
            stmt.close();
            println();
            
            println("// 场景：更新库存");
            println("UPDATE products SET stock = stock - 1 WHERE id = 1;");
            println();
            stmt = conn.createStatement();
            rows = stmt.executeUpdate("UPDATE products SET stock = stock - 1 WHERE id = 1");
            success("  ✓ 更新成功，影响行数: " + rows);
            stmt.close();
            println();
            
            println("// 场景：删除商品");
            println("DELETE FROM products WHERE name = '新商品';");
            println();
            stmt = conn.createStatement();
            rows = stmt.executeUpdate("DELETE FROM products WHERE name = '新商品'");
            success("  ✓ 删除成功，影响行数: " + rows);
            stmt.close();
            println();
            
            println("💡 核心：SQL是最重要的技能，前面7个查询能力必须掌握");
            
            line();
            
            
            // ==================== 4. 表设计 ⭐（重点） ====================
            title("4. 表设计 ⭐（非常重要）");
            
            subtitle("【设计原则1：实体识别】");
            println();
            println("电商系统需要哪些实体？");
            println("  - 用户（users）：存储用户信息");
            println("  - 商品（products）：存储商品信息");
            println("  - 订单（orders）：存储订单信息");
            println("  - 分类（categories）：商品分类");
            println();
            println("💡 原则：一个实体对应一张表");
            println();
            
            subtitle("【设计原则2：字段设计】");
            println();
            println("用户表（users）应该有哪些字段？");
            println();
            println("CREATE TABLE users (");
            println("    id INT PRIMARY KEY AUTO_INCREMENT,  -- 主键，自增");
            println("    username VARCHAR(50) NOT NULL,      -- 用户名，非空");
            println("    email VARCHAR(100) UNIQUE,          -- 邮箱，唯一");
            println("    password VARCHAR(255) NOT NULL,     -- 密码（加密后）");
            println("    age INT,                            -- 年龄");
            println("    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- 创建时间");
            println(");");
            println();
            println("💡 字段类型选择：");
            println("  - INT：整数（id、年龄、数量）");
            println("  - VARCHAR(n)：字符串（用户名、邮箱）");
            println("  - DECIMAL(10,2)：金额（10位数字，2位小数）");
            println("  - TIMESTAMP：时间戳");
            println("  - TEXT：长文本（商品描述）");
            println();
            
            subtitle("【设计原则3：主键设计】");
            println();
            println("什么是主键？");
            println("  - 唯一标识一行数据");
            println("  - 不能为NULL");
            println("  - 通常用自增ID");
            println();
            println("❌ 错误：用业务字段做主键");
            println("  PRIMARY KEY (username)  -- 用户名可能重复或修改");
            println();
            println("✅ 正确：用自增ID");
            println("  id INT PRIMARY KEY AUTO_INCREMENT");
            println();
            
            subtitle("【设计原则4：外键关联】");
            println();
            println("订单表如何关联用户和商品？");
            println();
            println("CREATE TABLE orders (");
            println("    id INT PRIMARY KEY AUTO_INCREMENT,");
            println("    user_id INT NOT NULL,              -- 外键：关联用户");
            println("    product_id INT NOT NULL,           -- 外键：关联商品");
            println("    quantity INT NOT NULL,");
            println("    total_price DECIMAL(10,2),");
            println("    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,");
            println("    ");
            println("    FOREIGN KEY (user_id) REFERENCES users(id),");
            println("    FOREIGN KEY (product_id) REFERENCES products(id)");
            println(");");
            println();
            println("💡 外键作用：");
            println("  1. 维护数据一致性（不能插入不存在的user_id）");
            println("  2. 实现表之间的关联查询（JOIN）");
            println();
            
            subtitle("【设计原则5：表关系类型】");
            println();
            println("1. 一对多（1:N）");
            println("   示例：一个用户有多个订单");
            println("   设计：orders表有user_id外键");
            println();
            println("2. 多对多（M:N）");
            println("   示例：一个订单有多个商品，一个商品属于多个订单");
            println("   设计：需要中间表");
            println();
            println("   CREATE TABLE order_items (");
            println("       id INT PRIMARY KEY AUTO_INCREMENT,");
            println("       order_id INT,");
            println("       product_id INT,");
            println("       quantity INT,");
            println("       FOREIGN KEY (order_id) REFERENCES orders(id),");
            println("       FOREIGN KEY (product_id) REFERENCES products(id)");
            println("   );");
            println();
            println("3. 一对一（1:1）");
            println("   示例：一个用户有一个用户详情");
            println("   设计：user_profiles表有user_id外键（UNIQUE）");
            println();
            
            subtitle("【设计原则6：数据库三范式】");
            println();
            println("第一范式（1NF）：列不可分");
            println("  ❌ 错误：address = '北京市海淀区中关村'");
            println("  ✅ 正确：province='北京市', district='海淀区', street='中关村'");
            println();
            println("第二范式（2NF）：不存在部分依赖");
            println("  ❌ 错误：订单表中存储完整的商品信息");
            println("     orders(id, product_name, product_price, quantity)");
            println("  ✅ 正确：订单表只存商品ID，通过外键关联");
            println("     orders(id, product_id, quantity)");
            println();
            println("第三范式（3NF）：不存在传递依赖");
            println("  ❌ 错误：学生表存储 class_id、class_name、teacher_name");
            println("  ✅ 正确：学生表只存class_id，班级信息放classes表");
            println();
            
            subtitle("【表设计实战：电商系统完整设计】");
            println();
            println("1. 用户表（users）");
            println("   ├─ id (主键)");
            println("   ├─ username");
            println("   ├─ email");
            println("   ├─ password");
            println("   └─ created_at");
            println();
            println("2. 商品表（products）");
            println("   ├─ id (主键)");
            println("   ├─ name");
            println("   ├─ price");
            println("   ├─ stock");
            println("   ├─ category (外键或直接存字符串)");
            println("   └─ created_at");
            println();
            println("3. 订单表（orders）");
            println("   ├─ id (主键)");
            println("   ├─ user_id (外键 → users.id)");
            println("   ├─ total_price");
            println("   ├─ status (pending/paid/shipped)");
            println("   └─ created_at");
            println();
            println("4. 订单明细表（order_items）多对多中间表");
            println("   ├─ id (主键)");
            println("   ├─ order_id (外键 → orders.id)");
            println("   ├─ product_id (外键 → products.id)");
            println("   ├─ quantity");
            println("   └─ price (下单时的价格，防止商品改价)");
            println();
            println("💡 表设计是SQL的基础，设计好了查询就简单");
            
            line();
            
            
            // ==================== 5. 实战案例 ⭐ ====================
            title("5. 实战案例 - 用户订单查询系统");
            
            subtitle("【需求分析】");
            println("功能：查询用户的所有订单，包括商品详情和总金额");
            println();
            
            subtitle("【SQL实现】");
            println();
            println("SELECT ");
            println("    u.username AS 用户名,");
            println("    o.id AS 订单号,");
            println("    o.created_at AS 下单时间,");
            println("    p.name AS 商品名称,");
            println("    o.quantity AS 数量,");
            println("    p.price AS 单价,");
            println("    (o.quantity * p.price) AS 小计");
            println("FROM users u");
            println("INNER JOIN orders o ON u.id = o.user_id");
            println("INNER JOIN products p ON o.product_id = p.id");
            println("WHERE u.id = 1");
            println("ORDER BY o.created_at DESC;");
            println();
            
            subtitle("【执行结果】");
            executeSQL(
                "SELECT " +
                "u.username AS 用户名, " +
                "o.id AS 订单号, " +
                "o.created_at AS 下单时间, " +
                "p.name AS 商品名称, " +
                "o.quantity AS 数量, " +
                "p.price AS 单价, " +
                "(o.quantity * p.price) AS 小计 " +
                "FROM users u " +
                "INNER JOIN orders o ON u.id = o.user_id " +
                "INNER JOIN products p ON o.product_id = p.id " +
                "WHERE u.id = 1 " +
                "ORDER BY o.created_at DESC"
            );
            println();
            
            subtitle("【MyBatis实现（Java代码）】");
            println();
            println("// Mapper接口");
            println("@Mapper");
            println("public interface OrderMapper {");
            println("    ");
            println("    @Select(\"SELECT u.username, o.id AS order_id, p.name AS product_name, \" +");
            println("            \"o.quantity, p.price, (o.quantity * p.price) AS subtotal \" +");
            println("            \"FROM users u \" +");
            println("            \"INNER JOIN orders o ON u.id = o.user_id \" +");
            println("            \"INNER JOIN products p ON o.product_id = p.id \" +");
            println("            \"WHERE u.id = #{userId}\")");
            println("    List<OrderDetail> getUserOrders(@Param(\"userId\") int userId);");
            println("}");
            println();
            println("// 使用");
            println("@Service");
            println("public class OrderService {");
            println("    ");
            println("    @Autowired");
            println("    private OrderMapper orderMapper;");
            println("    ");
            println("    public List<OrderDetail> getUserOrders(int userId) {");
            println("        return orderMapper.getUserOrders(userId);");
            println("    }");
            println("}");
            println();
            println("💡 MyBatis自动将SQL结果映射为Java对象");
            
            line();
            
            
            // ==================== 6. MyBatis核心用法 ====================
            title("6. MyBatis核心用法");
            
            subtitle("【MyBatis vs JDBC对比】");
            println();
            println("JDBC（原始方式）：");
            println("  1. 手动管理Connection");
            println("  2. 手动写PreparedStatement");
            println("  3. 手动遍历ResultSet");
            println("  4. 手动映射到Java对象");
            println("  代码量：~20行");
            println();
            println("MyBatis（ORM框架）：");
            println("  1. 自动管理Connection（连接池）");
            println("  2. 用@Select注解或XML配置SQL");
            println("  3. 自动映射ResultSet到对象");
            println("  4. 支持动态SQL");
            println("  代码量：~3行");
            println();
            
            subtitle("【MyBatis使用方式1：注解方式】");
            println();
            println("@Mapper");
            println("public interface UserMapper {");
            println("    ");
            println("    // 查询所有");
            println("    @Select(\"SELECT * FROM users\")");
            println("    List<User> findAll();");
            println("    ");
            println("    // 根据ID查询");
            println("    @Select(\"SELECT * FROM users WHERE id = #{id}\")");
            println("    User findById(@Param(\"id\") int id);");
            println("    ");
            println("    // 插入");
            println("    @Insert(\"INSERT INTO users(username, email, age) VALUES(#{username}, #{email}, #{age})\")");
            println("    int insert(User user);");
            println("    ");
            println("    // 更新");
            println("    @Update(\"UPDATE users SET email = #{email} WHERE id = #{id}\")");
            println("    int updateEmail(@Param(\"id\") int id, @Param(\"email\") String email);");
            println("    ");
            println("    // 删除");
            println("    @Delete(\"DELETE FROM users WHERE id = #{id}\")");
            println("    int deleteById(@Param(\"id\") int id);");
            println("}");
            println();
            
            subtitle("【MyBatis使用方式2：XML方式（推荐复杂SQL）】");
            println();
            println("<!-- UserMapper.xml -->");
            println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            println("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"");
            println("    \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
            println("<mapper namespace=\"com.example.mapper.UserMapper\">");
            println();
            println("    <!-- 查询用户订单 -->");
            println("    <select id=\"getUserOrders\" resultType=\"OrderDetail\">");
            println("        SELECT ");
            println("            u.username,");
            println("            o.id AS order_id,");
            println("            p.name AS product_name,");
            println("            o.quantity,");
            println("            p.price");
            println("        FROM users u");
            println("        INNER JOIN orders o ON u.id = o.user_id");
            println("        INNER JOIN products p ON o.product_id = p.id");
            println("        WHERE u.id = #{userId}");
            println("    </select>");
            println();
            println("    <!-- 动态SQL：条件查询 -->");
            println("    <select id=\"searchUsers\" resultType=\"User\">");
            println("        SELECT * FROM users");
            println("        <where>");
            println("            <if test=\"username != null\">");
            println("                AND username LIKE CONCAT('%', #{username}, '%')");
            println("            </if>");
            println("            <if test=\"minAge != null\">");
            println("                AND age >= #{minAge}");
            println("            </if>");
            println("        </where>");
            println("    </select>");
            println();
            println("</mapper>");
            println();
            
            subtitle("【MyBatis配置（Spring Boot）】");
            println();
            println("# application.yml");
            println("spring:");
            println("  datasource:");
            println("    url: jdbc:mysql://localhost:3306/mydb");
            println("    username: root");
            println("    password: 123456");
            println("    driver-class-name: com.mysql.cj.jdbc.Driver");
            println();
            println("mybatis:");
            println("  mapper-locations: classpath:mapper/*.xml");
            println("  type-aliases-package: com.example.entity");
            println("  configuration:");
            println("    map-underscore-to-camel-case: true  # 下划线转驼峰");
            println();
            
            subtitle("【MyBatis-Plus（增强版）】");
            println();
            println("// 继承BaseMapper，自动拥有CRUD方法");
            println("@Mapper");
            println("public interface UserMapper extends BaseMapper<User> {");
            println("    // 不用写任何代码，自动拥有：");
            println("    // - selectById(id)");
            println("    // - selectList(null)");
            println("    // - insert(user)");
            println("    // - updateById(user)");
            println("    // - deleteById(id)");
            println("}");
            println();
            println("// 使用");
            println("@Service");
            println("public class UserService {");
            println("    @Autowired");
            println("    private UserMapper userMapper;");
            println("    ");
            println("    public User getUser(int id) {");
            println("        return userMapper.selectById(id);");
            println("    }");
            println("    ");
            println("    public List<User> getAllUsers() {");
            println("        return userMapper.selectList(null);");
            println("    }");
            println("}");
            println();
            println("💡 MyBatis-Plus是MyBatis的增强工具，推荐使用");
            
            line();
            
            
            // ==================== 7. 进阶技巧 ====================
            title("7. 进阶技巧");
            
            subtitle("【技巧1：索引优化】");
            println();
            println("什么时候需要创建索引？");
            println("  ✅ WHERE条件字段");
            println("  ✅ ORDER BY排序字段");
            println("  ✅ JOIN关联字段");
            println("  ✅ 经常查询的字段");
            println();
            println("CREATE INDEX idx_username ON users(username);");
            println("CREATE INDEX idx_email ON users(email);");
            println("CREATE INDEX idx_product_category ON products(category);");
            println();
            println("查询性能对比：");
            println("  无索引：扫描100万行 → 1秒");
            println("  有索引：直接定位 → 0.01秒");
            println();
            
            subtitle("【技巧2：慢查询优化】");
            println();
            println("如何发现慢查询？");
            println("  1. 查看MySQL慢查询日志");
            println("  2. 使用EXPLAIN分析SQL");
            println();
            println("EXPLAIN SELECT * FROM products WHERE category = '手机';");
            println();
            println("优化技巧：");
            println("  ❌ SELECT *（查询所有字段）");
            println("  ✅ SELECT id, name, price（只查需要的）");
            println();
            println("  ❌ 没有WHERE条件");
            println("  ✅ 添加WHERE限制范围");
            println();
            println("  ❌ OR条件过多");
            println("  ✅ 改用IN或UNION");
            println();
            
            subtitle("【技巧3：事务控制】");
            println();
            println("什么时候需要事务？");
            println("  - 多个SQL操作必须同时成功或失败");
            println("  - 例如：下单（减库存 + 创建订单）");
            println();
            println("@Transactional");
            println("public void createOrder(int userId, int productId, int quantity) {");
            println("    // 1. 减库存");
            println("    productMapper.updateStock(productId, -quantity);");
            println("    ");
            println("    // 2. 创建订单");
            println("    Order order = new Order(userId, productId, quantity);");
            println("    orderMapper.insert(order);");
            println("    ");
            println("    // 如果这里抛异常，前面的操作会自动回滚");
            println("}");
            println();
            
            subtitle("【技巧4：分页查询】");
            println();
            println("// 手动分页");
            println("SELECT * FROM products LIMIT 10 OFFSET 0;  -- 第1页");
            println("SELECT * FROM products LIMIT 10 OFFSET 10; -- 第2页");
            println();
            println("// MyBatis-Plus分页");
            println("Page<Product> page = new Page<>(1, 10);");
            println("productMapper.selectPage(page, null);");
            println();
            println("// 前端调用");
            println("GET /api/products?page=1&size=10");
            println();
            
            subtitle("【技巧5：批量操作】");
            println();
            println("// 批量插入（MyBatis）");
            println("<insert id=\"batchInsert\">");
            println("    INSERT INTO users (username, email) VALUES");
            println("    <foreach collection=\"list\" item=\"user\" separator=\",\">");
            println("        (#{user.username}, #{user.email})");
            println("    </foreach>");
            println("</insert>");
            println();
            println("性能对比：");
            println("  循环插入1000次：10秒");
            println("  批量插入：0.5秒");
            
            line();
            
            
            // ==================== 8. 常见坑 + 面试 ====================
            title("8. 常见坑 + 面试");
            
            subtitle("【容易踩的坑】");
            println();
            
            println("坑1：N+1查询问题");
            println("  ❌ 先查用户列表，再循环查每个用户的订单");
            println("     List<User> users = userMapper.selectList(null);");
            println("     for (User user : users) {");
            println("         List<Order> orders = orderMapper.selectByUserId(user.getId());");
            println("     }");
            println("  ✅ 一条SQL用JOIN查出所有数据");
            println("     SELECT u.*, o.* FROM users u LEFT JOIN orders o ON u.id = o.user_id");
            println();
            
            println("坑2：SELECT * 性能问题");
            println("  ❌ SELECT * FROM products（查询不需要的字段）");
            println("  ✅ SELECT id, name, price FROM products（只查需要的）");
            println();
            
            println("坑3：忘记加索引");
            println("  ❌ WHERE email = 'xxx'（email没索引，全表扫描）");
            println("  ✅ CREATE INDEX idx_email ON users(email)");
            println();
            
            println("坑4：JOIN过多");
            println("  ❌ 一条SQL JOIN 5个表（性能差）");
            println("  ✅ 拆分成多次查询或使用缓存");
            println();
            
            println("坑5：没有LIMIT限制");
            println("  ❌ SELECT * FROM products（返回10万条）");
            println("  ✅ SELECT * FROM products LIMIT 100");
            println();
            
            subtitle("【高频面试问题】");
            println();
            
            println("问题1：什么是索引？为什么能加快查询？");
            println("答：");
            println("  索引是一种数据结构（B+树），类似书的目录");
            println("  有索引：直接定位到数据位置");
            println("  无索引：需要全表扫描");
            println();
            
            println("问题2：INNER JOIN和LEFT JOIN的区别？");
            println("答：");
            println("  INNER JOIN：只返回两表都有的数据（交集）");
            println("  LEFT JOIN：返回左表所有数据，右表没有则为NULL");
            println("  示例：查用户订单，LEFT JOIN会显示没下单的用户");
            println();
            
            println("问题3：数据库三范式是什么？");
            println("答：");
            println("  1NF：列不可分（地址要拆成省市区）");
            println("  2NF：不存在部分依赖（订单表不存完整商品信息）");
            println("  3NF：不存在传递依赖（学生表不存班主任姓名）");
            println();
            
            println("问题4：如何优化慢查询？");
            println("答：");
            println("  1. 添加索引（WHERE/ORDER BY字段）");
            println("  2. 避免SELECT *");
            println("  3. 使用LIMIT限制返回数量");
            println("  4. 优化JOIN（减少关联表）");
            println("  5. 使用EXPLAIN分析执行计划");
            println();
            
            println("问题5：MyBatis的#{}和${}区别？");
            println("答：");
            println("  #{} - 预编译，防SQL注入（推荐）");
            println("  ${} - 字符串拼接，有SQL注入风险");
            println("  示例：WHERE id = #{id} 生成 WHERE id = ?");
            println();
            
            println("问题6：项目中如何设计表？");
            println("答（结合实际）：");
            println("  \"在电商项目中设计了5张核心表：");
            println("   1. users：用户表，主键id，唯一索引email");
            println("   2. products：商品表，有category字段，创建了索引");
            println("   3. orders：订单表，外键user_id和product_id");
            println("   4. order_items：订单明细表（多对多中间表）");
            println("   5. categories：分类表");
            println("   遵循第三范式，避免数据冗余。\"");
            
            line();
            
            
            // ==================== 总结 ====================
            success("MySQL与MyBatis学习【精简版】完成！");
            println();
            
            subtitle("🎯 核心要点总结");
            println("1. SQL能力：SELECT/WHERE/JOIN/GROUP BY（最重要）");
            println("2. 表设计：主键、外键、三范式、表关系");
            println("3. MyBatis：简化JDBC，自动映射对象");
            println("4. 性能优化：索引、避免SELECT *、LIMIT");
            println("5. 最常用：多表JOIN查询、动态SQL");
            println();
            
            subtitle("📈 学习路径");
            println("第1周：掌握基础SQL（SELECT/WHERE/ORDER BY/LIMIT）");
            println("第2周：掌握JOIN查询和GROUP BY");
            println("第3周：学习表设计和三范式");
            println("第4周：集成MyBatis到Spring Boot项目");
            println("第5周：完成CRUD功能（增删改查）");
            println();
            
            subtitle("💡 快速上手技巧");
            println("✅ 多练习SQL：LeetCode数据库题");
            println("✅ 画ER图：理解表之间的关系");
            println("✅ 用MyBatis-Plus：代码量减少80%");
            println("✅ 创建索引：WHERE/JOIN字段必加");
            println("✅ 使用EXPLAIN：分析SQL性能");
            println();
            
            subtitle("🚀 下一步");
            println("- 练习50道SQL题（牛客/LeetCode）");
            println("- 设计一个完整的表结构（电商/博客/社交）");
            println("- 学习Redis缓存（减少数据库压力）");
            println("- 学习数据库连接池（HikariCP）");
            
        } catch (SQLException e) {
            error("发生错误: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
    
    /**
     * 初始化数据库
     */
    private static void initDatabase() throws SQLException {
        Statement stmt = conn.createStatement();
        
        // 创建用户表
        stmt.execute("DROP TABLE IF EXISTS orders");
        stmt.execute("DROP TABLE IF EXISTS users");
        stmt.execute("DROP TABLE IF EXISTS products");
        
        stmt.execute(
            "CREATE TABLE users (" +
            "  id INT PRIMARY KEY AUTO_INCREMENT," +
            "  username VARCHAR(50) NOT NULL," +
            "  email VARCHAR(100)," +
            "  age INT," +
            "  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
            ")"
        );
        
        // 创建商品表
        stmt.execute(
            "CREATE TABLE products (" +
            "  id INT PRIMARY KEY AUTO_INCREMENT," +
            "  name VARCHAR(100) NOT NULL," +
            "  price DECIMAL(10,2)," +
            "  stock INT DEFAULT 0," +
            "  category VARCHAR(50)," +
            "  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
            ")"
        );
        
        // 创建订单表
        stmt.execute(
            "CREATE TABLE orders (" +
            "  id INT PRIMARY KEY AUTO_INCREMENT," +
            "  user_id INT," +
            "  product_id INT," +
            "  quantity INT," +
            "  total_price DECIMAL(10,2)," +
            "  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
            "  FOREIGN KEY (user_id) REFERENCES users(id)," +
            "  FOREIGN KEY (product_id) REFERENCES products(id)" +
            ")"
        );
        
        // 插入测试数据
        stmt.execute("INSERT INTO users (username, email, age) VALUES ('张三', 'zhangsan@example.com', 25)");
        stmt.execute("INSERT INTO users (username, email, age) VALUES ('李四', 'lisi@example.com', 28)");
        stmt.execute("INSERT INTO users (username, email, age) VALUES ('王五', 'wangwu@example.com', 22)");
        
        stmt.execute("INSERT INTO products (name, price, stock, category) VALUES ('iPhone 15 Pro', 7999.00, 50, '手机')");
        stmt.execute("INSERT INTO products (name, price, stock, category) VALUES ('MacBook Pro', 12999.00, 20, '电脑')");
        stmt.execute("INSERT INTO products (name, price, stock, category) VALUES ('AirPods Pro', 1999.00, 100, '耳机')");
        stmt.execute("INSERT INTO products (name, price, stock, category) VALUES ('iPad Air', 4999.00, 30, '平板')");
        stmt.execute("INSERT INTO products (name, price, stock, category) VALUES ('Apple Watch', 3299.00, 60, '手表')");
        stmt.execute("INSERT INTO products (name, price, stock, category) VALUES ('小米14', 3999.00, 80, '手机')");
        
        stmt.execute("INSERT INTO orders (user_id, product_id, quantity, total_price) VALUES (1, 1, 1, 7999.00)");
        stmt.execute("INSERT INTO orders (user_id, product_id, quantity, total_price) VALUES (1, 3, 2, 3998.00)");
        stmt.execute("INSERT INTO orders (user_id, product_id, quantity, total_price) VALUES (2, 2, 1, 12999.00)");
        
        stmt.close();
    }
    
    /**
     * 执行SQL并打印结果
     */
    private static void executeSQL(String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        // 获取列信息
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        
        // 打印表头
        for (int i = 1; i <= columnCount; i++) {
            print(String.format("%-20s", metaData.getColumnLabel(i)));
        }
        println();
        println("─────────────────────────────────────────────────────────────");
        
        // 打印数据
        int rowCount = 0;
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                Object value = rs.getObject(i);
                String displayValue = value != null ? value.toString() : "NULL";
                // 截断过长的字符串
                if (displayValue.length() > 18) {
                    displayValue = displayValue.substring(0, 15) + "...";
                }
                print(String.format("%-20s", displayValue));
            }
            println();
            rowCount++;
        }
        
        if (rowCount == 0) {
            println("(没有数据)");
        } else {
            println("─────────────────────────────────────────────────────────────");
            info("  共 " + rowCount + " 行");
        }
        
        rs.close();
        stmt.close();
    }
    
    /**
     * 关闭连接
     */
    private static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                info("\n数据库连接已关闭");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
