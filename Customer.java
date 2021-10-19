/*
Name: Esha Sawant
Date: 5/10/21
Abstract: The customer class initializes customers when its constructor is called. It has 4 private variables and each have their getter methods.
It also overrides the toString method
 */

public class Customer {
    private String name;
    private String address;
    private int zip;
    private String ssn;

    //ArrayList<String> customers = new ArrayList<String>(10);
//Constructor
    public Customer(String name, String address, int zip,String ssn) {
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.ssn=ssn;
    }
/*
    public void newCustomer(String name, String address, int zip,String ssn){
        new Customer(name,address,zip,ssn);
    }
 */

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getZip() {
        return zip;
    }

    public String getSsn(){return ssn;}

    //Overrides
    public String toString(){
        return name+" : "+address+","+zip+" : "+ssn;
    }

}
