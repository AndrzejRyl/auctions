package com.agh.chmielarz.ryl.auctions;

import com.agh.chmielarz.ryl.auctions.events.AuctionFinishedEvent;
import com.agh.chmielarz.ryl.auctions.events.AuctionStartedEvent;
import com.agh.chmielarz.ryl.auctions.model.Auction;
import com.agh.chmielarz.ryl.auctions.model.Buyer;
import com.agh.chmielarz.ryl.auctions.model.Product;
import com.agh.chmielarz.ryl.auctions.model.auction_types.EnglishAuction;
import com.agh.chmielarz.ryl.auctions.model.buyer_strategies.DefaultBuyer;
import com.agh.chmielarz.ryl.auctions.utilities.CommandLineLogger;
import com.agh.chmielarz.ryl.auctions.utilities.StatsPrinter;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by FleenMobile on 2016-03-20.
 * <p>
 * Root of the project which will create all types of auctions
 * and test their capabilities using different types of buyers.
 */
public class AuctionHouse {

    private final static List<Product> mProducts = Arrays.asList(
            new Product(1, "Toothbrush", 12.30),
            new Product(2, "Audi R8", 120000),
            new Product(3, "Apache helicopter", 3500000),
            new Product(4, "Tom Cruise's house", 12000000)
    );


    public static void main(String[] args) {
        List<Auction> auctions = new ArrayList<>();
        List<Buyer> buyers = new ArrayList<>();
        EventBus eventBus;
        AuctionHouseEventListener listener;

        // Create EventBus and register for events
        eventBus = new EventBus("Auctions");
        listener = new AuctionHouseEventListener(eventBus);

        // Init logging
        new CommandLineLogger(eventBus);

        // Create auctions
        long i = 0;
        for (Product product : mProducts)
            auctions.add(new EnglishAuction(eventBus, i++, product));

        // Create buyers (let them all fight for all the products)
        for (i = 0; i < 10; i++)
            buyers.add(new DefaultBuyer(eventBus, i, mProducts));

        // Start auctions
        for (Auction auction : auctions)
            auction.startAuction();

        // Wait for all auctions to finish
        while (!listener.allAuctionsFinished()) ;

        StatsPrinter printer = new StatsPrinter(auctions);
        printer.printStats();
    }

    private static class AuctionHouseEventListener {
        private volatile int auctionsCount = 0;

        public AuctionHouseEventListener(EventBus eventBus) {
            eventBus.register(this);
        }

        public boolean allAuctionsFinished() {
            return auctionsCount == 0;
        }

        @Subscribe
        public void onAuctionStarted(AuctionStartedEvent event) {
            auctionsCount++;
        }

        @Subscribe
        public void onAuctionsFinished(AuctionFinishedEvent event) {
            auctionsCount--;
        }

        public int getAuctionsCount() {
            return auctionsCount;
        }
    }
}
