package com.agh.chmielarz.ryl.auctions.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by FleenMobile on 2016-03-20.
 */
public enum AuctionType {
    ENGLISH("english"), JAPANESE("japanese"), DUTCH("dutch"), SEALED_BID("sealed bid"), ELIMINATION("elimination");

    private String text;
    private static Map<String, AuctionType> auctionTypeMap = new HashMap<>();

    static {
        for (AuctionType auctionType : AuctionType.values())
            auctionTypeMap.put(auctionType.getText(), auctionType);
    }

    AuctionType(String text) {
        this.text = text;
    }

    private String getText() {
        return this.text;
    }

    public static AuctionType getByString(String key) {
        return auctionTypeMap.get(key);
    }
}
