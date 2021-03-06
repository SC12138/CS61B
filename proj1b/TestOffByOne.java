import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.

    /** Test for normal cases */
    @Test
    public void testOffOneNorm(){
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('r', 'q'));

        assertFalse(offByOne.equalChars('a', 'c'));
        assertFalse(offByOne.equalChars('s', 'c'));

        assertTrue(offByOne.equalChars('&', '%'));
        assertFalse(offByOne.equalChars('#', '%'));
        assertFalse(offByOne.equalChars('A', 'a'));
        assertFalse(offByOne.equalChars('*', 'a'));


    }

}
