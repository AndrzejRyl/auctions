package com.agh.chmielarz.ryl.auctions.configuration;

import com.agh.chmielarz.ryl.auctions.configuration.pojos.AuctionHousePojo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Tomek on 08.09.2016.
 */
public class ConfigurationFactory {

    public static Configuration getConfiguration(final String filePath) throws IOException {
        Path path = Paths.get(filePath);
        byte[] jsonData = Files.readAllBytes(path);
        ObjectMapper objectMapper = new ObjectMapper();

        AuctionHousePojo auctionHousePojo = objectMapper.readValue(jsonData, AuctionHousePojo.class);

        Configuration configuration;
        return null;
    }
}