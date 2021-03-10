package week2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;
    private Item[] queue;

    // construct an empty randomized queue
    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        n = 0;
        queue = (Item[]) new Object[1];
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
        if (item == null) throw new IllegalArgumentException("null");
        if (n == queue.length) {
            resize(queue.length * 2);
        }
        queue[n] = item;
        n++;
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = queue[i];
        }
        queue = copy;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("empty");
        if (n > 0 && n == queue.length / 4) {
            resize(n / 2);
        }
        int randIx = StdRandom.uniform(n);
        Item randEl = queue[randIx];
        for (int i = randIx; i < n; i++) {
            queue[i] = queue[i + 1];
        }
        n--;
        return randEl;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("empty");
        return queue[StdRandom.uniform(n)];
    }

    private class RandomIterator implements Iterator<Item> {
        private int current = 0;
        private Item[] copy = init();

        @SuppressWarnings("unchecked")
        private Item[] init() {
            Item[] copy = (Item[]) new Object[n];
            for (int i = 0; i < n; i++) {
                copy[i] = queue[i];
            }
            StdRandom.shuffle(copy);
            return copy;
        }

        public boolean hasNext() {
            return current != n;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("empty");
            Item el = copy[current];
            current++;
            return el;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> randomQueue = new RandomizedQueue<>();
        randomQueue.enqueue("A");
        randomQueue.enqueue("B");
        randomQueue.enqueue("C");
        Iterator<String> iterator = randomQueue.iterator();
        StdOut.println("============");
        while (iterator.hasNext()) {
            StdOut.println(iterator.next());
        }
    }

}
