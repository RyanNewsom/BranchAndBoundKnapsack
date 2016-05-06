package ex17.RyanNewsomKyleFrisbie;

import java.util.ArrayList;

/**
 * Created by Ryan on 5/6/2016.
 */
public class Logger {

    /**
     * Prints the initial info gathered from the input file
     * @param capacity - the capacity of the Knapsack
     * @param items - the items belonging to the Knapsack
     */
    public static void printInitialInfo(int capacity, ArrayList<Item> items){
        print("Capacity of Knapsack is " + capacity);
        print("Items are:");
        for(Item item : items){
            print(item.toString());
        }
    }

    /**
     * Prints a string notifying the user they are beginning the exploration
     */
    public static void beginExploration() {
        print("\nBegin exploration of the possibilities tree: \n");
    }

    public static void prunedNode(Node prunedNode){
        print("\nThis node was Pruned: \n    " + prunedNode.toString());
    }

    private static void print(String message){
        System.out.println(message);
    }

    public static void exploring(Node bestNode) {
        print("Exploring: " + bestNode.toString());
    }
}
