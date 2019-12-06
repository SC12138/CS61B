import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.Queue;
import org.junit.Test;

import javax.swing.*;
import java.util.Iterator;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        Queue<Queue<Item>> singleItemQueue = new Queue<Queue<Item>>();
        if (items.isEmpty()){
            return singleItemQueue;
        }
        for (Item i: items){
            Queue<Item> itemQueue = new Queue<Item>();
            itemQueue.enqueue(i);
            singleItemQueue.enqueue(itemQueue);
        }
        return singleItemQueue;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1==null || q2==null){ throw new RuntimeException("arg can't be null");}
        if (q1.size()==0){
            return q2;
        }
        if (q2.size()==0){
            return q1;
        }
        // both q1 and q2 are not empty
        Queue<Item> sortedQ = new Queue<>();
        Iterator<Item> i1 = q1.iterator();
        Iterator<Item> i2 = q2.iterator();
        Item t1 = i1.next();
        Item t2 = i2.next();

        while(t1!=null || t2!=null){
            Item itemadd = MergeSort.compare(t1, t2);
            sortedQ.enqueue(itemadd);
            if (itemadd.equals(t1)){
                if (i1.hasNext()){
                    t1=i1.next();
                }
                else{
                    t1=null;
                }
            }
            else {
                if (i2.hasNext()){
                    t2=i2.next();
                }
                else{
                    t2=null;
                }
            }
        }
        return sortedQ;
    }

    private static <Item extends Comparable> Item compare(Item a, Item b){
        if (a==null){
            return b;
        }
        else if(b==null){
            return a;
        }

        if (a.compareTo(b) <= 0){
            return a;
        }
        return b;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        if (items.isEmpty()){
            return items;
        }
        Queue<Queue<Item>> qsi = makeSingleItemQueues(items);
        return mergeRec(qsi);
    }

    private static <Item extends Comparable> Queue<Item> mergeRec(Queue<Queue<Item>> qsi){
        if (qsi.size()==1){
            return qsi.dequeue();  // if queue of single item queue
        }
        int half1 = qsi.size()/2;
        int f=0;
        Queue<Queue<Item>> front = new Queue<>();
        Queue<Queue<Item>> back = new Queue<>();

        for (Queue<Item> q:qsi){
            if (f<half1){
                front.enqueue(q);
            }
            else{
                back.enqueue(q);
            }
            f+=1;
        }
        return mergeSortedQueues(mergeRec(front), mergeRec(back));

    }



    public static void main(String[] args){
        Queue<String> wordQ = new Queue<>();
        wordQ.enqueue("uij");
        wordQ.enqueue("rdr");
        wordQ.enqueue("egg");
        wordQ.enqueue("apple");
        wordQ.enqueue("sdc");
        wordQ.enqueue("vivo");
        wordQ.enqueue("oppo");
        wordQ.enqueue("sdc");



        Queue<String> wordQ2 = new Queue<>();


        Queue<String> qsi = MergeSort.mergeSort(wordQ2);
        for (String s: qsi){
            System.out.println(s);
        }

    }
}
