import static org.junit.Assert.*;
import org.junit.Test;

import java.awt.image.Raster;
import java.time.chrono.JapaneseEra;
import java.util.ArrayList;
import java.util.List;


/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        int maxLen = 0;
        for (String s: asciis){
            if (s.length()>maxLen){
                maxLen = s.length();
            }
        }
        String[] sort = new String[asciis.length];
        System.arraycopy(asciis, 0, sort,0, asciis.length);
        for (int i = maxLen-1 ; i>=0;i-=1){
            RadixSort.sortHelperLSD(sort, i);
        }
        return sort;

    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {

        for (int i=0; i<asciis.length/2; i++){
            String temp = asciis[i];
            asciis[i] = asciis[asciis.length - i - 1];
            asciis[asciis.length - i -1] = temp;
        }

        int[] counts = new int[257]; // last index for String that is shorter
        int[] posit = new int[257];
        String[] sorted = new String[asciis.length];

        for (String s: asciis){
            if (s.length() < index+1){
                counts[256]+=1;
            }
            else{
                counts[(int)s.charAt(index)] += 1;
            }
        }
        /** construct position array */
        int pos = 0;
        for (int i=0; i<counts.length; i+=1){
            posit[i] = pos;
            pos += counts[i];
        }
        for (String s: asciis){
            if (s.length() < index+1){
                sorted[posit[256]] = s;
                posit[256] += 1;
            }
            else{
                sorted[posit[(int)s.charAt(index)]] = s;
                posit[(int)s.charAt(index)] += 1;
            }
        }
        System.arraycopy(sorted, sorted.length - counts[256], asciis, 0, counts[256]);
        System.arraycopy(sorted, 0, asciis, counts[256], sorted.length - counts[256]);

        for (int i=0; i<asciis.length/2; i++){
            String temp = asciis[i];
            asciis[i] = asciis[asciis.length - i - 1];
            asciis[asciis.length - i -1] = temp;
        }
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    @Test
    public void testRadix(){
        String[] ts = new String[]{"abc", "cde", "abe", "efgh", "f", " ",""};
        RadixSort.sortHelperLSD(ts, 0);
        String[] expected = new String[]{"f", "efgh", "cde", "abc", "abe", " ", ""};
        assertArrayEquals(expected, ts);

        ts = new String[]{"abe", "cde", "abc", "efgh", "f", " ",""};
        expected = new String[]{"f", "efgh", "cde", "abe", "abc", " ", ""};
        String[] result = RadixSort.sort(ts);
        assertArrayEquals(expected, result);
    }
}
