package com.agh.chmielarz.ryl.auctions.configuration.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Tomek on 12.09.2016.
 */
public class BuyerPojo {
    @JsonProperty("wants_to_bid_factor")
    private double wantsToBidFactor;
    @JsonProperty("next_bid_factor")
    private double nextBidFactor;
    @JsonProperty("wanting_factor")
    private double wantingFactor;

    public BuyerPojo() {
    }

    public double getWantsToBidFactor() {
        return wantsToBidFactor;
    }

    public double getNextBidFactor() {
        return nextBidFactor;
    }

    public double getWantingFactor() {
        return wantingFactor;
    }

    @Override
    public String toString() {
        return "BuyerPojo{" +
                "wantsToBidFactor=" + wantsToBidFactor +
                ", nextBidFactor=" + nextBidFactor +
                ", wantingFactor=" + wantingFactor +
                '}';
    }
}