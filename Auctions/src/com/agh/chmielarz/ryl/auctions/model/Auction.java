package com.agh.chmielarz.ryl.auctions.model;

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
    private List<Buyer> mBuyers = new ArrayList<>();
    private Product mProduct = null;

    public Auction(EventBus eventBus, Product product) {
        // Register for events
        eventBus.register(this);

        mProduct = product;
    }

    public void startAuction() {
        throw new RuntimeException("This method needs to be implemented!");
    }

    @Subscribe
    public void onBuyerBid(BuyerBidEvent event) {
        throw new RuntimeException("This method needs to be implemented!");
    }

    @Subscribe
    public void onBuyerIn(BuyerInEvent event) {
        throw new RuntimeException("This method needs to be implemented!");
    }

    @Subscribe
    public void onBuyerOut(BuyerOutEvent event) {
        throw new RuntimeException("This method needs to be implemented!");
    }
}
