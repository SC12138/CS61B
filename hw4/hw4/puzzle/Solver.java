package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;    

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;

public class Solver {
    private MinPQ<SearchNode> fringe;
    private int totalMoves;
    private SearchNode lastState;
    private Deque<WorldState> route;


    private class SearchNode implements WorldState{
        private int moves;
        private SearchNode prevNode;
        private WorldState word;
        private int disToGoal=-1;

        public SearchNode(WorldState w, SearchNode prev) {
            if (prev == null){ moves = 0;}
            else{ moves = prev.moves+1;}
            this.word = w;
            this.prevNode = prev;
        }

        public int getMoves(){
            return this.moves;
        }

        public SearchNode getPrevNode(){
            return this.prevNode;
        }

        @Override
        public int estimatedDistanceToGoal() {
            if (this.disToGoal == -1){
                this.disToGoal = word.estimatedDistanceToGoal();
            }
            return this.disToGoal;
        }

        @Override
        public Iterable<WorldState> neighbors() {
            return word.neighbors();
        }

        public String toString(){
            return this.word.toString();
        }
    }

    private class WordComparator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            if (o1.getMoves()+o1.estimatedDistanceToGoal() > o2.getMoves()+o2.estimatedDistanceToGoal()){
                return 1;
            }
            return -1;
        }
    }

    public Solver(WorldState initial){
        fringe = new MinPQ<>(new WordComparator());
        fringe.insert(new SearchNode(initial, null));
        SearchNode last = null;
        while (!fringe.isEmpty()){
            SearchNode visit = fringe.delMin();
            if (visit.isGoal()){
                last = visit;
                break;
            }
            for (WorldState i: visit.neighbors()){
                //System.out.println(i);
                if (visit.getPrevNode()!=null && i.equals(visit.getPrevNode().word)){
                    continue;
                }
                fringe.insert( new SearchNode(i, visit));
            }
        }

        //set total moves
        assert(last!=null);
        this.totalMoves = last.getMoves();
        //set last state
        this.lastState = last;
        SearchNode temp = last;
        this.route = new ArrayDeque<>();
        while (temp.getPrevNode()!=null){
            route.addFirst(temp);
            temp = temp.getPrevNode();
        }
    }

    public int moves(){
        return this.totalMoves;
    }

    public Iterable<WorldState> solution(){
        return this.route;
    }

}
