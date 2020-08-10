package VIA.VIATalks.Database.jdbc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizationMonitor {
    private int readers;
    private boolean isWriting;
    private int waitingWriters;

    //setting singleton
    private static SynchronizationMonitor instance;
    private static Lock lock = new ReentrantLock();

    private SynchronizationMonitor() {
        readers = 0;
        isWriting = false;
        waitingWriters = 0;
    }

    public static SynchronizationMonitor getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new SynchronizationMonitor();
                }
            }
        }
        return instance;
    }

    public synchronized void acquireRead() {
        while (isWriting || waitingWriters > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        readers++;
    }

    public synchronized void releaseRead() {
        readers--;
        if (readers == 0) {
            notifyAll();
        }
    }

    public synchronized void acquireWrite() {
        waitingWriters++;
        while (isWriting || readers > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        waitingWriters--;
        isWriting = true;
    }

    public synchronized void releaseWrite() {
        isWriting = false;
        notifyAll();
    }
}
