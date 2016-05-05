package com.kylefrisbie.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * This is the instance of the Knapsack problem
 */
public class Knapsack {
    private ArrayList<Node> mNodes = new ArrayList<>();
    protected ArrayList<Node> mPossibleNodesForExploration = new ArrayList<>();
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
        mNodes.add(new Node());
        mPossibleNodesForExploration.add(mNodes.get(0));
    }

    /**
     * Determines the optimal items to use for the Knapsack Problem
     * @return - the best option for the particular instance
     */
    public String determineOptimalItemsForKnapsackProblem(){
        while(mPossibleNodesForExploration.size() > 0) {
            seeWhoToExploreNext();
            createChildren(mBestNode);
            if (mPossibleNodesForExploration.size() == 0) {
                break;
            }
            pruneNodes();
        }
        if(mBestNode == null){
            return "Error, no best node found";
        } else{
            return mBestNode.toString();
        }
    }

    protected double calculateHighestPossibleProfit(Node node){
        //Check the nodes restrictions in terms of who it can/can't use
        //to use
        //return that value
        ArrayList<Item> itemsWeCanUse = mItems;
        ArrayList<Item> itemsNotAvailableForUse = node.getItemsNotAvailableForUse();

        itemsWeCanUse.removeAll(itemsNotAvailableForUse);
        //Look at the list of items and determine the highest profit using the items we are allowed
        for(int i = 0; i < itemsWeCanUse.size(); i++){
            Item item = itemsWeCanUse.get(i);
            if(node.getMaximumPossibleWeight() + item.getWeight() > mMaximumWeightForSack){
                //add fraction of that item
                int weightNumerator = mMaximumWeightForSack - node.getMaximumPossibleWeight();
                double weightRatio = (double)weightNumerator/(double)item.getWeight();

                item.setFractionOfItemUsed(weightRatio);
                node.addItemToUse(item);
                break;
            } else{
                //add the item
                item.setFractionOfItemUsed(1);
                node.addItemToUse(item);
            }
        }

        return node.getMaximumPossibleProfit();
    }

    protected void seeWhoToExploreNext(){
        Collections.sort(mPossibleNodesForExploration, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return (((Node) o1).getMaximumPossibleProfit() >= ((Node) o2).getMaximumPossibleProfit()) ? 1 : -1;
            }
        });
        mBestNode = mPossibleNodesForExploration.remove(0);
    }

    protected Item getNextItemToUse(Node node){
        ArrayList<Item> possibleItems = mItems;
        possibleItems.removeAll(node.getItemsUsed());
        possibleItems.removeAll(node.getItemsNotAvailableForUse());
        if (possibleItems.size() == 0) {
            return  null;
        }
        return possibleItems.get(0);
    }

    /**
     * Creates children for a node
     * @param node - the node to have children added to
     * @return - true if children were created, false if there's no more children to be created
     */
    protected void createChildren(Node node){
        //Based off the items we are using, find what's been used, create children based off what is next to be used
        Item nextToUse = getNextItemToUse(node);
        Node rightChild = node.clone();

        node.setMaximumPossibleProfit(calculateHighestPossibleProfit(node));

        if(nextToUse == null){
            return;
        }

        node.setLeftChild(node.clone());
        rightChild.addItemToUse(nextToUse);
        node.setRightChild(rightChild);

        mPossibleNodesForExploration.add(node.getLeftChild());
        mPossibleNodesForExploration.add(node.getRightChild());
    }

    protected void pruneNodes() {
        Node highestActualProfit = mPossibleNodesForExploration.get(0);
        for (int i = 1; i < mPossibleNodesForExploration.size(); i++) {
            if (highestActualProfit.getProfit() <
                    mPossibleNodesForExploration.get(i).getProfit()) {
                highestActualProfit = mPossibleNodesForExploration.get(i);
            }
        }
        ArrayList<Node> nodesNotPruned = (ArrayList<Node>) mPossibleNodesForExploration.clone();
        for (int i = 0; i < mPossibleNodesForExploration.size(); i++) {
            if (highestActualProfit.getProfit() >
                    mPossibleNodesForExploration.get(i).getMaximumPossibleProfit()) {
                nodesNotPruned.remove(mPossibleNodesForExploration.get(i));
            }

        }
        mPossibleNodesForExploration = nodesNotPruned;
    }
}
