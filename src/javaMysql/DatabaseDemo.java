package javaMysql;

import java.sql.*;
import static javaBasic.utils.PrintUtil.*;

/**
 * 数据库学习案例 - 使用H2内存数据库
 * 包含：SQL语句、数据库设计、JDBC操作
 * 
 * 优势：
 * - 无需安装MySQL，直接运行
 * - 语法与MySQL兼容
 * - 适合学习和调试
 */
public class DatabaseDemo {
    
    // 数据库连接信息（H2内存模式）
    private static final String URL = "jdbc:h2:mem:testdb;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    
    public static void main(String[] args) {
        
        title("数据库学习 - H2内存数据库（兼容MySQL语法）");
        
        Connection conn = null;
        
        try {
            // 1. 加载数据库驱动 =》简单理解：告诉 Java 程序"我要用 H2 数据库"，需要先把驱动加载进来。
            Class.forName("org.h2.Driver");
            info("H2数据库驱动加载成功");
            
            // 2. 建立连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            success("数据库连接成功！");
            
            // 3. 创建表并插入测试数据
            initDatabase(conn);
            
            // 4. SQL基础查询
            demonstrateBasicSQL(conn);
            
            // 5. JOIN查询
            demonstrateJOIN(conn);
            
            // 6. GROUP BY分组
            demonstrateGroupBy(conn);
            
            // 7. 子查询
            demonstrateSubQuery(conn);
            
            // 8. 数据库设计演示
            demonstrateDesign(conn);
            
            // 9. 索引
            demonstrateIndex(conn);
            
            // 10. 事务
            demonstrateTransaction(conn);
            
            // 11. JDBC操作
            demonstrateJDBC(conn);
            
            line();
            success("数据库学习完成！");
            
            info("\n核心要点总结：");
            println("1. SQL基础：SELECT、WHERE、ORDER BY、LIMIT");
            println("2. 连接查询：INNER JOIN、LEFT JOIN、RIGHT JOIN");
            println("3. 分组聚合：GROUP BY、COUNT、SUM、AVG、HAVING");
            println("4. 子查询：WHERE中子查询、FROM中子查询");
            println("5. 主键：唯一标识，不能为NULL");
            println("6. 索引：加快查询速度，但影响插入性能");
            println("7. 事务：ACID特性，确保数据一致性");
            println("8. 三范式：消除冗余，规范化设计");
            
        } catch (Exception e) {
            error("发生错误: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 关闭连接
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
    
    /**
     * 初始化数据库：创建表并插入测试数据
     */
    private static void initDatabase(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        
        subtitle("创建数据库表...");
        
        // 创建学生表
        stmt.execute("DROP TABLE IF EXISTS students");
        stmt.execute(
            "CREATE TABLE students (" +
            "  id INT PRIMARY KEY AUTO_INCREMENT," +
            "  name VARCHAR(50) NOT NULL," +
            "  age INT," +
            "  class_id INT," +
            "  score DECIMAL(5,2)" +
            ")"
        );
        
        // 创建班级表
        stmt.execute("DROP TABLE IF EXISTS classes");
        stmt.execute(
            "CREATE TABLE classes (" +
            "  id INT PRIMARY KEY AUTO_INCREMENT," +
            "  name VARCHAR(50) NOT NULL," +
            "  teacher VARCHAR(50)" +
            ")"
        );
        
        // 创建课程表
        stmt.execute("DROP TABLE IF EXISTS courses");
        stmt.execute(
            "CREATE TABLE courses (" +
            "  id INT PRIMARY KEY AUTO_INCREMENT," +
            "  name VARCHAR(50) NOT NULL," +
            "  credit INT" +
            ")"
        );
        
        // 创建选课表（学生-课程多对多关系）
        stmt.execute("DROP TABLE IF EXISTS enrollments");
        stmt.execute(
            "CREATE TABLE enrollments (" +
            "  id INT PRIMARY KEY AUTO_INCREMENT," +
            "  student_id INT," +
            "  course_id INT," +
            "  score DECIMAL(5,2)," +
            "  FOREIGN KEY (student_id) REFERENCES students(id)," +
            "  FOREIGN KEY (course_id) REFERENCES courses(id)" +
            ")"
        );

        /*
        * 小鸡:
        * 外键约束是确保数据参照完整性的规则，它通过插入限制防止子表引用不存在的父表数据（脏数据），
        * 并通过删除限制防止父表误删被子表引用的记录（级联保护），
        * 其核心语法为：FOREIGN KEY (子表字段) REFERENCES 父表名(父表主键)。
        *
         * */
        
        println("✓ 表创建成功：students, classes, courses, enrollments");
        
        subtitle("插入测试数据...");
        
        // 插入班级数据
        stmt.execute("INSERT INTO classes (name, teacher) VALUES ('软件1班', '王老师')");
        stmt.execute("INSERT INTO classes (name, teacher) VALUES ('软件2班', '李老师')");
        stmt.execute("INSERT INTO classes (name, teacher) VALUES ('网络1班', '张老师')");
        
        // 插入学生数据
        stmt.execute("INSERT INTO students (name, age, class_id, score) VALUES ('张三', 20, 1, 85.5)");
        stmt.execute("INSERT INTO students (name, age, class_id, score) VALUES ('李四', 21, 1, 92.0)");
        stmt.execute("INSERT INTO students (name, age, class_id, score) VALUES ('王五', 20, 2, 78.5)");
        stmt.execute("INSERT INTO students (name, age, class_id, score) VALUES ('赵六', 22, 2, 88.0)");
        stmt.execute("INSERT INTO students (name, age, class_id, score) VALUES ('钱七', 21, 3, 95.5)");
        stmt.execute("INSERT INTO students (name, age, class_id, score) VALUES ('孙八', 20, 3, 82.0)");
        
        // 插入课程数据
        stmt.execute("INSERT INTO courses (name, credit) VALUES ('Java编程', 4)");
        stmt.execute("INSERT INTO courses (name, credit) VALUES ('数据库原理', 3)");
        stmt.execute("INSERT INTO courses (name, credit) VALUES ('操作系统', 3)");
        stmt.execute("INSERT INTO courses (name, credit) VALUES ('数据结构', 4)");
        
        // 插入选课数据
        stmt.execute("INSERT INTO enrollments (student_id, course_id, score) VALUES (1, 1, 88.0)");
        stmt.execute("INSERT INTO enrollments (student_id, course_id, score) VALUES (1, 2, 92.0)");
        stmt.execute("INSERT INTO enrollments (student_id, course_id, score) VALUES (2, 1, 95.0)");
        stmt.execute("INSERT INTO enrollments (student_id, course_id, score) VALUES (2, 3, 85.0)");
        stmt.execute("INSERT INTO enrollments (student_id, course_id, score) VALUES (3, 2, 78.0)");
        stmt.execute("INSERT INTO enrollments (student_id, course_id, score) VALUES (4, 1, 90.0)");
        stmt.execute("INSERT INTO enrollments (student_id, course_id, score) VALUES (5, 4, 98.0)");
        
        success("测试数据插入成功！");
        
        stmt.close();
    }
    
    /**
     * 1. 演示基础SQL查询
     */
    private static void demonstrateBasicSQL(Connection conn) throws SQLException {
        title("1. SQL基础查询");
        
        subtitle("1.1 SELECT - 查询所有学生");
        String sql1 = "SELECT * FROM students";
        executeQuery(conn, sql1);
        
        subtitle("1.2 WHERE - 条件查询（年龄>20）");
        String sql2 = "SELECT name, age, score FROM students WHERE age > 20";
        executeQuery(conn, sql2);
        
        subtitle("1.3 ORDER BY - 按成绩降序排序");
        String sql3 = "SELECT name, score FROM students ORDER BY score DESC";
        executeQuery(conn, sql3);
        
        subtitle("1.4 LIMIT - 查询前3名学生");
        String sql4 = "SELECT name, score FROM students ORDER BY score DESC LIMIT 3";
        executeQuery(conn, sql4);
        
        subtitle("1.5 WHERE + AND - 多条件查询");
        String sql5 = "SELECT name, age, score FROM students WHERE age >= 20 AND score >= 85";
        executeQuery(conn, sql5);
        
        subtitle("1.6 LIKE - 模糊查询（名字包含'张'）");
        String sql6 = "SELECT name FROM students WHERE name LIKE '%张%'";
        executeQuery(conn, sql6);
        
        subtitle("1.7 IN - 查询指定班级的学生");
        String sql7 = "SELECT name, class_id FROM students WHERE class_id IN (1, 2)";
        executeQuery(conn, sql7);
        
        subtitle("1.8 BETWEEN - 范围查询");
        String sql8 = "SELECT name, score FROM students WHERE score BETWEEN 80 AND 90";
        executeQuery(conn, sql8);
    }
    
    /**
     * 2. 演示JOIN连接查询
     */
    private static void demonstrateJOIN(Connection conn) throws SQLException {
        title("2. JOIN 连接查询");
        
        subtitle("2.1 INNER JOIN - 内连接（学生和班级）");
        String sql1 = 
            "SELECT s.name AS 学生姓名, s.score AS 成绩, c.name AS 班级, c.teacher AS 老师 " +
            "FROM students s " +
            "INNER JOIN classes c ON s.class_id = c.id";
        println("SQL: " + sql1);
        executeQuery(conn, sql1);
        
        subtitle("2.2 LEFT JOIN - 左连接（显示所有学生，即使没有班级）");
        // 先插入一个没有班级的学生
        Statement stmt = conn.createStatement();
        stmt.execute("INSERT INTO students (name, age, score) VALUES ('无班级学生', 19, 75.0)");
        stmt.close();
        
        String sql2 = 
            "SELECT s.name AS 学生, c.name AS 班级 " +
            "FROM students s " +
            "LEFT JOIN classes c ON s.class_id = c.id";
        println("SQL: " + sql2);
        executeQuery(conn, sql2);
        
        subtitle("2.3 多表连接（学生-选课-课程）");
        String sql3 = 
            "SELECT s.name AS 学生, co.name AS 课程, e.score AS 成绩 " +
            "FROM students s " +
            "INNER JOIN enrollments e ON s.id = e.student_id " +
            "INNER JOIN courses co ON e.course_id = co.id " +
            "ORDER BY s.name";
        println("SQL: " + sql3);
        executeQuery(conn, sql3);
    }
    
    /**
     * 3. 演示GROUP BY分组查询
     */
    private static void demonstrateGroupBy(Connection conn) throws SQLException {
        title("3. GROUP BY 分组查询");
        
        subtitle("3.1 COUNT - 统计每个班级的学生数");
        String sql1 = 
            "SELECT c.name AS 班级, COUNT(s.id) AS 学生数 " +
            "FROM classes c " +
            "LEFT JOIN students s ON c.id = s.class_id " +
            "GROUP BY c.id, c.name";
        executeQuery(conn, sql1);
        
        subtitle("3.2 AVG - 计算每个班级的平均分");
        String sql2 = 
            "SELECT c.name AS 班级, AVG(s.score) AS 平均分 " +
            "FROM classes c " +
            "INNER JOIN students s ON c.id = s.class_id " +
            "GROUP BY c.id, c.name " +
            "ORDER BY AVG(s.score) DESC";
        executeQuery(conn, sql2);
        
        subtitle("3.3 MAX/MIN - 每个班级的最高分和最低分");
        String sql3 = 
            "SELECT c.name AS 班级, MAX(s.score) AS 最高分, MIN(s.score) AS 最低分 " +
            "FROM classes c " +
            "INNER JOIN students s ON c.id = s.class_id " +
            "GROUP BY c.id, c.name";
        executeQuery(conn, sql3);
        
        subtitle("3.4 HAVING - 筛选平均分>85的班级");
        String sql4 = 
            "SELECT c.name AS 班级, AVG(s.score) AS 平均分 " +
            "FROM classes c " +
            "INNER JOIN students s ON c.id = s.class_id " +
            "GROUP BY c.id, c.name " +
            "HAVING AVG(s.score) > 85";
        executeQuery(conn, sql4);
        
        subtitle("3.5 统计每门课程的选课人数");
        String sql5 = 
            "SELECT co.name AS 课程, COUNT(e.student_id) AS 选课人数 " +
            "FROM courses co " +
            "LEFT JOIN enrollments e ON co.id = e.course_id " +
            "GROUP BY co.id, co.name " +
            "ORDER BY COUNT(e.student_id) DESC";
        executeQuery(conn, sql5);
    }
    
    /**
     * 4. 演示子查询
     */
    private static void demonstrateSubQuery(Connection conn) throws SQLException {
        title("4. 子查询（SubQuery）");
        
        subtitle("4.1 WHERE中的子查询 - 查询成绩高于平均分的学生");
        String sql1 = 
            "SELECT name, score " +
            "FROM students " +
            "WHERE score > (SELECT AVG(score) FROM students) " +
            "ORDER BY score DESC";
        println("SQL: " + sql1);
        executeQuery(conn, sql1);
        
        subtitle("4.2 IN子查询 - 查询选了'Java编程'课的学生");
        String sql2 = 
            "SELECT name " +
            "FROM students " +
            "WHERE id IN (" +
            "  SELECT student_id " +
            "  FROM enrollments " +
            "  WHERE course_id = (SELECT id FROM courses WHERE name = 'Java编程')" +
            ")";
        println("SQL: " + sql2);
        executeQuery(conn, sql2);
        
        subtitle("4.3 FROM中的子查询 - 查询各班级的最高分学生");
        String sql3 = 
            "SELECT班级, 学生姓名, 成绩 FROM (" +
            "  SELECT c.name AS 班级, s.name AS 学生姓名, s.score AS 成绩, " +
            "    ROW_NUMBER() OVER (PARTITION BY c.id ORDER BY s.score DESC) AS rn " +
            "  FROM students s " +
            "  JOIN classes c ON s.class_id = c.id" +
            ") AS ranked " +
            "WHERE rn = 1";
        println("SQL: " + sql3);
        executeQuery(conn, sql3);
        
        subtitle("4.4 EXISTS子查询 - 查询有学生的班级");
        String sql4 = 
            "SELECT name AS 班级 " +
            "FROM classes c " +
            "WHERE EXISTS (" +
            "  SELECT 1 FROM students s WHERE s.class_id = c.id" +
            ")";
        println("SQL: " + sql4);
        executeQuery(conn, sql4);
    }
    
    /**
     * 5. 演示数据库设计
     */
    private static void demonstrateDesign(Connection conn) throws SQLException {
        title("5. 数据库设计");
        
        subtitle("5.1 主键（Primary Key）");
        info("主键的作用：");
        println("- 唯一标识每一行数据");
        println("- 不能为NULL");
        println("- 一个表只能有一个主键");
        println("- 主键自动创建索引");
        
        String sql1 = 
            "SELECT id, name FROM students WHERE id = 1";
        println("\n示例：通过主键查询（最快）");
        executeQuery(conn, sql1);
        
        subtitle("5.2 外键（Foreign Key）");
        info("外键的作用：");
        println("- 维护表之间的引用完整性");
        println("- 防止插入无效的关联数据");
        println("- students.class_id 引用 classes.id");
        println("- enrollments.student_id 引用 students.id");
        
        subtitle("5.3 数据库三范式");
        info("第一范式（1NF）：列不可分");
        println("❌ 错误：地址列包含'北京市海淀区中关村'");
        println("✓ 正确：省='北京市', 市='海淀区', 街道='中关村'");
        
        info("\n第二范式（2NF）：不存在部分依赖");
        println("❌ 错误：订单表中同时存储商品信息");
        println("✓ 正确：订单表 + 商品表，通过外键关联");
        
        info("\n第三范式（3NF）：不存在传递依赖");
        println("❌ 错误：学生表包含 班级ID、班级名称、班主任");
        println("✓ 正确：学生表 + 班级表，避免冗余");
        
        println("\n当前数据库设计符合三范式：");
        println("- students: 学生信息（class_id外键）");
        println("- classes: 班级信息");
        println("- courses: 课程信息");
        println("- enrollments: 选课关系（多对多中间表）");
    }
    
    /**
     * 6. 演示索引
     */
    private static void demonstrateIndex(Connection conn) throws SQLException {
        title("6. 索引（Index）");
        
        subtitle("6.1 什么是索引");
        info("索引的作用：");
        println("- 加快数据查询速度（类似书的目录）");
        println("- 主键自动创建索引");
        println("- 可以在常用查询字段上创建索引");
        
        warning("\n索引的代价：");
        println("- 占用额外存储空间");
        println("- 插入、更新、删除时需要维护索引");
        println("- 不要在所有列上都创建索引");
        
        subtitle("6.2 创建索引");
        Statement stmt = conn.createStatement();
        
        // 在name列创建索引
        stmt.execute("CREATE INDEX idx_student_name ON students(name)");
        success("在students.name上创建索引成功");
        
        // 在score列创建索引
        stmt.execute("CREATE INDEX idx_student_score ON students(score)");
        success("在students.score上创建索引成功");
        
        subtitle("6.3 查看索引效果");
        println("使用索引查询（WHERE name = '张三'）：");
        String sql = "SELECT * FROM students WHERE name = '张三'";
        executeQuery(conn, sql);
        
        subtitle("6.4 索引使用建议");
        info("应该创建索引的情况：");
        println("✓ 主键和外键");
        println("✓ 经常用于WHERE条件的列");
        println("✓ 经常用于JOIN的列");
        println("✓ 经常用于ORDER BY的列");
        
        info("\n不应该创建索引的情况：");
        println("✗ 很少查询的列");
        println("✗ 数据重复度高的列（如性别）");
        println("✗ 频繁更新的列");
        
        stmt.close();
    }
    
    /**
     * 7. 演示事务
     */
    private static void demonstrateTransaction(Connection conn) throws SQLException {
        title("7. 事务（Transaction）");
        
        subtitle("7.1 事务的ACID特性");
        info("A - Atomicity（原子性）：");
        println("  事务中的所有操作要么全部成功，要么全部失败");
        
        info("C - Consistency（一致性）：");
        println("  事务前后数据保持一致状态");
        
        info("D - Isolation（隔离性）：");
        println("  多个事务并发执行时互不干扰");
        
        info("I - Durability（持久性）：");
        println("  事务提交后，数据永久保存");
        
        subtitle("7.2 事务示例 - 转账操作");
        
        // 先创建账户表
        Statement stmt = conn.createStatement();
        stmt.execute("DROP TABLE IF EXISTS accounts");
        stmt.execute(
            "CREATE TABLE accounts (" +
            "  id INT PRIMARY KEY," +
            "  name VARCHAR(50)," +
            "  balance DECIMAL(10,2)" +
            ")"
        );
        stmt.execute("INSERT INTO accounts VALUES (1, '张三账户', 1000.00)");
        stmt.execute("INSERT INTO accounts VALUES (2, '李四账户', 500.00)");
        stmt.close();
        
        println("\n初始账户余额：");
        executeQuery(conn, "SELECT * FROM accounts");
        
        subtitle("7.3 执行转账事务（张三转给李四100元）");
        
        try {
            // 关闭自动提交
            conn.setAutoCommit(false);
            info("开始事务...");
            
            Statement txStmt = conn.createStatement();
            
            // 1. 张三账户减少100
            txStmt.executeUpdate("UPDATE accounts SET balance = balance - 100 WHERE id = 1");
            println("✓ 张三账户 -100");
            
            // 2. 李四账户增加100
            txStmt.executeUpdate("UPDATE accounts SET balance = balance + 100 WHERE id = 2");
            println("✓ 李四账户 +100");
            
            // 提交事务
            conn.commit();
            success("事务提交成功！");
            
            txStmt.close();
            
        } catch (SQLException e) {
            // 发生错误，回滚事务
            conn.rollback();
            error("事务失败，已回滚！");
            throw e;
        } finally {
            // 恢复自动提交
            conn.setAutoCommit(true);
        }
        
        println("\n转账后账户余额：");
        executeQuery(conn, "SELECT * FROM accounts");
        
        subtitle("7.4 演示事务回滚");
        info("模拟转账失败场景...");
        
        try {
            conn.setAutoCommit(false);
            Statement rollbackStmt = conn.createStatement();
            
            // 1. 张三账户减少500
            rollbackStmt.executeUpdate("UPDATE accounts SET balance = balance - 500 WHERE id = 1");
            println("✓ 张三账户 -500");
            
            // 2. 模拟发生错误（余额不足等）
            println("✗ 发生错误！准备回滚...");
            throw new SQLException("转账失败：余额不足");
            
        } catch (SQLException e) {
            conn.rollback();
            warning("事务已回滚，数据恢复到转账前状态");
            conn.setAutoCommit(true);
        }
        
        println("\n回滚后账户余额（未发生变化）：");
        executeQuery(conn, "SELECT * FROM accounts");
    }
    
    /**
     * 8. 演示JDBC操作
     */
    private static void demonstrateJDBC(Connection conn) throws SQLException {
        title("8. JDBC操作");
        
        subtitle("8.1 Statement vs PreparedStatement");
        info("Statement - 简单SQL执行");
        println("- 每次执行都要编译SQL");
        println("- 存在SQL注入风险");
        
        info("\nPreparedStatement - 预编译SQL（推荐）");
        println("✓ SQL预编译，执行效率高");
        println("✓ 防止SQL注入攻击");
        println("✓ 代码更清晰");
        
        subtitle("8.2 使用PreparedStatement插入数据");
        String insertSQL = "INSERT INTO students (name, age, class_id, score) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(insertSQL);
        
        pstmt.setString(1, "新学生");
        pstmt.setInt(2, 19);
        pstmt.setInt(3, 1);
        pstmt.setDouble(4, 88.5);
        
        int rows = pstmt.executeUpdate();
        success("插入成功，影响行数: " + rows);
        pstmt.close();
        
        subtitle("8.3 使用PreparedStatement更新数据");
        String updateSQL = "UPDATE students SET score = ? WHERE name = ?";
        pstmt = conn.prepareStatement(updateSQL);
        
        pstmt.setDouble(1, 90.0);
        pstmt.setString(2, "新学生");
        
        rows = pstmt.executeUpdate();
        success("更新成功，影响行数: " + rows);
        pstmt.close();
        
        subtitle("8.4 使用PreparedStatement查询数据");
        String querySQL = "SELECT * FROM students WHERE name = ?";
        pstmt = conn.prepareStatement(querySQL);
        pstmt.setString(1, "新学生");
        
        ResultSet rs = pstmt.executeQuery();
        println("查询结果：");
        while (rs.next()) {
            println("  ID: " + rs.getInt("id") +
                   ", 姓名: " + rs.getString("name") +
                   ", 年龄: " + rs.getInt("age") +
                   ", 成绩: " + rs.getDouble("score"));
        }
        rs.close();
        pstmt.close();
        
        subtitle("8.5 使用PreparedStatement删除数据");
        String deleteSQL = "DELETE FROM students WHERE name = ?";
        pstmt = conn.prepareStatement(deleteSQL);
        pstmt.setString(1, "新学生");
        
        rows = pstmt.executeUpdate();
        success("删除成功，影响行数: " + rows);
        pstmt.close();
        
        subtitle("8.6 JDBC最佳实践");
        info("关键点：");
        println("1. 使用PreparedStatement防止SQL注入");
        println("2. 及时关闭资源（Connection、Statement、ResultSet）");
        println("3. 使用try-with-resources自动关闭资源");
        println("4. 使用连接池管理数据库连接");
        println("5. 合理使用事务");
    }
    
    /**
     * 执行查询并打印结果
     */
    private static void executeQuery(Connection conn, String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        // 获取列信息
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        
        // 打印表头
        for (int i = 1; i <= columnCount; i++) {
            print(metaData.getColumnLabel(i) + "\t");
        }
        println();
        println("------------------------------------------------------");
        
        // 打印数据
        int rowCount = 0;
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                Object value = rs.getObject(i);
                print((value != null ? value.toString() : "NULL") + "\t");
            }
            println();
            rowCount++;
        }
        
        if (rowCount == 0) {
            println("(没有数据)");
        } else {
            println("------------------------------------------------------");
            info("共 " + rowCount + " 行");
        }
        
        rs.close();
        stmt.close();
        println();
    }
}
