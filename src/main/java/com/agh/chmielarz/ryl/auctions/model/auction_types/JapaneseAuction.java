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
public class JapaneseAuction extends Auction {

    private static final long INCREASE_PRICE_TIME = 1000;
    private Timer mTimer = new Timer();

    public JapaneseAuction(EventBus eventBus, long id, Product product) {
        super(eventBus, id, product);
        super.setAuctionType(AuctionType.JAPANESE);
    }

    @Override
    public void startAuction() {
        super.startAuction();

        Random r = new Random();
        setCurrentPrice(getProduct().getPrice() * r.nextDouble() * r.nextDouble());

        getEventBus().post(new AuctionPriceChangeEvent(getId(), getCurrentPrice()));
        mTimer.schedule(new IncreasePriceTask(), INCREASE_PRICE_TIME);
    }

    @Subscribe
    public void onIncreasePrice(IncreasePriceEvent event) {
        if (event.getAuctionId() != getId()) return;

        getBuyers().clear();
        setCurrentPrice(getNewPrice());

        getEventBus().post(new AuctionPriceChangeEvent(getId(), getCurrentPrice()));
        mTimer.schedule(new IncreasePriceTask(), INCREASE_PRICE_TIME);
    }

    @Override
    public void onBuyerBid(BuyerBidEvent event) {
        return;
    }

    @Override
    public void onBuyerIn(BuyerInEvent event) {
        super.onBuyerIn(event);
    }

    @Override
    public void onBuyerOut(BuyerOutEvent event) {
        return;
    }

    private class IncreasePriceTask extends TimerTask {
        @Override
        public void run() {
            if (getBuyers().size() > 1) {
                getEventBus().post(new IncreasePriceEvent(getId()));
            } else {
                if (getBuyers().size() == 1)
                    setWinner(getBuyers().get(0));
                getEventBus().post(new AuctionFinishedEvent(getId()));
            }
        }
    }

    private double getNewPrice() {
        Random r = new Random();
        return getCurrentPrice() * (1.0 + (r.nextDouble() * r.nextDouble()));
    }
}
