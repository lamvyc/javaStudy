package javaBasic.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import static javaBasic.utils.PrintUtil.*;

/**
 * IO流学习案例
 * 包含：File、InputStream、OutputStream、Reader、Writer
 */
public class IODemo {
    
    // 测试目录和文件路径
    private static final String TEST_DIR = "test_io";
    private static final String TEXT_FILE = TEST_DIR + "/test.txt";
    private static final String BINARY_FILE = TEST_DIR + "/test.dat";
    private static final String COPY_FILE = TEST_DIR + "/test_copy.txt";
    
    public static void main(String[] args) {
        
        title("IO流学习案例");
        
        try {
            // 1. File类操作
            demonstrateFile();
            
            // 2. 字节流 - OutputStream
            demonstrateOutputStream();
            
            // 3. 字节流 - InputStream
            demonstrateInputStream();
            
            // 4. 字符流 - Writer
            demonstrateWriter();
            
            // 5. 字符流 - Reader
            demonstrateReader();
            
            // 6. 缓冲流
            demonstrateBufferedStream();
            
            // 7. 数据流
            demonstrateDataStream();
            
            // 8. 对象流（序列化）
            demonstrateObjectStream();
            
            // 9. 文件复制
            demonstrateFileCopy();
            
            // 10. 综合应用
            demonstratePractical();
            
            line();
            success("IO流学习完成！");
            
            info("\n核心要点总结：");
            println("1. File类：文件和目录的操作（创建、删除、查询）");
            println("2. 字节流：处理二进制数据（图片、视频等）");
            println("   - InputStream：输入字节流");
            println("   - OutputStream：输出字节流");
            println("3. 字符流：处理文本数据（自动处理编码）");
            println("   - Reader：输入字符流");
            println("   - Writer：输出字符流");
            println("4. 缓冲流：提高IO效率");
            println("5. 数据流：读写基本数据类型");
            println("6. 对象流：对象序列化和反序列化");
            println("7. 使用try-with-resources自动关闭资源");
            
        } catch (Exception e) {
            error("发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 1. File类操作
     */
    private static void demonstrateFile() throws IOException {
        title("1. File类 - 文件和目录操作");
        
        subtitle("1.1 创建目录");
        File dir = new File(TEST_DIR);
        if (!dir.exists()) {
            boolean created = dir.mkdir();
            println("创建目录: " + dir.getAbsolutePath());
            println("创建结果: " + (created ? "成功" : "失败"));
        } else {
            println("目录已存在: " + dir.getAbsolutePath());
        }
        
        subtitle("1.2 创建文件");
        File file = new File(TEXT_FILE);
        if (!file.exists()) {
            boolean created = file.createNewFile();
            println("创建文件: " + file.getAbsolutePath());
            println("创建结果: " + (created ? "成功" : "失败"));
        } else {
            println("文件已存在: " + file.getAbsolutePath());
        }
        
        subtitle("1.3 文件信息");
        println("文件名: " + file.getName());
        println("绝对路径: " + file.getAbsolutePath());
        println("父目录: " + file.getParent());
        println("是否存在: " + file.exists());
        println("是否是文件: " + file.isFile());
        println("是否是目录: " + file.isDirectory());
        println("是否可读: " + file.canRead());
        println("是否可写: " + file.canWrite());
        println("文件大小: " + file.length() + " 字节");
        
        subtitle("1.4 列出目录内容");
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            println("目录内容:");
            for (File f : files) {
                println("  " + (f.isDirectory() ? "[DIR] " : "[FILE] ") + f.getName());
            }
        } else {
            println("目录为空");
        }
        
        subtitle("1.5 文件路径分隔符");
        println("路径分隔符: " + File.separator);  // Windows: \  Unix/Mac: /
        println("文件分隔符: " + File.pathSeparator);  // Windows: ;  Unix/Mac: :
        
        println();
    }
    
    /**
     * 2. OutputStream - 字节输出流
     */
    private static void demonstrateOutputStream() throws IOException {
        title("2. OutputStream - 字节输出流");
        
        subtitle("2.1 FileOutputStream - 写入字节");
        // try-with-resources 自动关闭资源
        try (FileOutputStream fos = new FileOutputStream(BINARY_FILE)) {
            // 写入单个字节
            fos.write(65);  // 'A'
            
            // 写入字节数组
            byte[] data = "Hello World".getBytes();
            fos.write(data);
            
            // 写入字节数组的一部分
            byte[] data2 = " Java IO".getBytes();
            fos.write(data2, 0, data2.length);
            
            success("字节写入成功: " + BINARY_FILE);
        }
        
        subtitle("2.2 追加模式写入");
        try (FileOutputStream fos = new FileOutputStream(BINARY_FILE, true)) {
            fos.write("\n追加的内容".getBytes());
            success("追加写入成功");
        }
        
        println();
    }
    
    /**
     * 3. InputStream - 字节输入流
     */
    private static void demonstrateInputStream() throws IOException {
        title("3. InputStream - 字节输入流");
        
        subtitle("3.1 FileInputStream - 读取字节");
        try (FileInputStream fis = new FileInputStream(BINARY_FILE)) {
            println("文件内容（逐字节读取）:");
            int byteData;
            StringBuilder content = new StringBuilder();
            while ((byteData = fis.read()) != -1) {
                content.append((char) byteData);
            }
            println(content.toString());
        }
        
        subtitle("3.2 一次读取多个字节");
        try (FileInputStream fis = new FileInputStream(BINARY_FILE)) {
            byte[] buffer = new byte[1024];
            int bytesRead = fis.read(buffer);
            println("读取字节数: " + bytesRead);
            println("内容: " + new String(buffer, 0, bytesRead));
        }
        
        subtitle("3.3 available() 方法");
        try (FileInputStream fis = new FileInputStream(BINARY_FILE)) {
            println("可读取字节数: " + fis.available());
        }
        
        println();
    }
    
    /**
     * 4. Writer - 字符输出流
     */
    private static void demonstrateWriter() throws IOException {
        title("4. Writer - 字符输出流");
        
        subtitle("4.1 FileWriter - 写入字符");
        try (FileWriter writer = new FileWriter(TEXT_FILE)) {
            // 写入字符串
            writer.write("Hello, 这是中文测试！\n");
            
            // 写入字符数组
            char[] chars = {'J', 'a', 'v', 'a', '\n'};
            writer.write(chars);
            
            // 写入部分字符数组
            writer.write(chars, 0, 4);
            writer.write('\n');
            
            // flush确保数据写入
            writer.flush();
            
            success("字符写入成功: " + TEXT_FILE);
        }
        
        subtitle("4.2 OutputStreamWriter - 指定编码");
        try (OutputStreamWriter osw = new OutputStreamWriter(
                new FileOutputStream(TEXT_FILE, true), StandardCharsets.UTF_8)) {
            osw.write("UTF-8编码的中文：你好世界\n");
            success("指定编码写入成功");
        }
        
        println();
    }
    
    /**
     * 5. Reader - 字符输入流
     */
    private static void demonstrateReader() throws IOException {
        title("5. Reader - 字符输入流");
        
        subtitle("5.1 FileReader - 读取字符");
        try (FileReader reader = new FileReader(TEXT_FILE)) {
            println("文件内容（逐字符读取）:");
            int charData;
            while ((charData = reader.read()) != -1) {
                print((char) charData);
            }
            println();
        }
        
        subtitle("5.2 一次读取多个字符");
        try (FileReader reader = new FileReader(TEXT_FILE)) {
            char[] buffer = new char[1024];
            int charsRead = reader.read(buffer);
            println("读取字符数: " + charsRead);
            println("内容: " + new String(buffer, 0, charsRead));
        }
        
        println();
    }
    
    /**
     * 6. 缓冲流 - 提高IO效率
     */
    private static void demonstrateBufferedStream() throws IOException {
        title("6. 缓冲流 - 提高IO效率");
        
        subtitle("6.1 BufferedWriter - 缓冲字符输出流");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TEXT_FILE))) {
            bw.write("第一行内容");
            bw.newLine();  // 写入换行符（跨平台）
            bw.write("第二行内容");
            bw.newLine();
            bw.write("第三行内容");
            success("BufferedWriter写入成功");
        }
        
