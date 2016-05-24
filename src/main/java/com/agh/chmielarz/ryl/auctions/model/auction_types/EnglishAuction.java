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
 * <p>
 * English auction main rules:
 * - seller sets the starting price
 * - buyers either bid higher prices or back out of the auction
 * - auction finishes after specified time
 * - winner is the buyer with the highest bid
 */
public class EnglishAuction extends Auction {

    private static final long AUCTION_TIME = 10 * 1000;

    public EnglishAuction(EventBus eventBus, long id, Product product) {
        super(eventBus, id, product);
        super.setAuctionType(AuctionType.ENGLISH);
    }

    @Override
    public void startAuction() {
        super.startAuction();

        // Set the minimum price based on product's market price
        Random r = new Random();
        setCurrentPrice(getProduct().getPrice() * r.nextDouble());

        // Inform about it
        getEventBus().post(new AuctionPriceChangeEvent(getId(), getCurrentPrice()));

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
