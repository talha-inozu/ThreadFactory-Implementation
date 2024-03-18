package org.tmi.threadfactory;

import java.util.ArrayList;
import java.util.List;

public class WorkerThread extends Thread {

    private int threadId;
    private Object waitLock = new Object();
    private boolean isShutdown = false;
    private List<Runnable> taskList = new ArrayList<>();
    private boolean running = true;

    public WorkerThread(int threadId) {
        this.threadId = threadId;
    }

    @Override
    public void run() {
        while (running) {
            try {
                if (taskList.size() > 0) {
                    Runnable task = taskList.remove(0);
                    task.run();
                    System.out.println("THREAD " + threadId + " is finished");
                } else {
                    if (isShutdown) {
                        break;
                    }
                    synchronized (waitLock) {
                        waitLock.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addTask(Runnable task) {
        taskList.add(task);
        openLock();
    }

    public void shutdown() {
        isShutdown = true;
        openLock();
    }

    public void terminate() {
        running = false;
        openLock();
    }

    private synchronized void openLock() {
        synchronized (waitLock) {
            waitLock.notify();
        }
    }
}
