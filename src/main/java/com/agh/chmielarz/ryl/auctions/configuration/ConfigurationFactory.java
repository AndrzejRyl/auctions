package com.agh.chmielarz.ryl.auctions.configuration;

import com.agh.chmielarz.ryl.auctions.configuration.pojos.ConfigurationPojo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by Tomek on 08.09.2016.
 */
public class ConfigurationFactory {

    public static Configuration getConfiguration(final String filePath) throws IOException {
        Path path = Paths.get(filePath);
        byte[] jsonData = Files.readAllBytes(path);
        ObjectMapper objectMapper = new ObjectMapper();

        ConfigurationPojo configurationPojo = objectMapper.readValue(jsonData, ConfigurationPojo.class);

        return null;
    }
}