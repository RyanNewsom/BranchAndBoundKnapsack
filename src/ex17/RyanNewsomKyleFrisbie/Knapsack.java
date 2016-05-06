package ex17.RyanNewsomKyleFrisbie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * This is the instance of the Knapsack problem
 * To Solve Knapsack Problem with Branch and Bound you...
 * 1)
 *
 */
public class Knapsack {
    protected ArrayList<Node> mPossibleNodesForExploration = new ArrayList<>();
    private ArrayList<Item> mItems = new ArrayList<>();
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
        Node rootNode = new Node();
        mItems = availableItems;
        mMaximumWeightForSack = maxWeightForSack;
        rootNode.setMaximumPossibleProfit(calculateHighestPossibleProfit(rootNode));
        mPossibleNodesForExploration.add(rootNode);
    }

    /**
     * Determines the optimal items to use for the Knapsack Problem
     * @return - the best option for the particular instance
     */
    public String determineOptimalItemsForKnapsackProblem(){
        Node bestNode = null; //The node with the highest possible profit

        while(mPossibleNodesForExploration.size() > 0) {
            bestNode = seeWhoToExploreNext();
            createChildren(bestNode);
            if (mPossibleNodesForExploration.size() == 0) {
                break;
            }
            pruneNodes();
        }
        if(bestNode == null){
            return "Error, no best node found";
        } else{
            return bestNode.toString();
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
                break;
            } else{
                //add the item
                item.setFractionOfItemUsed(1);
            }
        }

        return node.getMaximumPossibleProfit();
    }

    protected Node seeWhoToExploreNext(){
        //[TODO] Remove JDK 8 requirement
        Collections.sort(mPossibleNodesForExploration, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return (((Node) o1).getMaximumPossibleProfit() >= ((Node) o2).getMaximumPossibleProfit()) ? -1 : 1;
            }
        });
        return mPossibleNodesForExploration.remove(0);
    }

    /**
     * Getting the next item available base off the Node's item's it's used, and is barred from using
     * @param node - Node to look at
     * @return - the next item to use for the node
     */
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
        Node rightChild;
        Item nextToUse = getNextItemToUse(node);

        if(nextToUse == null){
            return;
        }
        //TODO - This is where we left off, we found that the children are not the nodes we are expecting, create a
            // TODO - method called. CreateRightNode & CreateLeftNode and write a unit test on it.S
        rightChild = new Node(node.getItemsUsed(), node.getItemsNotAvailableForUse(), node.getLeftChild(),
                node.getRightChild(), node.getMaximumPossibleProfit(), node.getActualProfit(), node.getMaximumPossibleWeight());
        rightChild.addItemToUse(nextToUse);

        node.setLeftChild(new Node(node.getItemsUsed(), node.getItemsNotAvailableForUse(), node.getLeftChild(), node.getRightChild(), node.getMaximumPossibleProfit(), node.getActualProfit(), node.getMaximumPossibleWeight()));
        node.setRightChild(rightChild);

        mPossibleNodesForExploration.add(node.getLeftChild());
        mPossibleNodesForExploration.add(node.getRightChild());
    }

    protected void pruneNodes() {
        Node highestActualProfit = mPossibleNodesForExploration.get(0);
        for (int i = 1; i < mPossibleNodesForExploration.size(); i++) {
            if (highestActualProfit.getMaximumPossibleProfit() <
                    mPossibleNodesForExploration.get(i).getMaximumPossibleProfit()) {
                highestActualProfit = mPossibleNodesForExploration.get(i);
            }
        }
        ArrayList<Node> nodesNotPruned = (ArrayList<Node>) mPossibleNodesForExploration.clone();
        for (int i = 0; i < mPossibleNodesForExploration.size(); i++) {
            if (highestActualProfit.getMaximumPossibleProfit() >
                    mPossibleNodesForExploration.get(i).getMaximumPossibleProfit()) {
                nodesNotPruned.remove(mPossibleNodesForExploration.get(i));
            }

        }
        mPossibleNodesForExploration = nodesNotPruned;
    }
}
