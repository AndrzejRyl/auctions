package com.agh.chmielarz.ryl.auctions.events;

/**
 * Created by Tomek on 2016-05-23.
 */
public class IncreasePriceEvent {
    private long mAuctionId;

    public IncreasePriceEvent(long auctionId) {
        mAuctionId = auctionId;
    }

    public long getAuctionId() {
        return mAuctionId;
    }

    @Override
    public String toString() {
        return String.format("[%s]: auctionID: %d", getClass().getName().toUpperCase(), mAuctionId);
    }
}
