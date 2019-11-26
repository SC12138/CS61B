package hw2;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.List;
import java.util.ArrayList;

public class PercolationStats {
    private double[] fracToParco;
    private int numOfExperi;
    private int size;

    /*
    public PercolationStats(int N, int T, PercolationFactory pf) {   // perform T independent experiments on an N-by-N grid
        if (N<=0 || T <=0) {throw new IllegalArgumentException("N and T should be positive");}
        size = N;
        numOfExperi = T;
        fracToParco = new double[numOfExperi];
        for (int i=0; i<T; i++){ // perform T experiments
            Percolation p = pf.make(size);
            //Percolation p = new Percolation(size);
            // construct an index list
            List<Integer> indexes = new ArrayList<>(size * size);
            int unopened = size * size; //
            for (int ind=0; ind < unopened; ind++){
                indexes.add(ind);
            }
            // uniformly get random index from the list
            for (int j=0; j<size*size; j++){
                int randInd = (int)Math.floor(StdRandom.uniform(unopened)); // generate a random index of the index list
                int openIndex = indexes.remove(randInd); // get the random index of the tile to open
                int temCol = openIndex % this.size;
                int temRow = (openIndex - temCol)/size;
                // open the tile
                p.open(temRow, temCol);
                unopened -= 1; // update number of unopened tiles
                if (p.percolates()){
                    this.fracToParco[i] = (j+1)/Math.pow(this.size, 2);
                    break;
                }

            }
        }
    }
    */

    public PercolationStats(int N, int T, PercolationFactory pf) {   // perform T independent experiments on an N-by-N grid
        if (N<=0 || T <=0) {throw new IllegalArgumentException("N and T should be positive");}
        size = N;
        numOfExperi = T;
        fracToParco = new double[numOfExperi];
        for (int i=0; i<T; i++){ // perform T experiments
            Percolation p = pf.make(size);
            //Percolation p = new Percolation(size);
            int openSite = 0;
            // uniformly get random index from the list
            while (true){
                int temCol = (int)StdRandom.uniform(size);
                int temRow = (int)StdRandom.uniform(size);
                // open the tile
                if (!p.isOpen(temRow, temCol)){
                    p.open(temRow, temCol);
                    openSite += 1;
                    if (p.percolates()){
                        this.fracToParco[i] = (openSite)/Math.pow(this.size, 2);
                        break;
                    }
                }
            }
        }
    }


    public double mean() {                                          // sample mean of percolation threshold
        return StdStats.mean(this.fracToParco);
    }

    public double stddev() {                                         // sample standard deviation of percolation threshold
        return StdStats.stddev(this.fracToParco);
    }

    public double confidenceLow() {                              // low endpoint of 95% confidence interval
        return (this.mean() - (1.96*this.stddev())/Math.sqrt(numOfExperi));
    }


    public double confidenceHigh() {                                 // high endpoint of 95% confidence interval
        return (this.mean() + (1.96*this.stddev())/Math.sqrt(numOfExperi));
    }


    /*
    public static void main(String[] args){
        PercolationStats pS = new PercolationStats(20, 30, null);
        System.out.println(pS.mean());
        System.out.println(pS.stddev());
    }

     */



}
