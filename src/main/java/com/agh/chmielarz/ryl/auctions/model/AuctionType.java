package com.agh.chmielarz.ryl.auctions.model;

/**
 * Created by FleenMobile on 2016-03-20.
 */
public enum AuctionType {
    ENGLISH("English"), JAPANESE("Japanese"), DUTCH("Dutch"), SEALED_BID("SealedBid"), ELIMINATION("Elimination");

    private String text;

    AuctionType(String text){
        this.text = text;
    }

    public String getText(){
        return this.text;
    }

    public static AuctionType fromString(String text){
        if (text != null) {
            for (AuctionType b : AuctionType.values()) {
                if (text.equalsIgnoreCase(b.text)) {
                    return b;
                }
            }
        }
        return null;
    }
}
