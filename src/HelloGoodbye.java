import edu.princeton.cs.algs4.StdOut;

public class HelloGoodbye {
    public static void main(String[] args) {
        String name1 = args[0].trim();
        String name2 = args[1].trim();
        StdOut.println(String.format("Hello %s and %s.", name1, name2));
        StdOut.println(String.format("Goodbye %s and %s.", name2, name1));
    }
}
