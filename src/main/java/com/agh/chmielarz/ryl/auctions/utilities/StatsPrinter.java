package com.agh.chmielarz.ryl.auctions.utilities;

import com.agh.chmielarz.ryl.auctions.model.Auction;

import java.util.List;

/**
 * Created by FleenMobile on 2016-03-20.
 */
public class StatsPrinter {
    private List<Auction> mAuctions;

    public StatsPrinter(List<Auction> auctions) {
        mAuctions = auctions;
    }

    public void printStats() {
        System.out.println("=============================");
        System.out.println("===========  STATS  =========");
        System.out.println("=============================");
        for (Auction auction : mAuctions) {
            System.out.printf("[Auction no %d] %s\n", auction.getId(), auction.getProduct().getName());
            System.out.printf("Auction type: %s\nMarket value of the product: %.2f\n", auction.getAuctionType(), auction.getProduct().getPrice());

            System.out.printf("\nFINAL PRICE: %.2f\nWINNER OF THE AUCTION: %d\n", auction.getCurrentPrice(), auction.getWinner());
            System.out.println("=============================");
        }
    }
}
