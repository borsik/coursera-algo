import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0].trim());
        RandomizedQueue<String> randomQueue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            for (String word: StdIn.readString().split(" ")) {
                randomQueue.enqueue(word);
            }
        }
        Iterator<String> iterator = randomQueue.iterator();
        while (iterator.hasNext() && k > 0) {
            StdOut.println(iterator.next());
            k--;
        }
    }
}
