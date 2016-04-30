package com.kylefrisbie;

import com.kylefrisbie.model.Item;
import com.kylefrisbie.model.Knapsack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This program does the Knapsack Problem using the Branch and Bound technique
 */
public class Main {
    private static ArrayList<Item> mItems = new ArrayList<>();
    private static int mMaxWeight;
    private static int mNumberOfItems;//[TODO] remove this if it's not in use @ finish

    public static void main(String[] args) {
        //Create a knapsack instance
        //ask knapsack to give me the optimal decision
        takeInUserInput(args[0]);
        Knapsack knapsack = new Knapsack(mMaxWeight, mItems);
        String result = knapsack.determineOptimalItemsForKnapsackProblem();
        System.out.println(result);

    }

    private static void takeInUserInput(String arg) {
        File file = new File(arg);
        int itemNumber = 1;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            Scanner in = new Scanner(inputStream);

            mMaxWeight = Integer.parseInt(in.next());
            mNumberOfItems = Integer.parseInt(in.next());
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
