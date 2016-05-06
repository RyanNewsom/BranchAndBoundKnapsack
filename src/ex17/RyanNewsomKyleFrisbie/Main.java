package ex17.RyanNewsomKyleFrisbie;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This program does the Knapsack Problem using the Branch and Bound technique
 * Pass in your text file as an argument
 */
public class Main {
    protected static ArrayList<Item> mItems = new ArrayList<>();
    protected static int mMaxWeight;

    public static void main(String[] args) {
        //Create a knapsack instance
        //ask knapsack to give me the optimal decision
        takeInUserInput(args[0]);
        Knapsack knapsack = new Knapsack(mMaxWeight, mItems);
        Node bestNodeToUse = knapsack.determineOptimalStrategyForKnapsackProblem();
        if(bestNodeToUse == null){
            System.out.println("Error, no best node found");
        } else {
            System.out.println(bestNodeToUse.toString());
        }
    }

    /**
     * Populated the ArrayList of Items and gets the max weight possible for the Sack
     * @param arg - file name
     */
    protected static void takeInUserInput(String arg) {
        File file = new File(arg);
        int itemNumber = 1;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            Scanner in = new Scanner(inputStream);

            mMaxWeight = Integer.parseInt(in.next());
            /*int numberOfItems = */Integer.parseInt(in.next());
            while(in.hasNext()){
                mItems.add(new Item(itemNumber++, in.nextInt(), in.nextInt()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found!!");
        } catch (NumberFormatException nfe){
            System.out.println("There was an error trying to parse an Integer from the text file");
        }
    }
}
