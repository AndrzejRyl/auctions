package com.agh.chmielarz.ryl.auctions.utilities;

import com.agh.chmielarz.ryl.auctions.events.*;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.EventListener;

/**
 * Created by FleenMobile on 2016-03-20.
 * <p>
 * This is a logger which will log all events going through EventBus
 */
public class CommandLineLogger {

    private final EventBus mEventBus;

    public CommandLineLogger(EventBus eventBus) {
        mEventBus = eventBus;
        mEventBus.register(this);
    }

    @Subscribe
    public void onLog(AuctionFinishedEvent event) {
        System.out.println(event);
    }

    @Subscribe
    public void onLog(AuctionPriceChangeEvent event) {
        System.out.println(event);
    }

    @Subscribe
    public void onLog(AuctionStartedEvent event) {
        System.out.println(event);
    }

    @Subscribe
    public void onLog(BuyerBidEvent event) {
        System.out.println(event);
    }

    @Subscribe
    public void onLog(BuyerInEvent event) { System.out.println(event); }

    @Subscribe
    public void onLog(BuyerOutEvent event) {
        System.out.println(event);
    }

    @Subscribe
    public void onLog(IncreasePriceEvent event){
        System.out.println(event);
    }
}
