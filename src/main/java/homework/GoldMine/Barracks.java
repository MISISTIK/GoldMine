package homework.GoldMine;

import java.util.concurrent.TimeUnit;
import static homework.GoldMine.Main.print;

public class Barracks implements Runnable {

    private final int creationCountdownInSec = 5;
    private Thread thisThread;

    public Thread getThread() {
        return thisThread;
    }

    public Barracks() {
        thisThread = new Thread(this);
        thisThread.setDaemon(true);
        thisThread.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (int i = 0; i < creationCountdownInSec; i++) {
                    print("New worker comes in " + String.valueOf(creationCountdownInSec-i) + " sec...",CmdColor.ANSI_PURPLE);
                    TimeUnit.SECONDS.sleep(1);
                }
                new Worker();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
