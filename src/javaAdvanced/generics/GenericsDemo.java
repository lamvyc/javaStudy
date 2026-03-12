package javaAdvanced.generics;

import java.util.*;
import static javaBasic.utils.PrintUtil.*;

/**
 * 泛型学习案例
 * 包含：泛型类、泛型方法、泛型接口
 */
public class GenericsDemo {
    
    public static void main(String[] args) {
        
        title("泛型学习案例");
        
        // 1. 为什么需要泛型
        demonstrateWhyGenerics();
        
        // 2. 泛型类
        demonstrateGenericClass();
        
        // 3. 泛型方法
        demonstrateGenericMethod();
        
        // 4. 泛型接口
        demonstrateGenericInterface();
        
        // 5. 泛型通配符
        demonstrateWildcard();
        
        // 6. 类型擦除
        demonstrateTypeErasure();
        
        // 7. 泛型的限制
        demonstrateGenericRestrictions();
        
        // 8. 实际应用
        demonstratePractical();
        
        line();
        success("泛型学习完成！");
        
        info("\n核心要点总结：");
        println("1. 泛型提供编译时类型检查，避免运行时ClassCastException");
        println("2. 泛型类：class Box<T> { ... }");
        println("3. 泛型方法：<T> void method(T param) { ... }");
        println("4. 泛型接口：interface List<E> { ... }");
        println("5. 通配符：? extends T（上界）、? super T（下界）");
        println("6. 类型擦除：泛型信息只存在于编译期");
        println("7. 不能实例化泛型数组：new T[10] ✗");
    }
    
    /**
     * 1. 为什么需要泛型
     */
    private static void demonstrateWhyGenerics() {
        title("1. 为什么需要泛型");
        
        subtitle("1.1 没有泛型的问题");
        info("JDK 1.5之前的集合类：");
        
        // 使用原始类型（不推荐）
        List list = new ArrayList();
        list.add("Hello");
        list.add(123);  // 可以添加任何类型
        list.add(3.14);
        
        println("列表内容: " + list);
        
        warning("问题1：可以添加任何类型的对象，没有类型限制");
        
        // 取出数据需要强制类型转换
        try {
            String str = (String) list.get(0);  // OK
            println("第1个元素: " + str);
            
            String str2 = (String) list.get(1);  // 运行时错误！
            println("第2个元素: " + str2);
        } catch (ClassCastException e) {
            error("问题2：运行时类型转换异常 - " + e.getMessage());
        }
        
        subtitle("1.2 使用泛型的好处");
        // 使用泛型
        List<String> stringList = new ArrayList<>();
        stringList.add("Hello");
        // stringList.add(123);  // 编译错误！类型不匹配
        
        String str = stringList.get(0);  // 不需要类型转换
        println("第1个元素: " + str);
        
        success("优点1：编译时类型检查，避免运行时错误");
        success("优点2：不需要强制类型转换，代码更简洁");
        
        println();
    }
    
    /**
     * 2. 泛型类
     */
    private static void demonstrateGenericClass() {
        title("2. 泛型类");
        
        subtitle("2.1 单个类型参数");
        Box<String> stringBox = new Box<>("Hello");
        println("String盒子: " + stringBox.get());
        
        Box<Integer> intBox = new Box<>(100);
        println("Integer盒子: " + intBox.get());
        
        Box<Double> doubleBox = new Box<>(3.14);
        println("Double盒子: " + doubleBox.get());
        
        subtitle("2.2 多个类型参数");
        Pair<String, Integer> pair = new Pair<>("张三", 25);
        println("姓名: " + pair.getFirst() + ", 年龄: " + pair.getSecond());
        
        Pair<Integer, String> reversePair = new Pair<>(1, "第一名");
        println("排名: " + reversePair.getFirst() + ", 描述: " + reversePair.getSecond());
        
        subtitle("2.3 泛型类的继承");
        StringBox sb = new StringBox("专门存储String");
        println("StringBox内容: " + sb.get());
        
        subtitle("2.4 类型参数命名规范");
        info("常用类型参数名称：");
        println("  E - Element（元素，集合中常用）");
        println("  T - Type（类型）");
        println("  K - Key（键）");
        println("  V - Value（值）");
        println("  N - Number（数字）");
        println("  ? - 通配符");
        
        println();
    }
    
