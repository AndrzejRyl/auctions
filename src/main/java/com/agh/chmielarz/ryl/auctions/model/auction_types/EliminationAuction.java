package com.agh.chmielarz.ryl.auctions.model.auction_types;

import com.agh.chmielarz.ryl.auctions.events.*;
import com.agh.chmielarz.ryl.auctions.model.Auction;
import com.agh.chmielarz.ryl.auctions.model.AuctionType;
import com.agh.chmielarz.ryl.auctions.model.Offer;
import com.agh.chmielarz.ryl.auctions.model.Product;
import com.google.common.eventbus.EventBus;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Tomek on 2016-05-25.
 */
public class EliminationAuction extends Auction {

    private static final long AUCTION_TIME = 10 * 1000;
    private LinkedList<Offer> mOffers = new LinkedList<>();
    private boolean mFinished = false;

    public EliminationAuction(EventBus eventBus, long id, Product product) {
        super(eventBus, id, product);
        super.setAuctionType(AuctionType.ELIMINATION);
    }

    @Override
    public void startAuction() {
        super.startAuction();

        getEventBus().post(new AuctionPriceChangeEvent(getId(), getCurrentPrice()));

        Timer timer = new Timer();
        timer.schedule(new AuctionTask(), AUCTION_TIME);
    }


    @Override
    public void onBuyerBid(BuyerBidEvent event) {
        if (event.getAuctionId() != getId()) return;

        mOffers.add(new Offer(event.getBuyerId(), event.getBid()));
        if (mOffers.size() == getBuyers().size()) {
            Collections.sort(mOffers);
            Offer minOffer = mOffers.poll();
            setCurrentPrice(minOffer.getmAmount());
            getEventBus().post(new KickOutEvent(getId(), minOffer.getmBuyerId()));
            getBuyers().remove(minOffer.getmBuyerId());

            if (mOffers.size() == 1) {
                setWinner(mOffers.getFirst().getmBuyerId());
                mFinished = true;
                getEventBus().post(new AuctionFinishedEvent(getId()));
            } else {
                mOffers.clear();
                getEventBus().post(new AuctionPriceChangeEvent(getId(), getCurrentPrice()));
            }
        }
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
            if (!mFinished)
                getEventBus().post(new AuctionFinishedEvent(getId()));
        }
    }
}
