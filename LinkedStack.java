/*
 * Student: Yauheniya Zapryvaryna
 * Instuctor: Bill Iverson
 * Bellevue College
 * CS 211
 * June 4, 2014
 * 
 * Class LinkedStack <E> was written to implement a Stack using a linked list
 */

import java.util.*;

public class LinkedStack <E> implements Iterable<E> {
    private ListNode<E> top;  // first value in the list
    private int size;         // current number of elements
  
    // default constructor that creates an empty LinkedStack
    public LinkedStack() {
        top = new ListNode<E>(null);
    }
    
    //tests if the stack is empty
    public boolean empty() {
        return top.next == null;
    }
    
    //Pushes an item onto the top of this stack.
    public E push(E item) {
    	top.next= new ListNode<E>(item, top.next, top);
        size++;
        return item;
    }
    
    //Removes the object at the top of this stack and returns that object as the value of this function.
    public E pop() {
       E answer;
       if (top.next == null) {
          throw new EmptyStackException();
       }
       answer = top.next.data;
       top.next = top.next.next;
       return answer;
    }  
    
    // Looks at the object at the top of this stack without removing it from the stack.
    public E peek() {
       if (top.next == null) {
          throw new EmptyStackException( );
       }
       return top.next.data;
    }
    /******************END OF MY CODE*******************************************/
    
    // post: returns an iterator for this list (method by authors of BJP)
    public Iterator<E> iterator() {
        return new LinkedIterator();
    }

    /*************************************NOT MY CODE*******************************/

    private static class ListNode<E> {
        public E data;         // data stored in this node
        public ListNode<E> next;  // link to next node in the list
        public ListNode<E> prev;  // link to previous node in the list

        // post: constructs a node with given data and null links
        public ListNode(E data) {
            this(data, null, null);
        }

        // post: constructs a node with given data and given links
        public ListNode(E data, ListNode<E> next, ListNode<E> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private class LinkedIterator implements Iterator<E> {
        private ListNode<E> current;  // location of next value to return
        private boolean removeOK;  // whether it's okay to remove now

        // post: constructs an iterator for the given list
        public LinkedIterator() {
            current = top.next;
            removeOK = false;
        }

        // post: returns true if there are more elements left, false otherwise
        public boolean hasNext() {
            return current != null;
        }

        // pre : hasNext()
        // post: returns the next element in the iteration
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E result = current.data;
            current = current.next;
            removeOK = true;
            return result;
        }

        // pre : next() has been called without a call on remove (i.e., at most
        //       one call per call on next)
        // post: removes the last element returned by the iterator
        public void remove() {
            if (!removeOK) {
                throw new IllegalStateException();
            }
            ListNode<E> prev2 = current.prev.prev;
            prev2.next = current;
            current.prev = prev2;
            size--;
            removeOK = false;
        }
    }
}
