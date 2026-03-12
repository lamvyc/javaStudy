package javaAdvanced.annotation;

import java.lang.annotation.*;
import java.lang.reflect.*;
import static javaBasic.utils.PrintUtil.*;

/**
 * 注解学习案例
 * 包含：自定义注解、元注解、运行时注解
 */
public class AnnotationDemo {
    
    public static void main(String[] args) {
        
        title("注解学习案例");
        
        try {
            // 1. 注解基础
            demonstrateAnnotationBasics();
            
            // 2. 元注解
            demonstrateMetaAnnotations();
            
            // 3. 自定义注解
            demonstrateCustomAnnotations();
            
            // 4. 运行时注解处理
            demonstrateRuntimeAnnotations();
            
            // 5. 注解应用场景
            demonstrateAnnotationUseCases();
            
            // 6. 实际应用：权限检查
            demonstratePermissionCheck();
            
            // 7. 实际应用：自动化测试
            demonstrateTestAnnotation();
            
            // 8. 实际应用：JSON序列化
            demonstrateJsonSerialization();
            
            line();
            success("注解学习完成！");
            
            info("\n核心要点总结：");
            println("1. 注解是元数据，为代码提供额外信息");
            println("2. 元注解：@Target、@Retention、@Documented、@Inherited");
            println("3. @Retention：SOURCE（编译期）、CLASS（字节码）、RUNTIME（运行时）");
            println("4. @Target：指定注解可以用在哪些位置");
            println("5. 运行时注解：使用反射获取注解信息");
            println("6. 常见应用：框架配置、权限控制、自动化测试");
            
        } catch (Exception e) {
            error("发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 1. 注解基础
     */
    private static void demonstrateAnnotationBasics() {
        title("1. 注解基础");
        
        subtitle("1.1 什么是注解");
        info("注解（Annotation）是元数据，为代码提供额外信息");
        println("- 不影响程序的执行");
        println("- 可以在编译期或运行时处理");
        println("- 格式：@注解名(参数)");
        
        subtitle("1.2 Java内置注解");
        println("@Override - 标记方法重写");
        println("@Deprecated - 标记过时的元素");
        println("@SuppressWarnings - 抑制编译警告");
        println("@FunctionalInterface - 标记函数式接口");
        println("@SafeVarargs - 抑制可变参数警告");
        
        subtitle("1.3 注解的组成");
        info("注解由以下部分组成：");
        println("1. @interface - 声明注解");
        println("2. 元注解 - 注解的注解");
        println("3. 注解元素 - 注解的参数");
        
        println();
    }
    
    /**
     * 2. 元注解
     */
    private static void demonstrateMetaAnnotations() {
        title("2. 元注解（注解的注解）");
        
        subtitle("2.1 @Target - 指定注解的使用位置");
        info("ElementType枚举值：");
        println("  TYPE - 类、接口、枚举");
        println("  FIELD - 字段");
        println("  METHOD - 方法");
        println("  PARAMETER - 参数");
        println("  CONSTRUCTOR - 构造方法");
        println("  LOCAL_VARIABLE - 局部变量");
        println("  ANNOTATION_TYPE - 注解类型");
        println("  PACKAGE - 包");
        
        subtitle("2.2 @Retention - 指定注解的保留策略");
        info("RetentionPolicy枚举值：");
        println("  SOURCE - 源码期，编译后丢弃（如@Override）");
        println("  CLASS - 字节码期，运行时不可见（默认）");
        println("  RUNTIME - 运行时，可通过反射读取");
        
        subtitle("2.3 @Documented - 包含在JavaDoc中");
        println("标记注解会出现在JavaDoc文档中");
        
        subtitle("2.4 @Inherited - 注解可被继承");
        println("子类会继承父类的注解");
        
        subtitle("2.5 @Repeatable - 可重复注解（JDK 8+）");
        println("允许在同一位置多次使用同一注解");
        
        println();
    }
    
    /**
     * 3. 自定义注解
     */
    private static void demonstrateCustomAnnotations() {
        title("3. 自定义注解");
        
        subtitle("3.1 无参数注解");
        info("@Marker - 标记型注解，不需要参数");
        
        subtitle("3.2 单值注解");
        info("@Author(\"张三\") - 只有一个value参数");
        
        subtitle("3.3 多值注解");
        info("@Info(name=\"测试\", version=\"1.0\") - 多个参数");
        
        subtitle("3.4 注解元素类型");
        info("注解参数支持的类型：");
        println("  - 基本类型（int、long、double等）");
        println("  - String");
        println("  - Class");
        println("  - 枚举");
        println("  - 注解");
        println("  - 以上类型的数组");
        
        subtitle("3.5 默认值");
        info("注解参数可以有默认值");
        println("String value() default \"default\";");
        
        println();
    }
    
    /**
     * 4. 运行时注解处理
     */
    private static void demonstrateRuntimeAnnotations() throws Exception {
        title("4. 运行时注解处理");
        
        subtitle("4.1 获取类上的注解");
        Class<?> clazz = AnnotatedClass.class;
        
        if (clazz.isAnnotationPresent(Author.class)) {
            Author author = clazz.getAnnotation(Author.class);
            println("作者: " + author.name());
            println("日期: " + author.date());
            println("版本: " + author.version());
        }
        
        if (clazz.isAnnotationPresent(Description.class)) {
            Description desc = clazz.getAnnotation(Description.class);
            println("描述: " + desc.value());
        }
        
        subtitle("4.2 获取方法上的注解");
        Method method = clazz.getMethod("annotatedMethod");
        
        if (method.isAnnotationPresent(Performance.class)) {
            Performance perf = method.getAnnotation(Performance.class);
            println("性能级别: " + perf.level());
            println("超时时间: " + perf.timeout() + "ms");
        }
        
        subtitle("4.3 获取字段上的注解");
        Field field = clazz.getDeclaredField("id");
        
        if (field.isAnnotationPresent(Column.class)) {
            Column column = field.getAnnotation(Column.class);
            println("列名: " + column.name());
            println("类型: " + column.type());
            println("是否为空: " + !column.nullable());
        }
        
        subtitle("4.4 处理所有注解");
        println("\n类的所有注解:");
        for (Annotation ann : clazz.getAnnotations()) {
            println("  - " + ann.annotationType().getSimpleName());
        }
        
        println();
    }
    
    /**
     * 5. 注解应用场景
     */
    private static void demonstrateAnnotationUseCases() {
        title("5. 注解应用场景");
        
        info("1. 框架配置");
        println("   @Controller、@Service、@Repository（Spring）");
        println("   @Entity、@Table、@Column（JPA）");
        
        info("\n2. 编译时检查");
        println("   @Override - 检查方法重写");
        println("   @SuppressWarnings - 抑制警告");
        
        info("\n3. 代码生成");
        println("   Lombok的@Data、@Getter、@Setter");
        
        info("\n4. 运行时处理");
        println("   @Test（JUnit测试）");
        println("   @RequestMapping（Spring MVC路由）");
        
        info("\n5. 文档生成");
        println("   JavaDoc相关注解");
        
        info("\n6. 权限控制");
        println("   自定义@RequirePermission");
        
        println();
    }
    
    /**
     * 6. 实际应用：权限检查
     */
    private static void demonstratePermissionCheck() throws Exception {
        title("6. 实际应用：权限检查");
        
        UserService service = new UserService();
        
        subtitle("6.1 检查方法权限");
        Method viewMethod = service.getClass().getMethod("viewData");
        checkPermission(viewMethod, "USER");
        
        Method deleteMethod = service.getClass().getMethod("deleteData");
        checkPermission(deleteMethod, "USER");
        
        println();
    }
    
    /**
     * 7. 实际应用：自动化测试
     */
    private static void demonstrateTestAnnotation() {
        title("7. 实际应用：自动化测试");
        
        subtitle("7.1 运行测试方法");
        TestRunner.run(MyTest.class);
        
        println();
    }
    
    /**
     * 8. 实际应用：JSON序列化
     */
    private static void demonstrateJsonSerialization() throws Exception {
        title("8. 实际应用：JSON序列化");
        
        subtitle("8.1 根据注解生成JSON");
        User user = new User();
        user.id = 1;
        user.username = "zhangsan";
        user.email = "zhangsan@example.com";
        user.password = "secret123";
        
        String json = JsonSerializer.toJson(user);
        println("生成的JSON:");
        println(json);
        
        println();
    }
    
    // ========== 辅助方法 ==========
    
    /**
     * 检查方法权限
     */
    private static void checkPermission(Method method, String currentRole) {
        if (method.isAnnotationPresent(RequirePermission.class)) {
            RequirePermission perm = method.getAnnotation(RequirePermission.class);
            String required = perm.value();
            
            print("方法 " + method.getName() + " 需要权限: " + required);
            
            if (currentRole.equals(required)) {
                success(" - 权限验证通过");
            } else {
                error(" - 权限不足！");
            }
        }
    }
}


// ========== 自定义注解定义 ==========

/**
 * 标记型注解：无参数
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Marker {
}

/**
 * 作者注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@interface Author {
    String name();
    String date() default "";
    String version() default "1.0";
}

/**
 * 描述注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@interface Description {
    String value();
}

/**
 * 性能注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Performance {
    enum Level {LOW, MEDIUM, HIGH}
    Level level() default Level.MEDIUM;
    int timeout() default 1000;
}

/**
 * 列注解（数据库）
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String name();
    String type() default "VARCHAR(255)";
    boolean nullable() default true;
}

/**
 * 权限注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RequirePermission {
    String value();  // 权限名称
}

/**
 * 测试注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Test {
    String name() default "";
}

/**
 * 前置条件注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Before {
}

/**
 * 后置条件注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface After {
}

/**
 * JSON字段注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface JsonField {
    String name() default "";  // JSON中的字段名
    boolean ignore() default false;  // 是否忽略
}


// ========== 使用注解的类 ==========

/**
 * 带注解的类
 */
@Author(name = "张三", date = "2026-03-12", version = "2.0")
@Description("这是一个演示注解的类")
class AnnotatedClass {
    
    @Column(name = "id", type = "INT", nullable = false)
    private int id;
    
    @Column(name = "name", type = "VARCHAR(50)")
    private String name;
    
    @Performance(level = Performance.Level.HIGH, timeout = 500)
    @Description("这是一个带注解的方法")
    public void annotatedMethod() {
        System.out.println("执行方法");
    }
}

/**
 * 用户服务类
 */
class UserService {
    
    @RequirePermission("USER")
    public void viewData() {
        System.out.println("查看数据");
    }
    
    @RequirePermission("ADMIN")
    public void deleteData() {
        System.out.println("删除数据");
    }
}

/**
 * 测试类
 */
class MyTest {
    
    @Before
    public void setUp() {
        System.out.println("  [Before] 初始化测试环境");
    }
    
    @Test(name = "测试加法")
    public void testAdd() {
        System.out.println("  [Test] 执行测试：1 + 1 = 2");
        int result = 1 + 1;
        if (result == 2) {
            System.out.println("  ✓ 测试通过");
        } else {
            System.out.println("  ✗ 测试失败");
        }
    }
    
    @Test(name = "测试减法")
    public void testSubtract() {
        System.out.println("  [Test] 执行测试：5 - 3 = 2");
        int result = 5 - 3;
        if (result == 2) {
            System.out.println("  ✓ 测试通过");
        } else {
            System.out.println("  ✗ 测试失败");
        }
    }
    
    @After
    public void tearDown() {
        System.out.println("  [After] 清理测试环境");
    }
    
    public void normalMethod() {
        System.out.println("这不是测试方法");
    }
}

/**
 * 用户类（JSON序列化示例）
 */
class User {
    @JsonField(name = "id")
    public int id;
    
    @JsonField(name = "username")
    public String username;
    
    @JsonField(name = "email")
    public String email;
    
    @JsonField(ignore = true)  // 忽略密码字段
    public String password;
}


// ========== 注解处理器 ==========

/**
 * 测试运行器
 */
class TestRunner {
    public static void run(Class<?> testClass) {
        System.out.println("运行测试类: " + testClass.getSimpleName());
        System.out.println();
        
        try {
            Object instance = testClass.newInstance();
            Method[] methods = testClass.getDeclaredMethods();
            
            // 运行@Before方法
            for (Method method : methods) {
                if (method.isAnnotationPresent(Before.class)) {
                    method.invoke(instance);
                }
            }
            
            // 运行@Test方法
            int testCount = 0;
            for (Method method : methods) {
                if (method.isAnnotationPresent(Test.class)) {
                    Test test = method.getAnnotation(Test.class);
                    String testName = test.name().isEmpty() ? method.getName() : test.name();
                    System.out.println("\n测试: " + testName);
                    method.invoke(instance);
                    testCount++;
                }
            }
            
            // 运行@After方法
            for (Method method : methods) {
                if (method.isAnnotationPresent(After.class)) {
                    method.invoke(instance);
                }
            }
            
            System.out.println("\n共运行 " + testCount + " 个测试");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * JSON序列化器
 */
class JsonSerializer {
    public static String toJson(Object obj) throws IllegalAccessException {
        StringBuilder json = new StringBuilder("{");
        
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        
        boolean first = true;
        for (Field field : fields) {
            if (field.isAnnotationPresent(JsonField.class)) {
                JsonField jsonField = field.getAnnotation(JsonField.class);
                
                // 如果标记为ignore，跳过
                if (jsonField.ignore()) {
                    continue;
                }
                
                if (!first) {
                    json.append(", ");
                }
                first = false;
                
                // 获取字段名
                String fieldName = jsonField.name().isEmpty() ? 
                    field.getName() : jsonField.name();
                
                // 获取字段值
                field.setAccessible(true);
                Object value = field.get(obj);
                
                // 构建JSON
                json.append("\"").append(fieldName).append("\": ");
                if (value instanceof String) {
                    json.append("\"").append(value).append("\"");
                } else {
                    json.append(value);
                }
            }
        }
        
        json.append("}");
        return json.toString();
    }
}
