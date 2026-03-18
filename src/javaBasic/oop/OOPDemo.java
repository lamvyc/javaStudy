package javaBasic.oop;

import static javaBasic.utils.PrintUtil.*;

/**
 * Java面向对象编程【精简通用模板】
 * 
 * 目标读者：前端开发者转全栈
 * 核心思路：从JavaScript快速迁移到Java OOP
 */
public class OOPDemo {
    
    public static void main(String[] args) {
        
        // ==================== 1. 前端视角理解 ⭐ ====================
        title("1. 前端视角理解 - Java OOP 的本质");
        
        subtitle("【一句话讲清本质】");
        println("  面向对象 = 用类（class）定义数据模板 + 用对象（object）存储实例");
        println("  就像前端的 Vue 组件或 React 组件，把数据和行为封装在一起");
        println();
        
        subtitle("【前端思维映射】");
        println("  如果你会 JavaScript 的 class，那你已经理解了 80% 的 Java OOP！");
        println();
        println("  前端概念                        →  Java 概念");
        println("  ────────────────────────────────────────────────");
        println("  组件（Component）               →  类（Class）");
        println("  组件实例（new Vue()）          →  对象（new User()）");
        println("  props                          →  属性（private fields）");
        println("  methods                        →  方法（public methods）");
        println("  继承（extends）                →  继承（extends）");
        println("  接口（TypeScript）             →  接口（interface）");
        println();
        
        subtitle("【JS vs Java 核心对比】");
        println();
        println("┌─────────────────────────────────────────────────────────────┐");
        println("│ JavaScript                    │ Java                         │");
        println("├─────────────────────────────────────────────────────────────┤");
        println("│ class User {                  │ class User {                 │");
        println("│   constructor(name, age) {    │   private String name;       │");
        println("│     this.name = name;         │   private int age;           │");
        println("│     this.age = age;           │                              │");
        println("│   }                           │   public User(String name,   │");
        println("│                               │                int age) {    │");
        println("│   sayHello() {                │     this.name = name;        │");
        println("│     console.log('Hi');        │     this.age = age;          │");
        println("│   }                           │   }                          │");
        println("│ }                             │                              │");
        println("│                               │   public void sayHello() {   │");
        println("│ const user = new User(...)    │     System.out.println();    │");
        println("│                               │   }                          │");
        println("│                               │ }                            │");
        println("│                               │                              │");
        println("│ User user = new User(...)     │                              │");
        println("└─────────────────────────────────────────────────────────────┘");
        println();
        
        println("💡 关键差异：");
        println("  1. Java 需要声明类型（String、int 等）");
        println("  2. Java 需要访问修饰符（public、private）");
        println("  3. Java 需要显式 getter/setter 方法");
        println("  4. Java 有接口（interface）实现多态");
        println();
        
        println("✅ 相似点：");
        println("  - 都有 class、constructor、this、super");
        println("  - 都支持继承（extends）和多态");
        println("  - 都用 new 创建对象");
        
        line();
        
        
        // ==================== 2. 为什么存在 ====================
        title("2. 为什么存在 - 解决什么问题");
        
        subtitle("【痛点场景】用户管理系统");
        println("前端需求：");
        println("  - 显示用户列表");
        println("  - 用户有不同角色（普通用户、VIP、管理员）");
        println("  - 不同角色有不同权限和折扣");
        println("  - 用户可以升级/降级");
        println();
        
        println("❌ 如果不用面向对象会怎样？");
        println("  1. 用 Map 存储：Map<String, Object> → 类型不安全，容易出错");
        println("  2. 代码重复：每种用户都要写一遍逻辑");
        println("  3. 难以维护：修改一处要改很多地方");
        println("  4. 无法扩展：新增用户类型很麻烦");
        println();
        
        println("✅ 用面向对象的好处：");
        println("  1. 封装：User 类统一管理用户数据，类型安全");
        println("  2. 继承：VIPUser 继承 User，代码复用");
        println("  3. 多态：不同用户统一接口，扩展方便");
        println("  4. 维护性：修改一个类，所有地方生效");
        println();
        
        subtitle("【前后端协作中的作用】");
        println("前端发送请求 → 后端用类封装数据 → 返回 JSON");
        println();
        println("前端：");
        println("  fetch('/api/users')");
        println("    .then(res => res.json())");
        println("    .then(users => console.log(users))  // [{id:1, name:'Alice'}, ...]");
        println();
        println("后端：");
        println("  @GetMapping(\"/api/users\")");
        println("  public List<User> getUsers() {");
        println("    List<User> users = userService.findAll();  // User 对象列表");
        println("    return users;  // 自动序列化成 JSON");
        println("  }");
        println();
        println("💡 User 类就是前后端的数据契约！");
        
        line();
        
        
        // ==================== 3. 核心用法 ⭐ ====================
        title("3. 核心用法 - 3～5 个典型场景");
        
        subtitle("【用法1】定义类和创建对象");
        println();
        println("// 定义用户类");
        println("class User {");
        println("  private String name;    // 私有属性（封装）");
        println("  private int age;");
        println();
        println("  public User(String name, int age) {  // 构造函数");
        println("    this.name = name;");
        println("    this.age = age;");
        println("  }");
        println();
        println("  public String getName() { return name; }  // Getter");
        println("  public void setAge(int age) { this.age = age; }  // Setter");
        println();
        println("  public void sayHello() {");
        println("    System.out.println(\"Hello, \" + name);");
        println("  }");
        println("}");
        println();
        println("// 使用");
        println("User user = new User(\"Alice\", 25);");
        println("user.sayHello();");
        println("user.setAge(30);");
        println();
        
        println("实际执行：");
        User user = new User("Alice", 25);
        user.sayHello();
        println("  年龄: " + user.getAge());
        user.setAge(30);
        println("  修改后年龄: " + user.getAge());
        println();
        
        subtitle("【用法2】继承 - 代码复用");
        println();
        println("// VIP 用户继承普通用户");
        println("class VIPUser extends User {");
        println("  private int level;");
        println();
        println("  public VIPUser(String name, int age, int level) {");
        println("    super(name, age);  // 调用父类构造函数");
        println("    this.level = level;");
        println("  }");
        println();
        println("  @Override  // 重写方法");
        println("  public void sayHello() {");
        println("    super.sayHello();  // 调用父类方法");
        println("    System.out.println(\"VIP Level: \" + level);");
        println("  }");
        println();
        println("  public double getDiscount() {");
        println("    return 0.9;  // VIP 9折");
        println("  }");
        println("}");
        println();
        
        println("实际执行：");
        VIPUser vip = new VIPUser("Bob", 30, 5);
        vip.sayHello();
        println("  折扣: " + (vip.getDiscount() * 100) + "%");
        println();
        
        subtitle("【用法3】多态 - 统一接口，不同行为");
        println();
        println("// 统一处理不同类型的用户");
        println("User[] users = {");
        println("  new User(\"Alice\", 25),");
        println("  new VIPUser(\"Bob\", 30, 5)");
        println("};");
        println();
        println("for (User u : users) {");
        println("  u.sayHello();  // 多态：不同对象不同行为");
        println("}");
        println();
        
        println("实际执行：");
        User[] users = {
            new User("Charlie", 28),
            new VIPUser("David", 35, 3)
        };
        for (User u : users) {
            u.sayHello();
        }
        println();
        
        subtitle("【用法4】接口 - 定义规范");
        println();
        println("// 定义折扣策略接口");
        println("interface DiscountStrategy {");
        println("  double calculate(double price);");
        println("}");
        println();
        println("// 不同实现");
        println("class NormalDiscount implements DiscountStrategy {");
        println("  public double calculate(double price) {");
        println("    return price;  // 无折扣");
        println("  }");
        println("}");
        println();
        println("class VIPDiscount implements DiscountStrategy {");
        println("  public double calculate(double price) {");
        println("    return price * 0.9;  // 9折");
        println("  }");
        println("}");
        println();
        println("// 使用多态");
        println("DiscountStrategy discount = isVIP ? new VIPDiscount() : new NormalDiscount();");
        println("double finalPrice = discount.calculate(100.0);");
        println();
        
        println("实际执行：");
        DiscountStrategy normalDiscount = new NormalDiscount();
        DiscountStrategy vipDiscount = new VIPDiscount();
        println("  普通用户: $" + normalDiscount.calculate(100.0));
        println("  VIP用户: $" + vipDiscount.calculate(100.0));
        println();
        
        subtitle("【用法5】抽象类 - 部分实现");
        println();
        println("// 抽象基类");
        println("abstract class BaseUser {");
        println("  protected String name;");
        println();
        println("  public BaseUser(String name) {");
        println("    this.name = name;");
        println("  }");
        println();
        println("  public void printName() {  // 具体方法");
        println("    System.out.println(\"Name: \" + name);");
        println("  }");
        println();
        println("  public abstract String getRole();  // 抽象方法");
        println("}");
        println();
        println("// 子类必须实现抽象方法");
        println("class Admin extends BaseUser {");
        println("  public Admin(String name) { super(name); }");
        println("  public String getRole() { return \"ADMIN\"; }");
        println("}");
        
        line();
        
        
        // ==================== 4. 底层原理（对比理解） ====================
        title("4. 底层原理 - Java vs JS 执行机制");
        
        subtitle("【类的实现机制】");
        println();
        println("JavaScript:");
        println("  - class 是语法糖，底层是原型链");
        println("  - 方法存储在 prototype 上");
        println("  - 动态类型，运行时可以添加属性");
        println("  - user.newProperty = 123  ✅ 可以");
        println();
        println("Java:");
        println("  - class 是真正的类型系统，编译时确定");
        println("  - 方法存储在方法区（Method Area）");
        println("  - 静态类型，编译时就确定了结构");
        println("  - user.newProperty = 123  ❌ 编译错误");
        println();
        
        subtitle("【内存分配】");
        println();
        println("User user = new User(\"Alice\", 25);");
        println();
        println("1. 栈内存（Stack）：存储引用变量 user");
        println("2. 堆内存（Heap）：分配对象空间，存储属性值");
        println("3. 方法区（Method Area）：存储类的方法代码");
        println();
        println("┌─────────┐       ┌────────────────────┐");
        println("│ 栈内存   │       │ 堆内存              │");
        println("│ user    │──────>│ 0x1234             │");
        println("│ 0x1234  │       │ name: \"Alice\"      │");
        println("└─────────┘       │ age: 25            │");
        println("                  └────────────────────┘");
        println();
        
        subtitle("【为什么性能更强】");
        println();
        println("1. 对象创建：");
        println("   JS: 运行时动态创建，灵活但慢");
        println("   Java: 编译时确定结构，内存布局固定，快");
        println();
        println("2. 方法调用：");
        println("   JS: 通过原型链查找，有额外开销");
        println("   Java: 静态绑定（直接调用）或虚方法表（动态绑定）");
        println();
        println("3. 类型检查：");
        println("   JS: 运行时类型检查");
        println("   Java: 编译时类型检查，运行时无需检查");
        println();
        println("结论：Java 对象操作比 JS 快 3-10 倍，但 JS 更灵活");
        
        line();
        
        
        // ==================== 5. 实战案例 ⭐ ====================
        title("5. 实战案例 - 电商订单系统（完整前后端）");
        
        subtitle("【业务场景】");
        println("功能：创建订单，根据用户等级计算折扣");
        println();
        
        subtitle("【前端代码】");
        println();
        println("// 前端发起请求");
        println("const createOrder = async () => {");
        println("  const response = await fetch('/api/orders', {");
        println("    method: 'POST',");
        println("    headers: { 'Content-Type': 'application/json' },");
        println("    body: JSON.stringify({");
        println("      userId: 1,");
        println("      productId: 100,");
        println("      quantity: 2");
        println("    })");
        println("  });");
        println();
        println("  const order = await response.json();");
        println("  console.log(order);  // { orderId: 123, totalAmount: 180.0, ... }");
        println("};");
        println();
        
        subtitle("【后端代码 - 完整实现】");
        println();
        
        println("// 1. 订单实体类");
        println("@Entity");
        println("public class Order {");
        println("  @Id @GeneratedValue");
        println("  private Long id;");
        println("  private Long userId;");
        println("  private Long productId;");
        println("  private Integer quantity;");
        println("  private Double totalAmount;");
        println();
        println("  // 构造函数、getter、setter");
        println("}");
        println();
        
        println("// 2. 折扣策略接口（多态）");
        println("interface DiscountStrategy {");
        println("  double calculate(double price);");
        println("}");
        println();
        println("class NormalDiscount implements DiscountStrategy {");
        println("  public double calculate(double price) { return price; }");
        println("}");
        println();
        println("class VIPDiscount implements DiscountStrategy {");
        println("  public double calculate(double price) { return price * 0.9; }");
        println("}");
        println();
        
        println("// 3. Service 层（业务逻辑）");
        println("@Service");
        println("public class OrderService {");
        println();
        println("  public Order createOrder(Long userId, Long productId, int quantity) {");
        println("    // 查询用户和商品");
        println("    User user = userService.findById(userId);");
        println("    Product product = productService.findById(productId);");
        println();
        println("    // 创建订单");
        println("    Order order = new Order();");
        println("    order.setUserId(userId);");
        println("    order.setProductId(productId);");
        println("    order.setQuantity(quantity);");
        println();
        println("    // 根据用户类型选择折扣策略（多态）");
        println("    DiscountStrategy discount = user.isVIP() ?");
        println("      new VIPDiscount() : new NormalDiscount();");
        println();
        println("    // 计算总金额");
        println("    double totalPrice = product.getPrice() * quantity;");
        println("    order.setTotalAmount(discount.calculate(totalPrice));");
        println();
        println("    // 保存订单");
        println("    return orderRepository.save(order);");
        println("  }");
        println("}");
        println();
        
        println("// 4. Controller 层（接口）");
        println("@RestController");
        println("@RequestMapping(\"/api/orders\")");
        println("public class OrderController {");
        println();
        println("  @Autowired");
        println("  private OrderService orderService;");
        println();
        println("  @PostMapping");
        println("  public Result<OrderDTO> createOrder(@RequestBody OrderRequest request) {");
        println("    // 调用 Service");
        println("    Order order = orderService.createOrder(");
        println("      request.getUserId(),");
        println("      request.getProductId(),");
        println("      request.getQuantity()");
        println("    );");
        println();
        println("    // 转换 DTO 返回前端");
        println("    OrderDTO dto = new OrderDTO(order.getId(), order.getTotalAmount());");
        println("    return Result.success(dto);");
        println("  }");
        println("}");
        println();
        
        subtitle("【数据流向】");
        println();
        println("前端 fetch('/api/orders')");
        println("    ↓ HTTP POST");
        println("Controller 接收 OrderRequest（DTO）");
        println("    ↓");
        println("Service 处理业务逻辑");
        println("    ├─ 查询 User 对象（封装）");
        println("    ├─ 查询 Product 对象");
        println("    ├─ 创建 Order 对象");
        println("    ├─ 多态计算折扣（DiscountStrategy）");
        println("    └─ 保存到数据库");
        println("    ↓");
        println("Controller 转换 OrderDTO");
        println("    ↓ 自动序列化成 JSON");
        println("前端收到 { orderId: 123, totalAmount: 180.0 }");
        println();
        
        subtitle("【模拟执行】");
        println();
        println("模拟场景：普通用户 vs VIP 用户购买同一商品");
        double productPrice = 100.0;
        int quantity = 2;
        println("  商品单价: $" + productPrice);
        println("  购买数量: " + quantity);
        println("  原价: $" + (productPrice * quantity));
        println();
        DiscountStrategy normalStrategy = new NormalDiscount();
        DiscountStrategy vipStrategy = new VIPDiscount();
        println("  普通用户支付: $" + normalStrategy.calculate(productPrice * quantity));
        println("  VIP用户支付: $" + vipStrategy.calculate(productPrice * quantity));
        
        line();
        
        
        // ==================== 6. Spring Boot落地 ====================
        title("6. Spring Boot 落地 - 会写接口就够了");
        
        subtitle("【Controller 怎么写】");
        println();
        println("@RestController");
        println("@RequestMapping(\"/api/users\")");
        println("public class UserController {");
        println();
        println("  @Autowired  // 依赖注入");
        println("  private UserService userService;");
        println();
        println("  @GetMapping(\"/{id}\")");
        println("  public Result<UserDTO> getUser(@PathVariable Long id) {");
        println("    User user = userService.findById(id);  // 调用 Service");
        println("    UserDTO dto = new UserDTO(user.getId(), user.getName());");
        println("    return Result.success(dto);  // 返回 DTO");
        println("  }");
        println();
        println("  @PostMapping");
        println("  public Result<UserDTO> createUser(@RequestBody UserRequest request) {");
        println("    User user = userService.create(request);");
        println("    return Result.success(new UserDTO(user));");
        println("  }");
        println("}");
        println();
        println("💡 核心：Controller 只负责接收请求和返回响应");
        println();
        
        subtitle("【Service 怎么用】");
        println();
        println("// Service 接口");
        println("public interface UserService {");
        println("  User findById(Long id);");
        println("  User create(UserRequest request);");
        println("}");
        println();
        println("// Service 实现");
        println("@Service");
        println("public class UserServiceImpl implements UserService {");
        println();
        println("  @Autowired");
        println("  private UserRepository userRepository;");
        println();
        println("  @Override");
        println("  public User findById(Long id) {");
        println("    return userRepository.findById(id)");
        println("      .orElseThrow(() -> new NotFoundException(\"用户不存在\"));");
        println("  }");
        println();
        println("  @Override");
        println("  @Transactional  // 简单事务：方法成功提交，失败回滚");
        println("  public User create(UserRequest request) {");
        println("    User user = new User();");
        println("    user.setName(request.getName());");
        println("    return userRepository.save(user);");
        println("  }");
        println("}");
        println();
        println("💡 核心：Service 负责业务逻辑");
        println();
        
        subtitle("【简单事务理解】");
        println();
        println("@Transactional 的作用：");
        println("  ✅ 方法执行成功 → 自动提交（commit）");
        println("  ❌ 方法抛出异常 → 自动回滚（rollback）");
        println();
        println("示例：");
        println("@Transactional");
        println("public void transferMoney(Long fromId, Long toId, double amount) {");
        println("  accountService.deduct(fromId, amount);  // 扣款");
        println("  accountService.add(toId, amount);       // 加款");
        println("  // 如果 add() 失败，deduct() 也会回滚");
        println("}");
        println();
        println("💡 现在不需要深入原理，记住这个注解就够了");
        
        line();
        
        
        // ==================== 7. 进阶 & 工程化 ====================
        title("7. 进阶 & 工程化 - 项目结构和最佳实践");
        
        subtitle("【项目分层架构】");
        println();
        println("src/main/java/com/example/");
        println("├── controller/       # 接收请求，返回响应");
        println("│   └── UserController.java");
        println("├── service/          # 业务逻辑");
        println("│   ├── UserService.java         (接口)");
        println("│   └── impl/UserServiceImpl.java (实现)");
        println("├── repository/       # 数据访问（JPA）");
        println("│   └── UserRepository.java");
        println("├── entity/           # 实体类（对应数据库表）");
        println("│   └── User.java");
        println("└── dto/              # 数据传输对象（前后端交互）");
        println("    ├── UserDTO.java");
        println("    └── UserRequest.java");
        println();
        
        subtitle("【常见进阶能力】");
        println();
        println("1. 流式处理（Stream API）");
        println("   List<User> vipUsers = users.stream()");
        println("     .filter(u -> u.isVIP())");
        println("     .collect(Collectors.toList());");
        println();
        println("2. Optional 避免空指针");
        println("   Optional<User> user = userRepository.findById(id);");
        println("   return user.orElseThrow(() -> new NotFoundException());");
        println();
        println("3. 枚举（Enum）");
        println("   enum UserRole { NORMAL, VIP, ADMIN }");
        println();
        
        subtitle("【最佳实践（简单原则）】");
        println();
        println("✅ 原则1：面向接口编程");
        println("  - Service 定义成接口");
        println("  - 依赖注入时注入接口，不是实现类");
        println();
        println("✅ 原则2：DTO 和 Entity 分离");
        println("  - Entity 对应数据库表（有密码、时间戳等）");
        println("  - DTO 对应前后端交互（只返回需要的字段）");
        println("  - ❌ 不要直接返回 Entity 给前端");
        println();
        println("✅ 原则3：单一职责");
        println("  - Controller：接收请求 + 返回响应");
        println("  - Service：业务逻辑");
        println("  - Repository：数据访问");
        println("  - Entity：数据模型");
        println();
        println("✅ 原则4：保持简单");
        println("  - 不要过度设计");
        println("  - 需要时再抽象，不要一开始就写复杂继承");
        
        line();
        
        
        // ==================== 8. 常见坑 + 面试 ====================
        title("8. 常见坑 + 面试 - 前端视角");
        
        subtitle("【容易踩的坑】");
        println();
        
        println("坑1：忘记 getter/setter（JS 思维）");
        println("  ❌ user.name = \"Alice\"  // 编译错误！private 不能直接访问");
        println("  ✅ user.setName(\"Alice\")");
        println();
        
        println("坑2：构造函数不写返回类型");
        println("  ❌ public void User() {}  // 这是普通方法，不是构造函数");
        println("  ✅ public User() {}       // 构造函数不写 void");
        println();
        
        println("坑3：继承时忘记 super()");
        println("  ❌ class VIPUser extends User {");
        println("       public VIPUser() {  // 编译错误！父类没有无参构造函数");
        println("       }");
        println("     }");
        println("  ✅ public VIPUser(String name, int age) {");
        println("       super(name, age);  // 显式调用父类构造函数");
        println("     }");
        println();
        
        println("坑4：直接返回 Entity 给前端");
        println("  ❌ return userService.findById(id);  // 暴露了 password 等敏感字段");
        println("  ✅ User user = userService.findById(id);");
        println("     return new UserDTO(user.getId(), user.getName());");
        println();
        
        println("坑5：忘记 @Override 注解");
        println("  ❌ public void makeSond() {  // 拼错了，但不会报错");
        println("     }");
        println("  ✅ @Override");
        println("     public void makeSound() {  // 拼错会编译报错");
        println("     }");
        println();
        
        subtitle("【高频面试问题】");
        println();
        
        println("问题1：面向对象三大特性？");
        println("答：");
        println("  1. 封装：private 隐藏属性，public 方法访问 → 保护数据");
        println("  2. 继承：extends 复用代码 → 减少重复");
        println("  3. 多态：接口 + 多个实现 → 统一接口，不同行为");
        println();
        
        println("问题2：重写（Override）和重载（Overload）的区别？");
        println("答：");
        println("  重写：子类改父类方法实现，方法签名相同");
        println("  重载：同名方法，参数不同（同一个类）");
        println();
        
        println("问题3：接口和抽象类的区别？");
        println("答：");
        println("  接口：只能有抽象方法（Java 8+ 可以有 default 方法），支持多实现");
        println("  抽象类：可以有具体方法，只能单继承");
        println("  选择：定义规范用接口，代码复用用抽象类");
        println();
        
        println("问题4：项目中如何应用 OOP？");
        println("答（结合实际项目）：");
        println("  \"在电商项目中：");
        println("   1. 封装：User、Order 等实体类封装数据");
        println("   2. 继承：BaseEntity 定义公共字段（id、创建时间等）");
        println("   3. 多态：DiscountStrategy 接口 + 多个折扣实现");
        println("   4. 接口：Service 接口解耦实现");
        println("   这样代码结构清晰，容易扩展。\"");
        
        line();
        
        
        // ==================== 总结 ====================
        success("Java 面向对象【精简版】学习完成！");
        println();
        
        subtitle("🎯 核心要点总结");
        println("1. 本质：类是模板，对象是实例（和 JS class 一样）");
        println("2. 封装：private 属性 + public getter/setter");
        println("3. 继承：extends 复用代码，super 调用父类");
        println("4. 多态：接口 + 多个实现，统一接口不同行为");
        println("5. 实战：Entity 对应数据库，DTO 对应前后端交互");
        println("6. Spring Boot：Controller → Service → Repository 分层");
        println();
        
        subtitle("📈 学习路线");
        println("第1周：掌握基础（class、封装、继承）");
        println("第2周：实战应用（写用户注册 API，DTO 转换）");
        println("第3周：进阶特性（多态、接口、抽象类）");
        println("第4周：综合项目（电商订单系统）");
        println();
        
        subtitle("💡 快速上手技巧");
        println("✅ 把 JS 的 class 改成 Java class");
        println("✅ 每个 API 都有对应的实体类");
        println("✅ 用 getter/setter 代替直接访问属性");
        println("✅ 用 DTO 而不是 Entity 返回给前端");
        println("✅ Controller → Service → Repository 分层写代码");
        println();
        
        subtitle("🚀 下一步");
        println("- 学习 JPA（实体类映射到数据库）");
        println("- 学习 Spring Boot 注解（@Autowired、@Transactional）");
        println("- 完成一个完整的 CRUD 系统");
    }
}


// ==================== 演示用的类 ====================

/**
 * 用户类（封装）
 */
class User {
    private String name;
    private int age;
    
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    
    public void sayHello() {
        println("  Hello, I'm " + name + ", age " + age);
    }
    
    // 用于多态演示
    public boolean isVIP() { return false; }
}

/**
 * VIP 用户类（继承）
 */
class VIPUser extends User {
    private int level;
    
    public VIPUser(String name, int age, int level) {
        super(name, age);
        this.level = level;
    }
    
    @Override
    public void sayHello() {
        super.sayHello();
        println("  VIP Level: " + level);
    }
    
    @Override
    public boolean isVIP() { return true; }
    
    public double getDiscount() {
        return 0.9;  // VIP 9折
    }
}

/**
 * 折扣策略接口（多态）
 */
interface DiscountStrategy {
    double calculate(double price);
}

class NormalDiscount implements DiscountStrategy {
    public double calculate(double price) {
        return price;  // 无折扣
    }
}

class VIPDiscount implements DiscountStrategy {
    public double calculate(double price) {
        return price * 0.9;  // 9折
    }
}
