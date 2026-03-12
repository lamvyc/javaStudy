# lib 目录说明

本目录存放项目所需的第三方库文件。

## 📦 包含的库

### h2.jar
- **版本**: 2.2.224
- **用途**: H2内存数据库驱动
- **大小**: 约2.5MB
- **官网**: https://www.h2database.com/

## 🔍 为什么使用H2？

1. **无需安装** - 纯Java实现，直接运行
2. **兼容MySQL** - 语法与MySQL几乎完全相同
3. **适合学习** - 轻量级，启动快速
4. **支持内存模式** - 数据存储在内存中，重启后清空
5. **IDE友好** - IDEA可以直接连接和查看数据

## 💡 使用方法

在DatabaseDemo.java中，已经配置好H2数据库连接：

```java
// H2内存数据库连接（兼容MySQL语法）
String url = "jdbc:h2:mem:testdb;MODE=MySQL;DB_CLOSE_DELAY=-1";
String user = "sa";
String password = "";
```

## 🚀 如何切换到真实MySQL

如果你想使用真实的MySQL数据库，只需要：

1. 安装MySQL数据库
2. 下载MySQL驱动jar包：mysql-connector-java-8.x.x.jar
3. 修改连接信息：
   ```java
   String url = "jdbc:mysql://localhost:3306/test?useSSL=false";
   String user = "root";
   String password = "your_password";
   ```

## 📚 更多信息

- H2官方文档: https://www.h2database.com/html/main.html
- H2 Maven仓库: https://mvnrepository.com/artifact/com.h2database/h2
