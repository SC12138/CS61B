package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState{
    private int size;
    private int[][] board;
    private int[][] t;

    public Board(int[][] tiles){
        this.size = tiles.length;
        this.board = new int[size][size];
        this.t = new int[size][size];

        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                board[i][j] = tiles[i][j];
                t[i][j] = i*size + j + 1;
            }
        }
        t[size-1][size-1] = 0;
    }

    public int tileAt(int i, int j){
        if (0 <= i && i <= size-1 && 0<=j && j<=size-1){
            return board[i][j];
        }
        throw new IndexOutOfBoundsException("Invalid index of accessing tiles");
    }


    public int size(){
        return this.size;
    }

    /**
     * Returns neighbors of this board.
     * SPOILERZ: This is the answer.
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == 0) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = 0;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = 0;
                }
            }
        }
        return neighbors;
    }


    public int hamming(){
        Board o = new Board(t);
        if (this.size != o.size()){
            throw new RuntimeException("Two tiles must be same size");
        }
        int dis = 0;
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                if (board[i][j]!=o.tileAt(i, j) && board[i][j]!=0){
                    dis += 1;
                }
            }
        }
        return dis;
    }

    public int manhattan(){
        int dis = 0;
        Board o = new Board(t);
        if (this.size != o.size()){
            throw new RuntimeException("Two tiles must be same size");
        }
        int [] boardIndex = new int[size*size];
        int [] oIndex = new int[size*size];
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                boardIndex[board[i][j]] = i*size + j;
                oIndex[o.tileAt(i, j)] = i*size + j;
            }
        }
        /*
        for (int i:boardIndex){
            System.out.print(i);
        }
        System.out.println();
        for (int i:oIndex){
            System.out.print(i);
        }
        System.out.println();

         */

        for (int i=1; i<size*size; i++){
            dis += Math.abs(boardIndex[i]/size - oIndex[i]/size) +
                    Math.abs(boardIndex[i]%size - oIndex[i]%size);
        }
        return dis;
    }

    @Override
    public int estimatedDistanceToGoal(){
        return manhattan();
    }

    @Override
    public boolean equals(Object y){
        if (this == y) {
            return true;
        }
        if (y == null || getClass() != y.getClass()) {
            return false;
        }
        Board o = (Board)y;
         if (this.size != o.size()){
             return false;
         }

         for (int i=0; i<size; i++){ // traverse the whole tile
             for (int j=0; j<size; j++){
                if (board[i][j] != o.tileAt(i,j)){
                    return false;
                }
             }
         }
         return true;
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    @Override
    public int hashCode(){
        return 0; //TODO
    }
}
