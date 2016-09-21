package com.agh.chmielarz.ryl.auctions.model.buyer_strategies;

import com.agh.chmielarz.ryl.auctions.model.Buyer;
import com.agh.chmielarz.ryl.auctions.model.BuyerStrategy;
import com.agh.chmielarz.ryl.auctions.model.Product;
import com.google.common.eventbus.EventBus;

import java.util.List;

/**
 * Created by FleenMobile on 2016-03-20.
 */
public class DefaultBuyer extends Buyer {
    public DefaultBuyer(EventBus eventBus, long id, List<Product> productsToBuy) {
        super(eventBus, id, productsToBuy, new DefaultStrategy(eventBus, id));
    }

    public DefaultBuyer(EventBus eventBus, long id, List<Product> productsToBuy, BuyerStrategy buyerStrategy) {
        super(eventBus, id, productsToBuy, buyerStrategy);
    }
}
