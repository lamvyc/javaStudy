/*
 * package 声明：
 * - package javaBasic.basics; 表示这个类属于 javaBasic.basics 包
 * - 包名必须和文件夹名称一致！
 * - 文件路径：src/javaBasic/basics/BasicSyntaxDemo.java
 * - 包名就是：javaBasic.basics（对应 javaBasic/basics 文件夹）
 * 
 * 如果文件在更深的目录，如：src/com/example/utils/Tool.java
 * 那么包名就是：package com.example.utils;
 * 包名即路径，路径即包名
 */
package javaBasic.basics;

/*
 * import 导入：
 * - import static javaBasic.utils.PrintUtil.*; 表示导入 javaBasic.utils 包下的 PrintUtil 类的所有静态方法
 * - javaBasic.utils 对应 src/javaBasic/utils/ 文件夹
 * - PrintUtil 对应 src/javaBasic/utils/PrintUtil.java 文件
 */
import static javaBasic.utils.PrintUtil.*;

/**
 * Java基础语法学习案例
 * 包含：变量、数据类型、条件语句、循环、数组
 */

/*
 * 程序入口 main 方法解释：
 * public  - 公开的，让JVM能从外部访问 【不能改】
 * static  - 静态的，程序启动时还没有对象，必须通过类名直接调用 【不能改】
 * void    - 无返回值，程序入口不需要返回值 【不能改】
 * main    - 固定方法名，JVM寻找这个名字作为程序入口 【不能改】
 * String[] args - 命令行参数数组（例：java BasicSyntaxDemo 参数1 参数2）【args可以改名】
 * 
 * 重要提示：
 * ✗ public static void main 这4个关键字一个都不能改，否则JVM找不到入口
 * ✓ args 可以改成任何名字，如：arguments、params、a 等都可以
 * 
 * === 为什么 main 必须是 static？===
 * 
 * 如果 main 不是 static：
 * 1. JVM启动程序时：需要先创建 BasicSyntaxDemo 对象
 * 2. 创建对象需要调用构造方法
 * 3. 但构造方法也在 BasicSyntaxDemo 类里
 * 4. 这就陷入死循环了！（鸡生蛋还是蛋生鸡？）
 * 
 * 有了 static：
 * JVM 可以直接调用：BasicSyntaxDemo.main(args)  // 不需要对象！
 * 
 * 类比理解：
 * - static 方法 = 公司的公共设施（会议室），任何人都能用，不属于某个员工
 * - 非 static 方法 = 员工的私人物品（手机），必须先有这个员工才能用他的手机
 */
public class BasicSyntaxDemo {
    public static void main(String[] args) {
        title("1. 变量和数据类型");
        
        // 基本数据类型
        int age = 25;                    // 整数
        double salary = 8888.88;         // 浮点数
        boolean isStudent = true;        // 布尔值
        char grade = 'A';                // 字符（单引号，只能存1个字符）
        String name = "张三";            // 字符串（双引号，可以存多个字符，引用类型）
        
        println("姓名: " + name);
        println("年龄: " + age);
        println("工资: " + salary);
        println("是否学生: " + isStudent);
        println("等级: " + grade);
        
        subtitle("char 和 String 的区别");
        
        // char：只能存储1个字符，用单引号 ''
        char letter1 = 'A';              // ✓ 正确：1个字符
        char letter2 = '中';             // ✓ 正确：1个中文字符
        // char letter3 = 'AB';          // ✗ 错误：不能存储多个字符
        // char letter4 = "A";           // ✗ 错误：不能用双引号
        
        // String：可以存储多个字符（包括0个），用双引号 ""
        String str1 = "A";               // ✓ 正确：1个字符
        String str2 = "ABC";             // ✓ 正确：多个字符
        String str3 = "你好世界";       // ✓ 正确：中文字符串
        String str4 = "";                // ✓ 正确：空字符串（0个字符）
        // String str5 = 'A';            // ✗ 错误：String不能用单引号
        
        println("char 类型: " + letter1 + " (只能存1个字符，用单引号)");
        println("String 类型: " + str1 + " (可以存多个字符，用双引号)");
        println("String 可以存多个: " + str2);
        println("String 可以为空: '" + str4 + "'");
        
        // 关键区别
        println("\n✓ 可以用 String 存储单个字符 'A'，但要写成 \"A\"（双引号）");
        println("✗ 不能用 char 存储 \"A\"（双引号），char 只能用 'A'（单引号）");
        
        
        title("2. 条件语句");
        
        subtitle("if-else 语句");
        int score = 85;
        if (score >= 90) {
            println("优秀");
        } else if (score >= 80) {
            println("良好");
        } else if (score >= 60) {
            println("及格");
        } else {
            println("不及格");
        }
        
        subtitle("switch 语句");
        int day = 3;
        switch (day) {
            case 1:
                println("星期一");
                break;
            case 2:
                println("星期二");
                break;
            case 3:
                println("星期三");
                break;
            default:
                println("其他日期");
        }
        
        
        title("3. 循环语句");
        
        subtitle("for 循环");
        print("输出1-5: ");
        for (int i = 1; i <= 5; i++) {
            print(i + " ");
        }
        println();
        
        subtitle("while 循环");
        print("倒计时: ");
        int count = 5;
        while (count > 0) {
            print(count + " ");
            count--;
        }
        println();
        
        subtitle("do-while 循环");
        int num = 0;
        do {
            println("执行了do-while，num = " + num);
            num++;
        } while (num < 1);
        
        
        title("4. 数组");
        
        // 声明和初始化数组
        int[] numbers = {10, 20, 30, 40, 50};
        println("数组长度: " + numbers.length);
        
        subtitle("使用for循环遍历数组");
        print("数组元素: ");
        for (int i = 0; i < numbers.length; i++) {
            print(numbers[i] + " ");
        }
        println();
        
        subtitle("使用for-each遍历数组");
        print("数组元素: ");
        for (int n : numbers) {
            print(n + " ");
        }
        println();
        
        subtitle("二维数组");
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                print(matrix[i][j] + " ");
            }
            println();
        }
        
        
        title("5. static 关键字演示");
        
        // static 方法：可以直接通过类名调用，不需要创建对象
        staticMethodDemo();  // 可以直接调用
        
        // 非 static 方法：必须先创建对象才能调用
        BasicSyntaxDemo demo = new BasicSyntaxDemo();  // 创建对象
        demo.nonStaticMethodDemo();  // 通过对象调用
        
        println("\n理解 static：");
        println("✓ static 方法属于类，任何时候都能用（不需要对象）");
        println("✓ 非 static 方法属于对象，必须先创建对象才能用");
        
        line();
        success("基础语法学习完成！");
    }
    
    // static 方法 - 属于类，可以直接调用
    public static void staticMethodDemo() {
        println("我是 static 方法，不需要创建对象就能调用！");
    }
    
    // 非 static 方法 - 属于对象，必须先 new 对象才能调用
    public void nonStaticMethodDemo() {
        println("我是非 static 方法，必须先创建对象才能调用我！");
    }
}
