# Java学习案例集

这是一套完整的Java核心知识学习案例，从基础到进阶，涵盖开发必备的所有重要知识点。

## 📚 学习内容

### 📘 Java基础部分（javaBasic）

### 1️⃣ src/javaBasic/basics/BasicSyntaxDemo.java - 基础语法
- 变量和数据类型（int, double, boolean, char, String）
- 条件语句（if-else, switch）
- 循环语句（for, while, do-while）
- 数组（一维数组、多维数组、遍历）

### 2️⃣ src/javaBasic/oop/OOPDemo.java - 面向对象编程
- 类和对象
- 构造函数（有参、无参）
- 封装（private属性 + getter/setter）
- 继承（extends）
- 多态（方法重写、父类引用指向子类对象）
- 接口（interface、implements）
- instanceof类型检查

### 3️⃣ src/javaBasic/collections/CollectionDemo.java - 集合框架
- **List**: ArrayList, LinkedList
- **Set**: HashSet, TreeSet
- **Map**: HashMap, LinkedHashMap
- Iterator（迭代器）
- 集合遍历和操作
- 集合排序和实用方法

### 4️⃣ src/javaBasic/lambda/LambdaStreamDemo.java - Lambda和Stream
- Lambda表达式语法
- 函数式接口
- Stream创建和操作
- 中间操作（filter, map, sorted, distinct, limit, skip）
- 终止操作（count, max, min, sum, average, collect）
- 对象流操作
- Collectors收集器

### 5️⃣ src/javaBasic/io/IODemo.java - IO流
- **File类** - 文件和目录操作
- **字节流** - InputStream、OutputStream
- **字符流** - Reader、Writer
- **缓冲流** - BufferedInputStream/OutputStream、BufferedReader/Writer
- **数据流** - DataInputStream/DataOutputStream
- **对象流** - ObjectInputStream/ObjectOutputStream（序列化）
- **文件复制** - 不同方式的文件复制实现
- **try-with-resources** - 自动资源管理
- **综合应用** - 文件统计、配置文件读取、日志追加

### 6️⃣ src/javaBasic/ExceptionDemo.java - 异常处理
- try-catch基本用法
- 多个catch块
- finally块
- throws和throw关键字
- 常见异常类型
- 自定义异常
- try-with-resources
- 异常链和最佳实践

### 📗 Java进阶部分（javaAdvanced）

### 7️⃣ src/javaAdvanced/threading/ThreadDemo.java - 多线程（重点）
- **Thread 和 Runnable** - 创建线程的两种方式
- **线程池（ThreadPoolExecutor）** - Executors工具类
- **synchronized 关键字** - 同步方法和同步块
- **Lock 接口** - ReentrantLock显式锁
- **线程安全问题演示** - 数据竞争和解决方案
- **Callable 和 Future** - 有返回值的任务
- **CountDownLatch** - 倒计时门闩
- **CyclicBarrier** - 循环栅栏
- **Semaphore** - 信号量
- **线程状态和生命周期**
- **java.util.concurrent 包** - 并发工具类

### 8️⃣ src/javaAdvanced/jvm/JVMDemo.java - JVM基础
- **JVM内存模型** - 堆、栈、方法区、程序计数器
- **垃圾回收机制** - GC算法和分代收集
- **类加载机制** - 加载、验证、准备、解析、初始化
- **类加载器** - Bootstrap、Extension、Application
- **双亲委派模型** - 类加载的安全机制
- **内存管理** - 对象分配和回收
- **JVM参数** - 常用配置参数
- **类初始化顺序** - 静态变量、静态块、实例变量、构造函数

### 9️⃣ src/javaAdvanced/reflection/ReflectionAnnotationDemo.java - 反射与注解
- **Class 类** - 获取Class对象的三种方式
- **反射创建对象** - Constructor.newInstance()
- **反射访问字段** - Field的get/set操作
- **反射调用方法** - Method.invoke()
- **访问私有成员** - setAccessible(true)
- **自定义注解** - @interface定义注解
- **注解解析** - 运行时读取注解信息
- **注解保留策略** - SOURCE、CLASS、RUNTIME
- **实际应用** - 简易ORM框架、对象工厂
- **反射的应用场景** - Spring、MyBatis等框架原理

