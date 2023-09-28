package org.example;

/**
 * this class file stores infromation for an org.example.Account object, containing
 * fields for the account name and the balance in the account.
 * @author Michael Catalanotti
 * 09/13/2023
 */
public class Account {//open class

    //declare member variables: name (string) balance (double)
    private String name;
    private double balance;

    /**
     * default constructor for an account object. sets the account name to empty account and balance to zero.
     */
    public Account() {
        name = "Empty org.example.Account";
        balance = 0.0;
    }

    /**
     * overloaded constructor for an account object. This constructor
     * instantiates an account object with the account name and balance
     * provided.
     *
     * @param n account name
     * @param b account balance
     */
    public Account(String n, double b) {
        name = n;
        balance = b;
    }

    /**
     * Copy constructor for an org.example.Account object. This constructor  returns a deep
     * copy of the org.example.Account object provided.
     *
     * @param c - account object to be made into a deep copy.
     */
    public Account(Account c) {
        name = c.getName();
        balance = c.getBalance();
    }

    /**
     * get method for name variable.
     *
     * @return - name variable
     */
    public String getName() {
        return name;
    }

    /**
     * set method for name variable.
     *
     * @param name - name of the account.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get method for balance variable.
     *
     * @return balance - account balance, double.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * set method for balance variable
     *
     * @param balance - balance amount.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Account deepCopy() {
        Account copy = new Account(this.getName(), this.getBalance());

        return copy;
    }





    /**
     * This method below determines if two accounts are equal. The method accepts type
     * Object and is cast as an account.
     * @param a -  Object class
     * @return true if accounts are equal in value for name and balance.
     *
     */

    @Override
    public boolean equals(Object a) {
        Account other = (Account) a;
        if(other.getName().length() == name.length()){
            for(int i = 0; i < other.getName().length(); i++){
                if(other.getName().charAt(i) != name.charAt(i)){
                    return false;
                }
            }
            } else {
                return false;
            }
        return true;
    }


    /**
     * this is a helper method for developers to use to check
     * and validate deep copies and such.
     * @return
     */

    /**
     @Override
     public String toString() {
     String str = "";

     str = String.format("%s\n%.2f\n", getName(), getBalance());
     return str;
     }
     */



}
