package me.lkhz;

import me.lkhz.dao.CouponDAO;
import me.lkhz.threadpool.ThreadPool;

public class Main {

    public static void main(String[] args) {
        ThreadPool threadPool = ThreadPool.getInstance();
        CouponDAO couponDAO = new CouponDAO();

        for(int i = 0; i < 10; i++){
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

    }
}
