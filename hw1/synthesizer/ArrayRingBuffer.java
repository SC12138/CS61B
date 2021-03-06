package synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {

    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        this.capacity = capacity;
        this.fillCount = 0;
        this.first = 0;
        this.last = 0;
        rb = (T[]) new Object[capacity];
    }

    @Override
    public Iterator<T> iterator(){
        return new ArrayBufferIterator();
    }

    private class ArrayBufferIterator implements Iterator{
        private int numLeft = fillCount; //index of next element to give ou
        private int i = first;
        @Override
        public boolean hasNext(){
            if(numLeft==0){
                return false;
            }
            return true;
        }

        @Override
        public T next(){
            /*
            T returnItem = dequeue();
            enqueue(returnItem);
            numLeft-=1;
            return returnItem;
            */
            T returnItem = rb[i];
            i = (i+1)%capacity;
            numLeft -= 1;
            return returnItem;

        }
    }
    /*
    @Override
    public int capacity(){
        return this.capacity;
    }

    @Override
    public int fillCount(){
        return this.fillCount;
    }

     */

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if(fillCount==capacity){
            throw new RuntimeException("Ring Buffer Overflow");
        }
        rb[last] = x;
        last = (last+1)%capacity;
        fillCount+=1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        T returnItem = peek();
        first = (first+1)%capacity;
        fillCount-=1;
        return returnItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if(fillCount == 0){
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T returnItem = rb[first];
        return returnItem;

    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}



