package com.agh.chmielarz.ryl.auctions.model.buyer_strategies;

import com.agh.chmielarz.ryl.auctions.model.Auction;
import com.agh.chmielarz.ryl.auctions.model.BuyerStrategy;
import com.agh.chmielarz.ryl.auctions.model.Product;
import com.google.common.eventbus.EventBus;

import java.awt.*;
import java.util.Random;

/**
 * Created by FleenMobile on 2016-03-20.
 */
public class DefaultStrategy extends BuyerStrategy {

    private final double wantingFactor;
    private final double wantsToBidFactor;
    private final double nextBidFactor;

    public DefaultStrategy(EventBus eventBus, long buyerId) {
        super(eventBus, buyerId);
        this.wantingFactor = 0.05;
        this.wantsToBidFactor = 0.5;
        this.nextBidFactor = 0.5;
    }

    public DefaultStrategy(EventBus eventBus, long buyerId, double wantingFactor, double wantsToBidFactor, double nextBidFactor) {
        super(eventBus, buyerId);
        this.wantingFactor = wantingFactor;
        this.wantsToBidFactor = wantsToBidFactor;
        this.nextBidFactor = nextBidFactor;
    }

    @Override
    public boolean wantsToBid(long id, double currentPrice, Auction auction) {
        switch (auction.getAuctionType()) {
            case ENGLISH:
                return wantsToBid(currentPrice, auction.getProduct());
            case JAPANESE:
                return wantsToBid(currentPrice, auction.getProduct());
            case DUTCH:
                return wantsToBid(currentPrice, auction.getProduct());
            case SEALED_BID:
                return wantsToBid(currentPrice, auction.getProduct());
            default:
                return false;
        }
    }

    @Override
    public double getNextBid(long id, double currentPrice, Auction auction) {
        switch (auction.getAuctionType()) {
            case ENGLISH:
                return getNextBid(currentPrice, auction.getProduct());
            case SEALED_BID:
                return getNextBid(currentPrice, auction.getProduct());
            case ELIMINATION:
                return getNextBid(currentPrice, auction.getProduct());
            default:
                return 0;
        }
    }

    private boolean wantsToBid(double currentPrice, Product product) {
        Random r = new Random();
        return r.nextDouble() < (((1.0 - (currentPrice / product.getPrice())) * wantsToBidFactor) + wantingFactor);
    }

    private double getNextBid(double currentPrice, Product product) {
        Random r = new Random();
        return currentPrice + (r.nextDouble() * nextBidFactor * product.getPrice());
    }
}
