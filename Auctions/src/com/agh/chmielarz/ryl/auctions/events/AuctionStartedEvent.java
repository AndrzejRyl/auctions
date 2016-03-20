package com.agh.chmielarz.ryl.auctions.events;

import com.agh.chmielarz.ryl.auctions.model.Auction;

/**
 * Created by FleenMobile on 2016-03-20.
 */
public class AuctionStartedEvent {
    private Auction mAuction;

    public AuctionStartedEvent(Auction auction) {
        mAuction = auction;
    }

    public Auction getAuction() {
        return mAuction;
    }

    @Override
    public String toString() {
        return String.format("[%s]: auctionID: %d", getClass().getName().toUpperCase(), mAuction.getId());
    }
}
