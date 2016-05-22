package com.agh.chmielarz.ryl.auctions.events;

/**
 * Created by FleenMobile on 2016-03-20.
 */
public class BuyerBidEvent {
    private long mBuyerId;
    private long mAuctionId;
    private double mBid;

    public BuyerBidEvent(long buyerId, long auctionId, double bid) {
        mBuyerId = buyerId;
        mAuctionId = auctionId;
        mBid = bid;
    }

    public long getBuyerId() {
        return mBuyerId;
    }

    public long getAuctionId() {
        return mAuctionId;
    }

    public double getBid() {
        return mBid;
    }


    @Override
    public String toString() {
        return String.format("[%s]: auctionID: %d, buyerID: %d, bid: %.2f", getClass().getName().toUpperCase(), mAuctionId, mBuyerId, mBid);
    }
}