### 🔟 src/javaAdvanced/generics/GenericsDemo.java - 泛型
- **为什么需要泛型** - 编译时类型检查，避免ClassCastException
- **泛型类** - Box<T>、Pair<K,V>、单参数和多参数泛型类
- **泛型方法** - <T> void method(T param)、有界类型参数
- **泛型接口** - Generator<T>、Comparator<T>
- **泛型通配符** - <?>无界、<? extends T>上界、<? super T>下界
- **PECS原则** - Producer Extends, Consumer Super
- **类型擦除** - 泛型信息在运行时被擦除
- **泛型的限制** - 不能实例化、不能创建数组、不能用基本类型
- **实际应用** - 泛型栈、泛型缓存、泛型工具类

### 1️⃣1️⃣ src/javaAdvanced/annotation/AnnotationDemo.java - 注解（深入）
- **注解基础** - 元数据、内置注解
- **元注解** - @Target、@Retention、@Documented、@Inherited
- **保留策略** - SOURCE、CLASS、RUNTIME详解
- **自定义注解** - 无参注解、单值注解、多值注解
- **注解元素类型** - 基本类型、String、Class、枚举、注解、数组
- **运行时注解处理** - 通过反射读取注解信息
- **实际应用1** - 权限检查（@RequirePermission）
- **实际应用2** - 自动化测试（@Test、@Before、@After）
- **实际应用3** - JSON序列化（@JsonField）
- **注解的应用场景** - 框架配置、权限控制、代码生成

### 📙 数据库部分（javaMysql）

### 1️⃣2️⃣ src/javaMysql/DatabaseDemo.java - 数据库基础（重点）
- **H2内存数据库** - 无需安装MySQL，兼容MySQL语法
- **SQL基础查询** - SELECT、WHERE、ORDER BY、LIMIT
- **条件查询** - AND、OR、LIKE、IN、BETWEEN
- **JOIN连接查询** - INNER JOIN、LEFT JOIN、多表连接
- **GROUP BY分组** - COUNT、SUM、AVG、MAX、MIN、HAVING
- **子查询** - WHERE子查询、FROM子查询、EXISTS子查询
- **主键和外键** - 唯一标识和引用完整性
- **索引** - 创建索引、索引的作用和代价
- **事务** - ACID特性、提交、回滚
- **数据库三范式** - 规范化设计原则
- **JDBC操作** - Connection、Statement、PreparedStatement
- **防止SQL注入** - PreparedStatement的使用
- **数据库设计实践** - 学生-班级-课程选课系统

## 🚀 如何运行

### 方法一：使用脚本（推荐）⭐

项目提供了便捷的脚本，无需手动编译！

#### 1. 构建项目
```bash
# 编译所有Java文件
./build.sh
```

编译成功后，会在 `out/production/javaStudy/` 目录生成 `.class` 文件。

#### 2. 运行程序
```bash
# 查看所有可运行的类
./run.sh

# 运行指定的类（Java基础）
./run.sh basics        # 运行基础语法示例
./run.sh collections   # 运行集合框架示例
./run.sh lambda        # 运行Lambda和Stream示例
./run.sh oop           # 运行面向对象编程示例
./run.sh io            # 运行IO流示例
./run.sh exception     # 运行异常处理示例

# 运行指定的类（Java进阶）
./run.sh threading     # 运行多线程示例
./run.sh jvm           # 运行JVM基础示例
./run.sh reflection    # 运行反射与注解示例
./run.sh generics      # 运行泛型示例
./run.sh annotation    # 运行注解示例

# 运行指定的类（数据库）
./run.sh database      # 运行数据库示例
```

**完整示例：**
```bash
# 一键编译并运行
./build.sh && ./run.sh basics
```

### 方法二：使用命令行手动编译

```bash
# 进入项目目录
cd /Users/unravel/IdeaProjects/javaStudy/

# 先编译工具类
javac -d out/production/javaStudy src/javaBasic/utils/PrintUtil.java

# 编译并运行指定文件
javac -d out/production/javaStudy -cp out/production/javaStudy src/javaBasic/basics/BasicSyntaxDemo.java
java -cp out/production/javaStudy javaBasic.basics.BasicSyntaxDemo
```

### 方法三：在IDE中运行

1. 用 IntelliJ IDEA 打开项目
2. 找到 `src/` 目录下的任意 `.java` 文件
3. 右键点击文件
4. 选择 "Run 'ClassName.main()'"

## 📖 学习建议

### 学习路线图（按难度递增）：

#### 🎯 阶段一：Java基础（5-7天）

1. **第1天-第2天**: `./run.sh basics` - 基础语法
   - 打好基础，熟练掌握基本语法
   - 多练习循环和数组

