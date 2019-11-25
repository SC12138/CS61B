package lab9;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Chen Song
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null){
            return null;
        }
        else if(key.compareTo(p.key) <0 ){ // fall down to left subtree
            return getHelper(key, p.left);
        }
        else if(key.compareTo(p.key) >0) {// fall down to right subtree
            return getHelper(key, p.right);
        }
        // key match
        return p.value;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null){
            this.size+=1;
            return new Node(key, value);
        }
        else if (key.compareTo(p.key) <0 ) { // fall down to left subtree
            p.left = putHelper(key, value, p.left);
        }
        else if (key.compareTo(p.key) >0 ) { // fall down to right subtree
            p.right = putHelper(key, value, p.right);
        }
        //key match
        else{
            p.value = value;
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        this.root = putHelper(key, value, this.root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return this.size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    private void keySetHelper(Set<K> ks, Node p){
        if (p == null){
            return;
        }
        ks.add(p.key);
        keySetHelper(ks, p.left);
        keySetHelper(ks, p.right);
        return;
    }

    @Override
    public Set<K> keySet() {
        Set<K> returnKeySet = new TreeSet<>();
        keySetHelper(returnKeySet, this.root);
        return returnKeySet;
    }


    /**
     * Find the minimum node from p and subtrees of p
     */
    private Node findMin(Node p){
        if (p==null){
            return null;
        }
        if (p.left == null){
            return p;
        }
        return findMin(p.left);
    }

    private Node deleteMin(Node p){
        return null;
    }

    private Node removeHelper(K key, Node p, V value){
        // no match
        if (p == null){
            return null;
        }
        if (key.compareTo(p.key)<0){ // fall to left subtree
            p.left = removeHelper(key, p.left, value);
        }
        else if (key.compareTo(p.key)>0){ // fall to right subtree
            p.right = removeHelper(key, p.right, value);
        }
        else{ //key match
            if (value != null && !p.value.equals(value)){ return p; } // value not match

            if (p.left == null){
                return p.right;} // if left subtree is null, return left subtree. p's parent would point to p's one child as subtree regardless left or right
            if (p.right == null){
                return p.left;} // same above. Therefore cases of 0 or 1 child are handled
            // Now deal with case of two children.
            // First find p's successor a.k.a. the smallest node among nodes larger than p
            Node successor = findMin(p.right);
            Node oriNodeToDelete = p;
            // delete the original successor
            removeHelper(successor.key, p.right, value); //TODO test if same as deleteMin()
            p = successor; //Replace p with successor. But the origin Node still holds its right/left subtrees which need to be transferred to new p(the successor)
            p.left = oriNodeToDelete.left;
            p.right = oriNodeToDelete.right;
        }
        return p;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        if (key == null){return null;}
        V renVal = get(key);
        if (renVal == null){return null;};

        this.root = removeHelper(key, root, null); // no need to check value
        this.size -= 1;
        return renVal;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {

        if (value == null || key == null){ return null;}
        V renVal = get(key);
        if (!renVal.equals(value)){ return null;}

        this.root = removeHelper(key, root, value); // no need to check value
        this.size -= 1;
        return renVal;
    }

    /**
     * @param p: Node to rotateLeft
     * @return root of rotated subtree
     */
    public Node rotateLeft(Node p){
        Node pRight = p.right;
        if (pRight == null){
            throw new RuntimeException("Can not rotateLeft since no right child");
        }
        p.right = pRight.left;
        pRight.left = p;
        return pRight;
    }

    public Node rotateRight(Node p){
        Node pLeft = p.left;
        if (pLeft == null){
            throw new RuntimeException("Can not rotateLeft since no right child");
        }
        p.left = pLeft.right;
        pLeft.right = p;
        return pLeft;
    }


    @Override
    public Iterator<K> iterator() {
        return this.keySet().iterator();
    }
}
