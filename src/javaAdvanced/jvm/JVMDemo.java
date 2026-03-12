package javaAdvanced.jvm;

import static javaBasic.utils.PrintUtil.*;

/**
 * JVM基础学习案例
 * 包含：JVM内存模型、垃圾回收、类加载机制
 */
public class JVMDemo {
    
    // 静态变量（存储在方法区/元空间）
    private static String staticField = "我在方法区/元空间";
    
    // 实例变量（存储在堆）
    private String instanceField = "我在堆中";
    
    public static void main(String[] args) {
        
        title("1. JVM内存模型概述");
        
        info("JVM内存结构（运行时数据区）：");
        println("1. 堆（Heap）- 存储对象实例");
        println("2. 方法区/元空间（Method Area/Metaspace）- 存储类信息、静态变量、常量");
        println("3. 虚拟机栈（VM Stack）- 存储局部变量、方法调用");
        println("4. 本地方法栈（Native Method Stack）");
        println("5. 程序计数器（Program Counter）");
        
        
        title("2. 堆内存演示");
        
        subtitle("2.1 对象存储在堆中");
        JVMDemo obj1 = new JVMDemo();  // 对象存储在堆
        JVMDemo obj2 = new JVMDemo();
        
        println("obj1 哈希码: " + System.identityHashCode(obj1));
        println("obj2 哈希码: " + System.identityHashCode(obj2));
        println("两个对象在堆中的不同位置");
        
        subtitle("2.2 引用存储在栈中");
        println("obj1 和 obj2 是引用变量，存储在栈中");
        println("它们指向堆中的对象实例");
        
        
        title("3. 方法区/元空间演示");
        
        subtitle("3.1 静态变量");
        println("静态变量存储位置: 方法区/元空间");
        println("静态变量的值: " + staticField);
        
        subtitle("3.2 类信息");
        Class<?> clazz = JVMDemo.class;
        println("类名: " + clazz.getName());
        println("类加载器: " + clazz.getClassLoader());
        println("类的信息存储在: 方法区/元空间");
        
        subtitle("3.3 常量池");
        String s1 = "Hello";  // 字符串常量，存储在字符串常量池
        String s2 = "Hello";  // 引用同一个常量
        String s3 = new String("Hello");  // 新对象，存储在堆中
        
        println("s1 == s2: " + (s1 == s2) + " (都指向常量池中的同一个对象)");
        println("s1 == s3: " + (s1 == s3) + " (s3是新对象，在堆中)");
        println("s1.equals(s3): " + s1.equals(s3) + " (内容相同)");
        
        
        title("4. 虚拟机栈演示");
        
        subtitle("4.1 方法调用和栈帧");
        println("每次方法调用都会创建一个栈帧");
        methodA();
        
        subtitle("4.2 局部变量存储在栈中");
        int localVar = 100;  // 局部变量，存储在栈
        println("局部变量 localVar: " + localVar + " (存储在栈中)");
        
        
        title("5. 垃圾回收（GC）演示");
        
        subtitle("5.1 对象的创建和回收");
        println("创建大量对象，观察垃圾回收");
        
        // 获取运行时信息
        Runtime runtime = Runtime.getRuntime();
        
        println("初始内存状态:");
        printMemoryInfo(runtime);
        
        // 创建大量对象
        subtitle("5.2 创建100万个对象");
        for (int i = 0; i < 1000000; i++) {
            String temp = new String("临时对象 " + i);
            // temp会很快变成垃圾对象（没有引用指向它）
        }
        
        println("\n创建对象后内存状态:");
        printMemoryInfo(runtime);
        
        // 建议JVM进行垃圾回收
        subtitle("5.3 建议进行垃圾回收");
        println("调用 System.gc() 建议JVM进行垃圾回收");
        System.gc();  // 仅是建议，JVM可能不会立即执行
        
        // 等待GC完成
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        println("\n垃圾回收后内存状态:");
        printMemoryInfo(runtime);
        
        
        title("6. 垃圾回收算法概念");
        
        info("主要的垃圾回收算法：");
        println("1. 标记-清除（Mark-Sweep）");
        println("   - 标记所有存活对象");
        println("   - 清除未标记的对象");
        println("   - 缺点：产生内存碎片");
        
        println("\n2. 复制算法（Copying）");
        println("   - 将内存分为两块");
        println("   - 存活对象复制到另一块");
        println("   - 适用于年轻代（新生代）");
        
        println("\n3. 标记-整理（Mark-Compact）");
        println("   - 标记存活对象");
        println("   - 将存活对象移到一端");
        println("   - 适用于老年代");
        
        println("\n4. 分代收集（Generational Collection）");
        println("   - 新生代（Young Generation）：频繁GC");
        println("   - 老年代（Old Generation）：较少GC");
        println("   - 永久代/元空间（Metaspace）：存储类信息");
        
        
        title("7. 类加载机制");
        
        subtitle("7.1 类加载的时机");
        info("类加载发生在以下情况：");
        println("1. 创建类的实例（new）");
        println("2. 访问类的静态变量或方法");
        println("3. 使用反射");
        println("4. 初始化子类时会先加载父类");
        println("5. JVM启动时的主类");
        
        subtitle("7.2 类加载的过程");
        println("加载（Loading）→ 验证（Verification）→ 准备（Preparation）");
        println("→ 解析（Resolution）→ 初始化（Initialization）");
        
        subtitle("7.3 类加载器演示");
        ClassLoader classLoader = JVMDemo.class.getClassLoader();
        println("当前类的类加载器: " + classLoader);
        println("父类加载器: " + classLoader.getParent());
        println("根类加载器: " + classLoader.getParent().getParent() + " (Bootstrap ClassLoader，C++实现)");
        
        subtitle("7.4 类加载器层次");
        println("1. Bootstrap ClassLoader（启动类加载器）");
        println("   - 加载 JDK 核心类库（rt.jar）");
        println("   - C++ 实现，Java中为null");
        
        println("\n2. Extension ClassLoader（扩展类加载器）");
        println("   - 加载 JDK 扩展目录的类");
        
        println("\n3. Application ClassLoader（应用类加载器）");
        println("   - 加载应用程序类路径的类");
        println("   - 我们自己写的类由它加载");
        
        subtitle("7.5 双亲委派模型");
        println("类加载时，先委托父加载器加载");
        println("父加载器无法加载时，子加载器才会尝试加载");
        println("好处：防止核心类被篡改，保证安全性");
        
        
        title("8. 类的初始化顺序");
        
        println("触发类初始化：");
        InitializationDemo demo = new InitializationDemo();
        
        
        title("9. finalize方法（已过时）");
        
        warning("finalize()方法已被标记为过时，不推荐使用");
        println("对象被回收前会调用finalize()方法");
        println("但是：");
        println("- 无法保证何时执行");
        println("- 可能不会执行");
        println("- 推荐使用 try-with-resources 或 AutoCloseable");
        
        
        title("10. JVM参数示例");
        
        info("常用JVM参数：");
        println("-Xms: 初始堆大小，例如 -Xms512m");
        println("-Xmx: 最大堆大小，例如 -Xmx2g");
        println("-Xss: 栈大小，例如 -Xss256k");
        println("-XX:+PrintGCDetails: 打印GC详细信息");
        println("-XX:+UseG1GC: 使用G1垃圾回收器");
        println("-XX:MaxMetaspaceSize: 元空间最大大小");
        
        println("\n示例运行命令：");
        println("java -Xms256m -Xmx512m -XX:+PrintGCDetails MyClass");
        
        
        title("11. 实际内存查看");
        
        println("当前JVM内存配置：");
        long maxMemory = runtime.maxMemory();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        
        println("最大内存（-Xmx）: " + (maxMemory / 1024 / 1024) + " MB");
        println("总内存（-Xms）: " + (totalMemory / 1024 / 1024) + " MB");
        println("空闲内存: " + (freeMemory / 1024 / 1024) + " MB");
        println("已用内存: " + ((totalMemory - freeMemory) / 1024 / 1024) + " MB");
        
        
        line();
        success("JVM基础学习完成！");
        
        info("核心要点总结：");
        println("1. JVM内存分为：堆、方法区、栈、本地方法栈、程序计数器");
        println("2. 对象实例在堆中，类信息在方法区，局部变量在栈中");
        println("3. 垃圾回收主要针对堆内存");
        println("4. 类加载：加载 → 验证 → 准备 → 解析 → 初始化");
        println("5. 类加载器：Bootstrap → Extension → Application");
        println("6. 双亲委派模型保证类加载的安全性");
        
        info("\n推荐阅读：");
        println("《深入理解Java虚拟机（第3版）》 - 周志明");
    }
    
