package com.agh.chmielarz.ryl.auctions.events;

/**
 * Created by Tomek on 2016-05-25.
 */
public class KickOutEvent {
    private long mAuctionId;
    private long mBuyerId;

    public KickOutEvent(long mAuctionId, long mBuyerId) {
        this.mAuctionId = mAuctionId;
        this.mBuyerId = mBuyerId;
    }


    public long getBuyerId() {
        return mBuyerId;
    }

    public long getAuctionId() {
        return mAuctionId;
    }

    @Override
    public String toString() {
        return String.format("[%s]: auctionID: %d, buyerId: %d", getClass().getName().toUpperCase(), mAuctionId, mBuyerId);
    }
}
