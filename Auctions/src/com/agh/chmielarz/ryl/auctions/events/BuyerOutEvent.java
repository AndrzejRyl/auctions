package com.agh.chmielarz.ryl.auctions.events;

/**
 * Created by FleenMobile on 2016-03-20.
 * <p>
 * Event representing buyer backing out of the auction (stakes are too high for him)
 */
public class BuyerOutEvent {
    private long mBuyerId;
    private long mAuctionId;

    public BuyerOutEvent(long buyerId, long auctionId) {
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
