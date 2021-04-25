import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class AppLogic {
}

class Customer {
    String ID;
    String Password;
    String Name;
    String Address;
    String PhoneNo;
    String CreditCardNo;
    String MembershipType;

    Customer() {
        //Instantiate the class and set empty string for string data type and 0 for long data type
        this.ID = " ";
        this.Password = " ";
        this.Name = " ";
        this.Address = " ";
        this.PhoneNo = " ";
        this.CreditCardNo = " ";
        this.MembershipType = " ";
    }

    Customer(String iD, String password, String Name, String Address, String PhoneNo, String CreditCard, String Membership) {
        //Instantiate the class and set empty string for string data type and 0 for long data type
        this.ID = iD;
        this.Password = password;
        this.Name = Name;
        this.Address = Address;
        this.PhoneNo = PhoneNo;
        this.CreditCardNo = CreditCard;
        this.MembershipType = Membership;
    }

    void getCustomer(String ID) {
        try {
            String customer="";

            File file = new File("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\Customers.txt");
            Scanner inputFile = new Scanner(file);

            while(inputFile.hasNext())
            {
                customer = inputFile.nextLine();
                String customer_array[] = customer.split(",");
                if (customer_array[0].equals(ID)) {
                    this.ID = customer_array[0];
                    this.Password = customer_array[1];
                    this.Name = customer_array[2];
                    this.Address = customer_array[3];
                    this.PhoneNo = customer_array[4];
                    this.CreditCardNo = customer_array[5];
                    this.MembershipType = customer_array[6] + "\r";
                    break;
                }
            }



            inputFile.close();
        }

        catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("Data retrieved successfully");
    }

    void setCustomer() {
        String customer;
        //Open the file to print data to Customers database "Customers.txt"
        try {
            File outputFile = new File("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\Customers.txt");
            if(!outputFile.exists()) {
                outputFile.createNewFile();
            }

            FileWriter fileWriter = new FileWriter("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\Customers.txt", true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            //Write data of customer in CSV format
            customer = (this.ID + "," + this.Password + "," + this.Name + "," + this.Address + "," + this.PhoneNo + "," + this.CreditCardNo + "," + this.MembershipType + "\r") ;

            //if Id is not an empty string write data of the customer to the database
            if (this.ID != " ") {
                bw.write(customer);
            }

            //close the file "customers.txt"
            bw.close();
        }

        catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("Successfully written to file!");
    }

    void printCustomer() {
        System.out.println("Customer Details: ");
        System.out.println("ID: " + this.ID);
        System.out.println("Password: " + this.Password);
        System.out.println("Name: " + this.Name);
        System.out.println("Address: " + this.Address);
        System.out.println("Phone No.: " + this.PhoneNo);
        System.out.println("Credit Card No.: " + this.CreditCardNo);
        System.out.println("Membership Type: " + this.MembershipType);
    }
}



class CustomerLogOn extends Customer {
    private String ID;
    private String Password;
    // actual login check
    public CustomerLogOn(String ID, String password){
        this.ID = ID;
        this.Password = password;
    }

    public Customer LoginCheck(){
        // initialize new customer
        var customer = new Customer();
        customer.getCustomer(ID);

        // compare with our current data
        if(this.ID.equals(customer.ID)){
            if(this.Password.equals(customer.Password)){
                System.out.println("Welcome user: "+customer.ID);
                return customer;
            }
        }
        System.out.println("Input is either wrong or Does not exist");
        return new Customer();
    }


}

class GetUserOrders {
    public LinkedList<Order> getOrder(String ID) {
        try {
            String order;

            File file = new File("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\Orders.txt");
            Scanner inputFile = new Scanner(file);
            LinkedList<Order> user_orders = new LinkedList<>();
            while(inputFile.hasNext())
            {
                Order tempO = new Order();
                Item temp = new Item();
                order = inputFile.nextLine();
                String[] order_array = order.split(",");
                if (order_array[0].equals(ID)) {
                    String elements;
                    tempO.ID= order_array[0];
                    tempO.orderNo = order_array[1];
                    tempO.orderStatus = order_array[2];
                    tempO.totalPricePaid = order_array[3];
                    elements = order_array[4];
                    //System.out.println(elements);
                    String[] elements_array = elements.split(";");
                    int n = elements_array.length;
                    for(int l = 1; l<= n;l++){
                        Item temp2 = new Item();
                        if( l%2== 1) {
                            temp.getItem(elements_array[l-1]);
                            //temp.printItem();

                        }
                        if(l%2== 0 ){
                            temp.Quantity= elements_array[l-1];
                            temp2.Quantity= temp.Quantity;
                            temp2.PremiumPrice= temp.PremiumPrice;
                            temp2.Name = temp.Name;
                            temp2.RegularPrice = temp.RegularPrice;
                            temp2.Description = temp.Description;
                            temp2.PrimaryKey= temp.PrimaryKey;
                            //temp.printItem();
                            tempO.Items.add(temp2);
                        }
                    }
                    user_orders.add(tempO);
                }

            }
            inputFile.close();
            return user_orders;

        }
        catch (IOException e) {
            System.out.println(e);
        }
        return new LinkedList<>();
    }
}