package com.agh.chmielarz.ryl.auctions;

import com.agh.chmielarz.ryl.auctions.configuration.Configuration;
import com.agh.chmielarz.ryl.auctions.configuration.ConfigurationFactory;
import com.agh.chmielarz.ryl.auctions.events.AuctionFinishedEvent;
import com.agh.chmielarz.ryl.auctions.events.AuctionStartedEvent;
import com.agh.chmielarz.ryl.auctions.model.Auction;
import com.agh.chmielarz.ryl.auctions.model.Buyer;
import com.agh.chmielarz.ryl.auctions.model.Product;
import com.agh.chmielarz.ryl.auctions.model.auction_types.*;
import com.agh.chmielarz.ryl.auctions.model.buyer_strategies.DefaultBuyer;
import com.agh.chmielarz.ryl.auctions.utilities.CommandLineLogger;
import com.agh.chmielarz.ryl.auctions.utilities.DataAnalyzer;
import com.agh.chmielarz.ryl.auctions.utilities.StatsPrinter;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.exit;

/**
 * Created by FleenMobile on 2016-03-20.
 * <p>
 * Root of the project which will create all types of auctions
 * and test their capabilities using different types of buyers.
 */
public class AuctionHouse {

    private static DataAnalyzer dataAnalyzer;

    public static void main(String[] args) {
        // Parameters validation
        if (args.length != 1) {
            System.out.println("Wrong number of parameters, type: [file name].");
            exit(-1);
        }
        String fileName = args[0];
        if (!(new File(fileName).exists())) {
            System.out.println("File: " + fileName + " does not exist.");
            exit(-1);
        }
        // Create EventBus and register for events
        EventBus eventBus = new EventBus("Auctions");
        // Create AuctionHouseEventListener for wainting purposes
        AuctionHouseEventListener listener = new AuctionHouseEventListener(eventBus);
        // Init logging
        CommandLineLogger commandLineLogger = new CommandLineLogger(eventBus);

        try {
            // Get configuration from json file
            Configuration configuration = ConfigurationFactory.getConfiguration(eventBus, fileName);
            // Get auction house objects
            List<Product> products = configuration.getProducts();
            List<Auction> auctions = configuration.getAuctions();
            List<Buyer> buyers = configuration.getBuyers();

            // Init data analyzer
            dataAnalyzer = new DataAnalyzer(eventBus, products, auctions);
            //System.out.println("Products: " + products.size());
            //System.out.println("Auctions: " + auctions.size());
            //System.out.println("Buyers: " + buyers.size());
            // Start all auctions
            if (products.size() <= 0) {
                System.out.println("Not enough products in file, please provide at least one product.");
                exit(-1);
            } else if (auctions.size() <= 0) {
                System.out.println("Not enough auctions in file, please provide at least one correct auction.");
                exit(-1);
            } else if (buyers.size() <= 1) {
                System.out.println("Not enough buyers in file, please provide at least one buyer.");
                exit(-1);
            }
            auctions.forEach(Auction::startAuction);
            // Wait for all auctions to finish
            while (!listener.allAuctionsFinished()) ;
            // Print all statistics
            StatsPrinter printer = new StatsPrinter(auctions);
            printer.printStats();
        } catch (IOException e) {
            System.out.println("Could not read configuration file.");
            exit(-1);
        }
    }

    private static class AuctionHouseEventListener {
        private volatile int auctionsCount = 0;

        public AuctionHouseEventListener(EventBus eventBus) {
            eventBus.register(this);
        }

        public boolean allAuctionsFinished() {
            if (auctionsCount == 0) {
                try {
                    dataAnalyzer.dumpData();
                    return true;
                } catch (IOException e) {
                    System.err.println("PROBLEM WITH DUMPING DATA!!!!" + e.getMessage());
                    return true;
                }
            }

            return false;
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
