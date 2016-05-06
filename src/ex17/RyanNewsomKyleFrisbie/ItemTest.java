package ex17.RyanNewsomKyleFrisbie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Ryan on 4/30/2016.
 */
public class ItemTest {


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetPrice() throws Exception {
        Item item = new Item(1, 40, 2);
        Item item3 = new Item(3, 50, 10);
        Item item4 = new Item(4, 10, 5);

        Assert.assertEquals(40, item.mPrice, 0);
    }

    @Test
    public void testFractionItemGetPrice() throws Exception {
        Item item = new Item(2, 30, 5);

        item.mFractionOfItemUsed = 0.9;
        Assert.assertEquals(27 ,item.getPrice(), 0);
    }


    @Test
    public void testEqualsId() throws Exception {
        Item item = new Item(7, 40 ,2);
        Item item2 = new Item(7, 0 ,0);

        Assert.assertEquals(item, item2);
    }

    @Test
    public void testEqualsPrice() throws Exception {
        Item item = new Item(1, 40 ,6);
        Item item2 = new Item(0, 40 ,0);

        Assert.assertNotEquals(item, item2);
    }

    @Test
    public void testEqualsWeight() throws Exception {
        Item item = new Item(3, 10 ,7);
        Item item2 = new Item(16, 0 ,7);

        Assert.assertNotEquals(item, item2);
    }

}