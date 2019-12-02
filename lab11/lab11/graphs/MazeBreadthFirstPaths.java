package lab11.graphs;

import edu.princeton.cs.algs4.In;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    Maze m;
    */
    private boolean targetFound;
    private int s;
    private int t;
    ArrayDeque<Integer> fringe;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        s = m.xyTo1D(sourceX, sourceY);
        t = m.xyTo1D(targetX, targetY);
        targetFound = false;
        fringe = new ArrayDeque<>();
        fringe.addLast(s);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(int v) {
        //if (this.targetFound == true){return;}
        marked[v] = true;        // update marked[]
        //if (fringe.isEmpty()){return;}
        for (int w: maze.adj(v)){
            if (marked[w] || fringe.contains(w)){ // add to fringe only if it's not marked
                continue;
            }
            fringe.addLast(w);
            //
            distTo[w] = distTo[v]+1; // update distTo[]
            edgeTo[w] = v;
            if (w == t){
                this.targetFound = true;
                marked[w] = true; // show vertex in graph
                //announce();
                //return; // Not necessary to continue
            }
            announce();
        }
    }

    @Override
    public void solve() {
        if (s == t){
            announce();
            return;
        }
        bfs(this.s);
        while (!fringe.isEmpty() && !targetFound){
            bfs(fringe.removeFirst());
        }
    }
}