    /**
     * 3. 泛型方法
     */
    private static void demonstrateGenericMethod() {
        title("3. 泛型方法");
        
        subtitle("3.1 基本泛型方法");
        printValue("Hello");
        printValue(123);
        printValue(3.14);
        printValue(true);
        
        subtitle("3.2 泛型方法返回值");
        Integer[] intArray = {1, 2, 3, 4, 5};
        Integer first = getFirst(intArray);
        println("数组第一个元素: " + first);
        
        String[] strArray = {"A", "B", "C"};
        String firstStr = getFirst(strArray);
        println("数组第一个元素: " + firstStr);
        
        subtitle("3.3 交换数组元素");
        Integer[] numbers = {1, 2, 3};
        println("交换前: " + Arrays.toString(numbers));
        swap(numbers, 0, 2);
        println("交换后: " + Arrays.toString(numbers));
        
        subtitle("3.4 多个类型参数的方法");
        printPair("姓名", "张三");
        printPair(1, "第一");
        printPair(true, 100);
        
        subtitle("3.5 有界类型参数");
        Integer[] nums = {5, 2, 8, 1, 9};
        Integer max = findMax(nums);
        println("最大值: " + max);
        
        Double[] doubles = {3.14, 2.71, 1.41};
        Double maxDouble = findMax(doubles);
        println("最大值: " + maxDouble);
        
        println();
    }
    
    /**
     * 4. 泛型接口
     */
    private static void demonstrateGenericInterface() {
        title("4. 泛型接口");
        
        subtitle("4.1 实现泛型接口 - 指定具体类型");
        Generator<String> stringGen = new StringGenerator();
        println("生成String: " + stringGen.generate());
        println("生成String: " + stringGen.generate());
        
        subtitle("4.2 实现泛型接口 - 保持泛型");
        Generator<Integer> intGen = new RandomGenerator<>();
        println("生成Integer: " + intGen.generate());
        println("生成Integer: " + intGen.generate());
        
        subtitle("4.3 比较器示例");
        List<Person> people = Arrays.asList(
            new Person("张三", 25),
            new Person("李四", 30),
            new Person("王五", 20)
        );
        
        println("排序前: " + people);
        Collections.sort(people, new AgeComparator());
        println("按年龄排序: " + people);
        
        println();
    }
    
    /**
     * 5. 泛型通配符
     */
    private static void demonstrateWildcard() {
        title("5. 泛型通配符");
        
        subtitle("5.1 无界通配符 <?>");
        List<Integer> intList = Arrays.asList(1, 2, 3);
        List<String> strList = Arrays.asList("A", "B", "C");
        
        printList(intList);
        printList(strList);
        
        subtitle("5.2 上界通配符 <? extends T>");
        info("只能读取，不能写入（除了null）");
        
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3);
        
        double sum1 = sumOfList(integers);
        double sum2 = sumOfList(doubles);
        println("Integer列表总和: " + sum1);
        println("Double列表总和: " + sum2);
        
        subtitle("5.3 下界通配符 <? super T>");
        info("只能写入，不能读取（除了Object）");
        
        List<Number> numbers = new ArrayList<>();
        addNumbers(numbers);
        println("添加后的列表: " + numbers);
        
        subtitle("5.4 PECS原则");
        info("Producer Extends, Consumer Super");
        println("✓ 如果需要从集合读取T类型数据，使用 ? extends T");
        println("✓ 如果需要向集合写入T类型数据，使用 ? super T");
        println("✓ 如果既读又写，不使用通配符");
        