2. **第3天-第5天**: `./run.sh oop` - 面向对象
   - 理解面向对象的核心思想
   - 重点：封装、继承、多态
   - 多写几个类练习

3. **第6天-第7天**: `./run.sh collections` - 集合框架
   - 必须熟练掌握ArrayList和HashMap
   - 理解List、Set、Map的区别
   - 练习各种遍历方式

4. **第7天-第8天**: `./run.sh lambda` - Lambda和Stream
   - Java 8核心特性
   - 从简单的Lambda开始
   - 逐步掌握Stream API

5. **第10天-第11天**: `./run.sh io` - IO流
   - 掌握File类操作文件和目录
   - 理解字节流和字符流的区别
   - 学会使用缓冲流提高效率
   - 掌握文件复制、读写等常用操作
   - IO流是文件处理的基础

6. **第12天**: `./run.sh exception` - 异常处理
   - 学会处理异常
   - 理解try-catch-finally
   - 知道何时使用throws

#### 🎯 阶段二：Java进阶（10-15天）

7. **第13天-第17天**: `./run.sh threading` - 多线程（重点难点）
   - ⭐ 必须掌握：Thread、Runnable、线程池
   - ⭐ 必须掌握：synchronized、Lock
   - 理解线程安全问题和解决方案
   - 掌握常用并发工具类
   - 多线程是面试高频考点

8. **第18天-第22天**: `./run.sh jvm` - JVM基础
   - 理解JVM内存模型（堆、栈、方法区）
   - 了解垃圾回收机制和算法
   - 掌握类加载机制和过程
   - 理解双亲委派模型
   - 推荐阅读：《深入理解Java虚拟机》

9. **第23天-第27天**: `./run.sh reflection` - 反射与注解
   - 掌握Class类和反射API
   - 学会使用反射创建对象和调用方法
   - 理解注解的定义和使用
   - 了解框架底层原理（Spring、MyBatis）
   - 注意：反射影响性能，慎用

10. **第28天-第30天**: `./run.sh generics` - 泛型
   - 理解泛型的作用和好处
   - 掌握泛型类、泛型方法、泛型接口
   - 理解泛型通配符和PECS原则
   - 了解类型擦除机制
   - 泛型是集合框架的基础

11. **第31天-第33天**: `./run.sh annotation` - 注解（深入）
   - 掌握元注解的使用
   - 学会自定义注解
   - 掌握运行时注解处理
   - 了解注解的实际应用场景
   - 注解是框架开发的核心技术

#### 🎯 阶段三：数据库（7天）

12. **第34天-第40天**: `./run.sh database` - 数据库基础（必学）
   - ⭐ 必须掌握：SELECT、WHERE、ORDER BY、LIMIT
   - ⭐ 必须掌握：JOIN连接查询（INNER JOIN、LEFT JOIN）
   - ⭐ 必须掌握：GROUP BY分组和聚合函数
   - 理解子查询的使用场景
   - 掌握主键、外键、索引的概念
   - 理解事务的ACID特性
   - 学会使用JDBC操作数据库
   - 使用PreparedStatement防止SQL注入
   - 理解数据库三范式设计原则
   - 数据库是后端开发的核心技能

### 实践建议：

- ✅ **每个文件都要亲自运行**，观察输出结果
- ✅ **修改代码**，看看会发生什么
- ✅ **尝试自己写**类似的例子
- ✅ **理解注释**中的说明
- ✅ 遇到不懂的，**多运行几次**调试

## 💡 调试技巧

```java
// 使用System.out.println()调试
System.out.println("变量的值: " + variable);

// 查看对象内容
System.out.println("对象: " + object);

// 在关键位置输出信息
System.out.println("执行到这里了");
```

## 🎯 学习目标

### 完成基础部分后（6个案例），你将掌握：
- ✅ Java基础语法
- ✅ 面向对象编程
- ✅ 集合框架的使用
- ✅ Lambda和Stream API
- ✅ IO流操作（文件读写）
- ✅ 异常处理机制

### 完成进阶部分后（5个案例），你将掌握：
- ✅ 多线程编程和并发控制
- ✅ JVM内存管理和性能优化
- ✅ 反射机制和注解处理
- ✅ 泛型编程（类型安全）
- ✅ 自定义注解和运行时处理
- ✅ 理解主流框架的底层原理

