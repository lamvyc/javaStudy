package javaAdvanced.jvm;

import static javaBasic.utils.PrintUtil.*;

/**
 * JVM学习【可记忆版 - 新模板】
 * 
 * 按照新模板设计：一个认知 + 一个代码 + 一个场景
 * 核心理念：能跑 > 能用 > 能懂
 */
public class JVMNewDemo {
    
    public static void main(String[] args) {
        
        // ==================== 0. 5秒速记卡 ⭐ ====================
        title("【0. 学完必须带走什么】⭐ 5秒速记卡");
        
        println("这一章你只允许记住这 5 件事：");
        println();
        
        println("① 一句话本质：");
        println("   JVM = Java虚拟机 = 帮你管内存的管家（自动垃圾回收）");
        println();
        
        println("② 核心场景：");
        println("   内存溢出时排查问题（堆/栈/方法区哪里出问题了）");
        println();
        
        println("③ 必会代码：");
        println("   System.gc() - 建议垃圾回收");
        println("   Runtime.getRuntime().totalMemory() - 查看总内存");
        println("   Runtime.getRuntime().freeMemory() - 查看空闲内存");
        println();
        
        println("④ 常见坑：");
        println("   内存泄漏：对象用完没释放引用，导致内存占满");
        println();
        
        println("⑤ 面试点：");
        println("   JVM内存结构：堆、栈、方法区");
        println("   垃圾回收算法：标记-清除、标记-整理、复制算法");
        println("   类加载过程：加载→验证→准备→解析→初始化");
        
        line();
        
        
        // ==================== 1. 前端视角理解 ⭐ ====================
        title("【1. 前端视角理解】建立认知桥梁");
        
        subtitle("一句话讲清本质（说人话）");
        println("  JVM = 虚拟的计算机");
        println("  - 前端：浏览器运行JS代码");
        println("  - Java：JVM运行Java代码");
        println("  - 都是「虚拟环境」，不直接操作硬件");
        println();
        
        subtitle("JS 类比（强绑定前端经验）");
        println();
        println("JavaScript 中的内存管理：");
        println("  let obj = { name: 'Alice' };  // 对象存在内存");
        println("  obj = null;                   // 浏览器自动回收");
        println();
        println("Java 中的内存管理：");
        println("  Object obj = new Object();    // 对象存在堆内存");
        println("  obj = null;                   // JVM自动垃圾回收");
        println();
        
        subtitle("JS vs Java 核心对比");
        println();
        println("┌──────────────────────────────────────────────────┐");
        println("│ 维度        │ JavaScript       │ Java (JVM)      │");
        println("├──────────────────────────────────────────────────┤");
        println("│ 运行环境    │ 浏览器/Node.js   │ JVM             │");
        println("│ 内存管理    │ V8引擎自动回收   │ JVM垃圾回收     │");
        println("│ 内存区域    │ 堆+栈            │ 堆+栈+方法区    │");
        println("│ 垃圾回收    │ 自动（看不到）   │ 自动（可配置）  │");
        println("└──────────────────────────────────────────────────┘");
        println();
        println("💡 关键：都是自动内存管理，开发者不用手动malloc/free");
        
        line();
        
        
        // ==================== 2. 为什么存在 ====================
        title("【2. 为什么存在】解决的痛点");
        
        subtitle("解决什么问题（真实痛点）");
        println();
        println("场景：后端服务运行中突然崩溃");
        println();
        println("❌ 如果没有JVM：");
        println("  1. 手动管理内存（C/C++的malloc/free）");
        println("  2. 内存泄漏很难发现");
        println("  3. 不同操作系统要写不同代码");
        println("  4. 程序崩溃难以排查");
        println();
        println("✅ 有了JVM：");
        println("  1. 自动垃圾回收（GC），不用手动释放内存");
        println("  2. 跨平台运行（Write Once, Run Anywhere）");
        println("  3. 内存问题有工具排查（jmap、jstack）");
        println("  4. 性能可调优（JVM参数）");
        println();
        
        subtitle("在前后端中的位置");
        println();
        println("前端发起请求");
        println("    ↓");
        println("后端Controller（运行在JVM上）← JVM在这里");
        println("    ↓");
        println("Service层（JVM管理内存）");
        println("    ↓");
        println("数据库操作");
        println();
        println("💡 JVM就像后端程序的「托管环境」，负责内存管理和程序运行");
        
        line();
        
        
        // ==================== 3. 核心用法 ⭐ ====================
        title("【3. 核心用法】必须会写");
        
        subtitle("用法1：查看JVM内存信息");
        println();
        println("场景：线上服务占用内存越来越高，需要排查");
        println();
        demonstrateMemoryInfo();
        println();
        
        subtitle("用法2：触发垃圾回收");
        println();
        println("场景：系统空闲时主动释放内存");
        println();
        demonstrateGC();
        println();
        
        subtitle("用法3：理解堆和栈的区别");
        println();
        println("场景：排查StackOverflowError和OutOfMemoryError");
        println();
        demonstrateHeapVsStack();
        println();
        
        subtitle("前后端交互示例");
        println();
        println("// 前端调用");
        println("fetch('/api/system/memory')");
        println("  .then(res => res.json())");
        println("  .then(data => {");
        println("    console.log('总内存:', data.totalMemory);");
        println("    console.log('已用内存:', data.usedMemory);");
        println("  });");
        println();
        println("// 后端处理（返回JVM内存信息）");
        println("@GetMapping(\"/api/system/memory\")");
        println("public MemoryInfo getMemory() {");
        println("    Runtime rt = Runtime.getRuntime();");
        println("    return new MemoryInfo(");
        println("        rt.totalMemory(),");
        println("        rt.totalMemory() - rt.freeMemory()");
        println("    );");
        println("}");
        
        line();
        
        
        // ==================== 4. 底层原理（轻理解） ====================
        title("【4. 底层原理】轻理解");
        
        subtitle("Java vs JS 执行差异");
        println();
        println("JavaScript：");
        println("  代码 → V8引擎解释执行 → 直接运行");
        println();
        println("Java：");
        println("  代码 → 编译成字节码(.class) → JVM解释/JIT编译 → 运行");
        println();
        
        subtitle("为什么这样设计（结论式）");
        println();
        println("原因：跨平台 + 性能优化");
        println("  - 字节码可以在任何有JVM的平台运行");
        println("  - JIT即时编译器把热点代码编译成机器码，性能接近C++");
        println();
        println("好处：");
        println("  - 开发效率高（不用管内存）");
        println("  - 跨平台（Windows/Linux/Mac都能跑）");
        println("  - 性能可控（可调整JVM参数）");
        
        line();
        
        
        // ==================== 5. 实战案例 ⭐⭐⭐ ====================
        title("【5. 实战案例】⭐⭐⭐ 核心");
        
        subtitle("业务场景");
        println("功能：监控系统内存使用情况API");
        println("场景：运维需要实时查看后端服务的内存状态");
        println();
        
        subtitle("后端代码（Java）");
        println();
        println("// Controller层（接收请求）");
        println("@RestController");
        println("@RequestMapping(\"/api/monitor\")");
        println("public class MonitorController {");
        println("    ");
        println("    @GetMapping(\"/memory\")");
        println("    public Result getMemoryInfo() {");
        println("        Runtime rt = Runtime.getRuntime();");
        println("        ");
        println("        MemoryInfo info = new MemoryInfo();");
        println("        info.setTotalMemory(rt.totalMemory() / 1024 / 1024); // MB");
        println("        info.setFreeMemory(rt.freeMemory() / 1024 / 1024);");
        println("        info.setUsedMemory(info.getTotalMemory() - info.getFreeMemory());");
        println("        info.setMaxMemory(rt.maxMemory() / 1024 / 1024);");
        println("        ");
        println("        return Result.success(info);");
        println("    }");
        println("    ");
        println("    @PostMapping(\"/gc\")");
        println("    public Result triggerGC() {");
        println("        System.gc();");
        println("        return Result.success(\"垃圾回收已触发\");");
        println("    }");
        println("}");
        println();
        
        subtitle("实际执行：获取内存信息");
        println();
        println("📡 前端请求：GET /api/monitor/memory");
        println("✓ 响应 200 OK");
        println();
        Runtime rt = Runtime.getRuntime();
        long totalMemory = rt.totalMemory() / 1024 / 1024;
        long freeMemory = rt.freeMemory() / 1024 / 1024;
        long usedMemory = totalMemory - freeMemory;
        long maxMemory = rt.maxMemory() / 1024 / 1024;
        
        println("响应数据（JSON格式）：");
        println("{");
        println("  \"success\": true,");
        println("  \"data\": {");
        println("    \"totalMemory\": " + totalMemory + ",    // 当前分配的总内存（MB）");
        println("    \"freeMemory\": " + freeMemory + ",     // 空闲内存（MB）");
        println("    \"usedMemory\": " + usedMemory + ",     // 已使用内存（MB）");
        println("    \"maxMemory\": " + maxMemory + "     // 最大可用内存（MB）");
        println("  }");
        println("}");
        println();
        
        subtitle("前端调用");
        println();
        println("// 使用fetch");
        println("async function getMemoryInfo() {");
        println("    const res = await fetch('/api/monitor/memory');");
        println("    const result = await res.json();");
        println("    if (result.success) {");
        println("        const { totalMemory, usedMemory, maxMemory } = result.data;");
        println("        console.log(`内存使用: ${usedMemory}MB / ${totalMemory}MB`);");
        println("        console.log(`使用率: ${(usedMemory/totalMemory*100).toFixed(2)}%`);");
        println("    }");
        println("}");
        println();
        println("// 使用axios");
        println("axios.get('/api/monitor/memory')");
        println("    .then(res => {");
        println("        const memory = res.data.data;");
        println("        console.log('内存使用情况:', memory);");
        println("    });");
        println();
        
        subtitle("数据流向");
        println();
        println("前端发起请求：fetch('/api/monitor/memory')");
        println("    ↓");
        println("Controller接收：getMemoryInfo()");
        println("    ↓");
        println("获取JVM内存：Runtime.getRuntime().totalMemory()");
        println("    ↓");
        println("返回JSON：{ totalMemory, usedMemory, ... }");
        println("    ↓");
        println("前端渲染：显示内存使用率进度条");
        
        line();
        
        
        // ==================== 6. Spring Boot落地 ====================
        title("【6. Spring Boot落地】会写接口就够了");
        
        subtitle("实际项目中的JVM监控");
        println();
        println("// 系统监控Controller");
        println("@RestController");
        println("@RequestMapping(\"/api/system\")");
        println("public class SystemController {");
        println("    ");
        println("    // 获取系统信息");
        println("    @GetMapping(\"/info\")");
        println("    public Result getSystemInfo() {");
        println("        Runtime rt = Runtime.getRuntime();");
        println("        ");
        println("        Map<String, Object> info = new HashMap<>();");
        println("        info.put(\"processors\", rt.availableProcessors());");
        println("        info.put(\"totalMemory\", rt.totalMemory());");
        println("        info.put(\"freeMemory\", rt.freeMemory());");
        println("        info.put(\"maxMemory\", rt.maxMemory());");
        println("        ");
        println("        return Result.success(info);");
        println("    }");
        println("}");
        println();
        
        subtitle("实用场景");
        println();
        println("1. 运维监控大屏：实时显示服务器内存使用情况");
        println("2. 告警系统：内存使用超过80%自动发送告警");
        println("3. 性能分析：记录内存变化趋势，定位内存泄漏");
        
        line();
        
        
        // ==================== 7. 进阶 & 工程化 ====================
        title("【7. 进阶 & 工程化】建立认知");
        
        subtitle("进阶能力1：JVM参数调优");
        println();
        println("场景：服务内存不够用，需要调整堆大小");
        println();
        println("常用JVM参数：");
        println("  -Xms512m          # 初始堆大小512MB");
        println("  -Xmx2048m         # 最大堆大小2GB");
        println("  -XX:+PrintGC      # 打印GC日志");
        println();
        println("Spring Boot启动示例：");
        println("  java -Xms512m -Xmx2048m -jar app.jar");
        println();
        
        subtitle("进阶能力2：内存分析工具");
        println();
        println("场景：线上出现内存泄漏，需要排查");
        println();
        println("常用工具：");
        println("  1. jmap  - 查看堆内存快照");
        println("     jmap -heap <pid>  # 查看堆信息");
        println("  ");
        println("  2. jstack - 查看线程栈");
        println("     jstack <pid>  # 查看线程状态");
        println("  ");
        println("  3. VisualVM - 可视化监控工具");
        println("     图形界面，实时监控内存/CPU/线程");
        println();
        
        subtitle("标准项目结构（与JVM相关的配置）");
        println();
        println("src/main/resources/");
        println("├── application.yml       # Spring Boot配置");
        println("│   └── JVM参数在启动脚本中配置");
        println("├── logback.xml          # 日志配置（包含GC日志）");
        println("└── application-prod.yml # 生产环境配置");
        println();
        
        subtitle("最佳实践（3条）");
        println();
        println("1. 设置合适的堆大小");
        println("   - 初始堆=最大堆（避免频繁扩容）");
        println("   - 堆大小 = 物理内存的1/2到2/3");
        println();
        println("2. 开启GC日志");
        println("   - 生产环境必开，便于排查问题");
        println("   - -XX:+PrintGCDetails -XX:+PrintGCDateStamps");
        println();
        println("3. 监控内存使用");
        println("   - 定期查看内存趋势");
        println("   - 内存使用超过80%要告警");
        
        line();
        
        
        // ==================== 8. 常见坑 + 面试 ⭐ ====================
        title("【8. 常见坑 + 面试】⭐ 避坑 + 应试");
        
        subtitle("⚠️ 常见坑（前端视角）");
        println();
        
        println("坑1：内存泄漏");
        println("❌ 错误：");
        println("  static List<Object> cache = new ArrayList<>();");
        println("  cache.add(new BigObject());  // 一直往静态集合加，不清理");
        println();
        println("✅ 正确：");
        println("  // 使用软引用/弱引用，或定期清理");
        println("  cache.clear();");
        println();
        println("原因：静态变量不会被GC回收，导致内存一直增长");
        println();
        
        println("坑2：频繁创建大对象");
        println("❌ 错误：");
        println("  for (int i = 0; i < 10000; i++) {");
        println("      byte[] big = new byte[1024 * 1024];  // 每次1MB");
        println("  }");
        println();
        println("✅ 正确：");
        println("  byte[] reusable = new byte[1024 * 1024];  // 复用");
        println("  for (int i = 0; i < 10000; i++) {");
        println("      // 复用同一个数组");
        println("  }");
        println();
        
        println("坑3：误以为System.gc()立即回收");
        println("❌ 误解：");
        println("  System.gc();  // 以为马上回收内存");
        println("  // 立即查看内存 - 可能还没回收");
        println();
        println("✅ 正确理解：");
        println("  System.gc();  // 只是建议JVM回收");
        println("  // JVM可能不会立即执行，看心情");
        println();
        
        subtitle("🎯 高频面试题");
        println();
        
        println("问题1：JVM内存结构有哪些部分？");
        println("答：");
        println("  1. 堆（Heap）- 存储对象实例，所有线程共享");
        println("  2. 栈（Stack）- 存储局部变量和方法调用，每个线程独立");
        println("  3. 方法区/元空间 - 存储类信息、静态变量、常量");
        println("  4. 程序计数器 - 记录当前执行的字节码指令");
        println("  5. 本地方法栈 - 执行Native方法");
        println();
        
        println("问题2：什么时候会发生OutOfMemoryError？");
        println("答：");
        println("  1. 堆内存溢出 - 创建对象太多，堆空间不够");
        println("     解决：增大-Xmx，检查内存泄漏");
        println("  2. 栈溢出 - 方法调用层次太深（如递归）");
        println("     解决：检查递归终止条件");
        println("  3. 方法区溢出 - 加载的类太多");
        println("     解决：增大MetaspaceSize");
        println();
        
        println("问题3：垃圾回收算法有哪些？");
        println("答：");
        println("  1. 标记-清除 - 标记垃圾，清除");
        println("  2. 标记-整理 - 标记垃圾，清除后整理内存");
        println("  3. 复制算法 - 把存活对象复制到另一块内存");
        println("  4. 分代收集 - 新生代用复制，老年代用标记-整理");
        println();
        
        println("问题4：项目中如何优化JVM性能？（结合实际）");
        println("答（结合项目）：");
        println("  \"在电商系统中，我们遇到高峰期内存溢出：");
        println("   1. 分析：用jmap导出堆快照，发现大量订单对象未释放");
        println("   2. 优化：增大堆内存从1G到4G（-Xmx4g）");
        println("   3. 调整：新生代比例调整为1:2，减少Full GC");
        println("   4. 监控：接入Prometheus监控JVM指标");
        println("   结果：Full GC从每小时10次降到2次，响应时间降低30%\"");
        
        line();
        
        
        // ==================== 总结 ====================
        title("【总结】");
        
        subtitle("🎯 核心要点");
        println("1. 本质：JVM = 虚拟机 + 自动内存管理");
        println("2. 必会：Runtime API查看内存信息");
        println("3. 场景：后端服务监控、性能调优");
        println("4. 坑点：内存泄漏、频繁GC");
        println("5. 面试：内存结构、垃圾回收算法");
        println();
        
        subtitle("📈 学习路径");
        println("第1天：理解JVM内存结构（堆、栈、方法区）");
        println("第2天：掌握Runtime API使用");
        println("第3天：了解垃圾回收机制");
        println("第4-5天：完成内存监控API实战");
        println("第6-7天：学习JVM调优参数");
        println();
        
        subtitle("💡 快速上手技巧");
        println("✅ 先记住内存结构（堆、栈、方法区）");
        println("✅ 用Runtime API写一个内存监控接口");
        println("✅ 理解堆溢出和栈溢出的区别");
        println("✅ 学会看GC日志（知道什么时候GC了）");
        println();
        
        subtitle("🚀 下一步");
        println("- 学习类加载机制（双亲委派模型）");
        println("- 深入垃圾回收器（G1、ZGC）");
        println("- 实践JVM调优（线上项目）");
        println("- 学习JVM监控工具（VisualVM、Arthas）");
        
        line();
        
        success("JVM学习【可记忆版】完成！");
        println();
        println("记住：JVM = 后端的「自动管家」，帮你管理内存");
        println("实战重点：会用Runtime API + 理解内存结构 + 能排查OOM");
    }
    
