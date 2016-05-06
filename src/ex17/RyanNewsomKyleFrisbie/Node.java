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
    private int mActualWeight;

    protected Node() {

    }

    public Node(int actualProfit, int maxProfit) {

        mActualProfit = actualProfit;
        mMaximumPossibleProfit = maxProfit;
    }

    /**
     * Used to construct right child nodes
     * @param mItemsUsed
     * @param mItemsNotAvailableForUse
     * @param mMaximumPossibleProfit
     * @param mActualProfit
     * @param mActualWeight
     */
    public Node(ArrayList<Item> mItemsUsed, ArrayList<Item> mItemsNotAvailableForUse,
                double mMaximumPossibleProfit, int mActualProfit, int mActualWeight) {
        this.mItemsUsed = (ArrayList<Item>) mItemsUsed.clone();
        this.mItemsNotAvailableForUse = (ArrayList<Item>) mItemsNotAvailableForUse.clone();
        this.mMaximumPossibleProfit = mMaximumPossibleProfit;
    }

    /**
     * Used to construct left child nodes
     * @param mItemsNotAvailableForUse
     * @param mItemsUsed
     * @param mActualProfit
     * @param mActualWeight
     */
    public Node(ArrayList<Item> mItemsNotAvailableForUse, ArrayList<Item> mItemsUsed, int mActualProfit, int mActualWeight) {
        this.mItemsNotAvailableForUse = mItemsNotAvailableForUse;
        this.mItemsUsed = mItemsUsed;
        this.mActualProfit = mActualProfit;
        this.mActualWeight = mActualWeight;
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
        mActualWeight += toUse.getWeight();
        mActualProfit += toUse.getPrice();
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

    public int getActualWeight() {
        return mActualWeight;
    }

    public void setActualWeight(int mWeight) {
        this.mActualWeight = mWeight;
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

    public void addItemNotAvailableForUse(Item item) {
        this.mItemsNotAvailableForUse.add(item);
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
