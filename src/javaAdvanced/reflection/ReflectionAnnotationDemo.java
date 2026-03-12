package javaAdvanced.reflection;

import java.lang.annotation.*;
import java.lang.reflect.*;
import static javaBasic.utils.PrintUtil.*;

/**
 * 反射与注解学习案例
 * 包含：Class类、反射创建对象、注解解析
 */
public class ReflectionAnnotationDemo {
    
    public static void main(String[] args) {
        
        title("1. 获取Class对象的三种方式");
        
        try {
            // 方式1：通过类名.class
            Class<?> clazz1 = Person.class;
            println("方式1 - 类名.class: " + clazz1.getName());
            
            // 方式2：通过对象.getClass()
            Person person = new Person();
            Class<?> clazz2 = person.getClass();
            println("方式2 - 对象.getClass(): " + clazz2.getName());
            
            // 方式3：通过Class.forName()（最常用）
            Class<?> clazz3 = Class.forName("javaAdvanced.reflection.Person");
            println("方式3 - Class.forName(): " + clazz3.getName());
            
            println("\n三种方式获取的是同一个Class对象: " + (clazz1 == clazz2 && clazz2 == clazz3));
            
            
            title("2. 获取类的信息");
            
            Class<?> personClass = Person.class;
            
            subtitle("2.1 类的基本信息");
            println("完整类名: " + personClass.getName());
            println("简单类名: " + personClass.getSimpleName());
            println("包名: " + personClass.getPackage().getName());
            println("父类: " + personClass.getSuperclass().getSimpleName());
            
            subtitle("2.2 类的修饰符");
            int modifiers = personClass.getModifiers();
            println("是否是public: " + Modifier.isPublic(modifiers));
            println("是否是abstract: " + Modifier.isAbstract(modifiers));
            println("是否是final: " + Modifier.isFinal(modifiers));
            
            
            title("3. 获取类的字段（Field）");
            
            subtitle("3.1 获取所有public字段");
            Field[] publicFields = personClass.getFields();
            println("public字段数量: " + publicFields.length);
            for (Field field : publicFields) {
                println("  - " + field.getName());
            }
            
            subtitle("3.2 获取所有字段（包括private）");
            Field[] allFields = personClass.getDeclaredFields();
            println("所有字段数量: " + allFields.length);
            for (Field field : allFields) {
                println("  - " + Modifier.toString(field.getModifiers()) + " " + 
                        field.getType().getSimpleName() + " " + field.getName());
            }
            
            subtitle("3.3 访问和修改字段值");
            Person p1 = new Person("张三", 25);
            
            // 访问public字段
            Field idField = personClass.getField("id");
            println("访问public字段 id: " + idField.get(p1));
            
            // 访问private字段（需要设置可访问）
            Field nameField = personClass.getDeclaredField("name");
            nameField.setAccessible(true);  // 突破private限制
            println("访问private字段 name: " + nameField.get(p1));
            
            // 修改字段值
            nameField.set(p1, "李四");
            println("修改后的name: " + p1.getName());
            
            
            title("4. 获取类的方法（Method）");
            
            subtitle("4.1 获取所有public方法");
            Method[] publicMethods = personClass.getMethods();
            println("public方法数量: " + publicMethods.length + " (包括继承的)");
            
            subtitle("4.2 获取声明的所有方法");
            Method[] allMethods = personClass.getDeclaredMethods();
            println("声明的方法数量: " + allMethods.length);
            for (Method method : allMethods) {
                println("  - " + method.getName());
            }
            
            subtitle("4.3 调用方法");
            Person p2 = new Person("王五", 30);
            
            // 调用public方法
            Method sayHelloMethod = personClass.getMethod("sayHello");
            println("调用sayHello方法:");
            sayHelloMethod.invoke(p2);  // 相当于 p2.sayHello()
            
            // 调用带参数的方法
            Method introduceMethod = personClass.getMethod("introduce", String.class);
            println("\n调用introduce方法:");
            introduceMethod.invoke(p2, "大家好");
            
            // 调用private方法
            Method privateMethod = personClass.getDeclaredMethod("privateMethod");
            privateMethod.setAccessible(true);
            println("\n调用private方法:");
            privateMethod.invoke(p2);
            
            
            title("5. 获取类的构造函数（Constructor）");
            
            subtitle("5.1 获取所有构造函数");
            Constructor<?>[] constructors = personClass.getConstructors();
            println("构造函数数量: " + constructors.length);
            for (Constructor<?> constructor : constructors) {
                print("  - " + constructor.getName() + "(");
                Parameter[] params = constructor.getParameters();
                for (int i = 0; i < params.length; i++) {
                    print(params[i].getType().getSimpleName() + " " + params[i].getName());
                    if (i < params.length - 1) print(", ");
                }
                println(")");
            }
            
            subtitle("5.2 通过反射创建对象");
            
            // 使用无参构造
            Constructor<?> noArgConstructor = personClass.getConstructor();
            Person p3 = (Person) noArgConstructor.newInstance();
            println("通过无参构造创建: " + p3);
            
            // 使用有参构造
            Constructor<?> argConstructor = personClass.getConstructor(String.class, int.class);
            Person p4 = (Person) argConstructor.newInstance("赵六", 28);
            println("通过有参构造创建: " + p4);
            
            
            title("6. 注解基础");
            
            subtitle("6.1 什么是注解");
            info("注解是元数据，为代码提供额外信息");
            println("- 可以用于类、方法、字段、参数等");
            println("- 可以在编译时或运行时处理");
            println("- Spring、MyBatis等框架大量使用注解");
            
            subtitle("6.2 Java内置注解");
            println("@Override - 标记方法重写");
            println("@Deprecated - 标记过时的元素");
            println("@SuppressWarnings - 抑制编译警告");
            println("@FunctionalInterface - 标记函数式接口");
            
            
            title("7. 自定义注解");
            
            subtitle("7.1 定义注解");
            info("注解定义在本文件末尾");
            println("@Author - 标记作者信息");
            println("@Table - 标记数据库表名");
            println("@Column - 标记数据库字段");
            
            
            title("8. 解析注解");
            
            subtitle("8.1 类上的注解");
            Class<?> userClass = User.class;
            
            if (userClass.isAnnotationPresent(Table.class)) {
                Table table = userClass.getAnnotation(Table.class);
                println("表名: " + table.value());
            }
            
            if (userClass.isAnnotationPresent(Author.class)) {
                Author author = userClass.getAnnotation(Author.class);
                println("作者: " + author.name());
                println("日期: " + author.date());
            }
            
            subtitle("8.2 字段上的注解");
            Field[] userFields = userClass.getDeclaredFields();
            for (Field field : userFields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);
                    println("字段 " + field.getName() + " -> 列名: " + column.name() + 
                           ", 类型: " + column.type());
                }
            }
            
