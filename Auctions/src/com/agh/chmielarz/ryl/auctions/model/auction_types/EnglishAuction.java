package com.agh.chmielarz.ryl.auctions.model.auction_types;

import com.agh.chmielarz.ryl.auctions.events.BuyerBidEvent;
import com.agh.chmielarz.ryl.auctions.events.BuyerInEvent;
import com.agh.chmielarz.ryl.auctions.events.BuyerOutEvent;
import com.agh.chmielarz.ryl.auctions.model.Auction;
import com.agh.chmielarz.ryl.auctions.model.AuctionType;
import com.agh.chmielarz.ryl.auctions.model.Product;
import com.google.common.eventbus.EventBus;

/**
 * Created by FleenMobile on 2016-03-20.
 */
public class EnglishAuction extends Auction {

    public EnglishAuction(EventBus eventBus, long id, Product product) {
        super(eventBus, id, product);
        super.setAuctionType(AuctionType.ENGLISH);
    }

    @Override
    public void startAuction() {
        super.startAuction();
        // TODO
    }

    @Override
    public void onBuyerBid(BuyerBidEvent event) {
        super.onBuyerBid(event);

        // TODO
    }

    @Override
    public void onBuyerIn(BuyerInEvent event) {
        super.onBuyerIn(event);

        // TODO
    }

    @Override
    public void onBuyerOut(BuyerOutEvent event) {
        super.onBuyerOut(event);

        // TODO
    }
}
