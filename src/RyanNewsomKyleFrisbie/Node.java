package RyanNewsomKyleFrisbie;

import java.util.ArrayList;

/**
 * A node is a possible choice for the 0-1 knapsack problem
 */
public class Node implements Cloneable {
    private ArrayList<Item> mItemsUsed = new ArrayList<>();
    private ArrayList<Item> mItemsNotAvailableForUse = new ArrayList<>();
    private Node mLeftChild;
    private Node mRightChild;
    private double mMaximumPossibleProfit;
    private int mProfit;
    private int mMaximumPossibleWeight;

    protected Node() {

    }

    protected Node(int profit, int maxProfit) {
        mProfit = profit;
        mMaximumPossibleProfit = maxProfit;
    }

    public double getMaximumPossibleProfit() {
        return mMaximumPossibleProfit;
    }

    public void setMaximumPossibleProfit(double mMaximumPossibleProfit) {
        this.mMaximumPossibleProfit = mMaximumPossibleProfit;
    }

    public ArrayList<Item> getItemsUsed() {
        return mItemsUsed;
    }

    public void addItemToUse(Item toUse){
        mItemsUsed.add(toUse);
        mMaximumPossibleWeight += toUse.getWeight();
        mMaximumPossibleProfit += toUse.getPrice();
    }

    public int getProfit() {
        return mProfit;
    }

    public void setProfit(int mProfit) {
        this.mProfit = mProfit;
    }

    public int getMaximumPossibleWeight() {
        return mMaximumPossibleWeight;
    }

    public void setWeight(int mWeight) {
        this.mMaximumPossibleWeight = mWeight;
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

    public ArrayList<Item> getItemsNotAvailableForUse() {
        return mItemsNotAvailableForUse;
    }

    public void setItemsNotAvailableForUse(ArrayList<Item> mItemsNotAvailableForUse) {
        this.mItemsNotAvailableForUse = mItemsNotAvailableForUse;
    }

    @Override
    public String toString(){
        return "The highest possible profit is: " + mProfit + "Using these items: " + mItemsUsed.toString();
    }

    @Override
    public Node clone(){
        try {
            return (Node) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