        println();
    }
    
    /**
     * 6. 类型擦除
     */
    private static void demonstrateTypeErasure() {
        title("6. 类型擦除");
        
        subtitle("6.1 什么是类型擦除");
        info("泛型信息只存在于编译期，运行时会被擦除");
        
        Box<String> stringBox = new Box<>("Hello");
        Box<Integer> intBox = new Box<>(123);
        
        // 运行时类型相同
        println("stringBox类型: " + stringBox.getClass().getName());
        println("intBox类型: " + intBox.getClass().getName());
        println("类型相同: " + (stringBox.getClass() == intBox.getClass()));
        
        subtitle("6.2 类型擦除的影响");
        warning("不能使用instanceof检查泛型类型：");
        println("// if (obj instanceof Box<String>) { ... }  // 编译错误");
        println("✓ if (obj instanceof Box<?>) { ... }  // 正确");
        
        println();
    }
    
    /**
     * 7. 泛型的限制
     */
    private static void demonstrateGenericRestrictions() {
        title("7. 泛型的限制");
        
        info("泛型的使用限制：");
        println("1. ✗ 不能实例化类型参数：new T()");
        println("2. ✗ 不能创建泛型数组：new T[10]");
        println("3. ✗ 不能使用基本类型：Box<int>（要用Box<Integer>）");
        println("4. ✗ 不能在静态上下文中使用类型参数");
        println("5. ✗ 不能创建泛型异常类");
        println("6. ✗ 不能在catch中使用类型参数");
        
        subtitle("示例：泛型数组的替代方案");
        // 不能直接创建泛型数组，但可以这样：
        @SuppressWarnings("unchecked")
        Box<String>[] boxes = (Box<String>[]) new Box<?>[10];
        boxes[0] = new Box<>("第一个");
        println("泛型数组元素: " + boxes[0].get());
        
        println();
    }
    
    /**
     * 8. 实际应用
     */
    private static void demonstratePractical() {
        title("8. 实际应用");
        
        subtitle("8.1 自定义泛型栈");
        Stack<String> stack = new Stack<>();
        stack.push("First");
        stack.push("Second");
        stack.push("Third");
        
        println("栈大小: " + stack.size());
        println("弹出: " + stack.pop());
        println("查看顶部: " + stack.peek());
        println("弹出: " + stack.pop());
        println("剩余大小: " + stack.size());
        
        subtitle("8.2 泛型缓存");
        Cache<String, Person> cache = new Cache<>();
        cache.put("p1", new Person("张三", 25));
        cache.put("p2", new Person("李四", 30));
        
        Person p = cache.get("p1");
        println("从缓存获取: " + p);
        println("缓存大小: " + cache.size());
        
        subtitle("8.3 泛型工具类");
        List<String> list = Arrays.asList("A", "B", "C", "D", "E");
        List<String> reversed = Utils.reverse(list);
        println("原列表: " + list);
        println("反转后: " + reversed);
        
        String[] array = {"X", "Y", "Z"};
        Utils.printArray(array);
        
        println();
    }
    
    // ========== 辅助方法 ==========
    
    // 泛型方法：打印值
    private static <T> void printValue(T value) {
        println("值: " + value + ", 类型: " + value.getClass().getSimpleName());
    }
    
    // 泛型方法：获取数组第一个元素
    private static <T> T getFirst(T[] array) {
        return array.length > 0 ? array[0] : null;
    }
    
    // 泛型方法：交换数组元素
    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    // 泛型方法：打印键值对
    private static <K, V> void printPair(K key, V value) {
        println("键: " + key + ", 值: " + value);
    }
    
    // 有界类型参数：找最大值
    private static <T extends Comparable<T>> T findMax(T[] array) {
        T max = array[0];
        for (T element : array) {
            if (element.compareTo(max) > 0) {
                max = element;
            }
        }
        return max;
    }
    
    // 通配符方法：打印列表
    private static void printList(List<?> list) {
        println("列表内容: " + list);
    }
    
    // 上界通配符：计算总和
    private static double sumOfList(List<? extends Number> list) {
        double sum = 0.0;
        for (Number num : list) {
            sum += num.doubleValue();
        }
        return sum;
    }
    
    // 下界通配符：添加数字
    private static void addNumbers(List<? super Integer> list) {
        list.add(1);
        list.add(2);
        list.add(3);
    }
}


// ========== 泛型类定义 ==========

/**
 * 泛型类：盒子
 */
class Box<T> {
    private T content;
    
    public Box(T content) {
        this.content = content;
    }
    
    public T get() {
        return content;
    }
    
    public void set(T content) {
        this.content = content;
    }
}

/**
 * 泛型类：键值对
 */
class Pair<K, V> {
    private K first;
    private V second;
    
    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }
    
    public K getFirst() {
        return first;
    }
    
    public V getSecond() {
        return second;
    }
}

/**
 * 继承泛型类：指定具体类型
 */
class StringBox extends Box<String> {
    public StringBox(String content) {
        super(content);
    }
}


// ========== 泛型接口定义 ==========

/**
 * 泛型接口：生成器
 */
interface Generator<T> {
    T generate();
}

/**
 * 实现泛型接口：指定具体类型
 */
class StringGenerator implements Generator<String> {
    private int count = 0;
    
    @Override
    public String generate() {
        return "String-" + (++count);
    }
}

/**
 * 实现泛型接口：保持泛型
 */
class RandomGenerator<T> implements Generator<T> {
    @Override
    @SuppressWarnings("unchecked")
    public T generate() {
        return (T) Integer.valueOf(new Random().nextInt(100));
    }
}

/**
 * Person类
 */
class Person {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    @Override
    public String toString() {
        return name + "(" + age + "岁)";
    }
}

/**
 * 年龄比较器
 */
class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return Integer.compare(p1.getAge(), p2.getAge());
    }
}


// ========== 实际应用示例 ==========

/**
 * 泛型栈
 */
class Stack<E> {
    private ArrayList<E> list = new ArrayList<>();
    
    public void push(E item) {
        list.add(item);
    }
    
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.remove(list.size() - 1);
    }
    
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.get(list.size() - 1);
    }
    
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    public int size() {
        return list.size();
    }
}

/**
 * 泛型缓存
 */
class Cache<K, V> {
    private Map<K, V> map = new HashMap<>();
    
    public void put(K key, V value) {
        map.put(key, value);
    }
    
    public V get(K key) {
        return map.get(key);
    }
    
    public int size() {
        return map.size();
    }
}

/**
 * 泛型工具类
 */
class Utils {
    // 反转列表
    public static <T> List<T> reverse(List<T> list) {
        List<T> result = new ArrayList<>(list);
        Collections.reverse(result);
        return result;
    }
    
    // 打印数组
    public static <T> void printArray(T[] array) {
        System.out.print("数组: [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
