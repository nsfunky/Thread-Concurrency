package com.symc;
  
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ReadWriteCounter {
    ReadWriteLock lock = new ReentrantReadWriteLock();

    private int count = 0;

    public int incrementAndGetCount() {
        lock.writeLock().lock();

        try {
            count = count + 1;
            return count;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int getCount() {
        lock.readLock().lock();
        try {
            return count;
        } finally {
            lock.readLock().unlock();
        }
    }
}

public class ReadWriteLockExample 
{
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        ReadWriteCounter counter = new ReadWriteCounter();

        Runnable readTask = () -> {
          System.out.println(Thread.currentThread().getName() +
                  " Read Task : " + counter.getCount());
        };

        Runnable writeTask = () -> {
            System.out.println(Thread.currentThread().getName() +
                    " Write Task : " + counter.incrementAndGetCount());
        };

        executorService.submit(readTask);
        executorService.submit(readTask);

        executorService.submit(writeTask);

        executorService.submit(readTask);
        executorService.submit(readTask);

        executorService.shutdown();
    }
}
