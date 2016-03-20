package com.agh.chmielarz.ryl.auctions.events;

/**
 * Created by FleenMobile on 2016-03-20.
 */
public class AuctionFinishedEvent {
    private long mId;

    public AuctionFinishedEvent(long id) {
        mId = id;
    }

    public long getId() {
        return mId;
    }
}
