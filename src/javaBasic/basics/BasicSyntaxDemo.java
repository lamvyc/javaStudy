package javaBasic.basics;

import static javaBasic.utils.PrintUtil.*;

/**
 * Java基础语法【精简通用模板】
 * 
 * 目标读者：前端开发者转全栈
 * 核心思路：从JavaScript快速迁移到Java
 */
public class BasicSyntaxDemo {
    
    public static void main(String[] args) {
        
        // ==================== 1. 前端视角理解 ⭐ ====================
        title("1. 前端视角理解 - Java 基础语法的本质");
        
        subtitle("【一句话讲清本质】");
        println("  Java 基础语法 = JS 语法 + 类型声明");
        println("  if/for/while 几乎一样，只是变量必须声明类型");
        println();
        
        subtitle("【前端思维映射】");
        println("  JS 概念                →  Java 概念");
        println("  ───────────────────────────────────────");
        println("  let x = 10             →  int x = 10");
        println("  const PI = 3.14        →  final double PI = 3.14");
        println("  'hello' / \"hello\"      →  \"hello\"（只能双引号）");
        println("  [1, 2, 3]              →  new int[]{1, 2, 3}");
        println("  null / undefined       →  null（没有undefined）");
        println("  typeof x               →  x instanceof Type");
        println();
        
        subtitle("【JS vs Java 核心对比】");
        println();
        println("┌────────────────────────────────────────────────────┐");
        println("│ JavaScript            │ Java                       │");
        println("├────────────────────────────────────────────────────┤");
        println("│ // 变量               │ // 变量                     │");
        println("│ let age = 25          │ int age = 25               │");
        println("│ const name = 'Alice'  │ final String name=\"Alice\" │");
        println("│                       │                            │");
        println("│ // 条件判断           │ // 条件判断                 │");
        println("│ if (x > 0) {}         │ if (x > 0) {}              │");
        println("│                       │                            │");
        println("│ // 循环               │ // 循环                     │");
        println("│ for (let i=0; i<3;){} │ for (int i=0; i<3; i++){} │");
        println("│                       │                            │");
        println("│ // 数组               │ // 数组                     │");
        println("│ const arr = [1, 2, 3] │ int[] arr = {1, 2, 3}      │");
        println("│ arr.push(4)           │ // 长度固定，不能添加       │");
        println("└────────────────────────────────────────────────────┘");
        println();
        
        println("💡 关键差异：");
        println("  1. Java 必须声明类型（int、String、boolean）");
        println("  2. Java 没有 undefined（只有 null）");
        println("  3. Java 字符串必须用双引号");
        println("  4. Java 数组长度固定");
        println();
        
        println("✅ 相似点：");
        println("  - if/else/switch 语法几乎一样");
        println("  - for/while 循环几乎一样");
        println("  - 运算符（+、-、*、/、%、&&、||、!）完全一样");
        
        line();
        
        
        // ==================== 2. 为什么存在 ====================
        title("2. 为什么存在 - 解决什么问题");
        
        subtitle("【痛点场景】用户登录接口");
        println("前端需求：");
        println("  - 发送用户名和密码到后端");
        println("  - 接收登录结果（成功/失败）");
        println();
        
        println("❌ 如果没有基础语法会怎样？");
        println("  1. 无法存储数据：不知道用什么变量类型");
        println("  2. 无法验证数据：不会写 if/else 判断");
        println("  3. 无法遍历数据：不会写 for 循环查询用户");
        println("  4. 无法返回结果：不知道返回什么类型");
        println();
        
        println("✅ 有了基础语法的好处：");
        println("  1. 变量存储：String username, String password");
        println("  2. 条件判断：if (username.isEmpty()) { ... }");
        println("  3. 循环遍历：for (User user : users) { ... }");
        println("  4. 类型安全：boolean success（编译时检查）");
        println();
        
        subtitle("【前后端协作中的作用】");
        println("前端发送请求 → 后端用基础语法处理数据 → 返回结果");
        println();
        println("前端：");
        println("  fetch('/api/login', {");
        println("    method: 'POST',");
        println("    body: JSON.stringify({ username: 'alice', password: '123' })");
        println("  })");
        println();
        println("后端：");
        println("  @PostMapping(\"/api/login\")");
        println("  public Result login(@RequestBody LoginRequest req) {");
        println("    String username = req.getUsername();  // 变量");
        println("    if (username.isEmpty()) {             // 条件判断");
        println("      return Result.error(\"用户名不能为空\");");
        println("    }");
        println("    // ... 验证逻辑");
        println("    return Result.success();");
        println("  }");
        println();
        println("💡 基础语法就是后端处理数据的工具！");
        
        line();
        
        
        // ==================== 3. 核心用法 ⭐ ====================
        title("3. 核心用法 - 5 个典型场景");
        
        subtitle("【用法1】变量和数据类型");
        println();
        println("// JS: let age = 25, name = 'Alice', price = 99.99");
        println("// Java: 必须声明类型");
        println();
        
        int age = 25;
        String name = "Alice";
        double price = 99.99;
        boolean isActive = true;
        
        println("int age = 25;              // 整数");
        println("String name = \"Alice\";     // 字符串");
        println("double price = 99.99;      // 小数");
        println("boolean isActive = true;   // 布尔");
        println();
        println("实际执行:");
        println("  age = " + age);
        println("  name = " + name);
        println("  price = " + price);
        println("  isActive = " + isActive);
        println();
        
        println("📋 Java 数据类型速记：");
        println("  整数：byte、short、int（常用）、long");
        println("  小数：float、double（常用）");
        println("  字符：char");
        println("  布尔：boolean");
        println("  字符串：String");
        println();
        
        subtitle("【用法2】条件判断（if/else）");
        println();
        println("// 场景：验证用户输入");
        String username = "alice";
        String password = "123456";
        
        println("if (username.isEmpty()) {");
        println("    return \"用户名不能为空\";");
        println("} else if (username.length() < 3) {");
        println("    return \"用户名至少3个字符\";");
        println("} else if (password.length() < 6) {");
        println("    return \"密码至少6个字符\";");
        println("} else {");
        println("    return \"验证通过\";");
        println("}");
        println();
        
        println("实际执行:");
        if (username.isEmpty()) {
            println("  ❌ 用户名不能为空");
        } else if (username.length() < 3) {
            println("  ❌ 用户名至少3个字符");
        } else if (password.length() < 6) {
            println("  ❌ 密码至少6个字符");
        } else {
            println("  ✅ 验证通过");
        }
        println();
        
        subtitle("【用法3】Switch 语句");
        println();
        println("// 场景：处理订单状态");
        String orderStatus = "PAID";
        
        println("switch (orderStatus) {");
        println("    case \"PENDING\": return \"待支付\";");
        println("    case \"PAID\": return \"已支付\";");
        println("    case \"SHIPPED\": return \"已发货\";");
        println("    default: return \"未知状态\";");
        println("}");
        println();
        
        println("实际执行:");
        String statusMsg = switch (orderStatus) {
            case "PENDING" -> "待支付";
            case "PAID" -> "已支付";
            case "SHIPPED" -> "已发货";
            default -> "未知状态";
        };
        println("  订单状态: " + statusMsg);
        println();
        
        subtitle("【用法4】循环（for/while）");
        println();
        println("// 场景：遍历用户列表，找出成年用户");
        int[] ages = {15, 20, 17, 25, 30, 16};
        
        println("int adultCount = 0;");
        println("for (int userAge : ages) {");
        println("    if (userAge >= 18) {");
        println("        adultCount++;");
        println("    }");
        println("}");
        println();
        
        println("实际执行:");
        int adultCount = 0;
        print("  成年用户年龄: ");
        for (int userAge : ages) {
            if (userAge >= 18) {
                print(userAge + " ");
                adultCount++;
            }
        }
        println();
        println("  成年用户数量: " + adultCount);
        println();
        
        subtitle("【用法5】数组操作");
        println();
        println("// 场景：存储商品价格，计算总价");
        double[] prices = {150.5, 200.0, 175.8, 220.3};
        
        println("double[] prices = {150.5, 200.0, 175.8, 220.3};");
        println("double total = 0;");
        println("for (double p : prices) {");
        println("    total += p;");
        println("}");
        println();
        
        println("实际执行:");
        double total = 0;
        for (double p : prices) {
            total += p;
        }
        println("  商品数量: " + prices.length);
        println("  总价: " + total);
        println("  平均价格: " + (total / prices.length));
        
        line();
        
        
        // ==================== 4. 底层原理（对比理解） ====================
        title("4. 底层原理 - Java vs JS 执行机制");
        
        subtitle("【类型系统对比】");
        println();
        println("JavaScript - 动态类型:");
        println("  let x = 10;        // x 是 number");
        println("  x = 'hello';       // ✅ 可以！x 变成 string");
        println("  特点：灵活但容易出错（运行时才发现错误）");
        println();
        
        println("Java - 静态类型:");
        println("  int x = 10;        // x 是 int");
        println("  x = \"hello\";       // ❌ 编译错误！类型不匹配");
        println("  特点：严格但安全（编译时就能发现错误）");
        println();
        
        subtitle("【内存分配】");
        println();
        println("基本类型（int、double、boolean）：");
        println("  - 直接存储在栈内存");
        println("  - 速度快，内存占用小");
        println("  int age = 25;  // 栈：age = 25");
        println();
        
        println("引用类型（String、数组、对象）：");
        println("  - 引用在栈，对象在堆");
        println("  - 需要通过引用访问");
        println("  String name = \"Alice\";");
        println("  // 栈：name = 内存地址 0x1234");
        println("  // 堆：0x1234 = \"Alice\" 对象");
        println();
        
        subtitle("【为什么性能不同】");
        println();
        println("1. 类型检查：");
        println("   JS: 运行时检查类型，慢");
        println("   Java: 编译时已确定类型，快");
        println();
        println("2. 数组访问：");
        println("   JS: Array 是对象，有方法开销");
        println("   Java: 原生数组，直接内存访问");
        println();
        println("3. 变量访问：");
        println("   JS: 需要在作用域链中查找");
        println("   Java: 编译时确定位置，直接访问");
        println();
        println("结论：Java 基本操作比 JS 快，但 JS 有 JIT 优化");
        
        line();
        
        
        // ==================== 5. 实战案例 ⭐ ====================
        title("5. 实战案例 - 用户登录系统（完整前后端）");
        
        subtitle("【业务场景】");
        println("功能：用户登录验证");
        println("  1. 验证用户名和密码不为空");
        println("  2. 验证长度（用户名3-20，密码6-20）");
        println("  3. 查询数据库验证用户");
        println("  4. 返回登录结果");
        println();
        
        subtitle("【前端代码】");
        println();
        println("// 前端发起请求");
        println("const login = async (username, password) => {");
        println("  const response = await fetch('/api/auth/login', {");
        println("    method: 'POST',");
        println("    headers: { 'Content-Type': 'application/json' },");
        println("    body: JSON.stringify({ username, password })");
        println("  });");
        println("  const result = await response.json();");
        println("  if (result.success) {");
        println("    router.push('/home');");
        println("  } else {");
        println("    message.error(result.message);");
        println("  }");
        println("};");
        println();
        
        subtitle("【后端代码 - 完整实现】");
        println();
        
        // 模拟数据库数据
        String[] dbUsernames = {"alice", "bob", "charlie"};
        String[] dbPasswords = {"123456", "password", "abc123"};
        
        // 接收前端参数
        String loginUsername = "alice";
        String loginPassword = "123456";
        
        println("// Controller 层");
        println("@PostMapping(\"/api/auth/login\")");
        println("public Result login(@RequestBody LoginRequest req) {");
        println("    String username = req.getUsername();");
        println("    String password = req.getPassword();");
        println();
        println("    // 1. 非空验证");
        println("    if (username == null || username.isEmpty()) {");
        println("        return Result.error(\"用户名不能为空\");");
        println("    }");
        println("    if (password == null || password.isEmpty()) {");
        println("        return Result.error(\"密码不能为空\");");
        println("    }");
        println();
        println("    // 2. 长度验证");
        println("    if (username.length() < 3 || username.length() > 20) {");
        println("        return Result.error(\"用户名长度必须在3-20之间\");");
        println("    }");
        println("    if (password.length() < 6 || password.length() > 20) {");
        println("        return Result.error(\"密码长度必须在6-20之间\");");
        println("    }");
        println();
        println("    // 3. 查询数据库");
        println("    boolean found = false;");
        println("    for (int i = 0; i < dbUsernames.length; i++) {");
        println("        if (dbUsernames[i].equals(username) &&");
        println("            dbPasswords[i].equals(password)) {");
        println("            found = true;");
        println("            break;");
        println("        }");
        println("    }");
        println();
        println("    // 4. 返回结果");
        println("    if (found) {");
        println("        return Result.success(\"登录成功\");");
        println("    } else {");
        println("        return Result.error(\"用户名或密码错误\");");
        println("    }");
        println("}");
        println();
        
        subtitle("【模拟执行】");
        println();
        println("接收到登录请求:");
        println("  username: " + loginUsername);
        println("  password: " + loginPassword);
        println();
        
        // 实际验证逻辑
        boolean success = false;
        String errorMessage = "";
        
        if (loginUsername == null || loginUsername.isEmpty()) {
            errorMessage = "用户名不能为空";
        } else if (loginPassword == null || loginPassword.isEmpty()) {
            errorMessage = "密码不能为空";
        } else if (loginUsername.length() < 3 || loginUsername.length() > 20) {
            errorMessage = "用户名长度必须在3-20之间";
        } else if (loginPassword.length() < 6 || loginPassword.length() > 20) {
            errorMessage = "密码长度必须在6-20之间";
        } else {
            for (int i = 0; i < dbUsernames.length; i++) {
                if (dbUsernames[i].equals(loginUsername) && 
                    dbPasswords[i].equals(loginPassword)) {
                    success = true;
                    break;
                }
            }
            if (!success) {
                errorMessage = "用户名或密码错误";
            }
        }
        
        println("验证结果:");
        if (success) {
            println("  ✅ 登录成功");
            println("  返回给前端: { success: true, message: '登录成功' }");
        } else {
            println("  ❌ 登录失败: " + errorMessage);
            println("  返回给前端: { success: false, message: '" + errorMessage + "' }");
        }
        println();
        
        subtitle("【数据流向】");
        println();
        println("前端 fetch('/api/auth/login')");
        println("    ↓ HTTP POST");
        println("Controller 接收参数（username, password）");
        println("    ↓");
        println("验证输入");
        println("    ├─ 非空验证（if）");
        println("    ├─ 长度验证（if）");
        println("    └─ 格式验证");
        println("    ↓");
        println("查询数据库（for 循环）");
        println("    ↓");
        println("返回 JSON 结果");
        println("    ↓");
        println("前端接收 { success, message }");
        
        line();
        
        
        // ==================== 6. Spring Boot落地 ====================
        title("6. Spring Boot 落地 - 会写接口就够了");
        
        subtitle("【Controller 层代码】");
        println();
        println("@RestController");
        println("@RequestMapping(\"/api/auth\")");
        println("public class AuthController {");
        println();
        println("    @PostMapping(\"/login\")");
        println("    public Result login(@RequestBody LoginRequest request) {");
        println("        String username = request.getUsername();");
        println("        String password = request.getPassword();");
        println();
        println("        // 基础语法：变量存储");
        println("        String errorMsg = null;");
        println();
        println("        // 基础语法：条件判断");
        println("        if (username == null || username.isEmpty()) {");
        println("            errorMsg = \"用户名不能为空\";");
        println("        } else if (username.length() < 3) {");
        println("            errorMsg = \"用户名至少3个字符\";");
        println("        }");
        println();
        println("        if (errorMsg != null) {");
        println("            return Result.error(errorMsg);");
        println("        }");
        println();
        println("        // 调用 Service");
        println("        boolean success = authService.login(username, password);");
        println("        return success ? ");
        println("            Result.success(\"登录成功\") : ");
        println("            Result.error(\"用户名或密码错误\");");
        println("    }");
        println("}");
        println();
        println("💡 核心：Controller 用基础语法做参数验证");
        println();
        
        subtitle("【请求对象定义】");
        println();
        println("public class LoginRequest {");
        println("    private String username;  // 基础语法：变量");
        println("    private String password;");
        println();
        println("    public String getUsername() { return username; }");
        println("    public void setUsername(String username) { ");
        println("        this.username = username; ");
        println("    }");
        println("}");
        
        line();
        
        
        // ==================== 7. 进阶 & 工程化 ====================
        title("7. 进阶 & 工程化 - 提升效率");
        
        subtitle("【进阶能力1：三元运算符】");
        println();
        int score = 85;
        String grade = score >= 90 ? "优秀" : score >= 60 ? "及格" : "不及格";
        println("int score = 85;");
        println("String grade = score >= 90 ? \"优秀\" : ");
        println("               score >= 60 ? \"及格\" : \"不及格\";");
        println("结果: " + grade);
        println();
        
        subtitle("【进阶能力2：字符串格式化】");
        println();
        String user = "Alice";
        int orderCount = 5;
        double totalAmount = 299.99;
        String msg = String.format("用户%s共下单%d笔，总金额%.2f元", 
            user, orderCount, totalAmount);
        println("String msg = String.format(");
        println("    \"用户%s共下单%d笔，总金额%.2f元\",");
        println("    user, orderCount, totalAmount");
        println(");");
        println("结果: " + msg);
        println();
        
        subtitle("【最佳实践】");
        println();
        println("✅ 原则1：命名规范");
        println("  变量：小驼峰（userName、isActive）");
        println("  常量：全大写（MAX_SIZE、API_KEY）");
        println("  类名：大驼峰（UserController、OrderService）");
        println();
        println("✅ 原则2：字符串比较用 equals");
        println("  ❌ if (str == \"hello\")  // 比较引用地址");
        println("  ✅ if (\"hello\".equals(str))  // 比较内容，防空指针");
        println();
        println("✅ 原则3：避免魔法数字");
        println("  ❌ if (status == 1)");
        println("  ✅ final int STATUS_ACTIVE = 1;");
        println("     if (status == STATUS_ACTIVE)");
        
        line();
        
        
        // ==================== 8. 常见坑 + 面试 ====================
        title("8. 常见坑 + 面试 - 前端视角");
        
        subtitle("【容易踩的坑】");
        println();
        
        println("坑1：字符串比较用 ==（JS 思维）");
        println("  ❌ if (name == \"Alice\")  // 比较引用地址");
        println("  ✅ if (\"Alice\".equals(name))  // 比较内容");
        println();
        
        println("坑2：数组越界");
        println("  ❌ int[] arr = {1, 2, 3};");
        println("     int value = arr[3];  // 下标从0开始，没有arr[3]");
        println("  ✅ if (index < arr.length) { arr[index]; }");
        println();
        
        println("坑3：整数除法");
        println("  ❌ int a = 5, b = 2;");
        println("     double result = a / b;  // 结果是 2.0（整数除法）");
        println("  ✅ double result = (double) a / b;  // 结果是 2.5");
        println();
        int a = 5, b = 2;
        println("  实际演示:");
        println("    5 / 2 = " + (a / b) + "（错误）");
        println("    5 / 2 = " + ((double) a / b) + "（正确）");
        println();
        
        println("坑4：null 值处理");
        println("  ❌ String str = null;");
        println("     if (str.equals(\"hello\")) {}  // NullPointerException");
        println("  ✅ if (\"hello\".equals(str)) {}  // 不会空指针");
        println();
        
        println("坑5：switch 忘记 break");
        println("  ❌ switch (status) {");
        println("         case 1: doSomething();  // 忘记break");
        println("         case 2: doOther();      // 会执行两个case");
        println("     }");
        println("  ✅ 每个 case 后都加 break");
        println();
        
        subtitle("【高频面试问题】");
        println();
        
        println("问题1：Java 和 JavaScript 的主要区别？");
        println("答：");
        println("  1. 类型系统：Java 静态类型，JS 动态类型");
        println("  2. 编译执行：Java 编译成字节码，JS 解释执行");
        println("  3. 应用场景：Java 后端/企业，JS 前端/Node");
        println();
        
        println("问题2：为什么字符串比较要用 equals？");
        println("答：");
        println("  String 是引用类型：");
        println("  == 比较内存地址（引用）");
        println("  equals() 比较字符串内容");
        println();
        
        println("问题3：数组和 List 的区别？");
        println("答：");
        println("  数组：固定长度，性能高，可存基本类型");
        println("  List：动态长度，可增删，只能存对象");
        println();
        
        println("问题4：项目中如何使用基础语法？");
        println("答（结合实际）：");
        println("  \"在用户管理模块中：");
        println("   1. 用 String 存储用户名，int 存储年龄");
        println("   2. 用 if/else 验证用户输入是否合法");
        println("   3. 用 for 循环遍历用户列表");
        println("   4. 用数组存储权限列表");
        println("   基础语法在 Controller 层做参数校验时最常用。\"");
        
        line();
        
        
        // ==================== 总结 ====================
        success("Java 基础语法【精简版】学习完成！");
        println();
        
        subtitle("🎯 核心要点总结");
        println("1. 本质：Java 语法 = JS 语法 + 类型声明");
        println("2. 变量：必须声明类型（int、String、boolean）");
        println("3. 字符串：用 equals() 比较，不能用 ==");
        println("4. 数组：长度固定，用 [] 访问");
        println("5. 实战：Controller 层做参数验证和数据处理");
        println("6. 最常用：if/else、for、String、int");
        println();
        
        subtitle("📈 学习路线");
        println("第1周：掌握变量、if/else、for、数组");
        println("第2周：写登录验证、订单状态处理");
        println("第3周：搭建 Spring Boot，写第一个 API");
        println("第4周：完成用户管理系统（CRUD）");
        println();
        
        subtitle("💡 快速上手技巧");
        println("✅ 变量声明：int x = 10（和 JS 的 let x = 10 类似）");
        println("✅ 条件判断：if/else 和 JS 一样");
        println("✅ 循环遍历：for (int i=0; i<n; i++) 和 JS 一样");
        println("✅ 字符串比较：用 equals()，不要用 ==");
        println("✅ 实战导向：立即用于后端 API 开发");
        println();
        
        subtitle("🚀 下一步");
        println("- 学习面向对象（类、继承、多态）");
        println("- 学习集合框架（List、Map）");
        println("- 学习 Lambda/Stream（函数式编程）");
        println("- 搭建 Spring Boot 项目");
    }
}
