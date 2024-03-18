package org.tmi.threadfactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadFactory {

    private int threadNumber = 1;
    private AtomicInteger atomicInteger = new AtomicInteger(1);
    private List<WorkerThread> threadList = new ArrayList<>();

    public ThreadFactory() {
        init();
    }

    public ThreadFactory(int threadNumber) {
        this.threadNumber = threadNumber;
        init();
    }

    public synchronized void init() {
        for (int i = 0; i < threadNumber; i++) {
            WorkerThread workerThread = new WorkerThread(i);
            workerThread.start();
            threadList.add(workerThread);
        }
    }

    public void execute(Runnable runnable) {
        WorkerThread workerThread = threadList.get(atomicInteger.getAndIncrement() % threadNumber);
        workerThread.addTask(runnable);
    }

    public void shutdown() {
        for (WorkerThread thread : threadList) {
            thread.shutdown();
        }
    }

    public void awaitTermination() throws InterruptedException {
        for (WorkerThread thread : threadList) {
            thread.join();
        }
    }

    public void awaitAndShutdown() throws InterruptedException {
        for (WorkerThread thread : threadList) {
            thread.shutdown();
            thread.join();
        }
    }
}
