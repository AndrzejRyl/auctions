package com.agh.chmielarz.ryl.auctions.events;

import com.agh.chmielarz.ryl.auctions.model.AuctionType;

/**
 * Created by FleenMobile on 2016-03-20.
 */
public class AuctionPriceChangeEvent {
    private long mId;
    private AuctionType mType;
    private double mCurrentPrice;

    public AuctionPriceChangeEvent(long id, AuctionType auctionType, double currentPrice) {
        mId = id;
        mType = auctionType;
        mCurrentPrice = currentPrice;
    }

    public long getId() {
        return mId;
    }

    public double getCurrentPrice() {
        return mCurrentPrice;
    }

    public AuctionType getAuctionType() { return mType; }


    @Override
    public String toString() {
        return String.format("[%s]: auctionID: %d, currentPrice: %.2f", getClass().getName().toUpperCase(), mId, mCurrentPrice);
    }
}
