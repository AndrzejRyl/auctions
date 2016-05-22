package com.agh.chmielarz.ryl.auctions.events;

/**
 * Created by FleenMobile on 2016-03-20.
 * <p>
 * Event representing byer starting to participate in an auction (stakes
 * are at the level which he can and will handle)
 */
public class BuyerInEvent {
    private long mBuyerId;
    private long mAuctionId;

    public BuyerInEvent(long buyerId, long auctionId) {
        mBuyerId = buyerId;
        mAuctionId = auctionId;
    }

    public long getBuyerId() {
        return mBuyerId;
    }

    public long getAuctionId() {
        return mAuctionId;
    }


    @Override
    public String toString() {
        return String.format("[%s]: auctionID: %d, buyerID: %d", getClass().getName().toUpperCase(), mAuctionId, mBuyerId);
    }
}
