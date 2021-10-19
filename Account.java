/*
Name: Esha Sawant
Date: 5/10/21
Abstract: The account class initializes a new account when its constructor is called. It has 2 private variables and 2 public variables and each have their getter and setter methods.
It also overrides the toString method.
 */
public class Account {
    int type;
    private int accNo;
    private String ssn;
    double amt;
    //ArrayList<Customer> customers=new ArrayList<Customer>(10);

    //Constructor
    public Account(String ssn, int accNo, int type, double balance){
        this.ssn=ssn;
        this.accNo=accNo;
        this.type=type;
        this.amt=balance;
    }

    public String getSsn(){return ssn;}

    public double getAmt(){
        return amt;
    }

    public void setAmt(double bal){
        amt=bal;
    }

    public int getAccNo(){
        return accNo;
    }

    public String getType(){
        if(type==1){
            return "Checking";
        }
        return "Savings";
    }

    //Overrides
    public String toString(){
        return accNo+":Balance:"+amt;
    }
}
