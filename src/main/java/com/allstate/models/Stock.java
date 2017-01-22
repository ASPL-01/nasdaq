package com.allstate.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stock {
    private String name;
    private String symbol;
    private double lastPrice;

    public Stock() {
    }

    public Stock(String name, String symbol, double lastPrice) {
        this.name = name;
        this.symbol = symbol;
        this.lastPrice = lastPrice;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("Symbol")
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @JsonProperty("LastPrice")
    public double getLastPrice() {
        return lastPrice;
    }
    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }
}
