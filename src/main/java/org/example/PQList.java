package org.example;

/**
 * @author Michael Catalanotti
 * this file stores infromation for a PQList. This class implements the PriorityQueue
 * interface. Additionally, PQList implements a priority queue using a singly linked list.
 * This priority queue will prioritize accounts with the highest balance first.
 */
public class PQList implements PriorityQueue {

    /**
     * this class stores information for a node object. each node in the singly linked list
     * contains an account object as well as a pointer to the next node in the list.
     */
    public class Node {
        Node next;
        Account data;
    }

    //private member fields, Node head and int length
    private Node head;
    private int length;

    /**
     * default constructor for a PQList
     * set head = null, length = 0
     */
    public PQList() {
        head = null;
        length = 0;
    }

    /**
     * this is a copy constructor for a PQList that instantiates
     * a deep copy of the list provided as an argument.
     * @param other the list to be copied.
     */
    public PQList(PQList other) {
        //check if the list is empty
        if (other.isEmpty()) {
            head = null;
            length = 0;
        } else {
            /**
             * if not empty, declare a temp list and iterate through this
             * list and add to the temp list.
             */
            Node temp = other.head;
            PQList tempList = new PQList();
            while (temp.next != null) {
                tempList.add(temp.data.deepCopy());
                temp = temp.next;
                if (temp.next == null) {
                    tempList.add(temp.data.deepCopy());
                    break;
                }
            }

            /**
             * the temp list holds a backwards version of the og list order.
             * iterate through the temporary list and add to the current list.
             */
            temp = tempList.head;
            while (temp.next != null) {
                add(temp.data.deepCopy());
                temp = temp.next;
                if (temp.next == null) {
                    add(temp.data.deepCopy());
                    break;
                }
            }
        }

    }


    /**
     * this method returns a deep copy of the PQList that calls the method.
     * @return deep copy of the current list.
     */
    public PQList deepCopy() {
        PQList dCopy = new PQList();
        if(!isEmpty()){
            //declare two lists
            PQList copy = new PQList();

            Node temp = head;
            /**
             * use while loop to iterate through the list being copied, and add each element
             * to the first newly declared lists.
             */
            while (temp.next != null) {
                copy.add(temp.data.deepCopy());
                temp = temp.next;
                if (temp.next == null) {
                    copy.add(temp.data.deepCopy());
                    break;
                }
            }
            /**
             * set a temp node to the head of the newly created list, and add
             * each element to the other newly declared list.
             * the last list will contain deep copies, in the correct order.
             */
            temp = copy.head;
            while (temp.next != null) {
                dCopy.add(temp.data.deepCopy());
                temp = temp.next;
                if (temp.next == null) {
                    dCopy.add(temp.data.deepCopy());
                    break;
                }
            }
        }
        //return list
        return dCopy;
    }

    /**
     * This method adds an item into the priority queue and maintains the priority for sorting
     * the list according to acocunts with the biggest balance.
     * This list uses the helper methods remove() and getMax() as well
     * as an array to order accounts correctly.
     * @param a account to be added to the list.
     */
    @Override
    public void add(Account a) {
        //cleck if list is empty
        if(isEmpty()){
            Node temp = new Node();
            temp.data = a.deepCopy();
            temp.next = head;
            head = temp;
            length++;
            //case: list has more than one item
        } else {
            Node temp = new Node();
            temp.data = a.deepCopy();
            temp.next = head;
            head = temp;
            length++;

            // declare an array of type accounts and set it to the length of the current list.
            Account[] sortedList = new Account[getLength()];
            Account iter = new Account();
            int i = 0;
            /**
             * use a while loop to iterate through the current list while it is not empty,
             * remove the maximum item,
             * store that account in the acocunt array
             */
            while (!isEmpty()) {
                iter = remove(getMax());
                sortedList[i] = iter.deepCopy();
                i++;
            }
            //clear the list so there are no duplicates
            clear();
            //for loop that runs for the length of array calling the insertItem() to add to the list
            //results in priorirty queue!
            for(int j = sortedList.length; j> 0; j--) {
                insertItem(sortedList[(j-1)]);
            }
        }

    }

    /**
     * this is a helper method for the add()
     * @param a account to be added to the list.
     */
    private void insertItem(Account a) {
        Node temp = new Node();
        temp.data = a.deepCopy();
        temp.next = head;
        head = temp;
        length++;
    }

    /**
     * this method removes all items in the list and prints them to the console
     * one by one!
     */

    public void removeAndShow(){
        if(isEmpty()){
            System.out.println("Empty list!");
        } else {
            Account temp;
            while(!isEmpty()){
                 temp = remove(getMax());
                System.out.println(temp);
            }
        }

    }

    /**
     * this is a helper method for the add(). it is used to remove an item from a list.
     * this method should only be called with getMax() as an argument!!!
     * @param a account to remove.
     * @return the removed account.
     */
    private Account remove(Account a){
        Node temp = new Node();
        Account remove = new Account();
        temp = head;
        //case: the head of the list is the object being removed
        if(temp.data.getBalance()==a.getBalance()){
            remove = head.data.deepCopy();
            temp = temp.next;
            head = temp;
            length--;
        }else{
            //case: the object is not the head of the list.
            temp = head;
            while(temp.next!= null && !(temp.data.getBalance()==a.getBalance())){
                //if the item is in the middle of the list
                if(a.getBalance() == temp.next.data.getBalance()){
                    remove = temp.next.data.deepCopy();
                    temp.next=temp.next.next;
                    length--;
                    break;
                }
                //if the item being removed is at the end of the list
                if(temp.next.next == null && (temp.next.data.getBalance()==a.getBalance())){
                    remove = temp.next.data.deepCopy();
                    temp.next = null;
                    length--;
                }

                temp = temp.next;
            }
        }
        return remove.deepCopy();
    }


    /**
     * this method iterates through the list and determines which account has the maximum balance.
     * @return the account with the maximum balance.
     */
    @Override
    public Account getMax() {
        Account max;
        if(!isEmpty()){
            Node temp = new Node();
            temp = head;
            max = temp.data;

            while(temp.next != null){
                temp = temp.next;
                if(temp.data.getBalance() > max.getBalance()){
                    max = temp.data.deepCopy();
                }
            }
        } else{
            max = null;
        }
        return max;
    }

    /**
     * This method clears the list.
     */
    @Override
    public void clear() {
        head = null;
        length = 0;
    }

    /**
     * determine the length of the list.
     * @return the length of the  list.
     */
    @Override
    public int getLength() {
        return length;
    }

    /**
     * this method determines if a list is empty.
     * @return true if empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return ((head == null) && (length == 0));
    }

    /**
     * this is a helper method that prints the entire contents of a list to the console.
     */
    public void showList() {
        if (head == null) {
            System.out.println("Empty list");
        } else {
            Node temp = new Node();
            temp = head;

            while (temp.next != null) {
                System.out.println(temp.data.toString());
                temp = temp.next;
                if (temp.next == null) {
                    System.out.println(temp.data.toString());
                    break;
                }
            }
        }
    }
}
