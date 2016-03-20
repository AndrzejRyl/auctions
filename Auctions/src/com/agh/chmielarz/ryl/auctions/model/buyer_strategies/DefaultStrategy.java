package com.agh.chmielarz.ryl.auctions.model.buyer_strategies;

import com.agh.chmielarz.ryl.auctions.model.BuyerStrategy;
import com.google.common.eventbus.EventBus;

import java.util.Random;

/**
 * Created by FleenMobile on 2016-03-20.
 */
public class DefaultStrategy extends BuyerStrategy {

    public DefaultStrategy(EventBus eventBus, long buyerId) {
        super(eventBus, buyerId);
        // No additional init needed but
        // this is a default strategy. Normally we would probably keep track of
        // some historical data like other buyers' bids etc.
    }

    @Override
    public boolean wantsToBid(long id, double currentPrice) {
        Random r = new Random();
        return currentPrice > r.nextInt() * 10.0 + 5000000;
    }

    @Override
    public double getNextBid(long id, double currentPrice) {
        Random r = new Random();
        return currentPrice * (1.2 + r.nextDouble());
    }
}