        subtitle("6.2 BufferedReader - 缓冲字符输入流");
        try (BufferedReader br = new BufferedReader(new FileReader(TEXT_FILE))) {
            println("按行读取文件内容:");
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                println("第" + lineNumber + "行: " + line);
                lineNumber++;
            }
        }
        
        subtitle("6.3 BufferedInputStream/BufferedOutputStream");
        info("用法类似，只是处理字节流，可以提高读写效率");
        
        println();
    }
    
    /**
     * 7. 数据流 - 读写基本数据类型
     */
    private static void demonstrateDataStream() throws IOException {
        title("7. 数据流 - 读写基本数据类型");
        
        String dataFile = TEST_DIR + "/data.dat";
        
        subtitle("7.1 DataOutputStream - 写入基本数据类型");
        try (DataOutputStream dos = new DataOutputStream(
                new FileOutputStream(dataFile))) {
            // 写入不同类型的数据
            dos.writeInt(100);
            dos.writeDouble(3.14);
            dos.writeBoolean(true);
            dos.writeUTF("Hello");  // 写入UTF-8字符串
            
            success("数据写入成功");
            println("写入内容: int=100, double=3.14, boolean=true, String=Hello");
        }
        
        subtitle("7.2 DataInputStream - 读取基本数据类型");
        try (DataInputStream dis = new DataInputStream(
                new FileInputStream(dataFile))) {
            // 按写入顺序读取
            int intValue = dis.readInt();
            double doubleValue = dis.readDouble();
            boolean boolValue = dis.readBoolean();
            String strValue = dis.readUTF();
            
            println("读取内容:");
            println("  int: " + intValue);
            println("  double: " + doubleValue);
            println("  boolean: " + boolValue);
            println("  String: " + strValue);
        }
        
        println();
    }
    
    /**
     * 8. 对象流 - 序列化和反序列化
     */
    private static void demonstrateObjectStream() throws IOException, ClassNotFoundException {
        title("8. 对象流 - 序列化和反序列化");
        
        String objFile = TEST_DIR + "/person.obj";
        
        subtitle("8.1 ObjectOutputStream - 序列化对象");
        Person person = new Person("张三", 25);
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(objFile))) {
            oos.writeObject(person);
            success("对象序列化成功");
            println("序列化对象: " + person);
        }
        
        subtitle("8.2 ObjectInputStream - 反序列化对象");
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(objFile))) {
            Person readPerson = (Person) ois.readObject();
            success("对象反序列化成功");
            println("反序列化对象: " + readPerson);
        }
        
        subtitle("8.3 序列化说明");
        info("要点：");
        println("- 类必须实现Serializable接口");
        println("- serialVersionUID用于版本控制");
        println("- transient关键字标记不需要序列化的字段");
        
        println();
    }
    
    /**
     * 9. 文件复制
     */
    private static void demonstrateFileCopy() throws IOException {
        title("9. 文件复制");
        
        // 先创建一个测试文件
        String sourceFile = TEST_DIR + "/source.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(sourceFile))) {
            for (int i = 1; i <= 100; i++) {
                bw.write("这是第 " + i + " 行内容");
                bw.newLine();
            }
        }
        
        subtitle("9.1 使用字节流复制（适合任何文件）");
        long startTime = System.currentTimeMillis();
        
        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(COPY_FILE)) {
            
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
        
        long endTime = System.currentTimeMillis();
        success("文件复制成功: " + COPY_FILE);
        println("耗时: " + (endTime - startTime) + " 毫秒");
        
        subtitle("9.2 使用缓冲流复制（效率更高）");
        String copyFile2 = TEST_DIR + "/copy2.txt";
        startTime = System.currentTimeMillis();
        
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(copyFile2))) {
            
            byte[] buffer = new byte[8192];  // 8KB缓冲区
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        }
        
        endTime = System.currentTimeMillis();
        success("缓冲流复制成功: " + copyFile2);
        println("耗时: " + (endTime - startTime) + " 毫秒");
        
        println();
    }
    
    /**
     * 10. 综合应用
     */
    private static void demonstratePractical() throws IOException {
        title("10. 综合应用示例");
        
        subtitle("10.1 统计文本文件信息");
        File file = new File(TEXT_FILE);
        int lines = 0;
        int words = 0;
        int chars = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines++;
                chars += line.length();
                // 简单的单词统计（按空格分隔）
                String[] wordArray = line.trim().split("\\s+");
                if (!line.trim().isEmpty()) {
                    words += wordArray.length;
                }
            }
        }
        
        println("文件统计:");
        println("  行数: " + lines);
        println("  单词数: " + words);
        println("  字符数: " + chars);
        
        subtitle("10.2 读取配置文件");
        String configFile = TEST_DIR + "/config.properties";
        // 创建配置文件
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(configFile))) {
            bw.write("# 应用配置文件");
            bw.newLine();
            bw.write("app.name=JavaApp");
            bw.newLine();
            bw.write("app.version=1.0");
            bw.newLine();
            bw.write("app.port=8080");
            bw.newLine();
        }
        
        // 读取配置
        println("\n配置文件内容:");
        try (BufferedReader br = new BufferedReader(new FileReader(configFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith("#") && !line.trim().isEmpty()) {
                    println("  " + line);
                }
            }
        }
        
        subtitle("10.3 文件追加日志");
        String logFile = TEST_DIR + "/app.log";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(logFile, true))) {
            String timestamp = new java.util.Date().toString();
            bw.write("[" + timestamp + "] 应用启动");
            bw.newLine();
            bw.write("[" + timestamp + "] 执行IO操作");
            bw.newLine();
            success("日志写入成功: " + logFile);
        }
        
        subtitle("10.4 IO流选择建议");
        info("选择合适的流：");
        println("✓ 文本文件 → 使用字符流（Reader/Writer）");
        println("✓ 二进制文件（图片、视频等） → 使用字节流（InputStream/OutputStream）");
        println("✓ 需要高效率 → 使用缓冲流（Buffered...）");
        println("✓ 读写基本类型 → 使用数据流（DataInputStream/DataOutputStream）");
        println("✓ 读写对象 → 使用对象流（ObjectInputStream/ObjectOutputStream）");
        println("✓ 按行处理文本 → 使用BufferedReader.readLine()");
        
        println();
    }
}


/**
 * 用于演示序列化的Person类
 */
class Person implements Serializable {
    // 序列化版本号（建议显式定义）
    private static final long serialVersionUID = 1L;
    
    private String name;
    private int age;
    
    // transient关键字：不序列化该字段
    private transient String password = "secret123";
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", password='" + password + "'}";
    }
}
