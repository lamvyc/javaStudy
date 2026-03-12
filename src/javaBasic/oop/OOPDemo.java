package javaBasic.oop;

import static javaBasic.utils.PrintUtil.*;

/**
 * 面向对象编程学习案例
 * 包含：类、构造函数、封装、继承、多态、接口
 */
public class OOPDemo {
    public static void main(String[] args) {
        
        title("1. 类和构造函数");
        Dog dog = new Dog("旺财", 3, "金毛");
        dog.showInfo();
        
        
        title("2. 封装（私有属性，公共方法访问）");
        println("狗的名字: " + dog.getName());
        dog.setAge(4);
        println("修改后的年龄: " + dog.getAge());
        
        
        title("3. 继承");
        Cat cat = new Cat("咪咪", 2, "白色");
        cat.showInfo();
        cat.climb();  // 子类特有方法
        
        
        title("4. 多态（方法重写）");
        subtitle("父类引用指向子类对象");
        // 父类引用指向子类对象
        Animal animal1 = new Dog("小黑", 1, "哈士奇");
        Animal animal2 = new Cat("小花", 1, "橘色");
        Animal animal3 = new Bird("小鸟", 1, 0.3);
        
        // 同样的方法调用，不同的行为（多态）
        animal1.makeSound();
        animal2.makeSound();
        animal3.makeSound();
        
        
        title("5. 接口");
        Bird bird = new Bird("老鹰", 5, 2.0);
        bird.fly();      // 实现的接口方法
        bird.land();     // 接口的默认方法
        
        
        title("6. 多态数组应用");
        Animal[] animals = {
            new Dog("大黄", 4, "土狗"),
            new Cat("黑猫", 3, "黑色"),
            new Bird("麻雀", 1, 0.15)
        };
        
        println("遍历动物数组:");
        for (Animal a : animals) {
            a.showInfo();
            a.makeSound();
            line('-', 40);
        }
        
        
        title("7. instanceof 类型检查");
        Animal myAnimal = new Dog("阿黄", 2, "柯基");
        if (myAnimal instanceof Dog) {
            Dog myDog = (Dog) myAnimal;  // 向下转型
            myDog.wagTail();  // 调用子类特有方法
        }
        
        line();
        success("面向对象学习完成！");
    }
}


// 1. 接口定义
interface Flyable {
    void fly();  // 抽象方法
    
    // Java 8 默认方法
    default void land() {
        println("降落中...");
    }
}


// 2. 父类（基类）
class Animal {
    // 私有属性（封装）
    private String name;
    private int age;
    
    // 构造函数
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // 无参构造函数
    public Animal() {
        this.name = "未命名";
        this.age = 0;
    }
    
    // Getter和Setter方法（封装）
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
        if (age >= 0) {  // 数据验证
            this.age = age;
        }
    }
    
    // 普通方法
    public void makeSound() {
        println(name + " 发出声音");
    }
    
    // 显示信息
    public void showInfo() {
        println("动物名称: " + name + ", 年龄: " + age);
    }
}


// 3. 子类（继承）
class Dog extends Animal {
    private String breed;  // 品种
    
    // 构造函数
    public Dog(String name, int age, String breed) {
        super(name, age);  // 调用父类构造函数
        this.breed = breed;
    }
    
    // 方法重写（多态）
    @Override
    public void makeSound() {
        println(getName() + " 汪汪叫");
    }
    
    // 子类特有方法
    public void wagTail() {
        println(getName() + " 摇尾巴");
    }
    
    @Override
    public void showInfo() {
        super.showInfo();
        println("品种: " + breed);
    }
}


// 4. 另一个子类
class Cat extends Animal {
    private String color;
    
    public Cat(String name, int age, String color) {
        super(name, age);
        this.color = color;
    }
    
    // 方法重写（多态）
    @Override
    public void makeSound() {
        println(getName() + " 喵喵叫");
    }
    
    public void climb() {
        println(getName() + " 爬树");
    }
    
    @Override
    public void showInfo() {
        super.showInfo();
        println("颜色: " + color);
    }
}


// 5. 实现接口的类
class Bird extends Animal implements Flyable {
    private double wingSpan;  // 翼展
    
    public Bird(String name, int age, double wingSpan) {
        super(name, age);
        this.wingSpan = wingSpan;
    }
    
    @Override
    public void makeSound() {
        println(getName() + " 唧唧喳喳");
    }
    
    // 实现接口方法
    @Override
    public void fly() {
        println(getName() + " 正在飞行，翼展: " + wingSpan + "米");
    }
    
    @Override
    public void showInfo() {
        super.showInfo();
        println("翼展: " + wingSpan + "米");
    }
}
