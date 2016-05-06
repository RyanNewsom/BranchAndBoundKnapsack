package ex17.RyanNewsomKyleFrisbie;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Who knew you could test Main? Muhaha
 */
public class MainTest {
    private ArrayList<Item> mItems = new ArrayList<>();

    @Test
    public void testTakeInUserInput() throws Exception {
        mItems.clear();
        mItems.add(new Item(1, 40, 2));
        mItems.add(new Item(2,30,5));
        mItems.add(new Item(3, 50, 10));
        mItems.add(new Item(4, 10, 5));
        Main.takeInUserInput("example.txt");

        boolean equals = mItems.equals(Main.mItems);
        Assert.assertTrue(equals);
    }
}