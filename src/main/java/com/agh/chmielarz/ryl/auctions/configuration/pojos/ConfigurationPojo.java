package com.agh.chmielarz.ryl.auctions.configuration.pojos;

/**
 * Created by Tomek on 16.06.2016.
 */
public class ConfigurationPojo {
    private ProductPojo[] products;
    private AuctionPojo[] auctions;
    private BuyerPojo[] buyers;

    public ConfigurationPojo() {
    }

    public ProductPojo[] getProducts() {
        return products;
    }

    public AuctionPojo[] getAuctions() {
        return auctions;
    }

    public BuyerPojo[] getBuyers() {
        return buyers;
    }
}