### 完成数据库部分后（1个案例），你将掌握：
- ✅ SQL查询语句（增删改查）
- ✅ 多表连接和复杂查询
- ✅ 数据库设计和优化
- ✅ JDBC编程和事务管理
- ✅ 可以设计和操作关系型数据库

完成所有12个案例后，你将具备扎实的Java核心技能，可以：
- 🚀 独立开发完整的后端项目
- 🚀 学习Spring、Spring Boot、MyBatis等主流框架
- 🚀 应对大部分Java后端面试题
- 🚀 进行性能优化和问题排查
- 🚀 设计和实现数据库应用

## 📂 项目结构

```
javaStudy/
├── src/                                      # 源代码目录
│   ├── javaBasic/                            # Java基础知识
│   │   ├── basics/                           # 基础语法
│   │   │   └── BasicSyntaxDemo.java
│   │   ├── collections/                      # 集合框架
│   │   │   └── CollectionDemo.java          # 集合框架示例
│   │   ├── lambda/                           # Lambda和Stream
│   │   │   └── LambdaStreamDemo.java
│   │   ├── oop/                              # 面向对象
│   │   │   └── OOPDemo.java
│   │   ├── io/                               # IO流
│   │   │   └── IODemo.java
│   │   ├── utils/                            # 工具类
│   │   │   └── PrintUtil.java
│   │   └── ExceptionDemo.java                # 异常处理
│   ├── javaAdvanced/                         # Java进阶知识
│   │   ├── threading/                        # 多线程
│   │   │   └── ThreadDemo.java
│   │   ├── jvm/                              # JVM基础
│   │   │   └── JVMDemo.java
│   │   ├── reflection/                       # 反射与注解
│   │   │   └── ReflectionAnnotationDemo.java
│   │   ├── generics/                         # 泛型
│   │   │   └── GenericsDemo.java
│   │   └── annotation/                       # 注解（深入）
│   │       └── AnnotationDemo.java
│   └── javaMysql/                            # 数据库
│       └── DatabaseDemo.java                 # 数据库综合示例
├── lib/                                      # 第三方库
│   └── h2.jar                                # H2数据库驱动
├── out/                                      # 编译输出目录（运行build.sh后生成）
│   └── production/javaStudy/                 # .class文件存放位置
├── build.sh                                  # 构建脚本
├── run.sh                                    # 运行脚本
└── README.md                                 # 项目说明文档
```

## 📝 注意事项

1. 所有代码都有详细的中文注释
2. 每个案例都可以独立运行
3. 代码输出清晰，便于理解
4. 首次运行前必须先执行 `./build.sh` 编译项目
5. 删除 `out/` 目录后需要重新运行 `./build.sh`
6. ExceptionDemo.java 会创建一个 test_output.txt 文件（用于演示文件操作）
7. **IODemo.java 会在项目根目录创建 test_io 文件夹**，用于演示文件操作
8. ThreadDemo.java 运行时间较长（约30秒），因为包含多线程同步演示
9. JVMDemo.java 会触发垃圾回收，可以观察内存变化
10. **DatabaseDemo.java 使用H2内存数据库**，无需安装MySQL
11. H2数据库jar包（lib/h2.jar）已包含在项目中
12. 建议按照学习路线图的顺序学习，循序渐进

## 🔥 下一步学习方向

### 完成本项目所有案例后，推荐学习：

1. **框架学习**
   - Spring Framework（IoC、AOP）
   - Spring Boot（快速开发）
   - Spring MVC（Web开发）

2. **数据库进阶**
   - MyBatis ORM框架
   - JPA / Hibernate
   - 安装和使用真实MySQL
   - Redis缓存
   - 数据库性能优化

3. **Web开发**
   - Servlet / JSP
   - RESTful API设计
   - Spring Boot + MyBatis
   -前后端分离开发

4. **进阶技术**
   - 设计模式（23种设计模式）
   - 分布式系统（Spring Cloud）
   - 消息队列（RabbitMQ、Kafka）
   - 缓存技术（Redis）
   - 微服务架构

5. **推荐书籍**
   - 《Effective Java（第3版）》
   - 《深入理解Java虚拟机（第3版）》 - 周志明
   - 《Java并发编程实战》
   - 《Spring实战（第5版）》

## 💬 学习交流

遇到问题？
- 仔细阅读代码注释
- 运行代码观察输出
- 尝试修改代码看效果
- 查阅Java官方文档

记住：**实践是最好的学习方法！** 💪

加油！🚀
