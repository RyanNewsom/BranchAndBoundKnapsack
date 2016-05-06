package ex17.RyanNewsomKyleFrisbie;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Ryan on 4/30/2016.
 */
public class KnapsackTest {
    private ArrayList<Item> mItemsWeCouldUse = new ArrayList<>();
    private ArrayList<Node> mTestNodes = new ArrayList<>();
    private int mMaxWeightPossible = 16;
    private Knapsack mKnapsack;

    @Before
    public void setUp() throws Exception {
        mItemsWeCouldUse.add(new Item(1,40,2));
        mItemsWeCouldUse.add(new Item(2,30,5));
        mItemsWeCouldUse.add(new Item(3,50,10));
        mItemsWeCouldUse.add(new Item(4,10,5));

        mKnapsack = new Knapsack(mMaxWeightPossible, mItemsWeCouldUse);

        mTestNodes.add(new Node(40, 98));
        mTestNodes.add(new Node(100, 115));
        mTestNodes.add(new Node(60, 70));
        mTestNodes.add(new Node(0, 82));
    }

    @Test
    public void testDetermineOptimalItemsForKnapsackProblem() throws Exception {
        mKnapsack.mPossibleNodesForExploration = mTestNodes;
        mKnapsack.seeWhoToExploreNext();
        double result = mKnapsack.mBestNode.getMaximumPossibleProfit();
        assertEquals(115, result, 0);
    }

    @Test
    public void testCalculateHighestPossibleProfit() throws Exception {
        Node node = new Node();
        double result;
        result = mKnapsack.calculateHighestPossibleProfit(node);

        assertEquals(115, result, 0);
    }

    @Test
    public void testSeeWhoToExploreNext() throws Exception {
        mKnapsack.mPossibleNodesForExploration = mTestNodes;
        mKnapsack.seeWhoToExploreNext();
        double result = mKnapsack.mBestNode.getMaximumPossibleProfit();
        assertEquals(115, result, 0);
    }

    @Test
    public void testCreateChildren() throws Exception {
        Node node = new Node();

        mKnapsack.createChildren(node);

        assertNotNull(node.getLeftChild());
        assertNotNull(node.getRightChild());
    }

    @Test
    public void testPruneNodes() throws Exception {
        mKnapsack.mPossibleNodesForExploration = mTestNodes;
        mKnapsack.pruneNodes();
        assertTrue((mKnapsack.mPossibleNodesForExploration.size() == 1) &&
        (mKnapsack.mPossibleNodesForExploration.get(0).getProfit() == 100));
    }
}