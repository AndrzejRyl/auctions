package com.agh.chmielarz.ryl.auctions.model;

/**
 * Created by FleenMobile on 2016-03-20.
 */
public class Product {
    private long mId;
    private String mName;
    private double mPrice;

    public Product(long id, String name, double price) {
        mId = id;
        mName = name;
        mPrice = price;
    }

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public double getPrice() {
        return mPrice;
    }
}
