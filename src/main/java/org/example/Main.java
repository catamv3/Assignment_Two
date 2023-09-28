package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * this file is a launchpoint for the program.
 * @author Michael Catalanotti
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //Section 1 Demonstrate Account class

        //Demonstrate default, copy, and parameterized constructor
        Account empty = new Account();
        Account m = new Account("Michael Catalanotti",10000.0);
        Account mCopy = new Account(m);

        //should print false
        System.out.println(m.equals(empty));
        //should print true
        System.out.println(m.equals(mCopy));
        empty.setBalance(m.getBalance());
        empty.setName(m.getName());
        //should print true
        System.out.println(m.equals(empty));

        //Step 1: open the account input file
        //Step 2: Create an instance of the priority queue
        //Step 3: populate the priority queue
        PQList listOne = new PQList();
        File accountFile = new File("accounts.txt");
        Scanner scnr = new Scanner(accountFile);

        int i = 0;
        while (scnr.hasNextLine() && i < 4) {
            String name = scnr.nextLine();
            String b = scnr.nextLine();
            double bal = Double.parseDouble(b);
            Account temp = new Account(name, bal);
            listOne.add(temp);
            i++;
        }

        //demonstrating getLength();
        System.out.println(listOne.getLength());

        //demonstrating getMax()
        System.out.println(listOne.getMax());

        //demonstrating PQList copy constructor
        listOne.showList();
        PQList listTwo = new PQList(listOne);
        System.out.println();
        listTwo.showList();

        //demonstrating clear() and isEmpty()
        //if it works, this line will print TRUE when run
        listTwo.clear();
        System.out.println(listTwo.isEmpty());

        //this method removes all items in a list one by one and prints them to the screen
        listTwo.removeAndShow();
        PQList listThree = listOne.deepCopy();
        listThree.removeAndShow();

    }//close main
}//close class