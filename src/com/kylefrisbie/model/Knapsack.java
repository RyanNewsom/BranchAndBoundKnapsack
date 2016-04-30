package com.kylefrisbie.model;

import java.util.ArrayList;

/**
 * This is the instance of the Knapsack problem
 */
public class Knapsack {
    private ArrayList<Node> mNodes = new ArrayList<>();
    private ArrayList<Node> mPossibleNodesForExploration = new ArrayList<>();
    private ArrayList<Item> mItems = new ArrayList<>();
    private Node mBestNode;
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

    /**
     * Determines the optimal items to use for the Knapsack Problem
     * @return - the best option for the partcular instance
     */
    public String determineOptimalItemsForKnapsackProblem(){
        if(mBestNode == null){
            return "Error, no best node found";
        } else{
            return mBestNode.toString();
        }
    }
}
