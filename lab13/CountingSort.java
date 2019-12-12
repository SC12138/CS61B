//import javafx.collections.ArrayChangeListener;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Class with 2 ways of doing Counting sort, one naive way and one "better" way
 *
 * @author Akhil Batra, Alexander Hwang
 *
 **/
public class CountingSort {
    /**
     * Counting sort on the given int array. Returns a sorted version of the array.
     * Does not touch original array (non-destructive method).
     * DISCLAIMER: this method does not always work, find a case where it fails
     *
     * @param arr int array that will be sorted
     * @return the sorted array
     */
    public static int[] naiveCountingSort(int[] arr) {
        // find max
        if (arr.length==0){
            return arr;
        }
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = max > i ? max : i;
        }

        // gather all the counts for each value
        int[] counts = new int[max + 1];
        for (int i : arr) {
            counts[i]++;
        }

        // when we're dealing with ints, we can just put each value
        // count number of times into the new array
        int[] sorted = new int[arr.length];
        int k = 0;
        for (int i = 0; i < counts.length; i += 1) {
            for (int j = 0; j < counts[i]; j += 1, k += 1) {
                sorted[k] = i;
            }
        }

        // however, below is a more proper, generalized implementation of
        // counting sort that uses start position calculation
        int[] starts = new int[max + 1];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        int[] sorted2 = new int[arr.length];
        for (int i = 0; i < arr.length; i += 1) {
            int item = arr[i];
            int place = starts[item];
            sorted2[place] = item;
            starts[item] += 1;
        }

        // return the sorted array
        return sorted2;
    }

    /**
     * Counting sort on the given int array, must work even with negative numbers.
     * Note, this code does not need to work for ranges of numbers greater
     * than 2 billion.
     * Does not touch original array (non-destructive method).
     *
     * @param arr int array that will be sorted
     */
    public static int[] betterCountingSort(int[] arr) {
        List<Integer> pos = new ArrayList<>();
        List<Integer> flipNeg = new ArrayList<>();
        for (int i:arr){
            if (i>=0){
                pos.add(i);
            }
            else{
                flipNeg.add(i* (-1));
            }
        }
        int[] arrayPos = pos.stream().mapToInt(i->i.intValue()).toArray();
        int[] arrayFlipNeg = flipNeg.stream().mapToInt(i->i.intValue()).toArray();
        /** Sort two arrays respectively */
        int[] sortPos = CountingSort.naiveCountingSort(arrayPos);
        int[] sortFlipNeg = CountingSort.naiveCountingSort(arrayFlipNeg);
        /** Flip back and concatenate */
        int[] sorted = new int[arr.length];
        int posit = 0;
        for (int i=sortFlipNeg.length-1; i>=0; i-=1){
            sorted[posit] = sortFlipNeg[i]*(-1);
            posit += 1;
        }
        for (int i:sortPos){
            sorted[posit] = i;
            posit += 1;
        }
        return sorted;
    }
}
