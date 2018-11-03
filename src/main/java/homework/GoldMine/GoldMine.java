package homework.GoldMine;

import static homework.GoldMine.Main.print;

public class GoldMine {

    private volatile boolean isEmpty;
    private final int goldPerSec = 3;

    private int gold;

    public GoldMine(int gold) {
        this.gold = gold;
        this.isEmpty = gold <= 0;
        print("Gold in Mine = " + gold,CmdColor.ANSI_GREEN);
    }

    boolean isEmpty() {
        return isEmpty;
    }

    synchronized int collectGold(String name) {
        gold -= goldPerSec;
        int delta = 3;
        if (gold <= 0) {
            delta = goldPerSec + gold;
            gold = 0;
            isEmpty = true;
        }
        print(name + " retrieves " + delta  + " gold (ramaining gold in mine = " + String.valueOf(gold)+ ")",CmdColor.ANSI_GREEN);
        if (isEmpty) {
            print("Gold mine is empty",CmdColor.ANSI_RED);
        }
        return delta;
    }
}
