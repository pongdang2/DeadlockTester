package me.lkhz.dao.vo;

public class CouponVO {
    private int id;
    private int remain_count;

    public CouponVO() {
    }

    public CouponVO(int id, int remain_count) {
        this.id = id;
        this.remain_count = remain_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRemain_count() {
        return remain_count;
    }

    public void setRemain_count(int remain_count) {
        this.remain_count = remain_count;
    }
}
