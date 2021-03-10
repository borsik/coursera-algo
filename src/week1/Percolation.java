package week1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int VIRTUAL_TOP = 0;
    private final WeightedQuickUnionUF unionFind;
    private final int size;
    private final boolean[] openSites;
    private int openSitesCount = 0;
    private final int virtualBottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("must be positive");
        }
        size = n;
        openSites = new boolean[n * n];

        unionFind = new WeightedQuickUnionUF(n * n + 2);
        virtualBottom = n * n + 1;
        for (int i = 1; i < size + 1; i++) {
            unionFind.union(VIRTUAL_TOP, i);
        }
        for (int i = (n * (n - 1) + 1); i < n * n + 1; i++) {
            unionFind.union(virtualBottom, i);
        }
    }

    private int to1D(int row, int col) {
        return (row - 1) * size + (col - 1) + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException("outside of range");
        }
        if (isOpen(row, col)) {
            return;
        } else {
            openSites[to1D(row, col) - 1] = true;
            openSitesCount++;

            if (row - 1 > 0 && isOpen(row - 1, col)) {
                unionFind.union(to1D(row, col), to1D(row - 1, col));
            }
            if (row + 1 <= size && isOpen(row + 1, col)) {
                unionFind.union(to1D(row, col), to1D(row + 1, col));
            }
            if (col - 1 > 0 && isOpen(row, col - 1)) {
                unionFind.union(to1D(row, col), to1D(row, col - 1));
            }
            if (col + 1 <= size && isOpen(row, col + 1)) {
                unionFind.union(to1D(row, col), to1D(row, col + 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException("outside of range");
        }
        return openSites[to1D(row, col) - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException("outside of range");
        }
        return isOpen(row, col) && unionFind.find(to1D(row, col)) == unionFind.find(VIRTUAL_TOP);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSitesCount;
    }

    // does the system percolate?
    public boolean percolates() {
        if (size == 1) {
            return isOpen(1, 1);
        } else if (size == 2) {
            return (isOpen(1, 1) && isOpen(2, 1)) || (isOpen(1, 2) && isOpen(2, 2));
        } else {
            return unionFind.find(virtualBottom) == unionFind.find(VIRTUAL_TOP);
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(2);
        p.open(1, 1);
        p.open(1, 2);
        p.open(1, 3);
        StdOut.println(p.percolates());
    }
}
