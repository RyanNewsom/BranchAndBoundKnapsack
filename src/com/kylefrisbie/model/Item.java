package com.kylefrisbie.model;

/**
 * An Item is an available Knapsack item for use
 */
public class Item {
    private int mItemId;
    private int mPrice;
    private int mWeight;
    private double mpricePerWeight;

    /**
     * Empty default constructor, not for use
     */
    private Item(){}

    /**
     * Constructor
     * @param itemId - the id for the item
     * @param price - the price for the item
     * @param weight - the weight for the item
     */
    public Item(int itemId, int price, int weight){
        mItemId = itemId;
        mPrice = price;
        mWeight = weight;
        mpricePerWeight = price/weight;
    }

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

    public double getPricePerWeight() {
        return mpricePerWeight;
    }

    public void setPricePerWeight(double mpricePerWeight) {
        this.mpricePerWeight = mpricePerWeight;
    }
}
