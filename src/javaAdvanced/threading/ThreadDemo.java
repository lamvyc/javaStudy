package javaAdvanced.threading;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import static javaBasic.utils.PrintUtil.*;

/**
 * 多线程学习案例
 * 包含：Thread、Runnable、线程池、synchronized、Lock
 */
public class ThreadDemo {
    
    // 共享资源（用于演示同步）
    private static int counter = 0;
    private static int syncCounter = 0;
    private static int lockCounter = 0;
    private static final Lock lock = new ReentrantLock();
    
    public static void main(String[] args) throws InterruptedException {
        
        title("1. 创建线程 - 继承Thread类");
        
        // 方式1：继承Thread类
        MyThread thread1 = new MyThread("线程-1");
        MyThread thread2 = new MyThread("线程-2");
        
        thread1.start();  // 启动线程
        thread2.start();
        
        thread1.join();  // 等待线程结束
        thread2.join();
        
        
        title("2. 创建线程 - 实现Runnable接口（推荐）");
        
        // 方式2：实现Runnable接口
        Thread thread3 = new Thread(new MyRunnable("任务-A"));
        Thread thread4 = new Thread(new MyRunnable("任务-B"));
        
        thread3.start();
        thread4.start();
        
        thread3.join();
        thread4.join();
        
        
        title("3. 使用Lambda创建线程（最简洁）");
        
        Thread thread5 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                println("Lambda线程 - 执行次数: " + i);
                sleep(300);
            }
        });
        
        thread5.start();
        thread5.join();
        
        
        title("4. 线程同步问题演示");
        
        subtitle("4.1 不加同步 - 会出现数据错误");
        counter = 0;
        Thread[] unsafeThreads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            unsafeThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter++;  // 非线程安全
                }
            });
            unsafeThreads[i].start();
        }
        
        // 等待所有线程结束
        for (Thread t : unsafeThreads) {
            t.join();
        }
        println("不加同步的结果: " + counter + " (期望: 10000)");
        if (counter != 10000) {
            warning("数据不一致！说明需要同步机制");
        }
        
        
        title("5. synchronized 关键字同步");
        
        syncCounter = 0;
        Thread[] syncThreads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            syncThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    incrementSyncCounter();  // 使用synchronized
                }
            });
            syncThreads[i].start();
        }
        
        for (Thread t : syncThreads) {
            t.join();
        }
        println("synchronized同步结果: " + syncCounter + " (期望: 10000)");
        if (syncCounter == 10000) {
            success("数据一致！synchronized保证了线程安全");
        }
        
        
        title("6. Lock 接口同步");
        
        lockCounter = 0;
        Thread[] lockThreads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            lockThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    incrementLockCounter();  // 使用Lock
                }
            });
            lockThreads[i].start();
        }
        
        for (Thread t : lockThreads) {
            t.join();
        }
        println("Lock同步结果: " + lockCounter + " (期望: 10000)");
        if (lockCounter == 10000) {
            success("数据一致！Lock保证了线程安全");
        }
        
        
        title("7. 线程池 - Executors");
        
        subtitle("7.1 固定大小线程池");
        ExecutorService fixedPool = Executors.newFixedThreadPool(3);
        
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            fixedPool.submit(() -> {
                println("任务 " + taskId + " 开始执行 - " + Thread.currentThread().getName());
                sleep(1000);
                println("任务 " + taskId + " 执行完成");
            });
        }
        
        fixedPool.shutdown();  // 关闭线程池
        fixedPool.awaitTermination(10, TimeUnit.SECONDS);  // 等待所有任务完成
        
        
        subtitle("7.2 单线程线程池");
        ExecutorService singlePool = Executors.newSingleThreadExecutor();
        
        for (int i = 1; i <= 3; i++) {
            final int taskId = i;
            singlePool.submit(() -> {
                println("单线程任务 " + taskId + " 执行");
            });
        }
        
        singlePool.shutdown();
        singlePool.awaitTermination(5, TimeUnit.SECONDS);
        
        
        subtitle("7.3 缓存线程池");
        ExecutorService cachedPool = Executors.newCachedThreadPool();
        
        for (int i = 1; i <= 3; i++) {
            final int taskId = i;
            cachedPool.submit(() -> {
                println("缓存线程池任务 " + taskId + " - " + Thread.currentThread().getName());
            });
        }
        
        cachedPool.shutdown();
        cachedPool.awaitTermination(5, TimeUnit.SECONDS);
        
        
        title("8. 线程池 - ThreadPoolExecutor（自定义配置）");
        
        ThreadPoolExecutor customPool = new ThreadPoolExecutor(
            2,                      // 核心线程数
            5,                      // 最大线程数
            60L,                    // 空闲线程存活时间
            TimeUnit.SECONDS,       // 时间单位
            new LinkedBlockingQueue<>(10)  // 任务队列
        );
        
        println("核心线程数: " + customPool.getCorePoolSize());
        println("最大线程数: " + customPool.getMaximumPoolSize());
        
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            customPool.submit(() -> {
                println("自定义线程池任务 " + taskId + " 执行");
                sleep(500);
            });
        }
        
        customPool.shutdown();
        customPool.awaitTermination(10, TimeUnit.SECONDS);
        
        
        title("9. Callable 和 Future（有返回值的任务）");
        
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        // Callable可以返回结果
        Callable<Integer> task1 = () -> {
            println("计算任务1开始");
            sleep(1000);
            return 100;
        };
        
        Callable<String> task2 = () -> {
            println("计算任务2开始");
            sleep(1500);
            return "Hello from Callable";
        };
        
        Future<Integer> future1 = executor.submit(task1);
        Future<String> future2 = executor.submit(task2);
        
        println("等待任务完成...");
        
        try {
            Integer result1 = future1.get();  // 阻塞等待结果
            String result2 = future2.get();
            
            println("任务1返回值: " + result1);
            println("任务2返回值: " + result2);
        } catch (ExecutionException e) {
            error("任务执行异常: " + e.getMessage());
        }
        
        executor.shutdown();
        
        
        title("10. CountDownLatch（倒计时门闩）");
        
        CountDownLatch latch = new CountDownLatch(3);  // 计数器设为3
        
        ExecutorService pool = Executors.newFixedThreadPool(3);
        
        for (int i = 1; i <= 3; i++) {
            final int workerId = i;
            pool.submit(() -> {
                println("工作线程 " + workerId + " 开始工作");
                sleep(1000 * workerId);
                println("工作线程 " + workerId + " 完成工作");
                latch.countDown();  // 计数器减1
            });
        }
        
        println("主线程等待所有工作线程完成...");
        latch.await();  // 等待计数器归零
        success("所有工作线程已完成！");
        
        pool.shutdown();
        
        
        title("11. CyclicBarrier（循环栅栏）");
        
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            println(">>> 所有线程都到达屏障，继续执行！");
        });
        
        ExecutorService barrierPool = Executors.newFixedThreadPool(3);
        
        for (int i = 1; i <= 3; i++) {
            final int threadId = i;
            barrierPool.submit(() -> {
                try {
                    println("线程 " + threadId + " 准备中...");
                    sleep(1000 * threadId);
                    println("线程 " + threadId + " 到达屏障");
                    barrier.await();  // 等待其他线程
                    println("线程 " + threadId + " 通过屏障，继续执行");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        
        barrierPool.shutdown();
        barrierPool.awaitTermination(10, TimeUnit.SECONDS);
        
        
        title("12. Semaphore（信号量）");
        
        Semaphore semaphore = new Semaphore(2);  // 只允许2个线程同时访问
        
        ExecutorService semPool = Executors.newFixedThreadPool(5);
        
        for (int i = 1; i <= 5; i++) {
            final int id = i;
            semPool.submit(() -> {
                try {
                    println("线程 " + id + " 尝试获取许可...");
                    semaphore.acquire();  // 获取许可
                    println(">>> 线程 " + id + " 获得许可，开始执行");
                    sleep(2000);
                    println("<<< 线程 " + id + " 释放许可");
                    semaphore.release();  // 释放许可
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        
        semPool.shutdown();
        semPool.awaitTermination(20, TimeUnit.SECONDS);
        
        
        title("13. 线程状态和生命周期");
        
        Thread stateThread = new Thread(() -> {
            println("线程内部：运行中 (RUNNABLE)");
            sleep(2000);
            println("线程内部：即将结束");
        });
        
        println("创建后状态: " + stateThread.getState());  // NEW
        
        stateThread.start();
        println("启动后状态: " + stateThread.getState());  // RUNNABLE
        
        sleep(100);
        println("运行中状态: " + stateThread.getState());  // RUNNABLE
        
        stateThread.join();
        println("结束后状态: " + stateThread.getState());  // TERMINATED
        
        
        line();
        success("多线程学习完成！");
        
        info("核心要点总结：");
        println("1. Thread vs Runnable: 推荐Runnable（更灵活）");
        println("2. synchronized: 简单的同步机制");
        println("3. Lock: 更强大的锁机制，支持公平锁、读写锁等");
        println("4. 线程池: 避免频繁创建销毁线程，提高性能");
        println("5. Callable/Future: 获取线程执行结果");
        println("6. CountDownLatch: 等待多个线程完成");
        println("7. CyclicBarrier: 多个线程互相等待");
        println("8. Semaphore: 控制并发访问数量");
    }
    
    // synchronized 方法
    private static synchronized void incrementSyncCounter() {
        syncCounter++;
    }
    
    // 使用Lock的方法
    private static void incrementLockCounter() {
        lock.lock();
        try {
            lockCounter++;
        } finally {
            lock.unlock();  // 确保释放锁
        }
    }
    
    // 辅助方法：线程休眠
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


// 方式1：继承Thread类
class MyThread extends Thread {
    private String name;
    
    public MyThread(String name) {
        this.name = name;
    }
    
    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(name + " 执行次数: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


// 方式2：实现Runnable接口（推荐）
class MyRunnable implements Runnable {
    private String name;
    
    public MyRunnable(String name) {
        this.name = name;
    }
    
    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(name + " 执行次数: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
