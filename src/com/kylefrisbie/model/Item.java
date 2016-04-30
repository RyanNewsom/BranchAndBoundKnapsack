package com.kylefrisbie.model;

/**
 * An Item is an available Knapsack item for use
 */
public class Item {
    private int mPrice;
    private int mWeight;
    private int mpricePerWeight;

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public int getWeight() {
        return mWeight;
    }

    public void setWeight(int mWeight) {
        this.mWeight = mWeight;
    }

    public int getpricePerWeight() {
        return mpricePerWeight;
    }

    public void setpricePerWeight(int mpricePerWeight) {
        this.mpricePerWeight = mpricePerWeight;
    }
}
