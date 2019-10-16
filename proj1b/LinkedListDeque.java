public class LinkedListDeque<T> implements Deque<T>{

    /** class of Deque node*/
    private class DequeNode{
        private T item;
        private DequeNode next;
        private DequeNode prev;
        public DequeNode(T i, DequeNode r, DequeNode p){
            item = i;
            next = r;
            prev = p;
        }

        public DequeNode(DequeNode n){
            prev = n.prev;
            //item = n.item;
            next = n.next;
        }
    }

    private int size;
    private DequeNode sentinel;
    public LinkedListDeque(){
        sentinel = new DequeNode(null,null,null);
        // circular sentinel node
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item){
        DequeNode firstNode =  new DequeNode(item, sentinel.next, sentinel);
        sentinel.next.prev = firstNode;
        sentinel.next = firstNode;
        size += 1;
    }

    @Override
    public void addLast(T item){
        DequeNode lastNode = new DequeNode(item, sentinel, sentinel.prev);
        sentinel.prev.next = lastNode;
        sentinel.prev = lastNode;
        size += 1;
    }

    @Override
    public boolean isEmpty(){
        return (size == 0);
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        if (size==0){
            System.out.println("None items!");
        }
        DequeNode tempNode = sentinel.next;

        for (int i=0; i<size; i++){
            System.out.print((String)tempNode.item);
            if(i!=size-1){ System.out.print("-->"); }
            tempNode = tempNode.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst(){
        if (size == 0){
            System.out.println("The list is empty!");
            return null;
        }
        T itemToMove = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next; //By then there should be no reference to the first node
        size-=1;
        return itemToMove;
    }

    @Override
    public T removeLast(){
        if (size == 0){
            System.out.println("The list is empty!");
            return null;
        }
        T itemToMove = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size-=1;
        return itemToMove;

    }

    @Override
    public T get(int index){
        if (size==0){
            System.out.println("The list is empty!");
        }
        if (index+1 > size){
            System.out.println("Index provided out of range");
        }
        DequeNode nodeToGet = sentinel;
        for (int i=0; i<index+1;i++){
            nodeToGet = nodeToGet.next;
        }
        return nodeToGet.item;
    }

    private T getRecursiveHelp(DequeNode d, int i){
        if (i==0){
            return d.item;
        }
        else{

            return getRecursiveHelp(d.next, --i);
        }
    }

    public T getRecursive(int index){
        if (size==0){
            System.out.println("The list is empty!");
        }
        return getRecursiveHelp(sentinel.next, index);

    }
}
