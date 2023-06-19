package me.lkhz;

import me.lkhz.dao.CouponDAO;
import me.lkhz.dao.CouponDAO_01;
import me.lkhz.dao.CouponDAO_02;
import me.lkhz.threadpool.ThreadPool;

public class Main {

    public static void main(String[] args) {
        ThreadPool threadPool = ThreadPool.getInstance();
        CouponDAO couponDAO;
        // 1. DeadLock 발생
        //couponDAO = new CouponDAO_01();

        // 2. 접근하는 테이블 순서를 같게 변경
        couponDAO = new CouponDAO_02();

        long startTime = System.currentTimeMillis();
        for(int i = 0; i < 100; i++){
            if(i%2 == 0){
                threadPool.add(couponDAO::getCoupon);
                //threadPool.add(() -> couponDAO.getCoupon());
            /*
            threadPool.add(new Runnable() {
                @Override
                public void run() {
                    couponDAO.getCoupon();
                }
            });
             */
            } else {
                threadPool.add(couponDAO::cancleCoupon);
            }
        }

        while(true){
            if(threadPool.isEmpty())
                break;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("프로그램 실행 시간: " + (endTime - startTime)/1000.0 + "초");

    }
}
