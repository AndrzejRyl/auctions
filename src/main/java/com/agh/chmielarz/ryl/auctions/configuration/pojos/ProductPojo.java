package com.agh.chmielarz.ryl.auctions.configuration.pojos;

/**
 * Created by Tomek on 12.09.2016.
 */
public class ProductPojo {
    private String name;
    private double price;

    public ProductPojo(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
