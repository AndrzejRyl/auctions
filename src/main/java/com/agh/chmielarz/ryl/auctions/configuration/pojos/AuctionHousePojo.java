package com.agh.chmielarz.ryl.auctions.configuration.pojos;

/**
 * Created by Tomek on 12.09.2016.
 */
public class AuctionHousePojo {
    private ProductPojo[] productPojos;
    private AuctionPojo[] auctionPojos;
    private BuyerPojo[] buyerPojos;

    public ProductPojo[] getProductPojos() {
        return productPojos;
    }

    public void setProductPojos(ProductPojo[] productPojos) {
        this.productPojos = productPojos;
    }

    public AuctionPojo[] getAuctionPojos() {
        return auctionPojos;
    }

    public void setAuctionPojos(AuctionPojo[] auctionPojos) {
        this.auctionPojos = auctionPojos;
    }

    public BuyerPojo[] getBuyerPojos() {
        return buyerPojos;
    }

    public void setBuyerPojos(BuyerPojo[] buyerPojos) {
        this.buyerPojos = buyerPojos;
    }
}