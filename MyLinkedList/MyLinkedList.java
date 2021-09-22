public class MyLinkedList<AnyType> implements Iterable<AnyType> {

    /**
     * Construct an empty LinkedList.
     */
    public MyLinkedList() {
        doClear();
    }

    private void clear() {
        doClear();
    }

    /**
     * Change the size of this collection to zero.
     */
    public void doClear() {
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    /**
     * Returns the number of items in this collection.
     *
     * @return the number of items in this collection.
     */
    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Adds an item to this collection, at the end.
     *
     * @param x any object.
     * @return true.
     */
    public boolean add(AnyType x) {
        add(size(), x);
        return true;
    }

    /**
     * Swaps the list element at position x with the element at position y.
     *
     * @param x an integer index.
     * @param y an integer index.
     * @return true.
     */
    public void swap(int x, int y) {
        Node<AnyType> a = this.getNode(x);
        Node<AnyType> b = this.getNode(y);
        if (a != null && b != null) {
            Node<AnyType> temp1 = b.prev;
            Node<AnyType> temp2 = b.next;
            a.prev.next = b;
            a.next.prev = b;
            b.next = a.next;
            b.prev = a.prev;

            temp1.next = a;
            temp2.prev = a;
            a.prev = temp1;
            a.next = temp2;
            modCount++;
        }
        return;
    }

    public void shiftNodes(int n) {
        if (n < 0) {
            backShift(-1 * n);
        } else {
            frontShift(n);
        }
        return;
    }

    public void frontShift(int n) {

        while (n-- > 0) {
            Node<AnyType> currBegin = beginMarker.next;
            beginMarker.next = currBegin.next;
            currBegin.next.prev = beginMarker;
            currBegin.prev = endMarker.prev;
            currBegin.next = endMarker;
            endMarker.prev = currBegin;
            currBegin.prev.next = currBegin;
            modCount++;
        }
        return;

    }

    public void backShift(int n) {

        while (n-- > 0) {
            Node<AnyType> currEnd = endMarker.prev;
            endMarker.prev = currEnd.prev;
            currEnd.prev.next = endMarker;
            currEnd.prev = beginMarker;
            currEnd.next = beginMarker.next;
            currEnd.next.prev = currEnd;
            beginMarker.next = currEnd;
            modCount++;
        }
        return;
    }

    public void eraseNodes(int i, int n) {
        Node<AnyType> a = getNode(i);
        Node<AnyType> b = getNode(i + n - 1);
        if (a != null && b != null && a != beginMarker.next && b != endMarker.prev) {
            a.prev.next = b.next;
            b.next.prev = a.prev;
        }
        return;
    }

    public void insertList(MyLinkedList<AnyType> list2, int i) {
        Node<AnyType> curr = getNode(i);
        if (curr != null) {
            curr.prev.next = list2.beginMarker.next;
            list2.beginMarker.next.prev = curr.prev;
            curr.prev = list2.endMarker.prev;
            list2.endMarker.prev.next = curr;
        }
        return;
    }


    /**
     * Adds an item to this collection, at specified position.
     * Items at or after that position are slid one position higher.
     *
     * @param x   any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    public void add(int idx, AnyType x) {
        addBefore(getNode(idx, 0, size()), x);
    }

    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     *
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    private void addBefore(Node<AnyType> p, AnyType x) {
        Node<AnyType> newNode = new Node<>(x, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }


    /**
     * Returns the item at position idx.
     *
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get(int idx) {
        return getNode(idx).data;
    }

    /**
     * Changes the item at position idx.
     *
     * @param idx    the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set(int idx, AnyType newVal) {
        Node<AnyType> p = getNode(idx);
        AnyType oldVal = p.data;

        p.data = newVal;
        return oldVal;
    }

    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     *
     * @param idx index to search at.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode(int idx) {
        return getNode(idx, 0, size() - 1);
    }

    /**
     * Gets the Node at position idx, which must range from lower to upper.
     *
     * @param idx   index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */
    private Node<AnyType> getNode(int idx, int lower, int upper) {
        Node<AnyType> p;

        if (idx < lower || idx > upper)
            throw new IndexOutOfBoundsException("getNode index: " + idx + "; size: " + size());

        if (idx < size() / 2) {
            p = beginMarker.next;
            for (int i = 0; i < idx; i++)
                p = p.next;
        } else {
            p = endMarker;
            for (int i = size(); i > idx; i--)
                p = p.prev;
        }

        return p;
    }

    /**
     * Removes an item from this collection.
     *
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove(int idx) {
        return remove(getNode(idx));
    }

    /**
     * Removes the object contained in Node p.
     *
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove(Node<AnyType> p) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;

        return p.data;
    }

    /**
     * Returns a String representation of this collection.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");

        for (AnyType x : this)
            sb.append(x + " ");
        sb.append("]");

        return new String(sb);
    }

    /**
     * Obtains an Iterator object used to traverse the collection.
     *
     * @return an iterator positioned prior to the first element.
     */
    public java.util.Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }

    /**
     * This is the implementation of the LinkedListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyLinkedList.
     */
    private class LinkedListIterator implements java.util.Iterator<AnyType> {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        public boolean hasNext() {
            return current != endMarker;
        }

        public AnyType next() {
            if (modCount != expectedModCount)
                throw new java.util.ConcurrentModificationException();
            if (!hasNext())
                throw new java.util.NoSuchElementException();

            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        public void remove() {
            if (modCount != expectedModCount)
                throw new java.util.ConcurrentModificationException();
            if (!okToRemove)
                throw new IllegalStateException();

            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }
    }

    /**
     * This is the doubly-linked list node.
     */
    private static class Node<AnyType> {
        public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
            data = d;
            prev = p;
            next = n;
        }

        public AnyType data;
        public Node<AnyType> prev;
        public Node<AnyType> next;
    }

    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println("Printing base linked list");
        System.out.println(list);
        System.out.println("Swapping positions 1 and 7");
        list.swap(1, 7);
        System.out.println(list);
        System.out.println("Back Shift by 2 positions:");
        list.shiftNodes(-2);
        System.out.println(list);
        System.out.println("Front Shift by 2 positions:");
        list.shiftNodes(2);
        System.out.println(list);
        System.out.println("Erase nodes starting at position 2 and 3 elements");
        list.eraseNodes(2, 3);
        System.out.println(list);
        MyLinkedList<Integer> list2 = new MyLinkedList<>();
        for (int j = 2; j < 5; j++) {
            list2.add(j);
        }
        System.out.println("Second list to be added:");
        System.out.println(list2);
        System.out.println("Adding list 2 to index position 2:");
        list.insertList(list2, 2);
        System.out.println(list);
    }
}
