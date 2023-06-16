package me.lkhz;

import me.lkhz.dao.CouponDAO;
import me.lkhz.threadpool.ThreadPool;

public class Main {

    public static void main(String[] args) {
        ThreadPool threadPool = ThreadPool.getInstance();
        CouponDAO couponDAO = new CouponDAO();

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
