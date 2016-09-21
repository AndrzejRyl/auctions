package com.agh.chmielarz.ryl.auctions;

import com.agh.chmielarz.ryl.auctions.configuration.Configuration;
import com.agh.chmielarz.ryl.auctions.configuration.ConfigurationFactory;

import java.io.IOException;

import static java.lang.System.exit;

/**
 * Created by Tomek on 21.09.2016.
 */
public class ConfigTest {
    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("Wrong number of parameters!");
            exit(-1);
        }

        String filepath = args[0];
        try {
            Configuration configuration = ConfigurationFactory.getConfiguration(filepath);
        } catch (IOException e) {
            e.printStackTrace();
            exit(-1);
        }
    }
}
