package com.agh.chmielarz.ryl.auctions.configuration;

import com.agh.chmielarz.ryl.auctions.model.Auction;
import com.agh.chmielarz.ryl.auctions.model.Buyer;
import com.agh.chmielarz.ryl.auctions.model.Product;

import java.util.List;

/**
 * Created by Tomek on 16.06.2016.
 */
public class Configuration {

    private final List<Product> products;
    private final List<Auction> auctions;
    private final List<Buyer> buyers;

    public Configuration(final List<Product> products, final List<Auction> auctions, final List<Buyer> buyers) {
        this.products = products;
        this.auctions = auctions;
        this.buyers = buyers;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Auction> getAuctions() {
        return auctions;
    }

    public List<Buyer> getBuyers() {
        return buyers;
    }
}
