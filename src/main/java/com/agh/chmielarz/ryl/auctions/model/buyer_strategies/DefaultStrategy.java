package com.agh.chmielarz.ryl.auctions.model.buyer_strategies;

import com.agh.chmielarz.ryl.auctions.model.Auction;
import com.agh.chmielarz.ryl.auctions.model.AuctionType;
import com.agh.chmielarz.ryl.auctions.model.BuyerStrategy;
import com.agh.chmielarz.ryl.auctions.model.Product;
import com.google.common.eventbus.EventBus;

import java.util.Random;

/**
 * Created by FleenMobile on 2016-03-20.
 */
public class DefaultStrategy extends BuyerStrategy {

    private static double MINIMUM_DECISION_RATE = 0.01;
    private static double AVERAGE_DECISION_RATE = 1.0;

    public DefaultStrategy(EventBus eventBus, long buyerId) {
        super(eventBus, buyerId);
        // No additional init needed but
        // this is a default strategy. Normally we would probably keep track of
        // some historical data like other buyers' bids etc.
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
            default:
                return false;
        }
    }

    @Override
    public double getNextBid(long id, double currentPrice) {
        Random r = new Random();
        return currentPrice * (1.0 + r.nextDouble());
    }

    private boolean wantsToBid(double currentPrice, Product product){
        Random r = new Random();
        if (currentPrice / product.getPrice() < AVERAGE_DECISION_RATE){
            System.out.println("Dupa: " + (AVERAGE_DECISION_RATE - (currentPrice / product.getPrice())));
            return r.nextDouble() < AVERAGE_DECISION_RATE - (currentPrice / product.getPrice());}
        else
            return r.nextDouble() < MINIMUM_DECISION_RATE;
    }
}
