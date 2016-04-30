package com.kylefrisbie.model;

import java.util.ArrayList;

/**
 * This is the instance of the Knapsack problem
 */
public class Knapsack {
    private ArrayList<Node> mNodes;
    private ArrayList<Node> mPossibleNodesForExploration;
    private ArrayList<Item> mItems;
    private int mMaximumWeightForSack;

    /**
     * Empty default constructor, not for use
     */
    private Knapsack(){}

    /**
     * Constructor
     * @param maxWeightForSack - the maximum weight the sack can hold before it rips
     * @param availableItems - the Item's available to be put in the sack
     */
    public Knapsack(int maxWeightForSack, ArrayList<Item> availableItems){
        mMaximumWeightForSack = maxWeightForSack;
        mItems = availableItems;
    }
}
