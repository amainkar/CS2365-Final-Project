import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class EntityObjects {
}
class CreditCard {
    String CreditCardNo;
    String CreditLimit;


    CreditCard() {
        //Instantiate the class and set empty string for string data type and 0 for long data type
        this.CreditCardNo= " ";
        this.CreditLimit = " ";
    }



    CreditCard getCreditCard(String CreditCardNo) {
        boolean found = false;
        CreditCard temp = new CreditCard();
        try {
            String CreditCard="";
                                                //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
            File file = new File("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\CreditCards.txt");
            Scanner inputFile = new Scanner(file);

            while(inputFile.hasNext())
            {
                CreditCard = inputFile.nextLine();
                String CreditCard_array[] = CreditCard.split(",");
                if (CreditCard_array[0].equals(CreditCardNo)) {
                    this.CreditCardNo= CreditCard_array[0];
                    this.CreditLimit = CreditCard_array[1];
                    temp = this;
                    break;
                }
            }

            inputFile.close();
        }

        catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("Data retrieved successfully");
        return temp;
    }

    void setCreditCard() {
        String CreditCard;
        //Open the file to print data to CreditCards database "CreditCards.txt"
        try {                                       //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
            File outputFile = new File("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\CreditCards.txt");
            if(!outputFile.exists()) {
                outputFile.createNewFile();
            }
                                                            //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
            FileWriter fileWriter = new FileWriter("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\CreditCards.txt", true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            //Write data of CreditCard in CSV format
            CreditCard = (this.CreditCardNo+ "," + this.CreditLimit + "\r") ;

            //if CreditCardNois not an empty string write data of the CreditCard to the database
            if (this.CreditCardNo!= " ") {
                bw.write(CreditCard);
            }

            //close the file "CreditCards.txt"
            bw.close();
        }

        catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("Successfully written to file!");
    }

    void printCreditCard() {
        System.out.println("CreditCard Details: ");
        System.out.println("CreditCardNo: " + this.CreditCardNo);
        System.out.println("CreditLimit: " + this.CreditLimit);

    }
}

class Item{
    String PrimaryKey;
    String Name;
    String RegularPrice;
    String PremiumPrice;
    String Quantity;
    String Description;


    Item() {
        //Instantiate the class and set empty string for string data type and 0 for long data type
        this.PrimaryKey = " ";
        this.Name = " ";
        this.RegularPrice = " ";
        this.PremiumPrice = " ";
        this.Quantity = " ";
        this.Description = " ";
    }

    void getItem(String PrimaryKey) {
        try {
            String item="";
                                            //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
            File file = new File("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\Stock.txt");
            Scanner inputFile = new Scanner(file);

            while(inputFile.hasNext())
            {
                item = inputFile.nextLine();
                String item_array[] = item.split(",");
                if (item_array[0].equals(PrimaryKey)) {
                    this.PrimaryKey = item_array[0];
                    this.Name = item_array[1];
                    this.RegularPrice = item_array[2];
                    this.PremiumPrice = item_array[3];
                    this.Quantity = item_array[4];
                    this.Description = item_array[5];
                    break;
                }
                else if(item_array[1].equals(PrimaryKey)){
                    this.PrimaryKey = item_array[0];
                    this.Name = item_array[1];
                    this.RegularPrice = item_array[2];
                    this.PremiumPrice = item_array[3];
                    this.Quantity = "1";
                    this.Description = item_array[5];
                    break;
                }
            }

            inputFile.close();
        }

        catch (IOException e) {
            System.out.println("Errors");
        }

        System.out.println("Data retrieved successfully");
    }

    void setItem(boolean n) {
        String item;
        //Open the file to print data to Items database "Items.txt"
        try {                                       //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
            File outputFile = new File("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\Stock.txt");
            item = (this.PrimaryKey + "," +  this.Name  + "," +this.RegularPrice + "," + this.PremiumPrice + "," + this.Quantity + "," + this.Description + "\r") ;
            if(!outputFile.exists() || n) {
                outputFile.createNewFile();                         //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
                FileWriter fileWriter = new FileWriter("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\Stock.txt");
                fileWriter.write(item);
                fileWriter.close();
            }

            else {                                                  //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
                FileWriter fileWriter = new FileWriter("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\Stock.txt", true);
                BufferedWriter bw = new BufferedWriter(fileWriter);
                //Write data of item in CSV format
                //if Id is not an empty string write data of the item to the database
                if (this.PrimaryKey != " ") {
                    bw.write(item);
                }

                //close the file "items.txt"
                bw.close();
            }

        }

        catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("Successfully written to file!");
    }

