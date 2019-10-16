import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator offByN = new OffByN(4);

    // Your tests go here.

    /** Test for normal cases */
    @Test
    public void testOffNNorm(){
        assertTrue(offByN.equalChars('a', 'e'));
        assertTrue(offByN.equalChars('u', 'q'));
        assertFalse(offByN.equalChars('a', 'c'));
        assertFalse(offByN.equalChars('s', 'c'));
    }
}
