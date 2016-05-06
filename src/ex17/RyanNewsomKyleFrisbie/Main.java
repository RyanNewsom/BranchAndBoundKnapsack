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
        Scanner in = new Scanner(System.in);
        String fileName;
        //Create a knapsack instance
        //ask knapsack to give me the optimal decision
        if(args[0] == null){
            System.out.println("Please enter the name of the data file you wish to import");
            fileName = in.next();
            takeInUserInput(fileName);
        } else {
            takeInUserInput(args[0]);
        }
        Knapsack knapsack = new Knapsack(mMaxWeight, mItems);
        Node bestNodeToUse = knapsack.determineOptimalStrategyForKnapsackProblem();
        if(bestNodeToUse == null){
            System.out.println("Error, no best node found");
        } else {
            System.out.println("The best node to use is: \n" + bestNodeToUse.toString());
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
                Item newItem = new Item(itemNumber++, in.nextInt(), in.nextInt());
                mItems.add(newItem);
            }
            Logger.printInitialInfo(mMaxWeight, mItems);
        } catch (FileNotFoundException e) {
            System.out.println("File was not found!!");
        } catch (NumberFormatException nfe){
            System.out.println("There was an error trying to parse an Integer from the text file");
        }
    }
}
