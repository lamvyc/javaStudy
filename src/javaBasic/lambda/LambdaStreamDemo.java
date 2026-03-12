package javaBasic.lambda;

import java.util.*;
import java.util.stream.*;
import static javaBasic.utils.PrintUtil.*;

/**
 * Lambda表达式和Stream API学习案例
 * Java 8 核心特性
 */
public class LambdaStreamDemo {
    
    public static void main(String[] args) {
        
        title("1. Lambda表达式基础");
        
        // 传统方式 vs Lambda方式
        subtitle("传统方式创建线程");
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                println("传统方式创建线程");
            }
        });
        thread1.start();
        
        subtitle("Lambda方式创建线程");
        Thread thread2 = new Thread(() -> println("Lambda方式创建线程（更简洁）"));
        thread2.start();
        
        
        title("2. Lambda表达式语法");
        
        // 无参数
        Runnable r1 = () -> println("无参数Lambda");
        r1.run();
        
        // 一个参数
        MyInterface1 func1 = (s) -> println("参数: " + s);
        func1.print("Hello");
        
        // 多个参数
        MyInterface2 func2 = (a, b) -> a + b;
        println("5 + 3 = " + func2.add(5, 3));
        
        // 多行代码块
        MyInterface2 func3 = (a, b) -> {
            int sum = a + b;
            println("计算中...");
            return sum;
        };
        println("10 + 20 = " + func3.add(10, 20));
        
        
        title("3. 集合中使用Lambda");
        
        List<String> names = Arrays.asList("张三", "李四", "王五", "赵六");
        
        subtitle("传统方式遍历");
        for (String name : names) {
            print(name + " ");
        }
        println();
        
        subtitle("Lambda方式遍历");
        names.forEach(name -> print(name + " "));
        println();
        
        subtitle("方法引用（更简洁）");
        names.forEach(System.out::println);
        
        
        title("4. Stream API - 创建流");
        
        info("从集合、数组、Stream.of()创建流");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream1 = numbers.stream();
        
        String[] arr = {"a", "b", "c"};
        Stream<String> stream2 = Arrays.stream(arr);
        
        Stream<String> stream3 = Stream.of("Java", "Python", "C++");
        println("流创建完成");
        
        
        title("5. Stream API - 中间操作");
        
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        subtitle("filter：过滤");
        print("偶数: ");
        nums.stream()
            .filter(n -> n % 2 == 0)
            .forEach(n -> print(n + " "));
        println();
        
        subtitle("map：转换");
        print("每个数乘以2: ");
        nums.stream()
            .map(n -> n * 2)
            .forEach(n -> print(n + " "));
        println();
        
        subtitle("sorted：排序");
        List<Integer> unsorted = Arrays.asList(5, 2, 8, 1, 9);
        print("排序: ");
        unsorted.stream()
                .sorted()
                .forEach(n -> print(n + " "));
        println();
        
        subtitle("distinct：去重");
        List<Integer> withDuplicates = Arrays.asList(1, 2, 2, 3, 3, 4);
        print("去重: ");
        withDuplicates.stream()
                      .distinct()
                      .forEach(n -> print(n + " "));
        println();
        
        subtitle("limit：限制数量");
        print("前3个元素: ");
        nums.stream()
            .limit(3)
            .forEach(n -> print(n + " "));
        println();
        
        subtitle("skip：跳过元素");
        print("跳过前5个: ");
        nums.stream()
            .skip(5)
            .forEach(n -> print(n + " "));
        println();
        
        
        title("6. Stream API - 终止操作");
        
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        
        // count：计数
        long count = list.stream().count();
        println("元素个数: " + count);
        
        // max/min：最大最小值
        Optional<Integer> max = list.stream().max(Integer::compareTo);
        Optional<Integer> min = list.stream().min(Integer::compareTo);
        println("最大值: " + max.get());
        println("最小值: " + min.get());
        
        // sum：求和
        int sum = list.stream().mapToInt(Integer::intValue).sum();
        println("总和: " + sum);
        
        // average：平均值
        OptionalDouble avg = list.stream().mapToInt(Integer::intValue).average();
        println("平均值: " + avg.getAsDouble());
        
        // anyMatch, allMatch, noneMatch
        boolean hasEven = list.stream().anyMatch(n -> n % 2 == 0);
        boolean allPositive = list.stream().allMatch(n -> n > 0);
        boolean noNegative = list.stream().noneMatch(n -> n < 0);
        println("是否有偶数: " + hasEven);
        println("是否全为正数: " + allPositive);
        println("是否没有负数: " + noNegative);
        
        
        title("7. Stream API - 复杂操作");
        
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");
        
        subtitle("链式操作：过滤 → 转换 → 排序");
        println("长度>5的单词，转大写并排序:");
        words.stream()
             .filter(w -> w.length() > 5)
             .map(String::toUpperCase)
             .sorted()
             .forEach(s -> println(s));
        
        
        title("8. 对象流操作");
        
        // 创建学生对象列表
        List<Student> students = Arrays.asList(
            new Student("张三", 85, 20),
            new Student("李四", 92, 22),
            new Student("王五", 78, 21),
            new Student("赵六", 88, 20),
            new Student("钱七", 95, 23)
        );
        
        subtitle("过滤成绩>=90的学生");
        students.stream()
                .filter(s -> s.getScore() >= 90)
                .forEach(s -> println(s.getName() + ": " + s.getScore()));
        
        subtitle("获取所有学生姓名");
        List<String> studentNames = students.stream()
                                            .map(Student::getName)
                                            .collect(Collectors.toList());
        println(studentNames);
        
        subtitle("按成绩排序");
        students.stream()
                .sorted(Comparator.comparing(Student::getScore))
                .forEach(s -> println(s.getName() + ": " + s.getScore()));
        
        subtitle("计算平均成绩");
        double avgScore = students.stream()
                                  .mapToInt(Student::getScore)
                                  .average()
                                  .getAsDouble();
        println("平均成绩: " + avgScore);
        
        subtitle("按年龄分组");
        Map<Integer, List<Student>> groupedByAge = students.stream()
                .collect(Collectors.groupingBy(Student::getAge));
        groupedByAge.forEach((age, stuList) -> {
            println("年龄 " + age + ": " + 
                stuList.stream()
                       .map(Student::getName)
                       .collect(Collectors.joining(", ")));
        });
        
        
        title("9. Collectors收集器");
        
        List<Integer> nums2 = Arrays.asList(1, 2, 3, 4, 5);
        
        // 转换为List
        List<Integer> list1 = nums2.stream()
                                   .filter(n -> n > 2)
                                   .collect(Collectors.toList());
        println("转换为List: " + list1);
        
        // 转换为Set
        Set<Integer> set1 = nums2.stream()
                                 .collect(Collectors.toSet());
        println("转换为Set: " + set1);
        
        // 连接字符串
        String joined = words.stream()
                            .collect(Collectors.joining(", "));
        println("连接字符串: " + joined);
        
        // 统计信息
        IntSummaryStatistics stats = nums2.stream()
                                          .mapToInt(Integer::intValue)
                                          .summaryStatistics();
        println("统计信息: " + stats);
        
        line();
        success("Lambda和Stream学习完成！");
    }
}


// 函数式接口定义
@FunctionalInterface
interface MyInterface1 {
    void print(String s);
}

@FunctionalInterface
interface MyInterface2 {
    int add(int a, int b);
}


// 学生类
class Student {
    private String name;
    private int score;
    private int age;
    
    public Student(String name, int score, int age) {
        this.name = name;
        this.score = score;
        this.age = age;
    }
    
    public String getName() { return name; }
    public int getScore() { return score; }
    public int getAge() { return age; }
}
