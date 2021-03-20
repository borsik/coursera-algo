import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;


public class UF {
    private int[] id;

    public UF(int N) {
        id = new int[N];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    void union(int p, int q) {
        int pid = id[p];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = id[q];
            }
        }
    }

    boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        UF uf = new UF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                StdOut.println(String.format("%s + %s", p, q));
            }
        }
    }
}