            subtitle("8.3 方法上的注解");
            Method getUserInfoMethod = userClass.getMethod("getUserInfo");
            if (getUserInfoMethod.isAnnotationPresent(Deprecated.class)) {
                warning("getUserInfo方法已过时");
            }
            
            
            title("9. 注解的实际应用 - 简易ORM");
            
            subtitle("9.1 根据注解生成SQL");
            String createTableSQL = generateCreateTableSQL(User.class);
            println("生成的建表SQL:");
            println(createTableSQL);
            
            subtitle("9.2 根据注解生成INSERT SQL");
            User user = new User();
            user.id = 1;
            user.username = "zhangsan";
            user.email = "zhangsan@example.com";
            
            String insertSQL = generateInsertSQL(user);
            println("\n生成的插入SQL:");
            println(insertSQL);
            
            
            title("10. 反射的应用场景");
            
            info("反射的常见应用：");
            println("1. 框架开发（Spring IoC容器）");
            println("2. 动态代理（AOP）");
            println("3. 注解处理（自动配置）");
            println("4. 序列化/反序列化（JSON、XML）");
            println("5. ORM框架（Hibernate、MyBatis）");
            println("6. 单元测试框架（JUnit）");
            
            warning("\n反射的缺点：");
            println("- 性能开销较大");
            println("- 破坏封装性");
            println("- 代码可读性差");
            println("建议：能不用反射就不用，必要时才使用");
            
            
            title("11. 综合示例 - 对象工厂");
            
