import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

    void setCustomer(boolean n) {
        String customer;
        //Open the file to print data to Customers database "Customers.txt"
        try {                                       //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
            File outputFile = new File("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\Customers.txt");
            customer = (this.ID + "," + this.Password + "," + this.Name + "," + this.Address + "," + this.PhoneNo + "," + this.CreditCardNo + "," + this.MembershipType + "\r") ;
            if(!outputFile.exists() || n) {
                outputFile.createNewFile();                     //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
                FileWriter fileWriter = new FileWriter("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\Customers.txt");
                fileWriter.write(customer);
                fileWriter.close();
            }

            else {                                              //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
                FileWriter fileWriter = new FileWriter("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\Customers.txt", true);
                BufferedWriter bw = new BufferedWriter(fileWriter);
                //Write data of customer in CSV format
                //if Id is not an empty string write data of the customer to the database
                if (this.ID != " ") {
                    bw.write(customer);
                }

                //close the file "customers.txt"
                bw.close();
            }

        }

        catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("Successfully written to file!");
    }

    void updateNumber(String username, String number) {
        List<Customer> customers = new ArrayList<Customer>();
        try {
            String customer="";
                                            //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
            File file = new File("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\Customers.txt");
            Scanner inputFile = new Scanner(file);

            while(inputFile.hasNext())
            {
                Customer i = new Customer();
                customer = inputFile.nextLine();
                String customer_array[] = customer.split(",");
                i.ID = customer_array[0];
                i.Password = customer_array[1];
                i.Name = customer_array[2];
                i.Address = customer_array[3];
                i.PhoneNo = customer_array[4];
                i.CreditCardNo = customer_array[5];
                i.MembershipType = customer_array[6];
                if (customer_array[0].equals(username)) {
                    i.CreditCardNo = number;
                }
                customers.add(i);
            }



            inputFile.close();
        }

        catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("Data retrieved successfully");
        int j=0;
        for(Customer im : customers) {
            if (j==0) {
                im.setCustomer(true);
                j++;
            }

            else
                im.setCustomer(false);

        }
    }

    void getCustomer(String ID) {
        try {
            String customer="";
                                        //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
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
        try {                                       //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
            File outputFile = new File("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\Customers.txt");
            if(!outputFile.exists()) {
                outputFile.createNewFile();
            }
                                                                //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
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
                                                //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
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



class MakeOrderInFile extends Order {
    public MakeOrderInFile(String id, String orderNo,String orderstatus, String totalPricePaid,String date, String CreditCard, LinkedList<Item> items) {
        this.ID = id; this.orderNo = orderNo; this.totalPricePaid= totalPricePaid; this.orderStatus= orderStatus;
        this.Items = items; this.orderStatus = orderstatus; boolean ans = false;  this.date = date; this.creditCardNo = CreditCard;
        StringBuilder Order; int x = 0;
        try {                                   //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
            File outputFile = new File("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\Orders.txt");
            if(!outputFile.exists()) {
                outputFile.createNewFile();
            }                                                  //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
            FileWriter fileWriter = new FileWriter("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\Orders.txt", true);
            BufferedWriter bw = new BufferedWriter(fileWriter);

            Order = new StringBuilder((this.ID + "," + this.orderNo + "," + this.orderStatus + "," + this.totalPricePaid + ","+this.date+","+this.creditCardNo+","));
            do{
                ans = Items.isEmpty();
                if(ans == true){
                    break;
                }
                else{
                    Item temp = Items.pop();
                    if(x == 0) {
                        Order.append(temp.PrimaryKey).append(';').append(temp.Quantity);
                    }
                    else{
                        Order.append(";").append(temp.PrimaryKey).append(';').append(temp.Quantity);
                    }
                }
                x++;
            }while(ans == false);
            Order.append("\n");

            if (this.ID != " ") {
                bw.write(Order.toString());
            }

            bw.close();
        }

        catch (IOException e) {
            System.out.println(e);
        }
    }

}


class Update_quantity{

    String Serial;
    String Item;
    String Premium;
    String Regular;
    String Quantity;
    String Discription;
    String old_string =" ";
    String new_string =" ";


    Update_quantity()
    {
        String Serial="";
        String Item="";
        String Premium="";
        String Regular="";
        String Quantity="";
        String Discription="";

    }

    void update_stock(String item,String quantity)
    {
        try {
            String updatequant="";
                                            //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
            File file = new File("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\Stock.txt");
            Scanner inputFile = new Scanner(file);

            while(inputFile.hasNext())
            {
                updatequant = inputFile.nextLine();
                String updatequant_array[] = updatequant.split(",");
                // System.out.println(updateorder_array[0]);
                if (updatequant_array[1].equals(item)) {
                    this.Serial= updatequant_array[0];
                    this.Item= updatequant_array[1];
                    this.Premium= updatequant_array[2];
                    this.Regular=updatequant_array[3];
                    this.Quantity=updatequant_array[4];
                    this.Discription = updatequant_array[5];
                    break;
                }
            }

            inputFile.close();
        }

        catch (IOException e) {
            System.out.println(e);
        }

        try{                    //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
            String filePath = "C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\Stock.txt";
            Scanner sc = new Scanner(new File(filePath));
            StringBuffer buffer = new StringBuffer();
            while (sc.hasNextLine()) {
                buffer.append(sc.nextLine()+System.lineSeparator());
            }
            String fileContents = buffer.toString();
            sc.close();
            String Oldstring = (this.Serial + "," + this.Item+ "," + this.Premium+ "," +this.Regular+ "," +this.Quantity+","+ this.Discription);
            int no = Integer.parseInt(this.Quantity);
            int quant = Integer.parseInt(quantity);
            int total = no + quant;
            this.Quantity = String.valueOf(total);
            System.out.println(this.Quantity);
            System.out.println(total);
            String Newstring = (this.Serial + "," + this.Item+ "," + this.Premium+ "," +this.Regular+ "," +this.Quantity+","+ this.Discription);

            fileContents = fileContents.replaceAll(Oldstring,Newstring);
            FileWriter writer = new FileWriter(filePath);
            writer.append(fileContents);
            writer.flush();





        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    void update_reserved_stock(String item,String quantity)
    {
        try {
            String updatequant="";
                                            //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
            File file = new File("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\ReservedStock.txt");
            Scanner inputFile = new Scanner(file);

            while(inputFile.hasNext())
            {
                updatequant = inputFile.nextLine();
                String updatequant_array[] = updatequant.split(",");
                // System.out.println(updateorder_array[0]);
                if (updatequant_array[1].equals(item)) {
                    this.Serial= updatequant_array[0];
                    this.Item= updatequant_array[1];
                    this.Premium= updatequant_array[2];
                    this.Regular=updatequant_array[3];
                    this.Quantity=updatequant_array[4];
                    this.Discription = updatequant_array[5];
                    break;
                }
            }

            inputFile.close();
        }

        catch (IOException e) {
            System.out.println(e);
        }

        try{                        //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
            String filePath = "C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\ReservedStock.txt";
            Scanner sc = new Scanner(new File(filePath));
            StringBuffer buffer = new StringBuffer();
            while (sc.hasNextLine()) {
                buffer.append(sc.nextLine()+System.lineSeparator());
            }
            String fileContents = buffer.toString();
            sc.close();
            String Oldstring = (this.Serial + "," + this.Item+ "," + this.Premium+ "," +this.Regular+ "," +this.Quantity+","+ this.Discription);
            int no = Integer.parseInt(this.Quantity);
            int quant = Integer.parseInt(quantity);
            int total = no + quant;
            this.Quantity = String.valueOf(total);
            System.out.println(this.Quantity);
            System.out.println(total);
            String Newstring = (this.Serial + "," + this.Item+ "," + this.Premium+ "," +this.Regular+ "," +this.Quantity+","+ this.Discription);

            fileContents = fileContents.replaceAll(Oldstring,Newstring);
            FileWriter writer = new FileWriter(filePath);
            writer.append(fileContents);
            writer.flush();





        }
        catch (IOException e) {
            System.out.println(e);
        }
    }


}


class UpdateOrder {
    String ID;
    String OrderID;
    String Status;
    String TotalPrice;
    String Itemlist;
    String date;
    String creditCard;
    String Oldstring="";
    String Newstring="";

    UpdateOrder()
    {
        this.ID="";
        this.OrderID= "";
        this.Status= "";
        this.TotalPrice="";
        this.Itemlist = "";
        this.date = "";
        this.creditCard = "";
    }
    // Funtion takes in Order ID and the status you want to update as String and then Updates it to Orders.txt
    void convert_status(String Or_Id, String status)
    {

        try {
            String updateorder="";
                                            //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
            File file = new File("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\Orders.txt");
            Scanner inputFile = new Scanner(file);

            while(inputFile.hasNext())
            {
                updateorder = inputFile.nextLine();
                String updateorder_array[] = updateorder.split(",");
                // System.out.println(updateorder_array[0]);
                if (updateorder_array[1].equals(Or_Id)) {
                    this.ID= updateorder_array[0];
                    this.OrderID= updateorder_array[1];
                    this.Status= updateorder_array[2];
                    this.TotalPrice=updateorder_array[3];
                    this.Itemlist = updateorder_array[6];
                    this.date = updateorder_array[4];
                    this.creditCard = updateorder_array[5];
                    break;
                }
            }

            inputFile.close();
        }

        catch (IOException e) {
            System.out.println(e);
        }

        try {
            System.out.println("Data retrieved successfully");  //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
            String filePath = "C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\Orders.txt";
            Scanner sc = new Scanner(new File(filePath));
            StringBuffer buffer = new StringBuffer();
            while (sc.hasNextLine()) {
                buffer.append(sc.nextLine()+System.lineSeparator());
            }

            String fileContents = buffer.toString();
            //System.out.println("Contents of the file: "+fileContents);
            sc.close();

            String Oldstring = (this.ID + "," + this.OrderID+ "," + this.Status+ "," +this.TotalPrice+ "," +this.date+","+this.creditCard+"," +this.Itemlist);

            //System.out.println( Oldstring);
            this.Status = status;
            //System.out.println(status);

            String Newstring = (this.ID + "," + this.OrderID+ "," + status+ "," +this.TotalPrice+ "," +this.date+","+this.creditCard+"," +this.Itemlist);

            System.out.println(Newstring);
            // System.out.println( Newstring);

            fileContents = fileContents.replaceAll(Oldstring,Newstring);
            FileWriter writer = new FileWriter(filePath);
            writer.append(fileContents);
            writer.flush();
        }

        catch (IOException e) {
            System.out.println(e);
        }
    }
}

class credit_charge{

    String Card;
    String Credit;

    String old_string =" ";
    String new_string =" ";


    credit_charge()
    {
        String Card="";
        String Credit="";

    }

    void credit_subtract(String cardno,String amount)
    {
        try {
            String credit_charge="";
                            //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
            File file = new File("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\CreditCards.txt");
            Scanner inputFile = new Scanner(file);

            while(inputFile.hasNext())
            {
                credit_charge = inputFile.nextLine();
                String credit_charge_array[] = credit_charge.split(",");
                // System.out.println(updateorder_array[0]);
                if (credit_charge_array[0].equals(cardno)) {
                    this.Card = credit_charge_array[0];
                    this.Credit = credit_charge_array[1];
                    break;
                }
            }

            inputFile.close();
        }

        catch (IOException e) {
            System.out.println(e);
        }

        try{                    //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
            String filePath = "C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\CreditCards.txt";
            Scanner sc = new Scanner(new File(filePath));
            StringBuffer buffer = new StringBuffer();
            while (sc.hasNextLine()) {
                buffer.append(sc.nextLine()+System.lineSeparator());
            }
            String fileContents = buffer.toString();
            sc.close();
            String Oldstring = (this.Card+","+this.Credit);
            float no = Float.parseFloat(this.Credit);
            float quant = Float.parseFloat(amount);
            float total = no - quant;
            this.Credit = String.valueOf(total);
            //System.out.println(this.Credit);
            //System.out.println(total);
            String Newstring = (this.Card+","+this.Credit);

            fileContents = fileContents.replaceAll(Oldstring,Newstring);
            FileWriter writer = new FileWriter(filePath);
            writer.append(fileContents);
            writer.flush();





        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}

