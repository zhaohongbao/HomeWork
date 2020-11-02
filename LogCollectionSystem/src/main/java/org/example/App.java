package org.example;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Hello world!
 */
public class App {

    //private static Queue<Logger> queue;
    private static volatile Queue<Logger> queue = new LinkedList<>();
    //private static volatile int id = 1;

    //private static final Logger LOGGER = Logger.getLogger(App.class);
    public static void main(String[] args) {
        Count count = new Count();
// 模拟日志收集系统
//        System.out.println("Hello World!");
//        LOGGER.error("This is error message.");
//        LOGGER.warn("This is warn message.");
//        LOGGER.info("This is info message.");
//        LOGGER.debug("This is a debug message.");
//        LOGGER.trace("This is a debug message.");
        ProduceErrorLogThread produceErrorLogThread = new ProduceErrorLogThread(queue,count);
        ProduceWarnLogThread produceWarnLogThread = new ProduceWarnLogThread(queue,count);
        ProduceInfoLogThread produceInfoLogThread = new ProduceInfoLogThread(queue,count);
        ProduceDebugLogThread produceDebugLogThread = new ProduceDebugLogThread(queue,count);
        ProduceTraceLogThread produceTraceLogThread = new ProduceTraceLogThread(queue,count);
        PrintedLogThread printedLogThread = new PrintedLogThread(queue);

        new Thread(produceErrorLogThread,"ProducedErrorLogThread").start();
        new Thread(produceWarnLogThread, "ProducedWarnLogThread").start();
        new Thread(produceInfoLogThread,"ProducedInfoLogThread").start();
        new Thread(produceDebugLogThread,"ProducedDebugLogThread").start();
        new Thread(produceTraceLogThread,"ProducedTraceLogThread").start();

        try {
            Thread.sleep(15000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 1; i <= 7; i++) {

            new Thread(printedLogThread, "PrintedThread-"+i).start();
            System.out.println("PrintThread " + i + " start.");
        }
        int size = queue.size();;
        while (size>0) {
            size = queue.size();
            System.out.println("the size of queue is " + size);
            try {
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
