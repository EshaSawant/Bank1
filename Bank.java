/*
Name: Esha Sawant
Project: Bank
Date: 5/10/21
Abstract: The bank class contains all the methods that do specific actions when called. It has a constructor that initializes the bank name.
The bank class can print bank information, account information, it can create new customers and accounts, allows transactions- withdraw,deposit or close account, give account details with ssn,
and can remove an account and customer when called.
It uses 5 Arraylists and 1 list to iterate through different customer, account and transaction values.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Bank {
    public String name;
    //float amt;
    int numCustomers;
    int numAccount;
    List<String> ssnList=new ArrayList<>();
    ArrayList<Account> acc1 = new ArrayList<>(30);
    ArrayList<Customer> cus = new ArrayList<>(30);
    ArrayList<Transaction> transact = new ArrayList<>(100);
    ArrayList<String> customers = new ArrayList<>(30);
    ArrayList<String> account = new ArrayList<>(30);


    public Bank(String name) {
        this.name = name;
    }

    //Prints accounts and customers and the total balance in the bank.
    public void bankInfo() {
        double tot = 0;
        System.out.println("Bank Name: " + name);
        System.out.printf("%nThe number of customers is %d.%n", numCustomers);
        for (Customer c : cus) {
            if (c != null) {
                System.out.println(c);
            }
        }
        System.out.printf("%nThe number of accounts is %d.%n", numAccount);
        for (Account a : acc1) {
            if (a != null) {
                System.out.println(a);
            }
        }
        for (Account a : acc1) {
            tot = tot + a.getAmt();
        }
        System.out.println("\nTotal Amount: $" + tot);
    }


    //Prints the account information of the account no passed as parameter.
    public void accountInfo(int accNo) {
        boolean flag=false;
        for (Account a : acc1) {
            if (a != null) {
                if (a.getAccNo() == accNo) {
                    System.out.println("\t-Number: " + a.getAccNo());
                    System.out.println("\t-" + a.getType());
                    System.out.println("\t-Balance: $" + a.getAmt());
                    //System.out.println();
                    for (Customer c : cus) {
                        if (c.getSsn().equals(a.getSsn())) {
                            System.out.println("\t-Name: " + c.getName());
                            flag=true;
                            System.out.println();
                        }
                    }
                }
            }
        }
        if(flag==true){
            return;
        }else{
            System.out.println("No Customer with SSN");
            System.out.println();
        }
    }


//Reads data from the file passed as string.
    public void readData(String file) {
        Scanner inputStream = null;

        try {
            inputStream = new Scanner(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            System.out.println("File not found or could not be opened.");
            System.exit(0);
        }

        // number of Customers
        numCustomers = inputStream.nextInt();
        // swallow newline character
        inputStream.nextLine();

        // first customer... (you will most likely want to use a loop here using the number above...)
        for (int i = 0; i < numCustomers; i++) {
            String customer1 = inputStream.nextLine();
            customers.add(customer1);
        }

        for (String i : customers) {
            String[] a = i.split(",");
            List<String> ab = Arrays.asList(a);
            cus.add(new Customer(ab.get(0), ab.get(1), Integer.parseInt(ab.get(2)), ab.get(3)));
        }

        numAccount = inputStream.nextInt();
        inputStream.nextLine();
        for (int i = 0; i < numAccount; i++) {
            account.add(inputStream.nextLine());
        }

        for (String i : account) {
            String[] a = i.split(",");
            List<String> ab = Arrays.asList(a);
            acc1.add(new Account(ab.get(0), Integer.parseInt(ab.get(1)), Integer.parseInt(ab.get(2)), Double.parseDouble(ab.get(3))));
        }

    }

//Initializes new customers if not read from the txt file.
    public void newCustomer(String name, String addr, int zip, String ssn) {
        for (Customer c : cus) {
            if (c != null) {
                if (Objects.equals(c.getSsn(), ssn)) { //checks if the customer exists or not
                    System.out.println(name + " is not added- Duplicated SSN");
                    return;
                }
            }
        }
        cus.add(new Customer(name, addr, zip, ssn));
        numCustomers++;
        System.out.println(name + " is added");
        //System.out.println(cus);
    }

//Initializes new accounts if not read from the text file
    public void newAccount(String ssn, int accNo, int type, double balance) {
        boolean flag = false;
        for (Account a : acc1) {
            checkSsn();
                if (ssnList.contains(ssn)){  //Check if the customer exists
                    System.out.println("No Customer with SSN");
                    flag = true;
                    return;
                }
                if (Objects.equals(a.getSsn(), ssn) && (a.type == type)) { //Checks if the customer has the same type of account or not
                    System.out.println("Account Creation Failed- " + ssn + " already has an " + a.getType() + " account ");
                    flag = true;
                    return;
                } else if (a.getAccNo() == accNo) { //Checks whether if the account no exists or not
                    System.out.println("Account Creation Failed- Account " + accNo + " already exists");
                    flag = true;
                    return;
                    //      }
            }
                }
        if (flag == true) {
            return;
        } else {
            acc1.add(new Account(ssn, accNo, type, balance));
            numAccount++;
            System.out.println("Account Creation - Number: " + accNo);
//        }
        }
    }

    private void checkSsn(){
        for(Customer c:cus) {
            for (Account a : acc1) {
                if(a.getSsn()==c.getSsn()){
                    ssnList.add(a.getSsn());
                    }
            }
        }
        //System.out.println(ssnList);
    }


//Transaction function: Deposit
    public void deposit(int accNo, double amt) {
        for (Account a : acc1) {
            if (a != null) {
                if (a.getAccNo() == accNo) {
                    transact.add(new Transaction(accNo, "Deposit (" + amt + ")"));
                    a.setAmt(a.getAmt() + amt);
                }
            }
        }
    }


//Transaction function: Withdraw
    public void withdraw(int accNo, double amt) {
        for (Account a : acc1) {
            if (a != null) {
                if (a.getAccNo() == accNo) {
                    transact.add(new Transaction(accNo, "Withdraw (" + amt + ")"));
                    a.setAmt(a.getAmt() - amt);
                }
            }
        }
    }


//Transaction function: close Account
    public boolean closeAccount(int accNo) {
        for (Account a : acc1) {
            if (a != null) {
                if (a.getAccNo() == accNo) {
                    transact.add(new Transaction(accNo, "Account Closed"));
                    numAccount--;
                    acc1.remove(a);
                    return true;
                }
            }
        }
        return false;
    }


//Prints the transcation history if the account number passed as parameter
    public void transaction(int accNo) {
        //System.out.println(transact);
        if (transact.isEmpty()) {
            System.out.println("No transaction for account "+accNo);
        }
        else{
            for (Transaction t : transact) {
                if ((t != null) && (t.getAccNo() == accNo)) {
                    //if (a.accNo == accNo) {
                    System.out.println("\n" + t);
                    //}
            } else {
                System.out.println("No transaction for account " + accNo);
                break;
            }
                }
            }
        }


//Compares the last 4 digits of the ssn with the accounts' ssn
    public void customerInfoWithSSN(int last4ssn) {
        boolean flag=false;
        for (Customer c : cus) {
            int sub = Integer.parseInt(c.getSsn().substring(7, 11));
            if (last4ssn == sub) {
                //System.out.println();
                System.out.println("Name: " + c.getName());
                System.out.println("Address: " + c.getAddress() + " ," + c.getZip());
                System.out.println("SSN: " + c.getSsn());
                for (Account a : acc1) {
                    if (a.getSsn().equals(c.getSsn())) {
                        System.out.println(a.getType() + " (" + a.getAccNo() + "), $" + a.amt);
                        flag=true;
                    }
                    //flag=true;
                }
            }
        }
        if(flag==false){
            System.out.println("No Account");
        }
    }


//Removes the customer and their accounts passed as parameter
    public void removeCustomer(String ssn) {
        Customer c1 = null;
        ArrayList<Account> a1=new ArrayList<>();
        boolean flag = false;
        for (Customer c : cus) {
            if (c.getSsn().equals(ssn)) {
                System.out.println("Customer removed - SSN: " + c.getSsn() + " Customer: " + c.getName());
                c1 = c;
                flag = true;
                for (Account a : acc1) {
                    if (c.getSsn().equals(a.getSsn())) {
                        System.out.println("Account closed: -Number: " + a.getAccNo() + ", Balance:" + a.amt);
                        a1.add(a);
                    }
                    }
                }
            }
            if (flag == true) {
                cus.remove(c1);
                numCustomers--;
                for(Account ab:a1) {
                    if (acc1.contains(ab)) {
                        acc1.remove(ab);
                        numAccount--;
                    }
                }
                //System.out.println(acc1);
                //System.out.println(cus);
            } else {
                System.out.println("Customer Removed failed. SSN does not exist");
            }
    }
}