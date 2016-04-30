package com.kylefrisbie.model;

import java.util.ArrayList;

/**
 * A node is a possible choice for the 0-1 knapsack problem
 */
public class Node {
    private int mMaximumPossibleProfit;
    private int mMaximumPossibleWeight;
    private ArrayList<Integer> mItemsUsed;
    private int mProfit;
    private Node mLeftChild;
    private Node mRightChild;

    public int getMaximumPossibleProfit() {
        return mMaximumPossibleProfit;
    }

    public void setMaximumPossibleProfit(int mMaximumPossibleProfit) {
        this.mMaximumPossibleProfit = mMaximumPossibleProfit;
    }

    public int getMaximumPossibleWeight() {
        return mMaximumPossibleWeight;
    }

    public void setMaximumPossibleWeight(int mMaximumPossibleWeight) {
        this.mMaximumPossibleWeight = mMaximumPossibleWeight;
    }

    public ArrayList<Integer> getItemsUsed() {
        return mItemsUsed;
    }

    public void setItemsUsed(ArrayList<Integer> mItemsUsed) {
        this.mItemsUsed = mItemsUsed;
    }

    public int getProfit() {
        return mProfit;
    }

    public void setProfit(int mProfit) {
        this.mProfit = mProfit;
    }

    public Node getLeftChild() {
        return mLeftChild;
    }

    public void setLeftChild(Node mLeftChild) {
        this.mLeftChild = mLeftChild;
    }

    public Node getRightChild() {
        return mRightChild;
    }

    public void setRightChild(Node mRightChild) {
        this.mRightChild = mRightChild;
    }

    @Override
    public String toString(){
        return "The highest possible profit is: " + mProfit + "Using these items: " + mItemsUsed.toString();
    }
}
