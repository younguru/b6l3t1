package ru.netology;

public class UselessBox {
    private final int TURN_COUNT;
    private final int TURN_TIMEOUT;
    private final int REACTION_TIMEOUT;
    private volatile boolean tumbler;

    public UselessBox(int turnCount, int turnTimeout, int reactionTimeout) {
        this.TURN_COUNT = turnCount;
        this.TURN_TIMEOUT = turnTimeout;
        this.REACTION_TIMEOUT = reactionTimeout;
        this.tumbler = false;
    }

    public void toy() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (tumbler) {
                    System.out.println("Выключаю тумблер оО");
                    tumbler = false;
                }
                Thread.sleep(REACTION_TIMEOUT);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void turn() {
        try {
            for (int i = 0; i < TURN_COUNT; i++) {
                while (tumbler) {
                    Thread.sleep(REACTION_TIMEOUT);
                }
                System.out.println("Включаю тумблер!");
                tumbler = true;
                Thread.sleep(TURN_TIMEOUT);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
