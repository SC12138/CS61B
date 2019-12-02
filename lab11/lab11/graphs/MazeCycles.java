package lab11.graphs;
import edu.princeton.cs.algs4.Stack;

import java.util.HashSet;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    Maze m;
    */
    private boolean cycleFound;
    private int s;
    private Stack<Integer> vs;

    public MazeCycles(Maze m) {
        super(m);
        s = 0;
        distTo[s] = 0;
        edgeTo[s] = s;
        cycleFound = false;
        vs = new Stack<>();
    }

    @Override
    public void solve() {
        vs.push(s);
        while (!vs.isEmpty() && !cycleFound){
            dfs(vs.pop());
        }
        announce();
    }

    private int findCross(int v, int w){
        assert(v != w);
        HashSet<Integer> crossVertex = new HashSet<>();
        int cross = -1;
        int vp = v;
        int wp = w;
        for (int i=0 ;i<maze.V(); i++){
            if (crossVertex.contains(vp)){
                cross = vp;
                break;
            }
            if (crossVertex.contains(wp)){
                cross = wp;
                break;
            }
            //no match
            crossVertex.add(vp);
            crossVertex.add(wp);
            //update vp amd wp
            vp = edgeTo[vp];
            wp = edgeTo[wp];
        }
        assert(cross>=0);
        //now cross is valid
        return cross;
    }

    private void resetCycle(int v, int w, int cross){
        Stack<Integer> rs = new Stack<>();
        int toPush = w;
        while (edgeTo[toPush]!=cross){
            rs.push(toPush);
            toPush = edgeTo[toPush];
        }
        assert(edgeTo[toPush] == cross);
        rs.push(toPush);
        int ni = cross; // new index
        int ne=0; // new value for edgeTo[ni]
        int[] edgeToNew = new int[maze.V()];
        for (int i=0 ; i<maze.V();i++){
            edgeToNew[i] = Integer.MAX_VALUE;
        }
        while (rs.size()>0){
            ne = rs.pop();
            edgeToNew[ni] = ne;
            ni = ne;
        }
        assert(ne == w);
        edgeToNew[w] = v;
        ni = v;
        while (ni != cross){
            edgeToNew[ni] = edgeTo[ni];
            ni = edgeTo[ni];
        }
        this.edgeTo = edgeToNew;
    }


    private void dfs(int v){
        marked[v] = true;
        for (int w: maze.adj(v)){
            // abnormal visited vertex: Not v's parent
            if ((marked[w]) && w != edgeTo[v]){
                cycleFound = true;
                marked[w] = true;        // update marked[]
                //edgeTo[w] = v;
                int cross = findCross(v, w);
                resetCycle(v, w, cross);
                announce();
                return;
            }
            // just normal visited vertex: is v's parent, no cycle
            if (marked[w] || containVertex(vs, w)){ continue; }
            // just normal unvisited vertex
            edgeTo[w] = v;
            distTo[w] = distTo[v] + 1;
            //marked[w] = true;
            vs.push(w);
            announce();

        }
    }

    private boolean containVertex(Stack<Integer> s, int v){
        for (int i: s){
            if (i==v){
                return true;
            }
        }
        return false;
    }
}

