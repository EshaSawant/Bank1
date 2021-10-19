/*
Name: Esha Sawant
Date: 5/10/21
Abstract: The transaction class has 3 private variables and each has a getter method.It also overrides the toString method.
The transaction class timestamps if any of the transaction functions are called- deposit, withdraw or close account.
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm:ss");

    private String transactionTime;
    private String description;
    private int accNo;

    /*
    public Transaction(int accNo,double amount) {
        this.transactionTime = LocalDateTime.now();
        this.amount = amount;
    }
     */

    //Constuctor
    public Transaction(int accNo,String description){
        this.transactionTime=now.format(formatter);
        this.accNo=accNo;
        this.description=description;
    }

    public int getAccNo() {
        return accNo;
    }

    public String getDescription(){
        return description;
    }

    public String getTransactionTime(){
        return transactionTime;
    }

    //Overrides
    public String toString() {
        return "-Account Number: "+accNo+","+description+","+transactionTime;
    }
}