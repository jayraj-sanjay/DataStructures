import java.util.NoSuchElementException;

public class SingleLinkedList {
    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public void addFront(int d) {
        Node newNode;
        if (size == 0) {
            head = new Node(d, null);
            tail = head;

        } else {
            newNode = new Node(d, head);
            head = newNode;
        }
        size++;
    }

    public void addBack(int d) {
        Node newNode;
        if (size == 0) {
            newNode = new Node(d, null);
            tail = newNode;
            head = tail;
        } else {
            newNode = new Node(d, null);
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public int removeFront() {
        int res;
        if (size == 0)
            throw new NoSuchElementException();
        else if (size == 1) {
            res = head.data;
            head = null;
            tail = null;
        } else {
            res = head.data;
            head = head.next;
        }
        size--;
        return res;
    }

    public int removeBack() {
        Node temp = head;
        int res;
        if (size == 0)
            throw new NoSuchElementException();
        else if (size == 1) {
            res = head.data;
            tail = head = null;
        } else {
            temp = head;
            while (temp.next.next != null)
                temp = temp.next;
            res = temp.next.data;
            temp.next = null;
            tail = temp;
        }
        size--;
        return res;
    }

    public int get(int index) {
        Node temp = head;
        if (index > size || index <= 0)
            throw new IndexOutOfBoundsException("index " + index + " is outside size of list " + size);
        else {
            for (int j = 0; j < index - 1; j++) {
                temp = temp.next;
            }
        }
        return temp.data;
    }

    public void reverse() {
        if (head == null || head == tail) // 0 or 1 nodes
            return;

        Node p1 = null;
        Node p2 = head;
        Node p3 = head.next;

        while (p3 != null) {
            p2.next = p1;
            p1 = p2;
            p2 = p3;
            p3 = p3.next;
        }
        p2.next = p1;

        Node temp = head;
        head = tail;
        tail = temp;

    }


    public static class Node {
        int data;
        Node next;

        Node(int d, Node n) {
            data = d;
            next = n;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node temp = head;
        while (temp != null) {
            sb.append(temp.data + " ");
            temp = temp.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SingleLinkedList s = new SingleLinkedList();
        for (int i = 0; i < 5; i++) {
            s.addFront(i);
        }
        System.out.println("Elements added to the front.");
        System.out.println(s);
        SingleLinkedList s1 = new SingleLinkedList();
        for (int i = 0; i < 5; i++) {
            s1.addBack(i);
        }
        System.out.println("Elements added to the back:");
        System.out.println(s1);
        s.removeBack();
        System.out.println("Element removed from the back:");
        System.out.println(s);
        s.removeFront();
        System.out.println("Element removed from the front:");
        System.out.println(s);
        System.out.println("Get element at position 2");
        System.out.println(s.get(2));
        s.reverse();
        System.out.println("Reverse the list:");
        System.out.println(s);


    }
}
