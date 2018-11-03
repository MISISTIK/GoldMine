package homework.GoldMine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {
    static int workerCount;
    static Semaphore semaphore;
    static CountDownLatch BARRIER = new CountDownLatch(1);
    static GoldMine goldMine;
    static long workingTimeSec;
    static final int initialWorkersNumber = 5;
    static final int initialGold = 1000;

    public static void main(String[] args) {
        try {
            semaphore = new Semaphore(1);
            workerCount = 0;
            workingTimeSec = 0;
            goldMine = new GoldMine(initialGold);
            List<Thread> workersList = new ArrayList<>();
            for (int i = 0; i < initialWorkersNumber; i++) {
                workersList.add(new Worker().getThread());
            }

            print("===== Mining work started! =====", CmdColor.ANSI_CYAN);
            Thread timerDaemon = new Thread(() -> {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        workingTimeSec = workingTimeSec < Long.MAX_VALUE ? workingTimeSec + 1 : workingTimeSec;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            timerDaemon.setDaemon(true);
            timerDaemon.start();
            BARRIER.countDown();
            new Barracks();

            for (Thread th : workersList) {
                th.join();
            }

            print("===== Mining work ended in " + String.valueOf(workingTimeSec) + " sec! =====", CmdColor.ANSI_CYAN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static synchronized int incrementWorkerCounter() {
        workerCount += 1;
        return workerCount;

    }


    static void print(String s, CmdColor color) {
        System.out.println(color.getColor() + s + CmdColor.ANSI_RESET.getColor());
    }
}
