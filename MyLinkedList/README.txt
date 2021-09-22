Used an existing Doubly Linked List data structure named MyLinkedList.java from Mark Allen Weiss - Data Structures and Algorithm Analysis in Java 3rd Edition.

Added some additional functions:
1)swap - receives two index positions as parameters, and swaps the nodes at these positions by changing the links, provided both positions are within the current size

2)shift - receives an integer (positive or negative) and shifts the list this many positions forward (if positive) or backward (if negative).  
           1,2,3,4,5    shifted +2    3,4,5,1,2
           1,2,3,4,5    shifted -1    5,1,2,3,4

3)erase - receives an index position and number of elements as parameters, and removes elements beginning at the index position for the number of elements specified, provided the index position is within the size and together with the number of elements does not exceed the size

4)insertList - receives another MyLinkedList and an index position as parameters, and copies the list from the passed list into the list at the specified position, provided the index position does not exceed the size.

All these above functions were tested in the main method of the class.

