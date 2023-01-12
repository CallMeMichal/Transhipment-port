package com.company;

public class Timer extends Thread implements Runnable {

    int days;

    public void run() {
        int time = 0;


        while (true) {
            try {
                this.sleep(5000);
            } catch (InterruptedException exc) {
                System.out.println("Timer stopped");
                return;
            }
            time++;
            days = time % 60;
            System.out.println("day:" + days);
        }

    }
}
