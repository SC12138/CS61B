import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static private int N = 4;
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator cc = new OffByOne();
    static CharacterComparator ccN = new OffByN(N);

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testPalinNorm(){
        palindrome.isPalindrome("racecar");
        assertTrue(palindrome.isPalindrome("racecar")); //true
        assertTrue(palindrome.isPalindrome("noon")); //true
        assertFalse(palindrome.isPalindrome("horse")); //false
        assertFalse(palindrome.isPalindrome("rancor")); //false
    }

    @Test
    public void testPalinEmpetySingleNull(){
        assertTrue(palindrome.isPalindrome("a")); //true
        assertTrue(palindrome.isPalindrome("")); //true
        assertFalse(palindrome.isPalindrome(null)); //false
    }

    @Test
    public void testPalinCap(){
        assertFalse(palindrome.isPalindrome("rAcecar")); //false
        assertFalse(palindrome.isPalindrome("nooN")); //false
        assertTrue(palindrome.isPalindrome("racEcar")); //true
    }

    @Test
    public void testPalinOffByOne(){
        assertTrue(palindrome.isPalindrome("flake", cc)); //true
        assertFalse(palindrome.isPalindrome("noon", cc)); //false
        assertFalse(palindrome.isPalindrome(null, cc)); //false

    }

    @Test
    public void testPalinOffByN(){
        assertTrue(palindrome.isPalindrome("aifee", ccN)); //true
        assertFalse(palindrome.isPalindrome("noon", ccN)); //false
        assertFalse(palindrome.isPalindrome(null, ccN)); //false

    }


}
