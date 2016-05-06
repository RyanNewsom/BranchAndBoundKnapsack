package ex17.RyanNewsomKyleFrisbie;

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
    private int mActualProfit;
    private int mMaximumPossibleWeight;

    protected Node() {

    }

    public Node(int actualProfit, int maxProfit) {

        mActualProfit = actualProfit;
        mMaximumPossibleProfit = maxProfit;
    }

    /**
     *
     * @param mItemsUsed
     * @param mItemsNotAvailableForUse
     * @param mLeftChild
     * @param mRightChild
     * @param mMaximumPossibleProfit
     * @param mActualProfit
     * @param mMaximumPossibleWeight
     */
    public Node(ArrayList<Item> mItemsUsed, ArrayList<Item> mItemsNotAvailableForUse, Node mLeftChild, Node mRightChild,
                double mMaximumPossibleProfit, int mActualProfit, int mMaximumPossibleWeight) {
        this.mItemsUsed = (ArrayList<Item>) mItemsUsed.clone();
        this.mItemsNotAvailableForUse = (ArrayList<Item>) mItemsNotAvailableForUse.clone();
        this.mLeftChild = mLeftChild;
        this.mRightChild = mRightChild;
        this.mMaximumPossibleProfit = mMaximumPossibleProfit;
        this.mActualProfit = mActualProfit;
        this.mMaximumPossibleWeight = mMaximumPossibleWeight;
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

    public int getActualProfit() {
        return mActualProfit;
    }

    /**
     * Set's the profit for the node
     * @param mProfit - this is the actual profit for the node
     */
    public void setActualProfit(int mProfit) {
        this.mActualProfit = mProfit;
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
        return "The highest possible profit is: " + mActualProfit + "Using these items: " + mItemsUsed.toString();
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
