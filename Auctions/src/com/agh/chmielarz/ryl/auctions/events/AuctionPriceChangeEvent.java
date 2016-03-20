package com.agh.chmielarz.ryl.auctions.events;

/**
 * Created by FleenMobile on 2016-03-20.
 */
public class AuctionPriceChangeEvent {
    private long mId;
    private double mCurrentPrice;

    public AuctionPriceChangeEvent(long id, double currentPrice) {
        mId = id;
        mCurrentPrice = currentPrice;
    }

    public long getId() {
        return mId;
    }

    public double getCurrentPrice() {
        return mCurrentPrice;
    }
}
