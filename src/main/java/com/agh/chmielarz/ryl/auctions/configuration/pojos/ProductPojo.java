package com.agh.chmielarz.ryl.auctions.configuration.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Tomek on 12.09.2016.
 */
public class ProductPojo {
    @JsonProperty("name")
    private String name;
    @JsonProperty("price")
    private double price;

    public ProductPojo() {
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ProductPojo{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
