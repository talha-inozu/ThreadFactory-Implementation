package org.tmi.threadfactory;

import java.util.List;

public class Example {
    public static void main(String[] args) throws InterruptedException {
        //initializing the thread factory
        ThreadFactory threadFactory = new ThreadFactory(10);

        //example runnable list
        List<String> runnableList = List.of("thread1", "thread2", "thread3", "thread4", "thread5");
        for (String threadName:runnableList){
            Runnable runnableThing = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread is running: " + threadName);
                }
            };
            //executes the runnable
            threadFactory.execute(runnableThing);
        }
        //joins thread and shutdown for not to accept new tasks
        threadFactory.awaitAndShutdown();
        System.out.println("It will be printed after all threads are finished.");
    }
}