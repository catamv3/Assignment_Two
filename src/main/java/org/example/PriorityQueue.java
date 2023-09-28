package org.example;

/**
 * This interface stores methods to be implemented by PQList class.
 * the methods include add(Account a), getMax(), clear(), getLength(), isEmpty()
 * @author Michael Catalanotti
 */
public interface PriorityQueue {

    void add(Account a);

    Account getMax();
    void clear();
    int getLength();
    boolean isEmpty();

}
