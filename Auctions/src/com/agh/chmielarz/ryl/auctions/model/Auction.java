package com.agh.chmielarz.ryl.auctions.model;

import com.agh.chmielarz.ryl.auctions.events.AuctionStartedEvent;
import com.agh.chmielarz.ryl.auctions.events.BuyerBidEvent;
import com.agh.chmielarz.ryl.auctions.events.BuyerInEvent;
import com.agh.chmielarz.ryl.auctions.events.BuyerOutEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FleenMobile on 2016-03-20.
 * <p>
 * Main interface for all types of auctions.
 * It uses eventbus @Subscribe annotation needed for EventBus communication.
 */
public abstract class Auction {
    private long mId;
    private EventBus mEventBus;
    private List<Long> mBuyers = new ArrayList<>();
    private Product mProduct = null;
    private AuctionType mAuctionType;
    private double currentPrice = 0;
    private long winner = 0;

    public Auction(EventBus eventBus, long id, Product product) {
        // Register for events
        mEventBus = eventBus;
        mEventBus.register(this);

        mId = id;
        mProduct = product;
        mAuctionType = AuctionType.ENGLISH;
    }

    public void startAuction() {
        // Inform everyone what auction has just started
        mEventBus.post(new AuctionStartedEvent(this));
    }

    @Subscribe
    public void onBuyerBid(BuyerBidEvent event) {
        if (event.getBid() > currentPrice) {
            currentPrice = event.getBid();
            winner = event.getBuyerId();
        }
    }

    @Subscribe
    public void onBuyerIn(BuyerInEvent event) {
        mBuyers.add(event.getBuyerId());
    }

    @Subscribe
    public void onBuyerOut(BuyerOutEvent event) {
        mBuyers.remove(event.getBuyerId());
    }

    public long getId() {
        return mId;
    }

    public Product getProduct() {
        return mProduct;
    }

    public AuctionType getAuctionType() {
        return mAuctionType;
    }

    public void setAuctionType(AuctionType auctionType) {
        mAuctionType = auctionType;
    }
}
