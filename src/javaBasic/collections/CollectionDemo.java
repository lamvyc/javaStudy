package javaBasic.collections;

/*
 * import java.util.*;
 * - import：导入（引入）其他包的类，这样就可以直接使用类名
 * - java.util：Java标准库的工具包，包含集合框架（ArrayList、HashMap等）
 * - *：星号表示导入这个包下的所有类
 * 
 * 效果：可以直接使用 ArrayList、HashMap、List、Set 等，
 *      而不用写完整名称 java.util.ArrayList
 * 
 * 等价于：
 * import java.util.ArrayList;
 * import java.util.HashMap;
 * import java.util.List;
 * import java.util.Set;
 * ... (导入 java.util 包下的所有类)
 */
import java.util.*;

/*
 * import static javaBasic.utils.PrintUtil.*;
 * - static：静态导入，导入的是静态方法（而不是类）
 * - javaBasic.utils.PrintUtil：自定义的工具类
 * - *：导入 PrintUtil 类中的所有静态方法
 * 
 * 效果：可以直接写 println()，而不用写 PrintUtil.println()
 */
import static javaBasic.utils.PrintUtil.*;

/**
 * 集合框架学习案例
 * 包含：List、Set、Map、Iterator、ArrayList、HashMap
 */
public class CollectionDemo {
    public static void main(String[] args) {
        
        title("1. ArrayList（List接口实现）");
        
        // 创建ArrayList
        ArrayList<String> fruits = new ArrayList<>();
        
        // 添加元素
        fruits.add("苹果");
        fruits.add("香蕉");
        fruits.add("橙子");
        fruits.add("香蕉");  // List允许重复元素
        
        println("水果列表: " + fruits);
        println("列表大小: " + fruits.size());
        println("第一个水果: " + fruits.get(0));
        
        // 修改元素
        fruits.set(1, "草莓");
        println("修改后: " + fruits);
        
        // 删除元素
        fruits.remove("橙子");
        println("删除橙子后: " + fruits);
        
        // 检查元素
        println("是否包含苹果: " + fruits.contains("苹果"));
        println("香蕉的索引: " + fruits.indexOf("香蕉"));
        
        
        title("2. LinkedList（List接口另一实现）");
        
        LinkedList<Integer> numbers = new LinkedList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        
        // LinkedList特有方法
        numbers.addFirst(5);   // 在开头添加
        numbers.addLast(40);   // 在末尾添加
        println("LinkedList: " + numbers);
        println("第一个元素: " + numbers.getFirst());
        println("最后一个元素: " + numbers.getLast());
        
        
        title("3. HashSet（Set接口实现）");
        
        // Set不允许重复元素
        HashSet<String> cities = new HashSet<>();
        cities.add("北京");
        cities.add("上海");
        cities.add("广州");
        cities.add("北京");  // 重复元素不会被添加
        
        println("城市集合: " + cities);
        println("集合大小: " + cities.size());  // 只有3个元素
        println("是否包含上海: " + cities.contains("上海"));
        
        // 删除元素
        cities.remove("广州");
        println("删除广州后: " + cities);
        
        
        title("4. TreeSet（有序Set）");
        
        TreeSet<Integer> sortedNumbers = new TreeSet<>();
        sortedNumbers.add(50);
        sortedNumbers.add(10);
        sortedNumbers.add(30);
        sortedNumbers.add(20);
        
        println("TreeSet自动排序: " + sortedNumbers);
        println("最小值: " + sortedNumbers.first());
        println("最大值: " + sortedNumbers.last());
        
        
        title("5. HashMap（Map接口实现）");
        
        // 创建HashMap（键值对）
        HashMap<String, Integer> studentScores = new HashMap<>();
        
        // 添加键值对
        studentScores.put("张三", 85);
        studentScores.put("李四", 92);
        studentScores.put("王五", 78);
        studentScores.put("赵六", 88);
        
        println("学生成绩表: " + studentScores);
        println("张三的成绩: " + studentScores.get("张三"));
        
        // 修改值
        studentScores.put("张三", 90);  // 更新张三的成绩
        println("更新后张三的成绩: " + studentScores.get("张三"));
        
        // 检查键值
        println("是否有李四的成绩: " + studentScores.containsKey("李四"));
        println("是否有人得了100分: " + studentScores.containsValue(100));
        
        // 删除键值对
        studentScores.remove("王五");
        println("删除王五后: " + studentScores);
        println("学生人数: " + studentScores.size());
        
        
        title("6. LinkedHashMap（保持插入顺序）");
        
        LinkedHashMap<String, String> capitals = new LinkedHashMap<>();
        capitals.put("中国", "北京");
        capitals.put("美国", "华盛顿");
        capitals.put("日本", "东京");
        capitals.put("英国", "伦敦");
        
        println("国家首都（保持插入顺序）: " + capitals);
        
        
        title("7. Iterator（迭代器）");
        
        ArrayList<String> colors = new ArrayList<>();
        colors.add("红色");
        colors.add("绿色");
        colors.add("蓝色");
        colors.add("黄色");
        
        subtitle("使用Iterator遍历");
        Iterator<String> iterator = colors.iterator();
        while (iterator.hasNext()) {
            String color = iterator.next();
            print(color + " ");
        }
        println();
        
        subtitle("使用Iterator删除元素");
        println("删除包含'绿'的颜色:");
        iterator = colors.iterator();
        while (iterator.hasNext()) {
            String color = iterator.next();
            if (color.contains("绿")) {
                iterator.remove();  // 安全删除
            }
        }
        println("删除后: " + colors);
        
        
        title("8. 遍历Map的多种方式");
        
        HashMap<String, Integer> ages = new HashMap<>();
        ages.put("小明", 20);
        ages.put("小红", 22);
        ages.put("小刚", 21);
        
        subtitle("方式1 - 遍历键");
        for (String name : ages.keySet()) {
            println(name + ": " + ages.get(name) + "岁");
        }
        
        subtitle("方式2 - 遍历值");
        for (Integer age : ages.values()) {
            println("年龄: " + age);
        }
        
        subtitle("方式3 - 遍历键值对（推荐）");
        for (Map.Entry<String, Integer> entry : ages.entrySet()) {
            println(entry.getKey() + ": " + entry.getValue() + "岁");
        }
        
        
        title("9. 集合排序");
        
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(50);
        nums.add(20);
        nums.add(80);
        nums.add(10);
        
        println("排序前: " + nums);
        Collections.sort(nums);  // 升序排序
        println("排序后: " + nums);
        
        Collections.reverse(nums);  // 反转
        println("反转后: " + nums);
        
        
        title("10. 集合实用操作");
        
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("B");
        list1.add("C");
        
        // 清空集合
        ArrayList<String> tempList = new ArrayList<>(list1);
        println("清空前: " + tempList);
        tempList.clear();
        println("清空后: " + tempList);
        println("是否为空: " + tempList.isEmpty());
        
        // 转换为数组
        String[] array = list1.toArray(new String[0]);
        println("转换为数组: " + Arrays.toString(array));
        
        // 从数组创建List
        List<String> listFromArray = Arrays.asList("X", "Y", "Z");
        println("从数组创建List: " + listFromArray);
        
        line();
        success("集合框架学习完成！");
    }
}