            subtitle("11.1 使用反射创建不同对象");
            Object obj1 = createInstance("javaAdvanced.reflection.Person");
            Object obj2 = createInstance("javaAdvanced.reflection.User");
            
            println("创建的对象1: " + obj1.getClass().getSimpleName());
            println("创建的对象2: " + obj2.getClass().getSimpleName());
            
            subtitle("11.2 使用反射调用方法");
            invokeMethod(obj1, "sayHello");
            
            
            line();
            success("反射与注解学习完成！");
            
            info("核心要点总结：");
            println("1. Class对象是反射的入口");
            println("2. 反射可以获取类的字段、方法、构造函数");
            println("3. setAccessible(true)可以访问private成员");
            println("4. 注解是元数据，用@interface定义");
            println("5. 注解保留策略：SOURCE、CLASS、RUNTIME");
            println("6. 反射+注解是很多框架的核心技术");
            
        } catch (Exception e) {
            error("发生异常: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // 使用反射创建对象
    private static Object createInstance(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            return clazz.newInstance();
        } catch (Exception e) {
            error("创建对象失败: " + e.getMessage());
            return null;
        }
    }
    
    // 使用反射调用方法
    private static void invokeMethod(Object obj, String methodName) {
        try {
            Method method = obj.getClass().getMethod(methodName);
            method.invoke(obj);
        } catch (Exception e) {
            error("调用方法失败: " + e.getMessage());
        }
    }
    
    // 根据注解生成建表SQL
    private static String generateCreateTableSQL(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(Table.class)) {
            return "";
        }
        
        Table table = clazz.getAnnotation(Table.class);
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE ").append(table.value()).append(" (\n");
        
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                sql.append("  ").append(column.name())
                   .append(" ").append(column.type());
                
                if (i < fields.length - 1) {
                    sql.append(",");
                }
                sql.append("\n");
            }
        }
        
        sql.append(");");
        return sql.toString();
    }
    
    // 根据注解生成INSERT SQL
    private static String generateInsertSQL(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        if (!clazz.isAnnotationPresent(Table.class)) {
            return "";
        }
        
        Table table = clazz.getAnnotation(Table.class);
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(table.value()).append(" (");
        
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder values = new StringBuilder(" VALUES (");
        
        boolean first = true;
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                
                if (!first) {
                    sql.append(", ");
                    values.append(", ");
                }
                first = false;
                
                sql.append(column.name());
                
                field.setAccessible(true);
                Object value = field.get(obj);
                if (value instanceof String) {
                    values.append("'").append(value).append("'");
                } else {
                    values.append(value);
                }
            }
        }
        
        sql.append(")");
        values.append(");");
        
        return sql.toString() + values.toString();
    }
}


// ========== 示例类 ==========

class Person {
    // 字段
    public int id = 1;
    private String name;
    private int age;
    
    // 构造函数
    public Person() {
        this.name = "未命名";
        this.age = 0;
    }
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Getter和Setter
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    // 公共方法
    public void sayHello() {
        System.out.println("  你好，我是 " + name);
    }
    
    public void introduce(String greeting) {
        System.out.println("  " + greeting + "！我叫 " + name + "，今年 " + age + "岁");
    }
    
    // 私有方法
    private void privateMethod() {
        System.out.println("  这是私有方法，通过反射调用");
    }
    
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}


// ========== 自定义注解 ==========

/**
 * 作者注解
 * @Retention(RUNTIME) - 运行时保留，可以通过反射读取
 * @Target(TYPE) - 可以用在类上
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Author {
    String name();
    String date() default "";
}

/**
 * 表名注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Table {
    String value();  // 表名
}

/**
 * 字段注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String name();   // 列名
    String type() default "VARCHAR(255)";  // 列类型
}


// ========== 使用注解的类 ==========

@Table("users")
@Author(name = "张三", date = "2026-03-12")
class User {
    
    @Column(name = "id", type = "INT PRIMARY KEY")
    public int id;
    
    @Column(name = "username", type = "VARCHAR(50)")
    public String username;
    
    @Column(name = "email", type = "VARCHAR(100)")
    public String email;
    
    public User() {
    }
    
    @Deprecated
    public String getUserInfo() {
        return "User: " + username;
    }
    
    public void sayHello() {
        System.out.println("  Hello from User: " + username);
    }
}