    /**
     * 演示：查看JVM内存信息
     */
    private static void demonstrateMemoryInfo() {
        Runtime rt = Runtime.getRuntime();
        
        println("代码示例：");
        println("  Runtime rt = Runtime.getRuntime();");
        println("  long total = rt.totalMemory() / 1024 / 1024;  // MB");
        println("  long free = rt.freeMemory() / 1024 / 1024;");
        println("  long used = total - free;");
        println();
        
        long totalMemory = rt.totalMemory() / 1024 / 1024;
        long freeMemory = rt.freeMemory() / 1024 / 1024;
        long usedMemory = totalMemory - freeMemory;
        long maxMemory = rt.maxMemory() / 1024 / 1024;
        
        println("实际执行结果：");
        println("  总内存: " + totalMemory + " MB");
        println("  空闲内存: " + freeMemory + " MB");
        println("  已使用: " + usedMemory + " MB");
        println("  最大可用: " + maxMemory + " MB");
        println("  使用率: " + String.format("%.2f%%", (double)usedMemory/totalMemory*100));
    }
    
    /**
     * 演示：触发垃圾回收
     */
    private static void demonstrateGC() {
        Runtime rt = Runtime.getRuntime();
        
        println("代码示例：");
        println("  System.gc();  // 建议JVM进行垃圾回收");
        println();
        
        println("GC前内存：" + (rt.totalMemory() - rt.freeMemory()) / 1024 / 1024 + " MB");
        
        // 创建一些临时对象
        for (int i = 0; i < 100000; i++) {
            String temp = new String("临时对象" + i);
        }
        
        println("创建对象后：" + (rt.totalMemory() - rt.freeMemory()) / 1024 / 1024 + " MB");
        
        // 建议垃圾回收
        System.gc();
        
        try {
            Thread.sleep(100); // 等待GC
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        println("GC后内存：" + (rt.totalMemory() - rt.freeMemory()) / 1024 / 1024 + " MB");
        println();
        println("💡 注意：System.gc()只是建议，JVM不一定立即执行");
    }
    
    /**
     * 演示：堆和栈的区别
     */
    private static void demonstrateHeapVsStack() {
        println("代码示例：");
        println("  int localVar = 100;           // 栈：局部变量");
        println("  String obj = new String(\"Hello\"); // 堆：对象实例");
        println();
        
        int localVar = 100;           // 存储在栈
        String obj = new String("Hello"); // 对象存储在堆，引用存储在栈
        
        println("实际执行：");
        println("  localVar（栈）: " + localVar);
        println("  obj（堆中的对象）: " + obj);
        println("  obj的哈希码: " + System.identityHashCode(obj));
        println();
        
        println("区别总结：");
        println("  栈（Stack）：");
        println("    - 存储局部变量、方法调用");
        println("    - 生命周期短，方法结束就回收");
        println("    - 速度快");
        println();
        println("  堆（Heap）：");
        println("    - 存储对象实例");
        println("    - 生命周期长，由GC回收");
        println("    - 空间大");
    }
}
