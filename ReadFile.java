// Right click your IntelliJ IDEA project root directory and select New > Directory.
// Call the new directory "resources" and place your .txt file in it.

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {

    public static void main(String[] args) {

        Scanner inputStream = null;

        try {
            inputStream = new Scanner(new FileInputStream("resources/test.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found or could not be opened.");
            System.exit(0);
        }

        // number of Customers
        int numCustomers = inputStream.nextInt();
        // swallow newline character
        inputStream.nextLine();

        // first customer... (you will most likely want to use a loop here using the number above...)
        String customer1 = inputStream.nextLine();

        System.out.printf("%nThe number of accounts is %d.%n", numCustomers);
        System.out.printf("First customer: %s%n", customer1);
    }
}