package com.liupeng.learning.juc;

import java.util.Map;

public class TestReadWriterLock {

}

class ReadWriterLock{
    private int writeRequests = 0;
    private Map<Thread,Integer> readers;
    private Thread writerThread;

    public synchronized void lockRead() throws InterruptedException{
        while (!canAcquireReadLock(Thread.currentThread())) {
            wait();
        }
        Integer accessCount = readers.get(Thread.currentThread());
        readers.put(Thread.currentThread(),accessCount+1);
    }

    public synchronized void unlockRead() {
        Integer accessCount = readers.get(Thread.currentThread());
        if (accessCount.equals(1)) {
            readers.remove(Thread.currentThread());
        } else {
            readers.put(Thread.currentThread(),accessCount+1);
        }
        notifyAll();
    }

    public synchronized void lockWriter() throws InterruptedException{
        writeRequests++;
        while (!canAcquireWriteLock(Thread.currentThread())) {
            wait();
        }
        writerThread = Thread.currentThread();
        writeRequests--;
    }

    public synchronized void unlockWriter() {
        writerThread = null;
        notifyAll();
    }

    public boolean canAcquireReadLock(Thread thread){
        if (isWriter(thread)) return true;
        if (hasWriter()) return false;
        if (isReader(thread)) return true;
        if (hasWriteRequests()) return false;
        return true;
    }

    public boolean isWriter(Thread thread){
        if (writerThread == thread) return true;
        return false;
    }

    public boolean hasWriter(){
        if (writerThread != null) return true;
        return false;
    }

    public boolean isReader(Thread thread){
        return readers.get(thread) != null;
    }

    public boolean hasWriteRequests() {
        return writeRequests > 0;
    }

    public boolean canAcquireWriteLock(Thread thread){
        if (isOnlyReader(thread)) return true;
        if (hasReaders()) return false;
        if (writerThread == null) return true;
        if (!isWriter(thread)) return false;
        return true;
    }

    public boolean isOnlyReader(Thread thread){
        return readers.size() == 1 && readers.get(thread) != null;
    }

    public boolean hasReaders(){
        return !readers.isEmpty();
    }

}
