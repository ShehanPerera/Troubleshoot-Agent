package com.github.shehanperera.threadpool;

import java.text.SimpleDateFormat;
import java.util.Date;

// Task class to be executed (Step 1)
class Task implements Runnable {

    private String name;

    public Task(String taskName) {
        name = taskName;
    }

    // Prints task name and sleeps for 1s
    // This Whole process is repeated 5 times
    public void run() {

        try {
            for (int i = 0; i <= 5; i++) {
                if (i == 0) {
                    Date d = new Date();
                    SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
                    System.out.println("Initialization Time for"
                            + " task name - " + name + " = " + ft.format(d));
                    //prints the initialization time for every task
                } else {
                    Date d = new Date();
                    SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
                    System.out.println("Executing Time for task name - " +
                            name + " = " + ft.format(d));
                    // prints the execution time for every task
                }
                Thread.sleep(1000);
            }
            System.out.println(name + " complete");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}