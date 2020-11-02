package org.example;

import java.util.Queue;

public class ProduceDebugLogThread implements Runnable {
    //private static final Logger logger =Logger.getLogger(produceLogThread.class);
    private static volatile Queue<Logger> queue = null;
    private static int id;
    private Count count;

    public ProduceDebugLogThread(Queue<Logger> queue) {
        this.queue = queue;
    }

    public ProduceDebugLogThread(Queue<Logger> queue, Count count) {
        this.queue = queue;
        this.count = count;
    }


    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Logger logger = new Logger();
            synchronized (logger.getClass()) {
                synchronized (count.getClass()) {
                    int id = count.getCnt() + 1;
                    count.setCnt(id);
                    logger.setId(id);
                }
                //logger.setId(this.id++);
                logger.setLevel("DEBUG");
                logger.setLogMsg("This is debug message.");
                //String loggerMsg = "This is error message.";
                //synchronized (logger.getClass()){
                queue.add(logger);
                System.out.println("Thread produce " + Thread.currentThread().getName() + " " + logger.toString());
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            this.logger.error("This is error message.");
//            this.logger.warn("This is warn message.");
//            this.logger.info("This is info message.");
//            this.logger.debug("This is a debug message.");
//            this.logger.trace("This is a debug message.");

//            logger.toString();

            //this.queue.add(logger);

        }
        //遍历queue

    }
}
