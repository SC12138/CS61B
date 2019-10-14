import java.util.Objects;

public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;
    private int minArrayLength = 8;

    public ArrayDeque(){

        size = 0;
        nextLast = 0;
        //Initiate a array of length 8
        items = (T[]) new Object[minArrayLength];
        nextFirst = items.length-1;
    }

    private int modPos(int x){
        if (x<0){ return x+items.length;}
        else{ return x;}
    }

    private void resize(int capacity){
        T[] newItems = (T[]) new Object[capacity];

        int firstIndex = (nextFirst+1)%items.length;
        int lastIndex = (nextLast-1)%items.length;
        lastIndex = modPos(lastIndex);
        if (firstIndex<lastIndex){
            //The list is a whole piece
            int copyLength = lastIndex-firstIndex+1; //should equal to size
            System.arraycopy(items, firstIndex, newItems, 0, copyLength);
            nextFirst = newItems.length-1; //the last element of the array
            nextLast = (size)%(newItems.length); //the element after the list
        }
        else{
            //The list is in two pieces
            int copyFirstLength = items.length-firstIndex;
            int copyLastLength = lastIndex+1; //should equal to size-copyFirstLength
            System.arraycopy(items, firstIndex, newItems, 0, copyFirstLength);
            System.arraycopy(items, 0, newItems, copyFirstLength, copyLastLength);
            nextFirst = newItems.length-1;
            nextLast = (size)%(newItems.length);
        }
        items = newItems;
    }

    public void addFirst(T item){
        items[nextFirst] = item;
        //keep nextFirst in range(0, length-1)
        nextFirst = (--nextFirst)%(items.length); //need to verify
        nextFirst = modPos(nextFirst);
        size += 1;
        if (size == items.length){
            resize(size*2);
        }
    }

    public void addLast(T item){
        items[nextLast] = item;
        //keep nextLast in range(0, length-1)
        nextLast = (++nextLast)%(items.length);
        size += 1;
        if (size == items.length){
            resize(size*2);
        }
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        if (nextFirst<items.length-1){
            for (int i =nextFirst+1; i<items.length; i++){
                System.out.print(items[i]);
                if (i!=items.length-1){System.out.print("-->");}
            }
        }
        for (int i=0; i<nextLast; i++){
            System.out.print("-->");
            System.out.print(items[i]);
        }
        System.out.println();
    }


    public T removeFirst(){
        if (size==0){
            System.out.println("The list is empty");
            return null;
        }
        int firstIndex = (nextFirst+1)%items.length;
        T returnValue = items[firstIndex];
        //Null out unnecessary value
        items[firstIndex] = null;
        nextFirst = firstIndex;
        size -=1;
        // keep array's length >= minArrayLength
        if ((size*4)<items.length && items.length >= minArrayLength*4){
            resize(items.length/4);
        }
        return returnValue;
    }


    public T removeLast(){
        if (size==0){
            System.out.println("The list is empty");
            return null;
        }
        int lastIndex = (nextLast-1)%items.length;
        lastIndex = modPos(lastIndex);
        T returnValue = items[lastIndex];
        nextLast = lastIndex;
        //Null out unnecessary value
        items[lastIndex] = null;
        size -= 1;
        // keep array's length >= minArrayLength
        if ((size*4)<items.length && items.length >= minArrayLength*4){
            resize(items.length/4);
        }
        return returnValue;
    }


    public T get(int index){
        if (index<size){
            int realIndex = (nextFirst+1+index)%(items.length);
            return items[realIndex];
        }
        else{
            System.out.println("Index provided out of range!");
            return null;
        }
    }
}
