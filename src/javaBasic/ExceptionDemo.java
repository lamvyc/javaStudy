package javaBasic;

import java.io.*;
import java.util.*;

/**
 * 异常处理学习案例
 * 包含：try/catch、throws、自定义异常
 */
public class ExceptionDemo {
    
    public static void main(String[] args) {
        
        System.out.println("=== 1. 基本try-catch ===");
        
        try {
            int result = 10 / 0;  // 会抛出ArithmeticException
            System.out.println("结果: " + result);
        } catch (ArithmeticException e) {
            System.out.println("捕获到异常: 不能除以零！");
            System.out.println("异常信息: " + e.getMessage());
        }
        
        System.out.println("程序继续执行...\n");
        
        
        System.out.println("=== 2. 多个catch块 ===");
        
        try {
            String str = null;
            System.out.println(str.length());  // NullPointerException
            
            int[] arr = new int[5];
            arr[10] = 100;  // ArrayIndexOutOfBoundsException
            
        } catch (NullPointerException e) {
            System.out.println("空指针异常: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("数组越界异常: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("其他异常: " + e.getMessage());
        }
        
        System.out.println();
        
        
        System.out.println("=== 3. try-catch-finally ===");
        
        try {
            System.out.println("执行try块");
            int result = 100 / 2;
            System.out.println("结果: " + result);
        } catch (Exception e) {
            System.out.println("执行catch块");
        } finally {
            // finally块总是会执行，常用于资源清理
            System.out.println("执行finally块（无论是否发生异常都会执行）");
        }
        
        System.out.println();
        
        
        System.out.println("=== 4. 常见异常类型 ===");
        
        // 4.1 ArithmeticException（算术异常）
        try {
            int x = 5 / 0;
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException: " + e.getMessage());
        }
        
        // 4.2 NullPointerException（空指针异常）
        try {
            String s = null;
            s.toLowerCase();
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: 对象为null");
        }
        
        // 4.3 ArrayIndexOutOfBoundsException（数组越界）
        try {
            int[] numbers = {1, 2, 3};
            int value = numbers[5];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException: 数组索引越界");
        }
        
        // 4.4 NumberFormatException（数字格式异常）
        try {
            int num = Integer.parseInt("abc");
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: 无法转换为数字");
        }
        
        // 4.5 ClassCastException（类型转换异常）
        try {
            Object obj = "Hello";
            Integer num = (Integer) obj;
        } catch (ClassCastException e) {
            System.out.println("ClassCastException: 类型转换失败");
        }
        
        System.out.println();
        
        
        System.out.println("=== 5. throws关键字（方法抛出异常）===");
        
        try {
            String content = readFile("test.txt");
            System.out.println(content);
        } catch (IOException e) {
            System.out.println("捕获到文件异常: " + e.getMessage());
        }
        
        System.out.println();
        
        
        System.out.println("=== 6. throw关键字（手动抛出异常）===");
        
        try {
            checkAge(15);
        } catch (IllegalArgumentException e) {
            System.out.println("年龄验证失败: " + e.getMessage());
        }
        
        try {
            checkAge(20);
            System.out.println("年龄验证通过");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println();
        
        
        System.out.println("=== 7. 自定义异常 ===");
        
        BankAccount account = new BankAccount(1000);
        
        // 正常取款
        try {
            account.withdraw(500);
            System.out.println("取款成功，余额: " + account.getBalance());
        } catch (InsufficientFundsException e) {
            System.out.println("取款失败: " + e.getMessage());
        }
        
        // 余额不足
        try {
            account.withdraw(800);
        } catch (InsufficientFundsException e) {
            System.out.println("取款失败: " + e.getMessage());
            System.out.println("当前余额: " + e.getCurrentBalance());
            System.out.println("需要金额: " + e.getAmountNeeded());
        }
        
        System.out.println();
        
        
        System.out.println("=== 8. try-with-resources（自动资源管理）===");
        
        // Java 7+ 特性：自动关闭资源
        String fileName = "test_output.txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("这是测试内容\n");
            writer.write("自动资源管理\n");
            System.out.println("文件写入成功: " + fileName);
        } catch (IOException e) {
            System.out.println("文件操作失败: " + e.getMessage());
        }
        // writer会自动关闭，不需要finally块
        
        System.out.println();
        
        
        System.out.println("=== 9. 异常链（异常传播）===");
        
        try {
            methodA();
        } catch (Exception e) {
            System.out.println("最终捕获异常: " + e.getMessage());
            System.out.println("原始异常: " + e.getCause().getMessage());
        }
        
        System.out.println();
        
        
        System.out.println("=== 10. 最佳实践 ===");
        
        // 捕获具体的异常，而不是Exception
        try {
            int[] arr = new int[3];
            arr[5] = 10;
        } catch (ArrayIndexOutOfBoundsException e) {  // 具体异常
            System.out.println("处理数组越界");
        }
        
        // 不要吞掉异常
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            // 不好的做法：什么都不做
            // 好的做法：至少记录日志
            System.out.println("发生异常，记录日志: " + e.getMessage());
        }
        
        System.out.println("\n程序正常结束");
    }
    
    
    // 使用throws声明方法可能抛出的异常
    public static String readFile(String fileName) throws IOException {
        // 模拟文件读取
        throw new IOException("文件不存在: " + fileName);
    }
    
    
    // 使用throw手动抛出异常
    public static void checkAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("年龄必须大于等于18岁");
        }
    }
    
    
    // 异常链示例
    public static void methodA() throws Exception {
        try {
            methodB();
        } catch (Exception e) {
            throw new Exception("方法A发生错误", e);  // 包装原始异常
        }
    }
    
    public static void methodB() throws Exception {
        throw new Exception("方法B的原始异常");
    }
}


// 自定义异常类：余额不足异常
class InsufficientFundsException extends Exception {
    private double currentBalance;
    private double amountNeeded;
    
    public InsufficientFundsException(double currentBalance, double amountNeeded) {
        super("余额不足！当前余额: " + currentBalance + "，需要: " + amountNeeded);
        this.currentBalance = currentBalance;
        this.amountNeeded = amountNeeded;
    }
    
    public double getCurrentBalance() {
        return currentBalance;
    }
    
    public double getAmountNeeded() {
        return amountNeeded;
    }
}


// 银行账户类
class BankAccount {
    private double balance;
    
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }
    
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(balance, amount);
        }
        balance -= amount;
    }
    
    public double getBalance() {
        return balance;
    }
}
