package homework.GoldMine;

import java.util.concurrent.TimeUnit;

import static homework.GoldMine.Main.*;

public class Worker implements Runnable {

    private String name;
    private GoldMine goldMine;
    private int earnedMoney;
    private Thread thisThread;

    public Thread getThread() {
        return thisThread;
    }

    public Worker() {
        this.name = "Vasiliy " + String.valueOf(incrementWorkerCounter());
        print("=== New worker " + this.name + " created. Number of workers: " + workerCount + " ===",CmdColor.ANSI_YELLOW);
        this.goldMine = Main.goldMine;
        this.earnedMoney = 0;
        thisThread = new Thread(this);
        thisThread.start();
    }

    @Override
    public void run() {
        try {
            BARRIER.await();
            while (!goldMine.isEmpty()) {
                TimeUnit.SECONDS.sleep(1);
                semaphore.acquire();
                earnedMoney += goldMine.collectGold(name);
                semaphore.release();
            }
            TimeUnit.MILLISECONDS.sleep(10);
            print(name + " finished working. Earned money = " + String.valueOf(earnedMoney),CmdColor.ANSI_BLUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
