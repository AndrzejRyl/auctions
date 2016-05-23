package com.agh.chmielarz.ryl.auctions.model.buyer_strategies;

import com.agh.chmielarz.ryl.auctions.model.AuctionType;
import com.agh.chmielarz.ryl.auctions.model.BuyerStrategy;
import com.agh.chmielarz.ryl.auctions.model.Product;
import com.google.common.eventbus.EventBus;

import java.util.Random;

/**
 * Created by FleenMobile on 2016-03-20.
 */
public class DefaultStrategy extends BuyerStrategy {

    public static double MINIMUM_DECISION_RATE = 0.01;
    public static double AVERAGE_DECISION_RATE = 1.0;

    public DefaultStrategy(EventBus eventBus, long buyerId) {
        super(eventBus, buyerId);
        // No additional init needed but
        // this is a default strategy. Normally we would probably keep track of
        // some historical data like other buyers' bids etc.
    }

    @Override
    public boolean wantsToBid(long id, double currentPrice, Product product, AuctionType auctionType) {
        Random r = new Random();

        switch (auctionType) {
            case ENGLISH:
                if (currentPrice / product.getPrice() < AVERAGE_DECISION_RATE)
                    return r.nextDouble() < AVERAGE_DECISION_RATE - (currentPrice / product.getPrice());
                else
                    return r.nextDouble() < MINIMUM_DECISION_RATE;
            case JAPANESE:
                if (currentPrice / product.getPrice() < AVERAGE_DECISION_RATE)
                    return r.nextDouble() < AVERAGE_DECISION_RATE - (currentPrice / product.getPrice());
                else
                    return r.nextDouble() < MINIMUM_DECISION_RATE;
            default:
                return false;
        }
    }

    @Override
    public double getNextBid(long id, double currentPrice) {
        Random r = new Random();
        return currentPrice * (1.0 + r.nextDouble());
    }
}
