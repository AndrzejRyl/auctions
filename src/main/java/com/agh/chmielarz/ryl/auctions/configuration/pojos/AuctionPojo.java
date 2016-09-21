package com.agh.chmielarz.ryl.auctions.configuration.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Tomek on 12.09.2016.
 */
public class AuctionPojo {
    @JsonProperty("auction_type")
    private String auctionType;
    @JsonProperty("auction_change_factor")
    private double auctionChangeFactor;
    @JsonProperty("start_price_factor")
    private double startPriceFactor;

    public AuctionPojo() {
    }

    public String getAuctionType() {
        return auctionType;
    }

    public double getAuctionChangeFactor() {
        return auctionChangeFactor;
    }

    public double getStartPriceFactor() {
        return startPriceFactor;
    }

    @Override
    public String toString() {
        return "AuctionPojo{" +
                "auctionType='" + auctionType + '\'' +
                ", auctionChangeFactor=" + auctionChangeFactor +
                ", startPriceFactor=" + startPriceFactor +
                '}';
    }
}