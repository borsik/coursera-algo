package week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    // perform independent trials on an n-by-n grid
    private static final double CONFIDENCE_95 = 1.96;
    private final double[] stats;

    public PercolationStats(int n, int trials) {
        Percolation p = new Percolation(n);
        stats = new double[trials];
        int i = 0;
        while (i < trials) {
            int j = 0;
            while (!p.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                    j++;
                }
            }
            stats[i] = j / ((double) n * n);
            i++;
        }
    }


    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(stats);
    }


    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(stats);
    }


    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(stats.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(stats.length);
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0].trim()), Integer.parseInt(args[1].trim()));
        StdOut.println(String.format("mean = %s", ps.mean()));
        StdOut.println(String.format("stddev = %s", ps.stddev()));
        StdOut.println(String.format("95%% confidence interval = [%s, %s]", ps.confidenceLo(), ps.confidenceHi()));
    }
}
