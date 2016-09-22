package com.agh.chmielarz.ryl.auctions.model.auction_types;

import com.agh.chmielarz.ryl.auctions.events.*;
import com.agh.chmielarz.ryl.auctions.model.Auction;
import com.agh.chmielarz.ryl.auctions.model.AuctionType;
import com.agh.chmielarz.ryl.auctions.model.Product;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Tomek on 2016-05-23.
 */
public class DutchAuction extends Auction {

    private static final long DECREASE_PRICE_TIME = 1000;
    private final double startPriceFactor;
    private final double auctionChangeFactor;
    private Timer mTimer = new Timer();

    public DutchAuction(EventBus eventBus, long id, Product product, double startPriceFactor, double auctionChangeFactor) {
        super(eventBus, id, product);
        super.setAuctionType(AuctionType.DUTCH);
        this.startPriceFactor = startPriceFactor;
        this.auctionChangeFactor = auctionChangeFactor;
    }

    @Override
    public void startAuction() {
        super.startAuction();

        setCurrentPrice(getProduct().getPrice() * startPriceFactor);

        getEventBus().post(new AuctionPriceChangeEvent(getId(), AuctionType.DUTCH, getCurrentPrice()));
        mTimer.schedule(new DecreasePriceTask(), DECREASE_PRICE_TIME);
    }

    @Subscribe
    public void onDecreasePrice(DecreasePriceEvent event) {
        if (event.getAuctionId() != getId()) return;

        setCurrentPrice(getNewPrice());

        getEventBus().post(new AuctionPriceChangeEvent(getId(), AuctionType.DUTCH, getCurrentPrice()));
        mTimer.schedule(new DecreasePriceTask(), DECREASE_PRICE_TIME);
    }

    @Override
    public void onBuyerBid(BuyerBidEvent event) {
        return;
    }

    @Override
    public void onBuyerIn(BuyerInEvent event) {
        super.onBuyerIn(event);

        if (!isWinner()) {
            setWinner(event.getBuyerId());
            getEventBus().post(new AuctionFinishedEvent(getId()));
        }
    }

    @Override
    public void onBuyerOut(BuyerOutEvent event) {
        return;
    }

    private class DecreasePriceTask extends TimerTask {
        @Override
        public void run() {
            if (!isWinner())
                getEventBus().post(new DecreasePriceEvent(getId()));
        }
    }

    private double getNewPrice() {
        Random r = new Random();
        return getCurrentPrice() - (r.nextDouble() * auctionChangeFactor * getProduct().getPrice());
    }

    private boolean isWinner() {
        return !(getWinner() < 0);
    }
}
