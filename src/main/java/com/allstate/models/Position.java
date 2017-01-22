package com.allstate.models;

import java.util.ArrayList;
import java.util.List;

public class Position {
    private double cash;
    private List<PositionDetail> positionDetails;

    public Position() {
        this.cash = 0;
        this.positionDetails = new ArrayList<>();
    }

    public double getCash() {
        return cash;
    }
    public void setCash(double cash) {
        this.cash = cash;
    }

    public List<PositionDetail> getPositionDetails() {
        return positionDetails;
    }
    public void addPositionDetail(String symbol, int shares, double amount){
        PositionDetail positionDetail = new PositionDetail(symbol, shares, amount);
        this.positionDetails.add(positionDetail);
    }

    private class PositionDetail{
        private String symbol;
        private int shares;
        private double amount;

        public PositionDetail() {
        }
        public PositionDetail(String symbol, int shares, double amount) {
            this.symbol = symbol;
            this.shares = shares;
            this.amount = amount;
        }

        public String getSymbol() {
            return symbol;
        }
        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public int getShares() {
            return shares;
        }
        public void setShares(int shares) {
            this.shares = shares;
        }

        public double getAmount() {
            return amount;
        }
        public void setAmount(double amount) {
            this.amount = amount;
        }
    }
}
