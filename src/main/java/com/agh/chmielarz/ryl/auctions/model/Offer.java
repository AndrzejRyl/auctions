package com.agh.chmielarz.ryl.auctions.model;

/**
 * Created by Tomek on 2016-05-24.
 */
public class Offer implements Comparable<Offer>{
    private final Long mBuyerId;
    private final Double mAmount;

    private Offer(){
        mBuyerId = null;
        mAmount = null;
    }

    public Offer(Long mBuyerId, Double mAmount) {
        this.mBuyerId = mBuyerId;
        this.mAmount = mAmount;
    }

    public double getmAmount() {
        return mAmount;
    }

    public long getmBuyerId() {
        return mBuyerId;
    }

    @Override
    public int compareTo(Offer o) {
        return mAmount.compareTo(o.mAmount);
    }
}
