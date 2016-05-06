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
        this.mActualProfit = mActualProfit;
        this.mActualWeight = mActualWeight;
    }

    /**
     * Used to construct left child nodes
     * @param mItemsUsed
     * @param mItemsNotAvailableForUse
     * @param mActualProfit
     * @param mActualWeight
     */
    public Node(ArrayList<Item> mItemsUsed, ArrayList<Item> mItemsNotAvailableForUse, int mActualProfit, int mActualWeight) {
        this.mItemsUsed =  (ArrayList<Item>) mItemsUsed.clone();
        this.mItemsNotAvailableForUse = (ArrayList<Item>) mItemsNotAvailableForUse.clone();
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

    public int getActualWeight() {
        return mActualWeight;
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
        StringBuilder itemList = new StringBuilder();
        for (int i = 0; i < mItemsUsed.size(); i++) {
            Item currentItem = mItemsUsed.get(i);
            itemList.append(String.format("%10s%5d%10s%7.2f%10s%5d\n", "Item:", currentItem.getItemId(), "Price: ", currentItem.getPrice(), "Weight:", currentItem.getWeight()));
        }
        return "The highest possible profit is: " + mActualProfit +
                "\nUsing these items:\n" +
                itemList.toString();

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
