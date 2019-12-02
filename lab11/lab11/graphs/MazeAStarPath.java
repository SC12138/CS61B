package lab11.graphs;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private int[] h;

    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private PriorityQueue<Integer> fringe;

    /** Comparator of vertices' distance to target*/
    public class distToComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            if ((distTo[o1] - maze.V() + h(o1)) < (distTo[o2] - maze.V() + h(o2)))
            //if (o1 < o2)
                { return -1; }
            return 1;
        }

    }

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        distToComparator comp = new distToComparator();
        fringe = new PriorityQueue<>(comp);
        /** Init heuristic */
        h = new int[maze.V()];
        for (int i=0; i<maze.V(); i++)
            { h[i] = this.h(i); }
        h[s]=0;
        /** Init the PQ by distance*/
        for (int i=0; i<maze.V(); i++){
            fringe.offer(i);
        }
        // the top vertex in PQ should be s
        assert(fringe.peek() == s);
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        return (Math.abs(maze.toX(v) - maze.toX(t)) +
                Math.abs(maze.toY(v) - maze.toY(t)));
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    private void update(int vtv, int aj){
        if (distTo[vtv]+1 < distTo[aj]){
            assert(fringe.contains(aj)); //aj is supposed to be in the fringe
            distTo[aj] = distTo[vtv]+1;
            edgeTo[aj] = vtv;
            /** update aj's priority in fringe since it's distTo changed */
            fringe.remove(aj);
            fringe.offer(aj);
        }
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        int pv = s; // previous visit vertex
        while (!fringe.isEmpty()){
            int vtv = fringe.peek(); // vertex to visit
            fringe.remove(vtv);
            marked[vtv] = true;
            /** visit this vertex by accessing all it's outward edges and corresponding vertices*/
            for (int aj: maze.adj(vtv)){  //traverse all it's neighbors
                update(vtv, aj);
                if (aj == t){
                    marked[aj] = true;
                    announce();
                    return;
                }
            }
            announce();
        }
    }

    @Override
    public void solve() {
        astar(s);
        announce();
    }

}

