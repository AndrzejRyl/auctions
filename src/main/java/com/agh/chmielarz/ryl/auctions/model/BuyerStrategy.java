package com.agh.chmielarz.ryl.auctions.model;

import com.agh.chmielarz.ryl.auctions.events.BuyerBidEvent;
import com.agh.chmielarz.ryl.auctions.events.BuyerInEvent;
import com.agh.chmielarz.ryl.auctions.events.BuyerOutEvent;
import com.google.common.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by FleenMobile on 2016-03-20.
 */
public abstract class BuyerStrategy {
    public EventBus mEventBus;
    // Auctions we're currently deciding on
    private List<Auction> mAuctions = new ArrayList<>();
    private long mBuyerId;

    List<AuctionType> normalFlowAuctions = Arrays.asList(AuctionType.ENGLISH, AuctionType.SEALED_BID, AuctionType.ELIMINATION);

    public BuyerStrategy(EventBus eventBus, long buyerId) {
        mEventBus = eventBus;
        mBuyerId = buyerId;
    }

    public void addAuction(Auction auction) {
        mAuctions.add(auction);

        // In most cases we get into the auctions straight away
        if (normalFlowAuctions.contains(auction.getAuctionType()))
            mEventBus.post(new BuyerInEvent(mBuyerId, auction.getId()));
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
     * Decide what to do next and send appropriate events
     * @param auctionId    Id of the auction connected to this event
     * @param currentPrice Current price of the product connected to this event
     */
    public void onAuctionPriceChange(long auctionId, double currentPrice) {
        // First check if we're still taking part in this auction
        if (!takingPartInAuction(auctionId)) return;

        Auction auction = mAuctions.stream().filter(value -> value.getId() == auctionId).findFirst().get();
        double nextBid = getNextBid(auctionId, currentPrice);

        // Bid or signal you want in if according to strategy you should
        if (normalFlowAuctions.contains(auction.getAuctionType())) {
            if (wantsToBid(auctionId, currentPrice, auction))
                mEventBus.post(new BuyerBidEvent(mBuyerId, auctionId, nextBid));
            else {
                mEventBus.post(new BuyerOutEvent(mBuyerId, auctionId));
                mAuctions.remove(auction);
            }
        } else {
            if (wantsToBid(auctionId, currentPrice, auction))
                mEventBus.post(new BuyerInEvent(mBuyerId, auctionId));
            if (auction.getAuctionType().equals(AuctionType.JAPANESE))
                mAuctions.remove(auction);
        }
    }

    /**
     * Decides whether to bid or not based on a strategy
     *
     * @param id           Id of the auction
     * @param currentPrice Current price of the product being sold on this auction
     * @return True if based on the strategy buyer should bid and false otherwise
     */
    public boolean wantsToBid(long id, double currentPrice, Auction auction) {
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

}