    List<Item> getListItems() {
        List<Item> items = new ArrayList<Item>();
        try {
            String item="";
                                                    //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
            File file = new File("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\Stock.txt");
            Scanner inputFile = new Scanner(file);

            while(inputFile.hasNext())
            {
                Item i = new Item();
                item = inputFile.nextLine();
                String item_array[] = item.split(",");
                i.PrimaryKey = item_array[0];
                i.Name = item_array[1];
                i.RegularPrice = item_array[2];
                i.PremiumPrice = item_array[3];
                i.Quantity = item_array[4];
                i.Description = item_array[5];
                items.add(i);
            }
            inputFile.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("Data retrieved successfully");
        return items;
    }

    List<Item> getListReservedItems() {
        List<Item> items = new ArrayList<Item>();
        try {
            String item="";
                                                    //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
            File file = new File("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\ReservedStock.txt");
            Scanner inputFile = new Scanner(file);

            while(inputFile.hasNext())
            {
                Item i = new Item();
                item = inputFile.nextLine();
                String item_array[] = item.split(",");
                i.PrimaryKey = item_array[0];
                i.Name = item_array[1];
                i.RegularPrice = item_array[2];
                i.PremiumPrice = item_array[3];
                i.Quantity = item_array[4];
                i.Description = item_array[5];
                items.add(i);
            }
            inputFile.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("Data retrieved successfully");
        return items;
    }

    void updateQuantity(String PrimaryKey, String quantity) {
        List<Item> items = new ArrayList<Item>();
        try {
            String item="";
                                                //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
            File file = new File("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\Stock.txt");
            Scanner inputFile = new Scanner(file);

            while(inputFile.hasNext())
            {
                Item i = new Item();
                item = inputFile.nextLine();
                String item_array[] = item.split(",");
                i.PrimaryKey = item_array[0];
                i.Name = item_array[1];
                i.RegularPrice = item_array[2];
                i.PremiumPrice = item_array[3];
                i.Description = item_array[5];
                if (item_array[0].equals(PrimaryKey)) {
                    i.Quantity = quantity;
                }
                else{
                    i.Quantity = item_array[4];
                    items.add(i);
                }

            }



            inputFile.close();
        }

        catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("Data retrieved successfully");
        int j=0;
        for(Item im : items) {
            if (j==0) {
                im.setItem(true);
                j++;
            }

            else
                im.setItem(false);

        }
    }

    void printItem() {
        System.out.println("Item Details: ");
        System.out.println("PrimaryKey: " + this.PrimaryKey);
        System.out.println("Name: " + this.Name);
        System.out.println("Regular Price: " + this.RegularPrice);
        System.out.println("Premium Price: " + this.PremiumPrice);
        System.out.println("Quantity: " + this.Quantity);
        System.out.println("Description: " + this.Description);
    }
}






class Supplier {
    String ID;
    String Password;


    Supplier() {
        //Instantiate the class and set empty string for string data type and 0 for long data type
        this.ID = " ";
        this.Password = " ";
    }

    void getSupplier(String ID) {
        try {
            String Supplier="";
                                            //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
            File file = new File("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\Suppliers.txt");
            Scanner inputFile = new Scanner(file);

            while(inputFile.hasNext())
            {
                Supplier = inputFile.nextLine();
                String Supplier_array[] = Supplier.split(",");
                if (Supplier_array[0].equals(ID)) {
                    this.ID = Supplier_array[0];
                    this.Password = Supplier_array[1];
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

    void setSupplier() {
        String Supplier;
        //Open the file to print data to Suppliers database "Suppliers.txt"
        try {                                       //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
            File outputFile = new File("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\Suppliers.txt");
            if(!outputFile.exists()) {
                outputFile.createNewFile();
            }
                                                                //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
            FileWriter fileWriter = new FileWriter("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\Suppliers.txt", true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            //Write data of Supplier in CSV format
            Supplier = (this.ID + "," + this.Password + "\r") ;

            //if Id is not an empty string write data of the Supplier to the database
            if (this.ID != " ") {
                bw.write(Supplier);
            }

            //close the file "Suppliers.txt"
            bw.close();
        }

        catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("Successfully written to file!");
    }

    void printSupplier() {
        System.out.println("Supplier Details: ");
        System.out.println("ID: " + this.ID);
        System.out.println("Password: " + this.Password);

    }
}



class Order {
    String ID;
    String orderNo;
    String orderStatus;
    String totalPricePaid;
    String date;
    String creditCardNo;
    LinkedList<Item> Items;



    Order() {
        //Instantiate the class and set empty string for string data type and 0 for long data type
        this.ID= " ";
        this.orderNo="";
        this.orderStatus = " ";
        this.totalPricePaid = " ";
        Items = new LinkedList<>();
    }

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
                    tempO.date = order_array[4];
                    tempO.creditCardNo = order_array[5];
                    elements = order_array[6];
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
    LinkedList<Order> getListOrderByOrderStatus(String status) {
        LinkedList<Order> orders = new LinkedList<>();
        try {
                                                            //Change filepath to local filepath before running!!!! otherwise code will not run!!!!
            File file = new File("C:\\Users\\athar\\OneDrive\\Documents\\CS 2365\\Project stuff\\OOS(1)\\src\\Orders.txt");
            Scanner inputFile = new Scanner(file);

            while(inputFile.hasNext())
            {
                String order="";
                int i=0;
                int j=0;
                int k=0;
                String[] items = new String[20];
                String[] quantities = new String[20];
                Order o = new Order();
                order = inputFile.nextLine();
                String order_array[] = order.split(",");

                if (order_array[2].equals(status)) {
                    o.ID = order_array[0];
                    o.orderNo = order_array[1];
                    o.orderStatus = order_array[2];
                    o.totalPricePaid = order_array[3];
                    o.date = order_array[4];
                    o.creditCardNo = order_array[5];
                    String elements = order_array[6];
                    String elements_array[] = elements.split(";");
                    for(String element : elements_array ) {
                        if (i%2==0) {
                            items[j] = element;
                            j++;
                        }
                        else {
                            quantities[k] = element;
                            k++;
                        }
                        i++;
                    }

                    for(k=0;k<j;k++) {
                        Item im = new Item();
                        im.getItem(items[k]);
                        im.Quantity = quantities[k];
                        o.Items.add(im);
                    }
                    orders.add(o);
                }
            }
            inputFile.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }

        return orders;
    }

    void printOrder() {
        System.out.println("Customer ID: " + this.ID);
        System.out.println("Order No: " + this.orderNo);
        System.out.println("orderStatus: " + this.orderStatus);
        System.out.println("Total Price Paid: " + this.totalPricePaid);
        for (Item i : this.Items) {
            i.printItem();
            System.out.println("\n");
        }

    }
}