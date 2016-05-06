package ex17.RyanNewsomKyleFrisbie;

/**
 * An Item is an available Knapsack item for use
 */
public class Item {
    private int mItemId;
    private int mPrice;
    private int mWeight;
    private double mFractionOfItemUsed = 1;

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
        int mpricePerWeight = price / weight;
    }

    public double getPrice() {
        return mPrice * mFractionOfItemUsed;
    }

    public int getWeight() {
        return mWeight;
    }


    public int getItemId() {
        return mItemId;
    }


    @Override
    public boolean equals(Object obj) {
        Item itemToCheck = (Item) obj;

        return this.mItemId == itemToCheck.getItemId();
    }
}
