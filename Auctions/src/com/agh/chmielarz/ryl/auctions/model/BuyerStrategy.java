package com.agh.chmielarz.ryl.auctions.model;

import com.google.common.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FleenMobile on 2016-03-20.
 */
public abstract class BuyerStrategy {
    public EventBus mEventBus;
    // Auctions we're currently deciding on
    private List<Auction> mAuctions = new ArrayList<>();

    public BuyerStrategy(EventBus eventBus) {
        mEventBus = eventBus;
    }

    public void addAuction(Auction auction) {
        mAuctions.add(auction);
    }

    public void removeAuction(long id) {
        mAuctions.removeIf(auction -> auction.getId() == id);
    }

    /**
     * Returns whether given buyer is taking part in the given auction
     *
     * @param id Id of the auction
     * @return True if buyer takes part in auction and false otherwise
     */
    public boolean takingPartInAuction(long id) {
        final boolean[] result = {false};

        // Check if we are taking part in this auction (result has to an array because of lambdas restrictions)
        mAuctions.forEach(auction -> result[0] = (result[0]) ? result[0] : auction.getId() == id);

        return result[0];
    }

    /**
     * Decides whether to bid or not based on a strategy
     *
     * @param id           Id of the auction
     * @param currentPrice Current price of the product being sold on this auction
     * @return True if based on the strategy buyer should bid and false otherwise
     */
    public boolean wantsToBid(long id, double currentPrice) {
        throw new RuntimeException("This method needs to be implemented!");
    }

    /**
     * Returns buyers bid based on a strategy
     *
     * @param id           Id of the auction
     * @param currentPrice Current price of the product being sold on this auction
     * @return Price that the given buyer should bid
     */
    public double getNextBid(long id, double currentPrice) {
        throw new RuntimeException("This method needs to be implemented!");
    }

    /**
     * Decide what to do next and send appropriate events
     *
     * @param auctionId    Id of the auction connected to this event
     * @param currentPrice Current price of the product connected to this event
     */
    public void onAuctionPriceChange(long auctionId, double currentPrice) {
        throw new RuntimeException("This method needs to be implemented!");
    }

}