    // 辅助方法：打印内存信息
    private static void printMemoryInfo(Runtime runtime) {
        long total = runtime.totalMemory();
        long free = runtime.freeMemory();
        long used = total - free;
        
        println("  总内存: " + (total / 1024 / 1024) + " MB");
        println("  已用内存: " + (used / 1024 / 1024) + " MB");
        println("  空闲内存: " + (free / 1024 / 1024) + " MB");
    }
    
    // 演示方法调用栈
    private static void methodA() {
        println("进入 methodA（创建栈帧A）");
        methodB();
        println("退出 methodA（销毁栈帧A）");
    }
    
    private static void methodB() {
        println("  进入 methodB（创建栈帧B）");
        methodC();
        println("  退出 methodB（销毁栈帧B）");
    }
    
    private static void methodC() {
        println("    进入 methodC（创建栈帧C）");
        println("    退出 methodC（销毁栈帧C）");
    }
}


// 类初始化顺序演示
class InitializationDemo {
    // 1. 静态变量
    private static String staticVar = initStaticVar();
    
    // 2. 静态代码块
    static {
        System.out.println("  2. 静态代码块执行");
    }
    
    // 3. 实例变量
    private String instanceVar = initInstanceVar();
    
    // 4. 实例代码块
    {
        System.out.println("  4. 实例代码块执行");
    }
    
    // 5. 构造函数
    public InitializationDemo() {
        System.out.println("  5. 构造函数执行");
    }
    
    private static String initStaticVar() {
        System.out.println("  1. 静态变量初始化");
        return "static";
    }
    
    private String initInstanceVar() {
        System.out.println("  3. 实例变量初始化");
        return "instance";
    }
}
