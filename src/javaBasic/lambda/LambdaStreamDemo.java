package javaBasic.lambda;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import static javaBasic.utils.PrintUtil.*;

/**
 * Lambda & Stream API【精简通用模板】
 * 
 * 目标读者：前端开发者转全栈
 * 核心思路：从JavaScript箭头函数和数组方法快速迁移到Java
 */
public class LambdaStreamDemo {
    
    public static void main(String[] args) {
        
        // ==================== 1. 前端视角理解 ⭐ ====================
        title("1. 前端视角理解 - Lambda & Stream 的本质");
        
        subtitle("【一句话讲清本质】");
        println("  Lambda = JS 的箭头函数");
        println("  Stream = JS 的数组方法（map、filter、reduce）");
        println("  用函数式编程处理数据，代码更简洁");
        println();
        
        subtitle("【前端思维映射】");
        println("  JS 概念                →  Java 概念");
        println("  ─────────────────────────────────────────");
        println("  箭头函数 () => {}      →  Lambda () -> {}");
        println("  .filter(x => x > 0)    →  .filter(x -> x > 0)");
        println("  .map(x => x * 2)       →  .map(x -> x * 2)");
        println("  .reduce((a,b) => a+b)  →  .reduce((a,b) -> a+b)");
        println("  .forEach(x => log(x))  →  .forEach(x -> log(x))");
        println("  .find(x => x > 5)      →  .filter(...).findFirst()");
        println();
        
        subtitle("【JS vs Java 核心对比】");
        println();
        println("┌────────────────────────────────────────────────────────┐");
        println("│ JavaScript                │ Java                       │");
        println("├────────────────────────────────────────────────────────┤");
        println("│ // 箭头函数               │ // Lambda 表达式            │");
        println("│ const fn = x => x * 2     │ Function<Integer, Integer> │");
        println("│                           │   fn = x -> x * 2          │");
        println("│                           │                            │");
        println("│ // 数组方法               │ // Stream API               │");
        println("│ const result = [1,2,3]    │ List<Integer> result =     │");
        println("│   .filter(x => x > 1)     │   List.of(1, 2, 3).stream()│");
        println("│   .map(x => x * 2)        │     .filter(x -> x > 1)    │");
        println("│                           │     .map(x -> x * 2)       │");
        println("│ // [4, 6]                 │     .toList();  // [4, 6]  │");
        println("└────────────────────────────────────────────────────────┘");
        println();
        
        println("💡 关键差异：");
        println("  1. Java 需要 .toList() 收集结果（JS 不需要）");
        println("  2. Java Stream 是惰性求值（JS 立即求值）");
        println("  3. Java 类型更严格（但可以用 var 简化）");
        println();
        
        println("✅ 前端开发者的优势：");
        println("  - 已经理解函数式编程思想");
        println("  - 熟悉链式调用和数据转换");
        println("  - 只需学习 Java 的语法差异");
        
        line();
        
        
        // ==================== 2. 为什么存在 ====================
        title("2. 为什么存在 - 解决什么问题");
        
        subtitle("【痛点场景】用户列表页（前端常见需求）");
        println("前端需求：");
        println("  - 筛选：只显示 VIP 用户");
        println("  - 转换：只返回 { id, name, level } 字段");
        println("  - 排序：按余额倒序");
        println("  - 分页：每页 10 条");
        println();
        
        println("❌ 不用 Lambda/Stream 的写法（传统 for 循环）");
        println();
        println("List<UserVO> result = new ArrayList<>();");
        println("for (User user : allUsers) {");
        println("    if (\"VIP\".equals(user.getLevel())) {");
        println("        result.add(new UserVO(");
        println("            user.getId(), user.getName(), user.getLevel()));");
        println("    }");
        println("}");
        println("// 还需要手动排序、分页... 代码 20+ 行");
        println();
        
        println("✅ 用 Lambda/Stream 的写法");
        
        List<User> allUsers = Arrays.asList(
            new User(1, "张三", "VIP", 5000, "2024-01-15"),
            new User(2, "李四", "NORMAL", 1000, "2024-02-10"),
            new User(3, "王五", "VIP", 8000, "2024-01-20"),
            new User(4, "赵六", "NORMAL", 500, "2024-03-05")
        );
        
        List<UserVO> vipUsers = allUsers.stream()
            .filter(u -> "VIP".equals(u.getLevel()))        // 筛选
            .map(u -> new UserVO(u.getId(), u.getName(), u.getLevel()))  // 转换
            .sorted(Comparator.comparing(UserVO::getId).reversed())      // 排序
            .limit(10)                                       // 分页
            .toList();
        
        println();
        println("List<UserVO> result = users.stream()");
        println("    .filter(u -> \"VIP\".equals(u.getLevel()))");
        println("    .map(u -> new UserVO(...))");
        println("    .sorted(Comparator.comparing(...).reversed())");
        println("    .limit(10)");
        println("    .toList();");
        println();
        println("实际执行结果: " + vipUsers);
        println();
        
        println("✅ 优势对比：");
        println("  - 代码量：减少 70%（从 20+ 行到 5 行）");
        println("  - 可读性：链式调用，接近前端 .filter().map()");
        println("  - 维护性：逻辑清晰，易于修改");
        println("  - 扩展性：轻松添加新的转换步骤");
        println();
        
        subtitle("【前后端协作中的作用】");
        println("前端发送请求 → 后端用 Stream 处理数据 → 返回 JSON");
        println();
        println("前端：");
        println("  fetch('/api/users?level=VIP')");
        println("    .then(res => res.json())");
        println("    .then(users => console.log(users))");
        println();
        println("后端：");
        println("  @GetMapping(\"/api/users\")");
        println("  public List<UserVO> getUsers(@RequestParam String level) {");
        println("    return userService.findAll().stream()");
        println("        .filter(u -> level.equals(u.getLevel()))");
        println("        .map(this::toVO)");
        println("        .toList();");
        println("  }");
        println();
        println("💡 Stream 就是后端处理数据的核心工具！");
        
        line();
        
        
        // ==================== 3. 核心用法 ⭐ ====================
        title("3. 核心用法 - 5 个典型场景");
        
        subtitle("【用法1】Lambda 基础语法");
        println();
        println("// 无参数");
        println("Runnable greet = () -> println(\"Hello\")");
        Runnable greet = () -> println("  执行: Hello");
        greet.run();
        println();
        
        println("// 一个参数（可省略括号）");
        println("Function<Integer, Integer> doubleFunc = x -> x * 2");
        Function<Integer, Integer> doubleFunc = x -> x * 2;
        println("  执行: double(5) = " + doubleFunc.apply(5));
        println();
        
        println("// 多个参数");
        println("BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b");
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        println("  执行: add(10, 20) = " + add.apply(10, 20));
        println();
        
        println("// 多行代码块");
        println("Function<Integer, Integer> process = x -> {");
        println("    int result = x * 2;");
        println("    println(\"processing...\");");
        println("    return result;");
        println("};");
        Function<Integer, Integer> process = x -> {
            int result = x * 2;
            println("  执行: processing " + x + "...");
            return result;
        };
        println("  结果: " + process.apply(5));
        println();
        
        subtitle("【用法2】Stream 基本操作");
        println();
        println("// filter - 筛选");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> filtered = numbers.stream()
            .filter(n -> n > 2)
            .toList();
        println("numbers.stream().filter(n -> n > 2).toList()");
        println("  原始: " + numbers);
        println("  结果: " + filtered);
        println();
        
        println("// map - 转换");
        List<Integer> doubled = numbers.stream()
            .map(n -> n * 2)
            .toList();
        println("numbers.stream().map(n -> n * 2).toList()");
        println("  原始: " + numbers);
        println("  结果: " + doubled);
        println();
        
        println("// reduce - 聚合");
        int sum = numbers.stream()
            .reduce(0, (a, b) -> a + b);
        println("numbers.stream().reduce(0, (a, b) -> a + b)");
        println("  原始: " + numbers);
        println("  总和: " + sum);
        println();
        
        subtitle("【用法3】实战场景 - 列表查询");
        println();
        println("// 场景：前端请求 GET /api/users?level=VIP");
        String levelFilter = "VIP";
        List<UserVO> filteredUsers = allUsers.stream()
            .filter(u -> levelFilter.equals(u.getLevel()))
            .map(u -> new UserVO(u.getId(), u.getName(), u.getLevel()))
            .toList();
        
        println("List<UserVO> result = users.stream()");
        println("    .filter(u -> level.equals(u.getLevel()))");
        println("    .map(u -> new UserVO(...))");
        println("    .toList();");
        println();
        println("返回给前端: " + filteredUsers);
        println();
        
        subtitle("【用法4】实战场景 - 分组统计");
        println();
        println("// 场景：按用户等级统计数量（返回给前端图表）");
        Map<String, Long> statistics = allUsers.stream()
            .collect(Collectors.groupingBy(User::getLevel, Collectors.counting()));
        
        println("Map<String, Long> stats = users.stream()");
        println("    .collect(Collectors.groupingBy(");
        println("        User::getLevel,");
        println("        Collectors.counting()");
        println("    ));");
        println();
        println("返回给前端: " + statistics);
        println("前端接收后可用于 ECharts/Chart.js");
        println();
        
        subtitle("【用法5】实战场景 - 排序 + 分页");
        println();
        println("// 场景：按余额倒序排列，第 2 页，每页 2 条");
        int pageNum = 1;
        int pageSize = 2;
        List<UserVO> pagedUsers = allUsers.stream()
            .sorted(Comparator.comparing(User::getBalance).reversed())
            .skip((long) pageNum * pageSize)
            .limit(pageSize)
            .map(u -> new UserVO(u.getId(), u.getName(), u.getLevel()))
            .toList();
        
        println("List<UserVO> result = users.stream()");
        println("    .sorted(Comparator.comparing(User::getBalance).reversed())");
        println("    .skip((long) page * size)");
        println("    .limit(size)");
        println("    .map(this::toVO)");
        println("    .toList();");
        println();
        println("返回给前端: " + pagedUsers);
        
        line();
        
        
        // ==================== 4. 底层原理（对比理解） ====================
        title("4. 底层原理 - Java vs JS 执行机制");
        
        subtitle("【执行机制对比】");
        println();
        println("JavaScript - 立即求值（Eager）:");
        println("  const result = [1, 2, 3, 4, 5]");
        println("    .filter(x => x > 2)   // 立即执行，创建新数组 [3, 4, 5]");
        println("    .map(x => x * 2)      // 立即执行，创建新数组 [6, 8, 10]");
        println("  特点：每个操作都立即执行，创建中间数组");
        println();
        
        println("Java Stream - 惰性求值（Lazy）:");
        println("  List<Integer> result = list.stream()");
        println("    .filter(x -> x > 2)   // 不执行，只记录操作");
        println("    .map(x -> x * 2)      // 不执行，只记录操作");
        println("    .toList()             // 终止操作，才真正执行");
        println("  特点：只在终止操作时才执行，不创建中间集合");
        println();
        
        println("🔍 观察执行顺序：");
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        nums.stream()
            .filter(n -> {
                println("  filter 检查: " + n);
                return n > 2;
            })
            .map(n -> {
                println("  map 转换: " + n + " -> " + (n * 2));
                return n * 2;
            })
            .limit(2)
            .forEach(n -> println("  结果: " + n));
        
        println();
        println("注意：不是\"先执行所有 filter，再执行所有 map\"");
        println("而是：每个元素依次通过整个管道");
        println();
        
        subtitle("【为什么性能不同】");
        println();
        println("1. 内存占用：");
        println("   JS: 每次操作创建新数组，内存占用多");
        println("   Java: 惰性求值，不创建中间集合，内存占用少");
        println();
        println("2. 执行效率：");
        println("   小数据量（<100）：JS 和 Java 差不多");
        println("   大数据量（>10000）：Java 更优（惰性求值 + 并行流）");
        println();
        println("3. 并行支持：");
        println("   JS: 不支持自动并行");
        println("   Java: .parallelStream() 一键并行");
        
        line();
        
        
        // ==================== 5. 实战案例 ⭐ ====================
        title("5. 实战案例 - 用户管理系统（完整前后端）");
        
        subtitle("【业务场景】");
        println("功能：用户列表（支持搜索、筛选、分页、排序）+ 统计");
        println();
        
        subtitle("【前端代码】");
        println();
        println("// 前端发起请求");
        println("const getUsers = async () => {");
        println("  const response = await fetch(");
        println("    '/api/users?keyword=张&level=VIP&page=0&size=10'");
        println("  );");
        println("  const users = await response.json();");
        println("  console.log(users);  // [{id:1, name:'张三', level:'VIP'}, ...]");
        println("};");
        println();
        println("// 获取统计数据");
        println("const getStatistics = async () => {");
        println("  const response = await fetch('/api/users/statistics');");
        println("  const stats = await response.json();");
        println("  console.log(stats);  // {countByLevel:{VIP:2, NORMAL:2}, ...}");
        println("};");
        println();
        
        subtitle("【后端代码 - 完整实现】");
        println();
        
        println("// Controller 层 - 用户列表接口");
        println("@RestController");
        println("@RequestMapping(\"/api/users\")");
        println("public class UserController {");
        println();
        println("    @Autowired");
        println("    private UserService userService;");
        println();
        println("    @GetMapping");
        println("    public List<UserVO> list(");
        println("        @RequestParam(required = false) String keyword,");
        println("        @RequestParam(required = false) String level,");
        println("        @RequestParam(defaultValue = \"0\") int page,");
        println("        @RequestParam(defaultValue = \"10\") int size");
        println("    ) {");
        println("        List<User> users = userService.findAll();");
        println();
        println("        // Stream 处理所有查询条件");
        println("        return users.stream()");
        println("            .filter(u -> keyword == null || ");
        println("                u.getName().contains(keyword))  // 关键词搜索");
        println("            .filter(u -> level == null || ");
        println("                level.equals(u.getLevel()))     // 等级筛选");
        println("            .sorted(Comparator.comparing(User::getId))  // 排序");
        println("            .skip((long) page * size)           // 分页");
        println("            .limit(size)");
        println("            .map(this::toVO)                    // 转换 VO");
        println("            .toList();");
        println("    }");
        println();
        println("    @GetMapping(\"/statistics\")");
        println("    public Map<String, Object> getStatistics() {");
        println("        List<User> users = userService.findAll();");
        println();
        println("        return Map.of(");
        println("            \"countByLevel\", users.stream()");
        println("                .collect(Collectors.groupingBy(");
        println("                    User::getLevel,");
        println("                    Collectors.counting())),");
        println("            \"avgBalance\", users.stream()");
        println("                .mapToDouble(User::getBalance)");
        println("                .average().orElse(0)");
        println("        );");
        println("    }");
        println("}");
        println();
        
        subtitle("【模拟执行】");
        println();
        
        println("1. 列表查询（关键词搜索 + 等级筛选）:");
        String keyword = "张";
        List<UserVO> searchResult = allUsers.stream()
            .filter(u -> keyword == null || u.getName().contains(keyword))
            .filter(u -> "VIP".equals(u.getLevel()))
            .map(u -> new UserVO(u.getId(), u.getName(), u.getLevel()))
            .toList();
        println("  搜索 '张' + VIP 用户: " + searchResult);
        println();
        
        println("2. 统计数据（按等级分组）:");
        Map<String, Long> groupStats = allUsers.stream()
            .collect(Collectors.groupingBy(User::getLevel, Collectors.counting()));
        println("  按等级统计: " + groupStats);
        println();
        
        println("3. 平均余额:");
        double avgBalance = allUsers.stream()
            .mapToDouble(User::getBalance)
            .average()
            .orElse(0);
        println("  平均余额: " + String.format("%.2f", avgBalance));
        println();
        
        subtitle("【数据流向】");
        println();
        println("前端 fetch('/api/users?level=VIP')");
        println("    ↓ HTTP GET");
        println("Controller 接收参数（level=VIP）");
        println("    ↓");
        println("Service 查询数据库");
        println("    ↓ List<User>");
        println("Stream 处理数据");
        println("    ├─ filter（筛选）");
        println("    ├─ map（转换 VO）");
        println("    ├─ sorted（排序）");
        println("    └─ toList（收集）");
        println("    ↓ List<UserVO>");
        println("自动序列化成 JSON");
        println("    ↓");
        println("前端收到 [{id:1, name:'张三', level:'VIP'}, ...]");
        
        line();
        
        
        // ==================== 6. Spring Boot落地 ====================
        title("6. Spring Boot 落地 - 会写接口就够了");
        
        subtitle("【分层架构中的使用】");
        println();
        println("Controller 层：");
        println("  ✅ 简单的 Stream 操作（如转换 VO）");
        println("  ❌ 不要写复杂的业务逻辑");
        println();
        println("@GetMapping(\"/api/products\")");
        println("public List<ProductVO> getProducts(@RequestParam String category) {");
        println("    return productService.findByCategory(category);");
        println("}");
        println();
        
        println("Service 层：⭐ Stream 的主战场");
        println("  ✅ 业务逻辑处理");
        println("  ✅ 数据转换、筛选、聚合");
        println();
        println("@Service");
        println("public class ProductService {");
        println("    public List<ProductVO> findByCategory(String category) {");
        println("        return productRepository.findAll().stream()");
        println("            .filter(p -> category.equals(p.getCategory()))");
        println("            .map(this::convertToVO)");
        println("            .toList();");
        println("    }");
        println("}");
        println();
        println("💡 核心：Service 层用 Stream 处理业务逻辑");
        println();
        
        subtitle("【JPA 查询结果转换】");
        println();
        println("// 单个对象转换（Optional.map）");
        println("@GetMapping(\"/api/users/{id}\")");
        println("public UserVO getUser(@PathVariable Long id) {");
        println("    return userRepository.findById(id)");
        println("        .map(this::toVO)");
        println("        .orElseThrow(() -> new NotFoundException());");
        println("}");
        println();
        println("// 批量转换");
        println("List<UserVO> vos = userRepository.findAll().stream()");
        println("    .map(this::toVO)");
        println("    .toList();");
        
        line();
        
        
        // ==================== 7. 进阶 & 工程化 ====================
        title("7. 进阶 & 工程化 - 提升效率");
        
        subtitle("【进阶能力1：并行流】");
        println();
        println("场景：处理大数据量（>10000）");
        println();
        println("// 串行流");
        println("long sum1 = bigData.stream()");
        println("    .mapToLong(Integer::longValue)");
        println("    .sum();");
        println();
        println("// 并行流（多线程处理）");
        println("long sum2 = bigData.parallelStream()");
        println("    .mapToLong(Integer::longValue)");
        println("    .sum();");
        println();
        println("使用场景：");
        println("  ✅ 数据量大（>10000）");
        println("  ✅ 计算密集型（不涉及 IO）");
        println("  ❌ 不要在数据库查询中用");
        println();
        
        subtitle("【进阶能力2：flatMap 处理嵌套结构】");
        println();
        println("场景：订单包含多个商品，获取所有商品名称");
        List<OrderDetail> orderDetails = Arrays.asList(
            new OrderDetail("ORD001", Arrays.asList("商品A", "商品B")),
            new OrderDetail("ORD002", Arrays.asList("商品C")),
            new OrderDetail("ORD003", Arrays.asList("商品D", "商品E"))
        );
        
        List<String> allProducts = orderDetails.stream()
            .flatMap(o -> o.getProducts().stream())
            .toList();
        
        println("List<String> products = orders.stream()");
        println("    .flatMap(o -> o.getProducts().stream())");
        println("    .toList();");
        println();
        println("结果: " + allProducts);
        println();
        println("💭 前端理解: orders.flatMap(o => o.products)");
        println();
        
        subtitle("【最佳实践】");
        println();
        println("✅ 原则1：能在数据库做的，不要在应用层做");
        println("  ❌ 错误：userRepository.findAll().stream()");
        println("           .filter(u -> \"VIP\".equals(u.getLevel()))");
        println("  ✅ 正确：userRepository.findByLevel(\"VIP\")");
        println();
        println("✅ 原则2：复杂逻辑抽取成方法");
        println("  return users.stream()");
        println("      .filter(this::isActiveVip)  // 抽取判断逻辑");
        println("      .map(this::toVO)            // 抽取转换逻辑");
        println("      .toList();");
        println();
        println("✅ 原则3：避免在 Stream 里调用数据库");
        println("  ❌ 错误：users.stream()");
        println("           .map(u -> orderRepository.findByUserId(u.getId()))  // N+1");
        println("  ✅ 正确：先查所有订单，再用 Stream 分组");
        
        line();
        
        
        // ==================== 8. 常见坑 + 面试 ====================
        title("8. 常见坑 + 面试 - 前端视角");
        
        subtitle("【容易踩的坑】");
        println();
        
        println("坑1：忘记 toList() 收集结果");
        println("  ❌ List<Integer> result = list.stream()");
        println("         .filter(x -> x > 0)");
        println("         .map(x -> x * 2);  // 返回 Stream，不是 List");
        println("  ✅ List<Integer> result = list.stream()");
        println("         .filter(x -> x > 0)");
        println("         .map(x -> x * 2)");
        println("         .toList();  // 必须终止操作");
        println();
        
        println("坑2：Stream 只能用一次");
        println("  ❌ Stream<String> stream = list.stream();");
        println("     stream.forEach(System.out::println);  // 第一次");
        println("     stream.count();  // 报错：stream 已关闭");
        println("  ✅ list.stream().forEach(...);");
        println("     long count = list.stream().count();  // 重新创建");
        println();
        
        println("坑3：在 Stream 里调用数据库（N+1 问题）");
        println("  ❌ users.stream()");
        println("         .map(u -> orderRepository.findByUserId(u.getId()))");
        println("  ✅ 先查所有订单，再用 Stream 分组");
        println();
        
        println("坑4：并行流的线程安全问题");
        println("  ❌ List<String> result = new ArrayList<>();");
        println("     list.parallelStream()");
        println("         .forEach(result::add);  // 并发修改，可能出错");
        println("  ✅ List<String> result = list.parallelStream()");
        println("         .toList();  // toList() 是线程安全的");
        println();
        
        println("坑5：修改原对象");
        println("  ❌ users.stream()");
        println("         .forEach(u -> u.setLevel(\"VIP\"));  // 修改了原对象");
        println("  ✅ 如果需要不可变性，创建新对象");
        println();
        
        subtitle("【高频面试问题】");
        println();
        
        println("问题1：Lambda 和 JS 箭头函数有什么区别？");
        println("答：");
        println("  1. Java Lambda 必须用于函数式接口");
        println("  2. JS 箭头函数更灵活，任何地方都能用");
        println("  3. Java 类型更严格，JS 更灵活");
        println();
        
        println("问题2：为什么 Stream 比 for 循环好？");
        println("答：");
        println("  1. 可读性：声明式编程，接近自然语言");
        println("  2. 简洁性：链式调用，减少 70% 代码");
        println("  3. 可维护性：逻辑集中，易于修改");
        println("  4. 并行支持：parallelStream 一键并行");
        println("  但简单场景（<100 元素）for 循环性能略好");
        println();
        
        println("问题3：什么时候不该用 Stream？");
        println("答：");
        println("  ❌ 简单 for 循环（<100 元素）");
        println("  ❌ 需要 break/continue 的场景");
        println("  ❌ Stream 里调用数据库（N+1 问题）");
        println("  ✅ 数据转换、筛选、聚合");
        println("  ✅ 多个操作链式调用");
        println();
        
        println("问题4：项目中哪里用了 Lambda/Stream？");
        println("答（结合实际项目）：");
        println("  \"在用户管理模块中：");
        println("   1. 列表筛选：前端传多个条件，用 filter 链式处理");
        println("   2. 数据转换：Entity 转 VO，用 map");
        println("   3. 统计图表：按等级分组，用 groupingBy");
        println("   这样代码简洁，前端同事也能看懂。\"");
        
        line();
        
        
        // ==================== 总结 ====================
        success("Lambda & Stream【精简版】学习完成！");
        println();
        
        subtitle("🎯 核心要点总结");
        println("1. 本质：Lambda = JS 箭头函数，Stream = JS 数组方法");
        println("2. 语法：filter/map/reduce 和 JS 几乎一样");
        println("3. 差异：需要 toList() 收集结果，惰性求值");
        println("4. 实战：列表查询、数据统计、批量操作");
        println("5. 落地：Service 层用 Stream，数据库用 SQL");
        println("6. 性能：小数据 for 循环，大数据 Stream 并行");
        println();
        
        subtitle("📈 学习路线");
        println("第1周：掌握 Lambda 语法 + Stream 基本操作");
        println("第2周：写用户列表 API（筛选、分页、排序）");
        println("第3周：统计接口（groupingBy 分组）");
        println("第4周：完成用户管理系统（前后端完整）");
        println();
        
        subtitle("💡 快速上手技巧");
        println("✅ Lambda：x -> x * 2（和 JS 箭头函数一样）");
        println("✅ filter：list.stream().filter(x -> x > 0).toList()");
        println("✅ map：list.stream().map(x -> x * 2).toList()");
        println("✅ 分组：stream().collect(groupingBy(..., counting()))");
        println("✅ Service 用 Stream，Repository 用 SQL");
        println();
        
        subtitle("🚀 下一步");
        println("- 搭建 Spring Boot 项目，写第一个 API");
        println("- 学习 JPA/MyBatis，连接数据库");
        println("- 完成前后端分离的完整项目");
    }
}


// ==================== 领域模型 ====================

/**
 * 用户实体
 */
class User {
    private final long id;
    private final String name;
    private final String level;
    private final double balance;
    private final String registerDate;
    
    public User(long id, String name, String level, double balance, String registerDate) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.balance = balance;
        this.registerDate = registerDate;
    }
    
    public long getId() { return id; }
    public String getName() { return name; }
    public String getLevel() { return level; }
    public double getBalance() { return balance; }
    public String getRegisterDate() { return registerDate; }
}

/**
 * 用户视图对象（返回给前端）
 */
class UserVO {
    private final long id;
    private final String name;
    private final String level;
    
    public UserVO(long id, String name, String level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }
    
    public long getId() { return id; }
    public String getName() { return name; }
    public String getLevel() { return level; }
    
    @Override
    public String toString() {
        return "UserVO{id=" + id + ", name='" + name + "', level='" + level + "'}";
    }
}

/**
 * 订单详情（包含商品列表）
 */
class OrderDetail {
    private final String orderId;
    private final List<String> products;
    
    public OrderDetail(String orderId, List<String> products) {
        this.orderId = orderId;
        this.products = products;
    }
    
    public String getOrderId() { return orderId; }
    public List<String> getProducts() { return products; }
}
