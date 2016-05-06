package ex17.RyanNewsomKyleFrisbie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * This is the instance of the Knapsack problem
 * To Solve Knapsack Problem with Branch and Bound you...
 * 1)
 */
public class Knapsack {
    protected ArrayList<Node> mPossibleNodesForExploration = new ArrayList<>();
    private ArrayList<Item> mItems = new ArrayList<>();
    private int mMaximumWeightForSack;

    /**
     * Empty default constructor, not for use
     */
    private Knapsack() {
    }

    /**
     * Constructor
     *
     * @param maxWeightForSack - the maximum weight the sack can hold before it rips
     * @param availableItems   - the Item's available to be put in the sack
     */
    public Knapsack(int maxWeightForSack, ArrayList<Item> availableItems) {
        Node rootNode = new Node();
        mItems = availableItems;
        mMaximumWeightForSack = maxWeightForSack;
        rootNode.setMaximumPossibleProfit(calculateHighestPossibleProfit(rootNode));
        mPossibleNodesForExploration.add(rootNode);
    }

    /**
     * Determines the optimal items to use for the Knapsack Problem
     *
     * @return - the best option for the particular instance
     */
    public String determineOptimalItemsForKnapsackProblem() {
        Node bestNode = null; //The node with the highest possible profit

        while (mPossibleNodesForExploration.size() > 0) {
            bestNode = seeWhoToExploreNext();
            createChildren(bestNode);
            if (mPossibleNodesForExploration.size() == 0) {
                break;
            }
            pruneNodes();
        }
        if (bestNode == null) {
            return "Error, no best node found";
        } else {
            return bestNode.toString();
        }
    }

    protected double calculateHighestPossibleProfit(Node node) {
        //Check the nodes restrictions in terms of who it can/can't use
        //to use
        //return that value
        ArrayList<Item> itemsWeCanUse = new ArrayList<>();
        int accumulatedHypotheticalWeight = 0;
        double maximumPossibleProfit = 0;

        itemsWeCanUse.addAll(mItems);
        itemsWeCanUse.removeAll(node.getItemsNotAvailableForUse());
        //Look at the list of items and determine the highest profit using the items we are allowed
        for (int i = 0; i < itemsWeCanUse.size(); i++) {
            Item item = itemsWeCanUse.get(i);
            if ((accumulatedHypotheticalWeight + item.getWeight()) > mMaximumWeightForSack) {
                //add fraction of that item
                int weightNumerator = mMaximumWeightForSack - accumulatedHypotheticalWeight;
                double weightRatio = (double) weightNumerator / (double) item.getWeight();

                maximumPossibleProfit += (item.getPrice() * weightRatio);
                break;
            } else {
                // include the full value for the item
                maximumPossibleProfit += item.getPrice();
                accumulatedHypotheticalWeight += item.getWeight();
            }
        }

        return maximumPossibleProfit;
    }

    protected Node seeWhoToExploreNext() {
        Collections.sort(mPossibleNodesForExploration, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return (o1.getMaximumPossibleProfit() >=  o2.getMaximumPossibleProfit()) ? -1 : 1;
            }
        });
        return mPossibleNodesForExploration.remove(0);
    }

    /**
     * Getting the next item available base off the Node's item's it's used, and is barred from using
     *
     * @param node - Node to look at
     * @return - the next item to use for the node
     */
    protected Item getNextItemToUse(Node node) {
        ArrayList<Item> possibleItems = new ArrayList<>();
        possibleItems.addAll(mItems);
        possibleItems.removeAll(node.getItemsUsed());
        possibleItems.removeAll(node.getItemsNotAvailableForUse());
        if (possibleItems.size() == 0) {
            return null;
        }
        return possibleItems.get(0);
    }

    /**
     * Create the right child of a parent node
     *
     * @param parentNode - node right child is being added to
     */
    protected Node createRightNode(Node parentNode) {
        Item nextToUse = getNextItemToUse(parentNode);
        Node rightChild;

        // if we are out of items to use, don't create another child
        if (nextToUse == null) {
            return null;
        }

        rightChild = new Node(parentNode.getItemsUsed(), parentNode.getItemsNotAvailableForUse(),
                parentNode.getMaximumPossibleProfit(), parentNode.getActualProfit(), parentNode.getActualWeight());
        rightChild.addItemToUse(nextToUse);

        return rightChild;
    }

    /**
     * Create the left child of a parent node
     *
     * @param parentNode - node left child is being added to
     */
    protected Node createLeftNode(Node parentNode) {
        ArrayList<Item> decisionItems = new ArrayList<>();

        // discover what is the next item not to use
        decisionItems.addAll(mItems);
        decisionItems.removeAll(parentNode.getItemsUsed());
        decisionItems.removeAll(parentNode.getItemsNotAvailableForUse());

        // add properties to leftChild

        Node leftChild = new Node(parentNode.getItemsUsed(), parentNode.getItemsNotAvailableForUse(),
                parentNode.getActualProfit(), parentNode.getActualWeight());
        leftChild.addItemNotAvailableForUse(decisionItems.get(0));
        leftChild.setMaximumPossibleProfit(calculateHighestPossibleProfit(leftChild));

        return leftChild;
    }

    /**
     * Creates children for a node
     *
     * @param node - the node to have children added to
     * @return - true if children were created, false if there's no more children to be created
     */
    protected void createChildren(Node node) {
        Node rightChild = createRightNode(node);
        if(rightChild == null) {
            return;
        }

        node.setRightChild(rightChild);
        node.setLeftChild(createLeftNode(node));

        // only add the right child if another item doesn't make it too heavy
        if (rightChild.getActualWeight() < mMaximumWeightForSack) {
            mPossibleNodesForExploration.add(node.getRightChild());
        }
        mPossibleNodesForExploration.add(node.getLeftChild());
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
            if (highestActualProfit.getActualProfit() >
                    mPossibleNodesForExploration.get(i).getMaximumPossibleProfit()) {
                nodesNotPruned.remove(mPossibleNodesForExploration.get(i));
            }

        }
        mPossibleNodesForExploration = nodesNotPruned;
    }
}
