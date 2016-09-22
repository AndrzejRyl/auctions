package com.agh.chmielarz.ryl.auctions.model.auction_types;

import com.agh.chmielarz.ryl.auctions.events.*;
import com.agh.chmielarz.ryl.auctions.model.Auction;
import com.agh.chmielarz.ryl.auctions.model.AuctionType;
import com.agh.chmielarz.ryl.auctions.model.Product;
import com.google.common.eventbus.EventBus;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by FleenMobile on 2016-03-20.
 */
public class EnglishAuction extends Auction {

    private static final long AUCTION_TIME = 10 * 1000;
    private final double startPriceFactor;

    public EnglishAuction(EventBus eventBus, long id, Product product, double startPriceFactor) {
        super(eventBus, id, product);
        super.setAuctionType(AuctionType.ENGLISH);
        this.startPriceFactor = startPriceFactor;
    }

    @Override
    public void startAuction() {
        super.startAuction();

        // Set the minimum price based on product's market price
        Random r = new Random();
        setCurrentPrice(getProduct().getPrice() * startPriceFactor);

        // Inform about it
        getEventBus().post(new AuctionPriceChangeEvent(getId(), AuctionType.ENGLISH, getCurrentPrice()));

        // Start the timer
        Timer timer = new Timer();
        timer.schedule(new AuctionTask(), AUCTION_TIME);

    }

    @Override
    public void onBuyerBid(BuyerBidEvent event) {
        super.onBuyerBid(event);
    }

    @Override
    public void onBuyerIn(BuyerInEvent event) {
        super.onBuyerIn(event);
    }

    @Override
    public void onBuyerOut(BuyerOutEvent event) {
        super.onBuyerOut(event);
    }

    private class AuctionTask extends TimerTask {
        @Override
        public void run() {
            // Finish the auction
            getEventBus().post(new AuctionFinishedEvent(getId()));
        }
    }
}
