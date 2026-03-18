package javaBasic.collections;

import java.util.*;
import static javaBasic.utils.PrintUtil.*;

/**
 * Java集合框架【精简通用模板】
 * 
 * 目标读者：前端开发者转全栈
 * 核心思路：从JavaScript Array/Object快速迁移到Java集合
 */
public class CollectionDemo {
    
    public static void main(String[] args) {
        
        // ==================== 1. 前端视角理解 ⭐ ====================
        title("1. 前端视角理解 - Java 集合的本质");
        
        subtitle("【一句话讲清本质】");
        println("  集合 = 动态存储数据的容器");
        println("  List = JS 的 Array，Map = JS 的 Object，Set = 去重的 Array");
        println();
        
        subtitle("【前端思维映射】");
        println("  JS 概念                →  Java 概念");
        println("  ───────────────────────────────────────");
        println("  Array（数组）          →  List<T>");
        println("  Object（对象）         →  Map<K, V>");
        println("  Set（集合）            →  Set<T>");
        println("  arr.push()             →  list.add()");
        println("  arr[0]                 →  list.get(0)");
        println("  obj.key                →  map.get(\"key\")");
        println("  arr.length             →  list.size()");
        println();
        
        subtitle("【JS vs Java 核心对比】");
        println();
        println("┌──────────────────────────────────────────────────────────┐");
        println("│ JavaScript                │ Java                         │");
        println("├──────────────────────────────────────────────────────────┤");
        println("│ const arr = []            │ List<String> list =          │");
        println("│                           │   new ArrayList<>();         │");
        println("│ arr.push('Apple')         │ list.add(\"Apple\")            │");
        println("│ arr[0]                    │ list.get(0)                  │");
        println("│ arr.length                │ list.size()                  │");
        println("│ arr.splice(0, 1)          │ list.remove(0)               │");
        println("│                           │                              │");
        println("│ const obj = {}            │ Map<String, Object> map =    │");
        println("│                           │   new HashMap<>();           │");
        println("│ obj.name = 'Alice'        │ map.put(\"name\", \"Alice\")    │");
        println("│ obj.name                  │ map.get(\"name\")              │");
        println("│ delete obj.name           │ map.remove(\"name\")           │");
        println("│                           │                              │");
        println("│ const set = new Set()     │ Set<Integer> set =           │");
        println("│                           │   new HashSet<>();           │");
        println("│ set.add(1)                │ set.add(1)                   │");
        println("└──────────────────────────────────────────────────────────┘");
        println();
        
        println("💡 关键差异：");
        println("  1. Java 需要声明泛型类型（List<String>）");
        println("  2. Java 不能用 [] 访问，要用 get() 方法");
        println("  3. Java Map 不能用 . 访问，要用 get(\"key\")");
        println("  4. Java 有多种实现（ArrayList、LinkedList、HashMap 等）");
        println();
        
        println("✅ 相似点：");
        println("  - 都可以动态增删元素");
        println("  - 都支持遍历（for-each、forEach）");
        println("  - 都有查找、排序等操作");
        println();
        
        subtitle("【集合框架结构】");
        println();
        println("Collection（接口）");
        println("  ├─ List（有序、可重复）");
        println("  │   ├─ ArrayList（最常用，动态数组）");
        println("  │   └─ LinkedList（链表结构）");
        println("  └─ Set（无序、不重复）");
        println("      ├─ HashSet（快速查找）");
        println("      └─ TreeSet（自动排序）");
        println();
        println("Map（接口，键值对）");
        println("  ├─ HashMap（最常用，哈希表）");
        println("  ├─ LinkedHashMap（保持插入顺序）");
        println("  └─ TreeMap（按 key 排序）");
        
        line();
        
        
        // ==================== 2. 为什么存在 ====================
        title("2. 为什么存在 - 解决什么问题");
        
        subtitle("【痛点场景】电商购物车功能");
        println("前端需求：");
        println("  - 显示购物车商品列表");
        println("  - 添加/删除商品");
        println("  - 修改商品数量");
        println("  - 计算总价");
        println("  - 按价格排序");
        println();
        
        println("❌ 如果不用集合会怎样？");
        println("  1. 用数组：长度固定，不能动态增删");
        println("     String[] items = new String[10];  // 满了怎么办？");
        println("  2. 手动扩容：代码复杂，容易出错");
        println("  3. 查找慢：需要遍历整个数组 O(n)");
        println("  4. 去重难：需要手动判断重复");
        println();
        
        println("✅ 用集合的好处：");
        println("  1. 动态增删：List 自动扩容，无需担心长度");
        println("  2. 快速查找：Map 的 O(1) 查找");
        println("  3. 自动去重：Set 自动去重");
        println("  4. 丰富操作：排序、筛选、统计等内置方法");
        println();
        
        subtitle("【前后端协作中的作用】");
        println("前端发送请求 → 后端用集合处理数据 → 返回 JSON");
        println();
        println("前端：");
        println("  fetch('/api/cart')");
        println("    .then(res => res.json())");
        println("    .then(items => console.log(items))  // [{id:1, name:'iPhone'}, ...]");
        println();
        println("后端：");
        println("  @GetMapping(\"/api/cart\")");
        println("  public List<CartItem> getCart() {");
        println("    List<CartItem> items = cartService.findAll();  // List 存储商品");
        println("    return items;  // 自动序列化成 JSON 数组");
        println("  }");
        println();
        println("💡 集合就是后端处理数据的核心工具！");
        
        line();
        
        
        // ==================== 3. 核心用法 ⭐ ====================
        title("3. 核心用法 - 5 个典型场景");
        
        subtitle("【用法1】List - 存储有序数据");
        println();
        println("// 创建和操作");
        println("List<String> users = new ArrayList<>();");
        println("users.add(\"Alice\");        // 添加");
        println("users.add(\"Bob\");");
        println("users.add(\"Charlie\");");
        println("System.out.println(users.get(0));    // 访问：Alice");
        println("System.out.println(users.size());    // 长度：3");
        println("users.remove(0);           // 删除第一个");
        println();
        
        println("实际执行：");
        List<String> users = new ArrayList<>();
        users.add("Alice");
        users.add("Bob");
        users.add("Charlie");
        println("  用户列表: " + users);
        println("  第一个用户: " + users.get(0));
        println("  用户数量: " + users.size());
        users.remove(0);
        println("  删除后: " + users);
        println();
        
        subtitle("【用法2】Map - 存储键值对");
        println();
        println("// 创建和操作");
        println("Map<String, Object> user = new HashMap<>();");
        println("user.put(\"name\", \"Alice\");");
        println("user.put(\"age\", 25);");
        println("user.put(\"email\", \"alice@example.com\");");
        println("System.out.println(user.get(\"name\"));   // 访问：Alice");
        println("user.remove(\"email\");                   // 删除");
        println();
        
        println("实际执行：");
        Map<String, Object> user = new HashMap<>();
        user.put("name", "Alice");
        user.put("age", 25);
        user.put("email", "alice@example.com");
        println("  用户信息: " + user);
        println("  姓名: " + user.get("name"));
        println("  年龄: " + user.get("age"));
        println();
        
        subtitle("【用法3】Set - 自动去重");
        println();
        println("// 创建和操作");
        println("Set<Integer> tags = new HashSet<>();");
        println("tags.add(1);");
        println("tags.add(2);");
        println("tags.add(2);  // 重复元素不会被添加");
        println("tags.add(3);");
        println("System.out.println(tags);  // [1, 2, 3]");
        println();
        
        println("实际执行：");
        Set<Integer> tags = new HashSet<>();
        tags.add(1);
        tags.add(2);
        tags.add(2);
        tags.add(3);
        tags.add(3);
        println("  标签集合: " + tags);
        println("  自动去重，只保留 " + tags.size() + " 个元素");
        println();
        
        subtitle("【用法4】遍历集合");
        println();
        println("// 增强 for 循环");
        println("for (String u : users) {");
        println("    System.out.println(u);");
        println("}");
        println();
        println("// Lambda 表达式");
        println("users.forEach(u -> System.out.println(u));");
        println();
        
        println("实际执行：");
        print("  ");
        users.forEach(u -> print(u + " "));
        println();
        println();
        
        subtitle("【用法5】筛选和排序");
        println();
        println("// 筛选：价格 > 2000 的商品");
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "iPhone", 5999.0));
        products.add(new Product(2L, "iPad", 3999.0));
        products.add(new Product(3L, "AirPods", 1299.0));
        
        List<Product> filtered = new ArrayList<>();
        for (Product p : products) {
            if (p.getPrice() > 2000) {
                filtered.add(p);
            }
        }
        
        println("List<Product> filtered = new ArrayList<>();");
        println("for (Product p : products) {");
        println("    if (p.getPrice() > 2000) {");
        println("        filtered.add(p);");
        println("    }");
        println("}");
        println();
        println("筛选结果: " + filtered.size() + " 个商品");
        for (Product p : filtered) {
            println("  " + p.getName() + ": ¥" + p.getPrice());
        }
        
        println();
        println("// 排序：按价格升序");
        products.sort((p1, p2) -> (int)(p1.getPrice() - p2.getPrice()));
        println("products.sort((p1, p2) -> (int)(p1.getPrice() - p2.getPrice()));");
        println();
        println("排序结果:");
        for (Product p : products) {
            println("  " + p.getName() + ": ¥" + p.getPrice());
        }
        
        line();
        
        
        // ==================== 4. 底层原理（对比理解） ====================
        title("4. 底层原理 - Java vs JS 执行机制");
        
        subtitle("【ArrayList vs JavaScript Array】");
        println();
        println("JavaScript Array:");
        println("  - 实现：动态数组 + 稀疏数组（可以有空洞）");
        println("  - 类型：可以存储任意类型混合");
        println("  - 访问：O(1)");
        println("  - 插入/删除：O(n)");
        println();
        println("Java ArrayList:");
        println("  - 实现：动态数组（底层是 Object[]）");
        println("  - 类型：泛型约束，只能存储一种类型");
        println("  - 访问：O(1)");
        println("  - 插入/删除：O(n)（需要移动元素）");
        println("  - 扩容：初始容量 10，扩容 1.5 倍");
        println();
        
        subtitle("【HashMap vs JavaScript Object】");
        println();
        println("JavaScript Object:");
        println("  - 实现：哈希表 + 原型链");
        println("  - key：只能是 String 或 Symbol");
        println("  - 访问：O(1)");
        println();
        println("Java HashMap:");
        println("  - 实现：数组 + 链表/红黑树");
        println("  - key：可以是任意对象");
        println("  - 访问：O(1)，冲突时 O(log n)");
        println("  - 扩容：初始容量 16，负载因子 0.75");
        println("  - 优化：链表长度 >= 8 且数组长度 >= 64 时转红黑树");
        println();
        
        subtitle("【为什么性能不同】");
        println();
        println("1. 类型检查：");
        println("   JS: 运行时检查类型，灵活但慢");
        println("   Java: 编译时确定类型，快");
        println();
        println("2. 内存管理：");
        println("   JS: 动态类型，内存占用不确定");
        println("   Java: 泛型擦除，基本类型需要装箱（Integer）");
        println();
        println("3. 性能对比：");
        println("   ArrayList.get(): O(1) - 快");
        println("   HashMap.get(): O(1) - 快");
        println("   JS Array[i]: O(1) - 快");
        println("   结论：访问性能相当，Java 大数据量下更稳定");
        
        line();
        
        
        // ==================== 5. 实战案例 ⭐ ====================
        title("5. 实战案例 - 商品管理系统（完整前后端）");
        
        subtitle("【业务场景】");
        println("功能：商品列表查询、筛选、排序、统计");
        println();
        
        subtitle("【前端代码】");
        println();
        println("// 前端发起请求");
        println("const getProducts = async () => {");
        println("  const response = await fetch('/api/products?minPrice=2000&sortBy=price');");
        println("  const products = await response.json();");
        println("  console.log(products);  // [{id:1, name:'iPhone', price:5999}, ...]");
        println("};");
        println();
        println("// 获取统计数据");
        println("const getStatistics = async () => {");
        println("  const response = await fetch('/api/products/statistics');");
        println("  const stats = await response.json();");
        println("  console.log(stats);  // {total:5, categories:{手机:2, 电脑:1, ...}}");
        println("};");
        println();
        
        subtitle("【后端代码 - 完整实现】");
        println();
        
        // 模拟商品数据
        List<ProductDetail> productList = new ArrayList<>();
        productList.add(new ProductDetail(1L, "iPhone 15", 5999.0, "手机", 1200));
        productList.add(new ProductDetail(2L, "小米14", 3999.0, "手机", 800));
        productList.add(new ProductDetail(3L, "MacBook Pro", 12999.0, "电脑", 500));
        productList.add(new ProductDetail(4L, "iPad Air", 4999.0, "平板", 600));
        productList.add(new ProductDetail(5L, "AirPods Pro", 1899.0, "耳机", 2000));
        
        println("// 1. Controller 层 - 商品列表接口");
        println("@RestController");
        println("@RequestMapping(\"/api/products\")");
        println("public class ProductController {");
        println();
        println("    @Autowired");
        println("    private ProductService productService;");
        println();
        println("    @GetMapping");
        println("    public List<ProductVO> list(");
        println("        @RequestParam(required = false) Double minPrice,");
        println("        @RequestParam(defaultValue = \"id\") String sortBy");
        println("    ) {");
        println("        // 查询所有商品");
        println("        List<Product> products = productService.findAll();");
        println();
        println("        // 价格筛选");
        println("        List<Product> filtered = new ArrayList<>();");
        println("        for (Product p : products) {");
        println("            if (minPrice == null || p.getPrice() >= minPrice) {");
        println("                filtered.add(p);");
        println("            }");
        println("        }");
        println();
        println("        // 排序");
        println("        if (\"price\".equals(sortBy)) {");
        println("            filtered.sort((p1, p2) -> ");
        println("                (int)(p1.getPrice() - p2.getPrice()));");
        println("        }");
        println();
        println("        return filtered;  // 自动转 JSON");
        println("    }");
        println("}");
        println();
        
        println("// 2. 统计接口 - 按分类统计商品数");
        println("@GetMapping(\"/statistics\")");
        println("public Map<String, Object> getStatistics() {");
        println("    List<Product> products = productService.findAll();");
        println();
        println("    // 按分类统计（Map 的典型用法）");
        println("    Map<String, Integer> categoryStats = new HashMap<>();");
        println("    for (Product p : products) {");
        println("        String category = p.getCategory();");
        println("        categoryStats.put(category, ");
        println("            categoryStats.getOrDefault(category, 0) + 1);");
        println("    }");
        println();
        println("    return Map.of(");
        println("        \"total\", products.size(),");
        println("        \"categories\", categoryStats");
        println("    );");
        println("}");
        println();
        
        subtitle("【模拟执行】");
        println();
        
        println("1. 查询所有商品:");
        println("  共 " + productList.size() + " 个商品");
        
        println();
        println("2. 按价格筛选（>= 2000）:");
        List<ProductDetail> priceFiltered = new ArrayList<>();
        for (ProductDetail p : productList) {
            if (p.getPrice() >= 2000) {
                priceFiltered.add(p);
            }
        }
        println("  筛选结果: " + priceFiltered.size() + " 个商品");
        for (ProductDetail p : priceFiltered) {
            println("    " + p.getName() + " - ¥" + p.getPrice());
        }
        
        println();
        println("3. 按分类统计:");
        Map<String, Integer> categoryStats = new HashMap<>();
        for (ProductDetail p : productList) {
            String category = p.getCategory();
            categoryStats.put(category, categoryStats.getOrDefault(category, 0) + 1);
        }
        println("  统计结果:");
        categoryStats.forEach((category, count) -> 
            println("    " + category + ": " + count + " 个商品"));
        
        println();
        println("4. 按销量排序（Top 3）:");
        List<ProductDetail> sortedBySales = new ArrayList<>(productList);
        sortedBySales.sort((p1, p2) -> p2.getSales() - p1.getSales());
        println("  销量排行:");
        for (int i = 0; i < Math.min(3, sortedBySales.size()); i++) {
            ProductDetail p = sortedBySales.get(i);
            println("    " + (i + 1) + ". " + p.getName() + " - 销量 " + p.getSales());
        }
        
        println();
        subtitle("【数据流向】");
        println();
        println("前端 fetch('/api/products?minPrice=2000')");
        println("    ↓ HTTP GET");
        println("Controller 接收参数（minPrice=2000）");
        println("    ↓");
        println("Service 查询数据库");
        println("    ↓ List<Product>");
        println("ArrayList 处理数据");
        println("    ├─ 筛选（for + if）");
        println("    ├─ 排序（sort）");
        println("    └─ 转换（VO）");
        println("    ↓ List<ProductVO>");
        println("自动序列化成 JSON");
        println("    ↓");
        println("前端收到 [{id:1, name:'iPhone', ...}, ...]");
        
        line();
        
        
        // ==================== 6. Spring Boot落地 ====================
        title("6. Spring Boot 落地 - 会写接口就够了");
        
        subtitle("【Service 层使用集合】");
        println();
        println("@Service");
        println("public class ProductService {");
        println();
        println("    @Autowired");
        println("    private ProductRepository productRepository;");
        println();
        println("    // 查询并转换");
        println("    public List<ProductVO> findByCategory(String category) {");
        println("        // JPA 返回 List<Product>");
        println("        List<Product> products = ");
        println("            productRepository.findByCategory(category);");
        println();
        println("        // 转换成 List<ProductVO>");
        println("        List<ProductVO> result = new ArrayList<>();");
        println("        for (Product p : products) {");
        println("            result.add(convertToVO(p));");
        println("        }");
        println("        return result;");
        println("    }");
        println();
        println("    // 批量操作");
        println("    @Transactional");
        println("    public void batchUpdatePrice(List<Long> ids, double rate) {");
        println("        List<Product> products = productRepository.findAllById(ids);");
        println();
        println("        for (Product p : products) {");
        println("            p.setPrice(p.getPrice() * rate);");
        println("        }");
        println();
        println("        productRepository.saveAll(products);  // 批量保存");
        println("    }");
        println("}");
        println();
        println("💡 核心：Service 层用集合处理业务逻辑");
        println();
        
        subtitle("【Repository 返回集合】");
        println();
        println("@Repository");
        println("public interface ProductRepository ");
        println("    extends JpaRepository<Product, Long> {");
        println();
        println("    // 返回 List");
        println("    List<Product> findByCategory(String category);");
        println();
        println("    // 返回 List，按价格排序");
        println("    List<Product> findByCategoryOrderByPriceAsc(String category);");
        println();
        println("    // 自定义查询");
        println("    @Query(\"SELECT p FROM Product p WHERE p.price BETWEEN ?1 AND ?2\")");
        println("    List<Product> findByPriceRange(Double min, Double max);");
        println("}");
        println();
        println("💡 核心：Repository 自动返回集合类型");
        
        line();
        
        
        // ==================== 7. 进阶 & 工程化 ====================
        title("7. 进阶 & 工程化 - 提升效率");
        
        subtitle("【常见进阶能力】");
        println();
        
        println("1. Stream API（流式处理）");
        println("   // 筛选价格 > 2000 的商品");
        println("   List<Product> result = products.stream()");
        println("       .filter(p -> p.getPrice() > 2000)");
        println("       .collect(Collectors.toList());");
        println();
        
        println("2. Collections 工具类");
        List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9));
        println("   List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9);");
        println("   Collections.sort(numbers);       // 排序");
        Collections.sort(numbers);
        println("   结果: " + numbers);
        Collections.reverse(numbers);
        println("   Collections.reverse(numbers);    // 反转");
        println("   结果: " + numbers);
        println();
        
        println("3. LinkedHashMap 保持顺序");
        Map<String, Integer> orderedMap = new LinkedHashMap<>();
        orderedMap.put("第一个", 1);
        orderedMap.put("第二个", 2);
        orderedMap.put("第三个", 3);
        println("   LinkedHashMap 保证插入顺序: " + orderedMap);
        println();
        
        subtitle("【最佳实践】");
        println();
        println("✅ 原则1：选择合适的集合类型");
        println("  - List：需要有序、可重复（用户列表、商品列表）");
        println("  - Map：需要快速查找（缓存、配置）");
        println("  - Set：需要去重（标签、权限）");
        println();
        println("✅ 原则2：指定初始容量");
        println("  // 如果知道大概有 100 个元素");
        println("  List<String> list = new ArrayList<>(100);");
        println("  Map<String, Object> map = new HashMap<>(100);");
        println();
        println("✅ 原则3：使用增强 for 循环");
        println("  // ✅ 推荐");
        println("  for (String item : list) { }");
        println("  // ❌ 不推荐");
        println("  for (int i = 0; i < list.size(); i++) { }");
        println();
        println("✅ 原则4：避免在循环中修改集合");
        println("  // ❌ 错误：ConcurrentModificationException");
        println("  for (String item : list) { list.remove(item); }");
        println("  // ✅ 正确：使用 Iterator");
        println("  Iterator<String> it = list.iterator();");
        println("  while (it.hasNext()) { it.next(); it.remove(); }");
        
        line();
        
        
        // ==================== 8. 常见坑 + 面试 ====================
        title("8. 常见坑 + 面试 - 前端视角");
        
        subtitle("【容易踩的坑】");
        println();
        
        println("坑1：用 [] 访问集合（JS 思维）");
        println("  ❌ list[0]  // 编译错误");
        println("  ✅ list.get(0)");
        println();
        
        println("坑2：用 . 访问 Map（JS 思维）");
        println("  ❌ map.name  // 编译错误");
        println("  ✅ map.get(\"name\")");
        println();
        
        println("坑3：List 越界");
        println("  ❌ list.get(10)  // IndexOutOfBoundsException");
        println("  ✅ if (index < list.size()) { list.get(index); }");
        println();
        
        println("坑4：Arrays.asList() 返回的 List 不能增删");
        println("  ❌ List<String> list = Arrays.asList(\"A\", \"B\");");
        println("     list.add(\"C\");  // UnsupportedOperationException");
        println("  ✅ List<String> list = new ArrayList<>(Arrays.asList(\"A\", \"B\"));");
        println("     list.add(\"C\");  // 可以添加");
        println();
        
        println("坑5：HashMap 的 key 用自定义对象");
        println("  ❌ User u1 = new User(\"Alice\");");
        println("     map.put(u1, \"value\");");
        println("     User u2 = new User(\"Alice\");");
        println("     map.get(u2);  // 返回 null（不同对象）");
        println("  ✅ 使用 String/Long 等基本类型做 key");
        println("     或者重写 equals() 和 hashCode()");
        println();
        
        subtitle("【高频面试问题】");
        println();
        
        println("问题1：ArrayList 和 LinkedList 的区别？");
        println("答：");
        println("  ArrayList：底层动态数组，查询 O(1) 快，增删 O(n) 慢");
        println("  LinkedList：底层双向链表，查询 O(n) 慢，增删 O(1) 快");
        println("  选择：查询多用 ArrayList，增删多用 LinkedList");
        println();
        
        println("问题2：HashMap 的底层原理？");
        println("答：");
        println("  数据结构：数组 + 链表/红黑树");
        println("  存储过程：计算 hashCode → 找数组下标 → 冲突用链表");
        println("  扩容：初始容量 16，负载因子 0.75，扩容 2 倍");
        println("  优化：链表长度 >= 8 且数组长度 >= 64 时转红黑树");
        println();
        
        println("问题3：List 和数组的区别？");
        println("答：");
        println("  数组：长度固定，性能高，可存基本类型");
        println("  List：长度动态，可增删，只能存对象");
        println();
        
        println("问题4：项目中如何使用集合？");
        println("答（结合实际项目）：");
        println("  \"在商品管理模块中：");
        println("   1. List<Product>：存储商品列表");
        println("   2. Map<Long, Product>：商品缓存（ID 到商品的映射）");
        println("   3. Set<Long>：去重的商品 ID 集合");
        println("   4. Map<String, Integer>：按分类统计商品数量");
        println("   这些集合在 Service 层用于数据处理和转换。\"");
        
        line();
        
        
        // ==================== 总结 ====================
        success("Java 集合框架【精简版】学习完成！");
        println();
        
        subtitle("🎯 核心要点总结");
        println("1. 本质：List = Array，Map = Object，Set = 去重 Array");
        println("2. List：有序可重复，ArrayList 最常用");
        println("3. Map：键值对，HashMap 最常用");
        println("4. Set：无序不重复，自动去重");
        println("5. 实战：Controller 返回 List，Service 处理数据，Repository 查询");
        println("6. 性能：ArrayList 查询 O(1)，HashMap 查找 O(1)");
        println();
        
        subtitle("📈 学习路线");
        println("第1周：掌握 List、Map、Set 基本操作");
        println("第2周：写商品列表 API，用 List 返回数据");
        println("第3周：集合的筛选、排序、统计");
        println("第4周：完成商品管理系统（CRUD + 统计）");
        println();
        
        subtitle("💡 快速上手技巧");
        println("✅ List 替代 Array：List<String> list = new ArrayList<>()");
        println("✅ Map 替代 Object：Map<String, Object> map = new HashMap<>()");
        println("✅ Set 自动去重：Set<Integer> set = new HashSet<>()");
        println("✅ 用 get() 访问：list.get(0)、map.get(\"key\")");
        println("✅ Controller 返回 List：return productService.findAll()");
        println();
        
        subtitle("🚀 下一步");
        println("- 学习 Stream API（函数式操作集合）");
        println("- 学习 JPA（数据库自动返回 List）");
        println("- 完成一个完整的后台管理系统");
    }
}


// ==================== 领域模型 ====================

/**
 * 商品实体（简化版）
 */
class Product {
    private final Long id;
    private final String name;
    private final Double price;
    
    public Product(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    
    public Long getId() { return id; }
    public String getName() { return name; }
    public Double getPrice() { return price; }
}

/**
 * 商品实体（详细版）
 */
class ProductDetail {
    private final Long id;
    private final String name;
    private Double price;
    private final String category;
    private final int sales;
    
    public ProductDetail(Long id, String name, Double price, String category, int sales) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.sales = sales;
    }
    
    public Long getId() { return id; }
    public String getName() { return name; }
    public Double getPrice() { return price; }
    public String getCategory() { return category; }
    public int getSales() { return sales; }
    public void setPrice(Double price) { this.price = price; }
}
