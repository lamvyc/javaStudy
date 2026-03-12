package javaBasic.utils;

/**
 * 控制台打印工具类
 * 提供便捷的打印方法，简化System.out.println()的使用
 */
public class PrintUtil {
    
    // 普通打印
    public static void print(Object obj) {
        System.out.print(obj);
    }
    
    // 打印并换行
    public static void println(Object obj) {
        System.out.println(obj);
    }
    
    // 空行
    public static void println() {
        System.out.println();
    }
    
    // 打印标题（带分隔符）
    public static void title(String title) {
        System.out.println("\n=== " + title + " ===");
    }
    
    // 打印子标题
    public static void subtitle(String subtitle) {
        System.out.println("\n--- " + subtitle + " ---");
    }
    
    // 打印分隔线
    public static void line() {
        System.out.println("----------------------------------------");
    }
    
    // 打印分隔线（自定义字符）
    public static void line(char ch, int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(ch);
        }
        System.out.println();
    }
    
    // 格式化打印（类似printf）
    public static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }
    
    // 打印数组
    public static void printArray(int[] arr) {
        print("[");
        for (int i = 0; i < arr.length; i++) {
            print(arr[i]);
            if (i < arr.length - 1) print(", ");
        }
        println("]");
    }
    
    // 打印数组（String）
    public static void printArray(String[] arr) {
        print("[");
        for (int i = 0; i < arr.length; i++) {
            print(arr[i]);
            if (i < arr.length - 1) print(", ");
        }
        println("]");
    }
    
    // 打印成功信息（绿色效果用✓表示）
    public static void success(String message) {
        System.out.println("✓ " + message);
    }
    
    // 打印错误信息（红色效果用✗表示）
    public static void error(String message) {
        System.out.println("✗ " + message);
    }
    
    // 打印警告信息
    public static void warning(String message) {
        System.out.println("⚠ " + message);
    }
    
    // 打印信息（带图标）
    public static void info(String message) {
        System.out.println("ℹ " + message);
    }
}
