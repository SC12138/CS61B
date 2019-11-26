package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] open;
    private WeightedQuickUnionUF union;
    private int FULL;
    private int size;
    private int LOWERBOUND;
    private int openSite;
    public Percolation(int N) {               // create N-by-N grid, with all sites initially blocked
        if (N < 0){
            throw new IllegalArgumentException("N can not be negative");
        }
        union = new WeightedQuickUnionUF(N*N+2); //N*N refers to water, N*N+1 refers to lower bound
        open = new boolean[N][N];
        size = N;
        FULL = N*N;
        LOWERBOUND = N*N+1;
        openSite = 0;
        for (boolean[] subArray: open){
            for (boolean o: subArray){
                o=false;
            }
        }
    }

    private int xyToInt(int row, int col){
        if (row<0 || row>=size || col<0 || col>=size){
            throw new IndexOutOfBoundsException("Invalid site position");
        }
        return row*this.size + col;
    }

    public void open(int row, int col) {       // open the site (row, col) if it is not open already
        int siteIndex = xyToInt(row, col);
        if (isOpen(row, col)){
            return;
        }
        open[row][col] = true;
        openSite += 1;
        // connect with sites nearby
        if(row>0 && isOpen(row-1, col)){ // up
            union.union(siteIndex, xyToInt(row-1, col));
        }
        if(row<size-1 && isOpen(row+1, col)){ // down
            union.union(siteIndex, xyToInt(row+1, col));
        }
        if(col>0 && isOpen(row, col-1)){ //left
            union.union(siteIndex, xyToInt(row, col-1));
        }
        if(col<size-1 && isOpen(row, col+1)) { //right
            union.union(siteIndex, xyToInt(row, col+1));
        }
        // if site is on the upper bound
        if (row == 0){
            union.union(siteIndex, FULL);
        }
        // if site is on the lower bound
        if (row == size-1){
            union.union(siteIndex, LOWERBOUND);
        }
    }


    public boolean isOpen(int row, int col) {  // is the site (row, col) open?
        int siteIndex = xyToInt(row, col);
        return (open[row][col] == true);
    }


    public boolean isFull(int row, int col) {  // is the site (row, col) full?
        int siteIndex = xyToInt(row, col);
        return union.connected(siteIndex, FULL);
    }


    public int numberOfOpenSites() {          // number of open sites
        return this.openSite;
    }


    public boolean percolates() {              // does the system percolate?
        return (union.connected(FULL, LOWERBOUND));
    }


    public static void main(String[] args){   // use for unit testing (not required)

    }
}
