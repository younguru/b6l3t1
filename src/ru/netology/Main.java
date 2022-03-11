package ru.netology;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        final UselessBox uselessBox = new UselessBox(5, 2000, 1000);
        Thread thread1 = new Thread(null, uselessBox::toy, "Игрушка");
        Thread thread2 = new Thread(null, uselessBox::turn, "Пользователь");
        thread1.start();
        thread2.start();
        thread2.join();
        thread1.interrupt();
    }
}
