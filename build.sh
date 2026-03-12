#!/bin/zsh

# Java 项目构建脚本

echo "🔨 开始编译项目..."

# 创建输出目录
mkdir -p out/production/javaStudy

# 编译所有 Java 文件（先编译工具类）
javac -d out/production/javaStudy src/javaBasic/utils/PrintUtil.java

# 编译基础文件
javac -d out/production/javaStudy -cp out/production/javaStudy -sourcepath src \
  src/javaBasic/basics/BasicSyntaxDemo.java \
  src/javaBasic/collections/CollectionDemo.java \
  src/javaBasic/lambda/LambdaStreamDemo.java \
  src/javaBasic/oop/OOPDemo.java \
  src/javaBasic/io/IODemo.java \
  src/javaBasic/ExceptionDemo.java

# 编译进阶文件
javac -d out/production/javaStudy -cp out/production/javaStudy -sourcepath src \
  src/javaAdvanced/threading/ThreadDemo.java \
  src/javaAdvanced/jvm/JVMDemo.java \
  src/javaAdvanced/reflection/ReflectionAnnotationDemo.java \
  src/javaAdvanced/generics/GenericsDemo.java \
  src/javaAdvanced/annotation/AnnotationDemo.java

# 编译数据库文件（需要H2数据库依赖）
javac -d out/production/javaStudy -cp "out/production/javaStudy:lib/h2.jar" -sourcepath src \
  src/javaMysql/DatabaseDemo.java

# 检查编译是否成功
if [ $? -eq 0 ]; then
    echo "✅ 编译成功！"
    echo ""
    echo "📦 编译输出位置: out/production/javaStudy"
    echo ""
    echo "🚀 运行示例："
    echo ""
    echo "   【Java基础】"
    echo "   ./run.sh basics        # 基础语法"
    echo "   ./run.sh collections   # 集合框架"
    echo "   ./run.sh lambda        # Lambda和Stream"
    echo "   ./run.sh oop           # 面向对象"
    echo "   ./run.sh io            # IO流"
    echo "   ./run.sh exception     # 异常处理"
    echo ""
    echo "   【Java进阶】"
    echo "   ./run.sh threading     # 多线程"
    echo "   ./run.sh jvm           # JVM基础"
    echo "   ./run.sh reflection    # 反射与注解"
    echo "   ./run.sh generics      # 泛型"
    echo "   ./run.sh annotation    # 注解"
    echo ""
    echo "   【数据库】"
    echo "   ./run.sh database      # 数据库（SQL、JDBC、事务）"
else
    echo "❌ 编译失败，请检查错误信息"
    exit 1
fi
