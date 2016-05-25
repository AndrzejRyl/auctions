package com.agh.chmielarz.ryl.auctions.model;

import com.agh.chmielarz.ryl.auctions.events.AuctionFinishedEvent;
import com.agh.chmielarz.ryl.auctions.events.AuctionPriceChangeEvent;
import com.agh.chmielarz.ryl.auctions.events.AuctionStartedEvent;
import com.agh.chmielarz.ryl.auctions.events.KickOutEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FleenMobile on 2016-03-20.
 */
public abstract class Buyer {
    private EventBus mEventBus;
    private long mId;
    private List<Product> mProductsToBuy;
    private BuyerStrategy mStrategy;

    // Auctions this buyer is still taking part in
    private List<Auction> mAuctions;

    public Buyer(EventBus eventBus, long id, List<Product> productsToBuy) {
        mEventBus = eventBus;
        mId = id;
        mProductsToBuy = productsToBuy;
        mAuctions = new ArrayList<>();

        mEventBus.register(this);
    }

    @Subscribe
    public void onAuctionStarted(AuctionStartedEvent event) {
        mAuctions.add(event.getAuction());
        mStrategy.addAuction(event.getAuction());
    }

    @Subscribe
    public void onAuctionFinished(AuctionFinishedEvent event) {
        mAuctions.removeIf(auction -> auction.getId() == event.getId());
        mStrategy.removeAuction(event.getId());
    }

    @Subscribe
    public void onAuctionPriceChange(AuctionPriceChangeEvent event) {
        // Decide what to do next
        double currentPrice = event.getCurrentPrice();
        mStrategy.onAuctionPriceChange(event.getId(), currentPrice);
    }

    @Subscribe
    public void onKickOut(KickOutEvent event) {
        if (event.getBuyerId() != mId) return;

        mAuctions.removeIf(auction -> auction.getId() == event.getAuctionId());
        mStrategy.removeAuction(event.getAuctionId());
    }

    public void setStrategy(BuyerStrategy strategy) {
        mStrategy = strategy;
    }

    public EventBus getEventBus() {
        return mEventBus;
    }

    public long getId() {
        return mId;
    }
}
