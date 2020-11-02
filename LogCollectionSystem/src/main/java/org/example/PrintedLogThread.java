package org.example;

import java.util.Queue;

public class PrintedLogThread implements Runnable {
    private static volatile Queue<Logger> queue = null;
    private static int id = 1;

    public PrintedLogThread(Queue<Logger> queue) {
        this.queue = queue;
    }

    public PrintedLogThread(Queue<Logger> queue, int id) {
        this.queue = queue;
        this.id = id;
    }

    @Override
    public void run() {
        int cnt = queue.size();

        while (cnt > 0) {
        //while (true) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Logger logger = queue.poll();
            synchronized (this.getClass()) {
                if (queue.size() == 0) {
                    break;
                }
                Logger logger = queue.poll();

//                cnt = queue.size();
//                System.out.println("the queue size is " + cnt);

                //异常处理，当queue size为0时，各个线程终止
//                if (queue.size() == 0) {
//                    break;
//                }

                try {
                    System.out.println("Thread print " + Thread.currentThread().getName() + " " + logger.toString());
                    if (logger.getLevel().equals("ERROR")) {
//                    System.out.println("ERROR日志请立即排查处理");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                cnt = queue.size();
                System.out.println("the queue size is " + cnt);
                //System.out.println("Thread print " + logger.toString());
//                if (logger.getLevel().equals("ERROR")) {
//                    System.out.println("ERROR日志请立即排查处理");
//                }

                //System.out.println("Thread print " + Thread.currentThread().getName() + logger.toString());
//                if (logger.getLevel().equals("ERROR")) {
//                    System.out.println("ERROR日志请立即排查处理");
//                }
            }


            //System.out.println("Thread print "+Thread.currentThread().getName()+logger.toString());
//            if(logger.getLevel().equals("ERROR")){
//                System.out.println("ERROR日志请立即排查处理");
//            }
            //cnt = queue.size();
            //System.out.println("the queue size is " + cnt);
        }
    }
}
