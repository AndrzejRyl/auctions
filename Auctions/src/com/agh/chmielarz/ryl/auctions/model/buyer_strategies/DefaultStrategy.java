package com.agh.chmielarz.ryl.auctions.model.buyer_strategies;

import com.agh.chmielarz.ryl.auctions.model.BuyerStrategy;
import com.google.common.eventbus.EventBus;

/**
 * Created by FleenMobile on 2016-03-20.
 */
public class DefaultStrategy extends BuyerStrategy {

    public DefaultStrategy(EventBus eventBus) {
        super(eventBus);

        // TODO
    }

    @Override
    public boolean wantsToBid(long id, double currentPrice) {

        // TODO
        return super.wantsToBid(id, currentPrice);
    }

    @Override
    public double getNextBid(long id, double currentPrice) {
        // TODO
        return super.getNextBid(id, currentPrice);
    }

    @Override
    public void onAuctionPriceChange(long auctionId, double currentPrice) {
        super.onAuctionPriceChange(auctionId, currentPrice);

        // TODO
    }
}
