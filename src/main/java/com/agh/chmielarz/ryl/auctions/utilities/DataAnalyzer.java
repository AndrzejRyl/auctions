package com.agh.chmielarz.ryl.auctions.utilities;

import com.agh.chmielarz.ryl.auctions.events.*;
import com.agh.chmielarz.ryl.auctions.model.Auction;
import com.agh.chmielarz.ryl.auctions.model.AuctionType;
import com.agh.chmielarz.ryl.auctions.model.Product;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.common.math.DoubleMath;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by FleenMobile on 2016-06-16.
 *
 * This is a class which listens to all events and builds charts
 * based on provided data
 *
 */
public class DataAnalyzer {

    private static final String DATA_PATH = "";
    private static final String
        ENGLISH_AUCTION_FILE_NAME = "ENGLISH.txt",
        DUTCH_AUCTION_FILE_NAME = "DUTCH.txt",
        ELIMINATION_AUCTION_FILE_NAME = "ELIMINATION.txt",
        JAPANESE_AUCTION_FILE_NAME = "JAPANESE.txt",
        SEAL_BID_AUCTION_FILE_NAME = "SEAL_BID.txt";

    private Map<Long, List<Double>>
        englishAuctionData = new HashMap<>(),
        dutchAuctionData = new HashMap<>(),
        eliminationAuctionData = new HashMap<>(),
        japaneseAuctionData = new HashMap<>(),
        sealBidAuctionData = new HashMap<>();

    private final EventBus mEventBus;

    private List<Product> mProducts;
    private List<Auction> mAuctions;

    public DataAnalyzer(EventBus eventBus, List<Product> products, List<Auction> auctions) {
        mEventBus = eventBus;
        mEventBus.register(this);
        mProducts = products;
        mAuctions = auctions;

    }

    @Subscribe
    public void onAnalyze(AuctionPriceChangeEvent event) {
        AuctionType type = event.getAuctionType();
        double newPrice = event.getCurrentPrice();
        long productId = getProductId(event.getId());

        // Build up a structure that is mapping productID
        // to list of prices along the way
        switch (type) {
            case ENGLISH:
                if (!englishAuctionData.containsKey(productId))
                    englishAuctionData.put(productId, new ArrayList<>());
                englishAuctionData.get(productId).add(newPrice);
                break;
            case DUTCH:
                if (!dutchAuctionData.containsKey(productId))
                    dutchAuctionData.put(productId, new ArrayList<>());
                dutchAuctionData.get(productId).add(newPrice);
                break;
            case ELIMINATION:
                if (!eliminationAuctionData.containsKey(productId))
                    eliminationAuctionData.put(productId, new ArrayList<>());
                eliminationAuctionData.get(productId).add(newPrice);
                break;
            case SEALED_BID:
                if (!sealBidAuctionData.containsKey(productId))
                    sealBidAuctionData.put(productId, new ArrayList<>());
                sealBidAuctionData.get(productId).add(newPrice);
                break;
            case JAPANESE:
                if (!japaneseAuctionData.containsKey(productId))
                    japaneseAuctionData.put(productId, new ArrayList<>());
                japaneseAuctionData.get(productId).add(newPrice);
                break;
        }
    }

    private long getProductId(long id) {
        for (Auction auction : mAuctions) {
            if (id == auction.getId())
                return auction.getProduct().getId();
        }
        return -1;
    }

    public void dumpData() throws IOException {
        appendDataToFile(englishAuctionData, ENGLISH_AUCTION_FILE_NAME);
        appendDataToFile(dutchAuctionData, DUTCH_AUCTION_FILE_NAME);
        appendDataToFile(eliminationAuctionData, ELIMINATION_AUCTION_FILE_NAME);
        appendDataToFile(sealBidAuctionData, SEAL_BID_AUCTION_FILE_NAME);
        appendDataToFile(japaneseAuctionData, JAPANESE_AUCTION_FILE_NAME);
    }

    private void appendDataToFile(Map<Long, List<Double>> data, String fileName) throws IOException {
        Map<Long, FileWriter> files = new HashMap<>();

        // Open files for every product
        for (Product product : mProducts) {
            files.put(product.getId(), new FileWriter(new File(product.getId() + "_" + fileName), true));
        }

        // Append data to those files
        StringBuilder sb;
        for (Map.Entry<Long, List<Double>> entry : data.entrySet()) {
            sb = new StringBuilder("");
            sb.append(entry.getValue().stream().map(String::valueOf).collect(Collectors.joining(",")));
            sb.append("\n");
            files.get(entry.getKey()).append(sb.toString());
        }

        // Close files
        for (FileWriter fileWriter : files.values())
            fileWriter.close();
    }

}
