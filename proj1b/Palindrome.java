public class Palindrome {

    public Deque<Character> wordToDeque(String word){
        Deque<Character> deque = new ArrayDeque<>(); //static type=Deque, Dynamic type = ArrayDeque
        //retrieve ith char in a String: str.charAt(index)
        if (word != null){
            for (int i=0; i<word.length(); i++){
                deque.addLast(word.charAt(i));
            }
        }
        return deque;
    }

    /** helper method of isPalindrome() for using recursion */
    private boolean isPalindrome(Deque<Character> wordDeque, CharacterComparator cc){
        if (wordDeque.size()<2){
            return true;
        }
        boolean charDiff;
        charDiff = (cc == null)?
                (wordDeque.removeFirst() != wordDeque.removeLast())
                :!(cc.equalChars(wordDeque.removeFirst(), wordDeque.removeLast()));
        return (charDiff)?(false):(isPalindrome(wordDeque, cc));


    }

    public boolean isPalindrome(String word){
        if (word==null){
            return false;
        }
        else if (word.length()==0 || word.length()==1){
            return true;
        }
        Deque<Character> deque = wordToDeque(word);
        /** Use recursion */
        return isPalindrome(deque, null);
    }


    /** Overload method of isPalindrome(String word) */
    public boolean isPalindrome(String word, CharacterComparator cc){
        if (word==null){
            return false;
        }
        else if (word.length()==0 || word.length()==1){
            return true;
        }
        Deque<Character> deque = wordToDeque(word);
        /** Use recursion */
        return isPalindrome(deque, cc);
    }

}
