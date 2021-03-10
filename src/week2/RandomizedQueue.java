package week2;

import java.awt.event.ItemEvent;
import java.util.Iterator;
import edu.princeton.cs.algs4.*;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;
    private Item[] queue;

    // construct an empty randomized queue
    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        n = 0;
        queue = (Item[]) new Object[0];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        queue[n] = item;

    }

    // remove and return a random item
    public Item dequeue() {

    }

    // return a random item (but do not remove it)
    public Item sample() {

    }

    private class RandomIterator implements Iterator<Item> {
        public boolean hasNext() {
            return false;
        }

        public Item next() {
            return null;
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}
