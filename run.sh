#!/bin/zsh

# Java 项目运行脚本

if [ $# -eq 0 ]; then
    echo "📚 可运行的类："
    echo ""
    echo "【Java基础 - javaBasic】"
    echo "  1. basics         - 基础语法"
    echo "  2. collections    - 集合框架"
    echo "  3. lambda         - Lambda 表达式和 Stream"
    echo "  4. oop            - 面向对象编程"
    echo "  5. io             - IO流（File、Stream、Reader、Writer）"
    echo "  6. exception      - 异常处理"
    echo ""
    echo "【Java进阶 - javaAdvanced】"
    echo "  7. threading      - 多线程（Thread、线程池、synchronized、Lock）"
    echo "  8. jvm            - JVM基础（内存模型、垃圾回收、类加载）"
    echo "  9. reflection     - 反射与注解（Class、反射创建对象、注解解析）"
    echo "  10. generics      - 泛型（泛型类、泛型方法、泛型接口）"
    echo "  11. annotation    - 注解（自定义注解、元注解、运行时注解）"
    echo ""
    echo "【数据库 - javaMysql】"
    echo "  12. database      - 数据库（SQL、JOIN、事务、索引、JDBC）"
    echo ""
    echo "用法: ./run.sh [类名]"
    echo "示例: ./run.sh collections"
    echo "示例: ./run.sh threading"
    echo "示例: ./run.sh database"
    exit 0
fi

# 类路径
CP="out/production/javaStudy"

# 根据参数运行对应的类
case "$1" in
    basics)
        echo "🚀 运行 BasicSyntaxDemo..."
        java -cp "$CP" javaBasic.basics.BasicSyntaxDemo
        ;;
    collections)
        echo "🚀 运行 CollectionDemo..."
        java -cp "$CP" javaBasic.collections.CollectionDemo
        ;;
    lambda)
        echo "🚀 运行 LambdaStreamDemo..."
        java -cp "$CP" javaBasic.lambda.LambdaStreamDemo
        ;;
    oop)
        echo "🚀 运行 OOPDemo..."
        java -cp "$CP" javaBasic.oop.OOPDemo
        ;;
    io)
        echo "🚀 运行 IODemo..."
        java -cp "$CP" javaBasic.io.IODemo
        ;;
    exception)
        echo "🚀 运行 ExceptionDemo..."
        java -cp "$CP" javaBasic.ExceptionDemo
        ;;
    threading)
        echo "🚀 运行 ThreadDemo..."
        java -cp "$CP" javaAdvanced.threading.ThreadDemo
        ;;
    jvm)
        echo "🚀 运行 JVMDemo..."
        java -cp "$CP" javaAdvanced.jvm.JVMDemo
        ;;
    reflection)
        echo "🚀 运行 ReflectionAnnotationDemo..."
        java -cp "$CP" javaAdvanced.reflection.ReflectionAnnotationDemo
        ;;
    generics)
        echo "🚀 运行 GenericsDemo..."
        java -cp "$CP" javaAdvanced.generics.GenericsDemo
        ;;
    annotation)
        echo "🚀 运行 AnnotationDemo..."
        java -cp "$CP" javaAdvanced.annotation.AnnotationDemo
        ;;
    database)
        echo "🚀 运行 DatabaseDemo..."
        java -cp "out/production/javaStudy:lib/h2.jar" javaMysql.DatabaseDemo
        ;;
    *)
        echo "❌ 未知的类: $1"
        echo "运行 './run.sh' 查看可用的类"
        exit 1
        ;;
esac
