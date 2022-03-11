package ru.netology;

public class UselessBox {
    private final int TURNCOUNT;
    private final int TURNTIMEOUT;
    private final int REACTIONTIMEOUT;
    private volatile boolean tumbler;

    public UselessBox(int TURNCOUNT, int TURNTIMEOUT, int REACTIONTIMEOUT) {
        this.TURNCOUNT = TURNCOUNT;
        this.TURNTIMEOUT = TURNTIMEOUT;
        this.REACTIONTIMEOUT = REACTIONTIMEOUT;
        this.tumbler = false;
    }

    public synchronized void toy() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (tumbler) {
                    System.out.println("Выключаю тумблер оО");
                    tumbler = false;
                }
                wait(REACTIONTIMEOUT);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public synchronized void turn() {
        try {
            for (int i = 0; i < TURNCOUNT; i++) {
                while (tumbler) {
                    wait(REACTIONTIMEOUT);
                }
                System.out.println("Включаю тумблер!");
                tumbler = true;
                wait(TURNTIMEOUT);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
