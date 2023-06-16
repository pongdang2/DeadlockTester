package me.lkhz.threadpool;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {

    private static ThreadPool instance;
    private int capacity;
    private List<Thread> threadPool;


    private ThreadPool() {
        this.capacity = 100;
        this.threadPool = new ArrayList<>();
    }

    public static ThreadPool getInstance() {
        if (instance == null)
            instance = new ThreadPool();
        return instance;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
    public int getCapacity(){
        return this.capacity;
    }

    /**
     *
     * @param runnable 실행할 Runnable 인터페이스
     * @return thread pool에 추가 성공했으면 True, 꽉차있어서 실패했으면 false;
     */
    public boolean add(Runnable runnable){
        // thread pool 정리
        cleanUp();

        // thread pool에 추가
        if (threadPool.size() < capacity) {
            Thread curThread = new Thread(runnable);
            threadPool.add(curThread);
            curThread.start();
            System.out.println(curThread.getName() + "Start");
            return true;
        }
        return false;
    }

    /**
     * 다끝난 thread 회수
     */
    public void cleanUp(){
        // thread pool 정리
        threadPool.removeIf(thread -> thread.getState() == Thread.State.TERMINATED);
        /*
        for (Thread thread : threadPool) {
            if(thread.getState() == Thread.State.TERMINATED){
                threadPool.remove(thread);
            }
        }
        */
    }

    public void clean(){
        this.threadPool = new ArrayList<>();
    }

    public boolean isEmpty(){
        return threadPool.size() == 0;
    }
}
