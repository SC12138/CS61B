/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {

    public static void main(String[] args) {
        int N = 4;
        CharacterComparator ccOffOne = new OffByOne();
        CharacterComparator ccOffN = new OffByN(N);
        int minLength = 4;
        In in = new In("../library-sp18/data/words.txt");
        Palindrome palindrome = new Palindrome();

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, ccOffN)) {
                System.out.println(word);
            }
        }
    }
}
