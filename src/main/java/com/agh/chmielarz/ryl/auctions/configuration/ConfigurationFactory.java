package com.agh.chmielarz.ryl.auctions.configuration;

import com.agh.chmielarz.ryl.auctions.configuration.pojos.AuctionPojo;
import com.agh.chmielarz.ryl.auctions.configuration.pojos.BuyerPojo;
import com.agh.chmielarz.ryl.auctions.configuration.pojos.ConfigurationPojo;
import com.agh.chmielarz.ryl.auctions.configuration.pojos.ProductPojo;
import com.agh.chmielarz.ryl.auctions.model.*;
import com.agh.chmielarz.ryl.auctions.model.auction_types.*;
import com.agh.chmielarz.ryl.auctions.model.buyer_strategies.DefaultBuyer;
import com.agh.chmielarz.ryl.auctions.model.buyer_strategies.DefaultStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.eventbus.EventBus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tomek on 08.09.2016.
 */
public class ConfigurationFactory {

    public static Configuration getConfiguration(final EventBus eventBus, final String filePath) throws IOException {
        Path path = Paths.get(filePath);
        byte[] jsonData = Files.readAllBytes(path);
        ObjectMapper objectMapper = new ObjectMapper();

        ConfigurationPojo configurationPojo = objectMapper.readValue(jsonData, ConfigurationPojo.class);

        long i = 0;
        List<Product> products = new LinkedList<Product>();
        for(ProductPojo productPojo : configurationPojo.getProducts()) {
            System.out.println(productPojo);
            products.add(new Product(i++, productPojo.getName(), productPojo.getPrice()));
        }

        i = 0;
        List<Auction> auctions = new LinkedList<Auction>();
        for(AuctionPojo auctionPojo : configurationPojo.getAuctions()){
            System.out.println(auctionPojo);
            for(Product product : products){
                switch (AuctionType.getByString(auctionPojo.getAuctionType().toLowerCase())){
                    case ENGLISH:
                        auctions.add(new EnglishAuction(eventBus, i++, product, auctionPojo.getStartPriceFactor()));
                        break;
                    case JAPANESE:
                        auctions.add(new JapaneseAuction(eventBus,i++,product, auctionPojo.getStartPriceFactor(), auctionPojo.getAuctionChangeFactor()));
                        break;
                    case DUTCH:
                        auctions.add(new DutchAuction(eventBus,i++,product, auctionPojo.getStartPriceFactor(), auctionPojo.getAuctionChangeFactor()));
                        break;
                    case SEALED_BID:
                        auctions.add(new SealBidAuction(eventBus, i++, product));
                        break;
                    case ELIMINATION:
                        auctions.add(new EliminationAuction(eventBus, i++, product));
                        break;
                    default:
                        System.out.println("NO AUCTION TYPE: " + auctionPojo.getAuctionType());
                }
            }
        }

        i = 0;
        List<Buyer> buyers = new LinkedList<Buyer>();
        for(BuyerPojo buyerPojo : configurationPojo.getBuyers()){
            System.out.println(buyerPojo);
            BuyerStrategy buyerStrategy = new DefaultStrategy(eventBus, i, buyerPojo.getWantingFactor(), buyerPojo.getWantsToBidFactor(), buyerPojo.getNextBidFactor());
            buyers.add(new DefaultBuyer(eventBus, i, products, buyerStrategy));
            i++;
        }

        return new Configuration(products, auctions, buyers);
    }
}