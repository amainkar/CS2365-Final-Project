import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Controller {

    //Buffer buffer = new Buffer();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextField username;
    @FXML
    TextField password;
    @FXML
    TextField usernameS;
    @FXML
    TextField passwordS;
    @FXML
    TextField nameS;
    @FXML
    TextField address;
    @FXML
    TextField phoneNo;
    @FXML
    TextField CreditCardNo;
    @FXML
    TextField subscription;
    @FXML
    Text invalidCredentials;
    @FXML
    Text Created;
    @FXML
    Text Exists;

    @FXML
    ListView<String> item = new ListView<String>();
    @FXML
    ListView<String> description = new ListView<String>();
    @FXML
    ListView<String> premiumPrice = new ListView<String>();
    @FXML
    ListView<String> regularPrice = new ListView<String>();

    @FXML
    ListView<String> cartItem = new ListView<String>();
    @FXML
    ListView<String> cartAmount = new ListView<String>();

    @FXML
    Text TotalPrice;
    @FXML
    ToggleGroup tgShip;
    @FXML
    ListView<String> orders = new ListView<String>();

    @FXML
    ListView<String> orderItems = new ListView<String>();
    @FXML
    ListView<String> orderItemsAmount = new ListView<String>();
    @FXML
    Text orderStatus;
    @FXML
    Text orderNumber;

    @FXML
    ListView<String> StockItems = new ListView<String>();
    @FXML
    ListView<String> StockItemsAmount = new ListView<String>();
    @FXML
    ListView<String> ReservedStockItems = new ListView<String>();
    @FXML
    ListView<String> ReservedStockItemsAmount = new ListView<String>();

    @FXML
    Text InsufficientStock;
    @FXML
    Text OrderPlaced;

    @FXML
    Text OrderCreditCard;
    @FXML
    Text OrderDate;

    static Customer customer = new Customer();
    static Supplier supplier = new Supplier();

    static LinkedList<Item> cart = new LinkedList<Item>();

    static String CurrentOrder;


    public void CreateUser(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CreateUserLogin.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void CreateSupplier(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CreateSupplierLogin.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void SetToHomePage(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SetToUserLogin(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        //invalidCredentials.setVisible(false);
        stage.show();
    }

    public void SetToSupplierLogin(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SupplierLogin.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void BackToAccountPage(javafx.event.ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void SetToAccountPage(javafx.event.ActionEvent actionEvent) throws IOException {

        String user = username.getText();
        String pass = password.getText();
        CustomerLogOn login = new CustomerLogOn(user,pass);
        customer = login.LoginCheck();

        if(!customer.ID.equals(" ")) {
            root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        }
        else{
            invalidCredentials.setVisible(true);
        }
    }

    public void SetToSupplierPage(javafx.event.ActionEvent actionEvent) throws IOException {
        String user = usernameS.getText();
        String pass = passwordS.getText();
        SupplierLogOn temp1 = new SupplierLogOn(user,pass);
        System.out.println(user + " " + pass);
        supplier = temp1.SupplierLoginCheck();

        if(!supplier.ID.equals(" ")) {
            root = FXMLLoader.load(getClass().getResource("SupplierPage.fxml"));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        }
        else{
            invalidCredentials.setVisible(true);
        }
    }

    public void CreateUserAccount(javafx.event.ActionEvent actionEvent) throws IOException {
        String user = username.getText();
        String pass = password.getText();
        String add = address.getText();
        String name = nameS.getText();
        String phone = phoneNo.getText();
        String credit = CreditCardNo.getText();
        String subs = subscription.getText();


        CreateCustomerAccount temp = new CreateCustomerAccount();
        boolean isSet = temp.CreateCustomerAccount(user, pass, name, add, phone, credit, subs);
        if (isSet) {
            Created.setVisible(true);
            Exists.setVisible(false);
        } else {
            Created.setVisible(false);
            Exists.setVisible(true);
        }
    }

    public void CreateSupplierAccount(javafx.event.ActionEvent actionEvent) throws IOException {
        String user = username.getText();
        String pass = password.getText();

        CreateSupplierAccount temp = new CreateSupplierAccount();
        boolean isSet = temp.createaccount(user,pass);
        if (isSet){
            Created.setVisible(true);
            Exists.setVisible(false);
        }
        else {
            Created.setVisible(false);
            Exists.setVisible(true);
        }
    }

    public void ViewCatalogue(javafx.event.ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ViewCatalogue.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        /*
        String[] temp = {"Potatoes","Chicken", "Bagels"};
        System.out.println("This");
        ObservableList temp1 = FXCollections.observableArrayList("Potatoes","Chicken", "Bagels");
        item.getItems().addAll(temp1);*/


    }

    public void FillCatalogue(javafx.event.ActionEvent actionEvent) throws IOException{

        returnArrays obj = new returnArrays();


        //System.out.println(temp2);

        item.getItems().clear();
        description.getItems().clear();
        regularPrice.getItems().clear();
        premiumPrice.getItems().clear();

        item.getItems().addAll(obj.returnNames());
        description.getItems().addAll(obj.returnDescription());
        regularPrice.getItems().addAll(obj.returnRegularPrice());
        premiumPrice.getItems().addAll(obj.returnPremiumPrice());
    }

    public void AddToCart(javafx.event.ActionEvent actionEvent) throws IOException{

        String thingToAdd = item.getSelectionModel().getSelectedItem();
        //System.out.println(thingToAdd);
        boolean found = false;
        Item temp = new Item();



        for (int i = 0; i < cart.size(); i++) {
            //System.out.println("l");

            if(Controller.cart.get(i).Name.equals(thingToAdd)){
                int temp1 = Integer.valueOf(Controller.cart.get(i).Quantity);
                temp1++;
                Controller.cart.get(i).Quantity = String.valueOf(temp1);
                found = true;
            }
        }
        if(found == false){
            //System.out.println("m");
            temp.getItem(thingToAdd);
            Controller.cart.add(temp);
            System.out.println(cart.size());
        }

        for (int i = 0; i < cart.size(); i++) {

            if(cart.get(i).Name.equals(thingToAdd)){
                System.out.println(cart.get(i).Name + " " + cart.get(i).Quantity);
            }
        }

    }

    public void DisplayCart(javafx.event.ActionEvent actionEvent) throws IOException{
        root = FXMLLoader.load(getClass().getResource("ViewCart.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        //System.out.println(customer.Name);

    }

    public void ViewOrdertest(javafx.event.ActionEvent actionEvent) throws IOException{

        cartItem.getItems().clear();
        cartAmount.getItems().clear();

        float tempAmt=0;

        boolean IschargedMemberShip = false;
        LinkedList<Order> UserOrders = new LinkedList<>();
        GetUserOrders temp1 = new GetUserOrders();
        UserOrders = temp1.getOrder(customer.ID);

        if(UserOrders.size() == 0 ) {
            for (int i = 0; i < cart.size(); i++) {
                if(cart.get(i).Name.equals("Premium Membership Charge") ){
                    IschargedMemberShip = true;
                }
            }
        }
        else {
            IschargedMemberShip = true;
        }

        System.out.println(customer.MembershipType);
        if(IschargedMemberShip == false && customer.MembershipType.equals("Premium\r")){
            Item membershipFee = new Item();
            membershipFee.Name ="Premium Membership Charge";
            membershipFee.Quantity = "1";
            membershipFee.PrimaryKey= "0";
            membershipFee.Description= "Premium Membership Fee";
            membershipFee.PremiumPrice= "40";
            membershipFee.RegularPrice= "40";
            cart.add(membershipFee);
        }

        for (int i = 0; i < cart.size(); i++) {
            cartItem.getItems().add(cart.get(i).Name);
            cartAmount.getItems().add(cart.get(i).Quantity);
            if(customer.MembershipType.equals("Premium\r")){
                int itemAmt, itemPrice;
                itemAmt = Integer.valueOf(cart.get(i).Quantity);
                itemPrice = Integer.valueOf(cart.get(i).PremiumPrice);

                tempAmt = tempAmt + (itemAmt * itemPrice);
            }
            else {
                int itemAmt, itemPrice;
                itemAmt = Integer.valueOf(cart.get(i).Quantity);
                itemPrice = Integer.valueOf(cart.get(i).RegularPrice);

                tempAmt = tempAmt + (itemAmt * itemPrice);
            }

            TotalPrice.setText(String.valueOf(tempAmt));

        }

    }

    public void ShipMail(javafx.event.ActionEvent actionEvent) throws IOException{
        float tempAmt=0;
        for (int i = 0; i < cart.size(); i++) {

            if(customer.MembershipType.equals("Premium\r")){
                int itemAmt, itemPrice;
                itemAmt = Integer.valueOf(cart.get(i).Quantity);
                itemPrice = Integer.valueOf(cart.get(i).PremiumPrice);

                tempAmt = tempAmt + (itemAmt * itemPrice);
            }
            else {
                int itemAmt, itemPrice;
                itemAmt = Integer.valueOf(cart.get(i).Quantity);
                itemPrice = Integer.valueOf(cart.get(i).RegularPrice);

                tempAmt = tempAmt + (itemAmt * itemPrice);
            }
        }
        TotalPrice.setText(String.valueOf(tempAmt+3));



    }

    public void StorePickUp(javafx.event.ActionEvent actionEvent) throws IOException{
        float tempAmt=0;
        for (int i = 0; i < cart.size(); i++) {

            if(customer.MembershipType.equals("Premium\r")){
                int itemAmt, itemPrice;
                itemAmt = Integer.valueOf(cart.get(i).Quantity);
                itemPrice = Integer.valueOf(cart.get(i).PremiumPrice);

                tempAmt = tempAmt + (itemAmt * itemPrice);
            }
            else {
                int itemAmt, itemPrice;
                itemAmt = Integer.valueOf(cart.get(i).Quantity);
                itemPrice = Integer.valueOf(cart.get(i).RegularPrice);

                tempAmt = tempAmt + (itemAmt * itemPrice);
            }
        }
        TotalPrice.setText(String.valueOf(tempAmt));



    }

    public void GetUserOrder(javafx.event.ActionEvent actionEvent) throws IOException{
        root = FXMLLoader.load(getClass().getResource("UserOrders.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        LinkedList<Order> UserOrders = new LinkedList<>();
        GetUserOrders temp = new GetUserOrders();

        UserOrders = temp.getOrder(customer.ID);

        for (int i = 0; i < UserOrders.size(); i++) {
            orders.getItems().add(UserOrders.get(i).orderNo);
        }


    }

    public void DisplayUserOrder(javafx.event.ActionEvent actionEvent) throws IOException{
        LinkedList<Order> UserOrders = new LinkedList<>();
        GetUserOrders temp = new GetUserOrders();

        UserOrders = temp.getOrder(customer.ID);

        orders.getItems().clear();

        for (int i = 0; i < UserOrders.size(); i++) {
            orders.getItems().add(UserOrders.get(i).orderNo);
        }
    }

    public void ViewOrderDetails(javafx.event.ActionEvent actionEvent) throws IOException{
        CurrentOrder = orders.getSelectionModel().getSelectedItem();

        root = FXMLLoader.load(getClass().getResource("OrderDetails.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void ViewOrderDetails1(javafx.event.ActionEvent actionEvent) throws IOException{
        LinkedList<Order> UserOrders = new LinkedList<>();
        Order temp = new Order();
        UserOrders = temp.getOrder(customer.ID);

        orderItems.getItems().clear();
        orderItemsAmount.getItems().clear();

        Order currOrder = new Order();


        for (int i = 0; i < UserOrders.size(); i++) {
            if(CurrentOrder.equals(UserOrders.get(i).orderNo)){
                currOrder = UserOrders.get(i);
            }
        }

        //System.out.println("#$#@");
        System.out.println(currOrder.orderStatus +" "+currOrder.totalPricePaid);
        orderStatus.setText(currOrder.orderStatus);
        TotalPrice.setText(currOrder.totalPricePaid);
        orderNumber.setText(currOrder.orderNo);

        for (int i = 0; i < currOrder.Items.size(); i++) {
            orderItems.getItems().add(currOrder.Items.get(i).Name);
            orderItemsAmount.getItems().add(currOrder.Items.get(i).Quantity);
        }

    }

    public void DeleteOrder(javafx.event.ActionEvent actionEvent) throws IOException {
        cart.clear();
        System.out.println("Order deleted!");

        root = FXMLLoader.load(getClass().getResource("ViewCart.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void UpdateCreditCard(javafx.event.ActionEvent actionEvent) throws IOException, InterruptedException {
        CreditCard temp = new CreditCard();

        String number = CreditCardNo.getText();
        CreditCard tempVar = new CreditCard();
        tempVar.CreditLimit = "0";
        tempVar.CreditCardNo = number;
        Main.buffer.messageBuffer.add(tempVar);
        Main.buffer.messageBufferFull = true;
        int response = 0;
        while (Main.buffer.responseBufferFull == false) {
            //wait();
            Thread.sleep(1000);
        }
        response = Main.buffer.responseBuffer.poll();
        if(response != 0){
            customer.CreditCardNo = number;
            System.out.println("credit card no changed");
            Customer rand = new Customer();
            rand.updateNumber(customer.ID, number);

            root = FXMLLoader.load(getClass().getResource("ViewCart.fxml"));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);

        }
        else {
            System.out.println("please try again");
        }
        Main.buffer.responseBufferFull = false;
        Main.buffer.messageBufferFull = false;
    }

    public void MakeOrder(javafx.event.ActionEvent actionEvent) throws IOException, InterruptedException {
        String totalCost = TotalPrice.getText();
        System.out.println("(" + totalCost + ")");
        System.out.println(customer.CreditCardNo);

        CreditCard creditCardToPass = new CreditCard();
        creditCardToPass.CreditCardNo = customer.CreditCardNo;
        creditCardToPass.CreditLimit = totalCost;

        Main.buffer.messageBuffer.add(creditCardToPass);
        Main.buffer.messageBufferFull = true;
        int response = 0;
        boolean isOrderplaced = false;
        //notify();
        while (Main.buffer.responseBufferFull == false) {
             //wait();
             Thread.sleep(1000);
        }
        response = Main.buffer.responseBuffer.poll();
        System.out.println(response);
        if (response == 0) {
            System.out.println("Customer Credit Card is invalid");

            root = FXMLLoader.load(getClass().getResource("UpdateCreditCard.fxml"));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);



        } else {
            System.out.println("Credit Card successfully Charged");
            isOrderplaced = true;
        }

        Main.buffer.responseBufferFull = false;
        Main.buffer.messageBufferFull = false;
            //Thread.sleep(1000);
        if(response!=0) {
            MakeOrderInFile temp = new MakeOrderInFile(customer.ID, String.valueOf(response), "ordered", totalCost, String.valueOf(java.time.LocalDate.now()),customer.CreditCardNo,cart);
            cart.clear();
        }


    }

    public void ExitApp(javafx.event.ActionEvent actionEvent) throws IOException {
        Main.buffer.endCond = true;
        Platform.exit();
    }

    public void ViewStockSupplier(javafx.event.ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ViewStockSupplier.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void BackToSupplierPage(javafx.event.ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SupplierPage.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void LoadStock(javafx.event.ActionEvent actionEvent) throws IOException {
        Item temp = new Item();
        List<Item> stock = new LinkedList<>();
        List<Item> reservedStock = new LinkedList<>();
        stock = temp.getListItems();
        reservedStock = temp.getListReservedItems();

        System.out.println(stock.size() +" "+ reservedStock.size());

        for (int i = 0; i < stock.size(); i++) {
            StockItems.getItems().add(stock.get(i).Name);
            StockItemsAmount.getItems().add(stock.get(i).Quantity);
        }

        for (int i = 0; i < reservedStock.size(); i++) {
            ReservedStockItems.getItems().add(reservedStock.get(i).Name);
            ReservedStockItemsAmount.getItems().add(reservedStock.get(i).Quantity);
        }
    }

    public void GoToProcessOrder(javafx.event.ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ProcessOrder.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void LoadOrderedOrders(javafx.event.ActionEvent actionEvent) throws IOException {
        Order temp = new Order();
        LinkedList<Order> orderedOrders = new LinkedList<>();
        orderedOrders = temp.getListOrderByOrderStatus("ordered");

        orders.getItems().clear();


        if(InsufficientStock.isVisible() == true) {
            InsufficientStock.setVisible(false);
        }
        if(OrderPlaced.isVisible() == true) {
            OrderPlaced.setVisible(false);
        }

        for (int i = 0; i < orderedOrders.size(); i++){
            orders.getItems().add(orderedOrders.get(i).orderNo);
        }

    }

    public void ProcessOrder(javafx.event.ActionEvent actionEvent) throws IOException {
        String selectedOrder = orders.getSelectionModel().getSelectedItem();

        Order orderToProcess = new Order();
        LinkedList<Order> orderedOrders = new LinkedList<>();
        orderedOrders = orderToProcess.getListOrderByOrderStatus("ordered");

        for (int i = 0; i < orderedOrders.size(); i++){
            if(orderedOrders.get(i).orderNo.equals(selectedOrder)){
                orderToProcess = orderedOrders.get(i);
            }
        }

        Item temp = new Item();
        List<Item> stock = new LinkedList<>();
        stock = temp.getListItems();
        boolean IsOutofStock = false;

        for (int i = 0; i < orderToProcess.Items.size(); i++){
            for (int j = 0; j < stock.size(); j++){
                if(orderToProcess.Items.get(i).Name.equals(stock.get(j).Name)){
                    if(Integer.valueOf(orderToProcess.Items.get(i).Quantity)<=Integer.valueOf(stock.get(j).Quantity)){

                    }
                    else {
                        IsOutofStock = true;
                        System.out.println("Insufficient Stock");
                        InsufficientStock.setVisible(true);
                        break;
                    }
                }
            }

        }

        if(IsOutofStock == false){
            OrderPlaced.setVisible(true);
        }

        if(IsOutofStock == false) {
            for (int i = 0; i < orderToProcess.Items.size(); i++) {
                for (int j = 0; j < stock.size(); j++) {
                    if (orderToProcess.Items.get(i).Name.equals(stock.get(j).Name)) {
                        if (Integer.valueOf(orderToProcess.Items.get(i).Quantity) <= Integer.valueOf(stock.get(j).Quantity)) {
                            int newAmt = -Integer.valueOf(orderToProcess.Items.get(i).Quantity);
                            int restockAmt = Integer.valueOf(orderToProcess.Items.get(i).Quantity);
                            Update_quantity temp1 = new Update_quantity();
                            temp1.update_stock(orderToProcess.Items.get(i).Name, String.valueOf(newAmt));
                            temp1.update_reserved_stock(orderToProcess.Items.get(i).Name, String.valueOf(restockAmt));
                            System.out.println("Remaining stock:" + newAmt);
                        } else {
                            System.out.println("Insufficient Stock");
                        }
                    }
                }
            }
        }

        if(IsOutofStock == false) {
            UpdateOrder orderUpdater = new UpdateOrder();
            orderUpdater.convert_status(selectedOrder, "ready");
        }


    }

    public void GoToShipOrder(javafx.event.ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ShipOrder.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void LoadReadyOrders(javafx.event.ActionEvent actionEvent) throws IOException {
        Order temp = new Order();
        LinkedList<Order> orderedOrders = new LinkedList<>();
        orderedOrders = temp.getListOrderByOrderStatus("ready");

        if(OrderPlaced.isVisible() == true){
            OrderPlaced.setVisible(false);
        }

        orders.getItems().clear();

        for (int i = 0; i < orderedOrders.size(); i++){
            orders.getItems().add(orderedOrders.get(i).orderNo);
        }

    }

    public void ShipOrder(javafx.event.ActionEvent actionEvent) throws IOException {

        String selectedOrder = orders.getSelectionModel().getSelectedItem();

        Order orderToProcess = new Order();
        LinkedList<Order> readyOrders = new LinkedList<>();
        readyOrders = orderToProcess.getListOrderByOrderStatus("ready");

        for (int i = 0; i < readyOrders.size(); i++){
            if(readyOrders.get(i).orderNo.equals(selectedOrder)){
                orderToProcess = readyOrders.get(i);
            }
        }

        Item temp = new Item();
        List<Item> stock = new LinkedList<>();
        stock = temp.getListItems();
        boolean IsOutofStock = false;


        for (int i = 0; i < orderToProcess.Items.size(); i++) {
            for (int j = 0; j < stock.size(); j++) {
                if (orderToProcess.Items.get(i).Name.equals(stock.get(j).Name)) {
                        int restockAmt = -Integer.valueOf(orderToProcess.Items.get(i).Quantity);
                        Update_quantity temp1 = new Update_quantity();
                        temp1.update_reserved_stock(orderToProcess.Items.get(i).Name, String.valueOf(restockAmt));
                        System.out.println("Remaining stock:" + restockAmt);
                }
            }
        }

        UpdateOrder orderUpdater = new UpdateOrder();
        OrderPlaced.setVisible(true);
        orderUpdater.convert_status(selectedOrder, "shipped");

    }

    public void GoToViewInvoice(javafx.event.ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("UserInvoice.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void LoadInvoiceOrders(javafx.event.ActionEvent actionEvent) throws IOException {
        LinkedList<Order> UserOrders = new LinkedList<>();
        GetUserOrders temp = new GetUserOrders();

        UserOrders = temp.getOrder(customer.ID);

        orders.getItems().clear();

        for (int i = 0; i < UserOrders.size(); i++) {
            orders.getItems().add(UserOrders.get(i).orderNo);
        }
    }
    public void LoadInvoicePage(javafx.event.ActionEvent actionEvent) throws IOException {
        CurrentOrder = orders.getSelectionModel().getSelectedItem();

        root = FXMLLoader.load(getClass().getResource("InvoiceDetails.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void ViewInvoiceDetails(javafx.event.ActionEvent actionEvent) throws IOException {
        LinkedList<Order> UserOrders = new LinkedList<>();
        Order temp = new Order();
        UserOrders = temp.getOrder(customer.ID);

        orderItems.getItems().clear();
        orderItemsAmount.getItems().clear();

        Order currOrder = new Order();


        for (int i = 0; i < UserOrders.size(); i++) {
            if(CurrentOrder.equals(UserOrders.get(i).orderNo)){
                currOrder = UserOrders.get(i);
            }
        }

        //System.out.println("#$#@");
        System.out.println(currOrder.orderStatus +" "+currOrder.totalPricePaid);
        OrderCreditCard.setText(currOrder.creditCardNo);
        TotalPrice.setText(currOrder.totalPricePaid);
        OrderDate.setText(currOrder.date);

        for (int i = 0; i < currOrder.Items.size(); i++) {
            orderItems.getItems().add(currOrder.Items.get(i).Name);
            orderItemsAmount.getItems().add(currOrder.Items.get(i).Quantity);
        }

    }


}





class SupplierLogOn extends Supplier {
    private String ID;
    private String Password;
    // actual login check
    public SupplierLogOn(String ID, String password){
        this.ID = ID;
        this.Password = password;
    }
    public Supplier SupplierLoginCheck(){
        // intialize new supplier
        var supplier = new Supplier();
        supplier.getSupplier(ID);
        // compare with our current data
        if(this.ID.equals(supplier.ID)){
            if(this.Password.equals(supplier.Password)){
                System.out.println("Welcome user: "+supplier.ID);
                return supplier;
            }
        }
        System.out.println("ID and password is either wrong or Does not exist");
        return new Supplier();
    }
}

class CreateCustomerAccount{

    boolean CreateCustomerAccount(String ID,String Password,String Name,String Address,String PhoneNo,String CreditCardNo,String MembersipType ){

        Customer cus = new Customer(); // object created from Customer.java
        cus.getCustomer(ID);
        if(cus.ID.equals(ID)) // condition checks for ID
        {

            System.out.println("Account already present in the system");
            return false; // returns false and prints the message
        }
        else{

            cus.ID=ID;
            cus.Password=Password;
            cus.Name=Name;
            cus.Address=Address;
            cus.PhoneNo=PhoneNo;
            cus.CreditCardNo=CreditCardNo;
            cus.MembershipType=MembersipType;
            cus.setCustomer();
            System.out.println("Account added to the system");
            return true;// returns true and prints the message and writes to the file
        }

    }

}

class CreateSupplierAccount{

    boolean createaccount(String ID,String Password) { // boolean method to create Supplier's Account

        Supplier sup = new Supplier(); // object creation from harshit's Supplier.java

        sup.getSupplier(ID);
        if(sup.ID.equals(ID)) // condition checks for ID
        {

            System.out.println("Account already present in the system");
            return false; // returns false and prints the message
        }
        else {
            sup.ID = ID;
            sup.Password = Password;
            sup.setSupplier();
            System.out.println("Account added to the system");
            return true;  // returns true and prints the message and writes to the file
        }
    }


}


class returnArrays {
    String[] array;

    returnArrays() {
        array = new String[20];
    }

    String[] returnNames() {
        Item item = new Item();
        List<Item> items = new ArrayList<Item>();
        items = item.getListItems();
        int i=0, x=0, j=0;

        for (i=1 ; i<items.size();i++) {
            x++;
        }
        String[] array1 = new String[x];

        for (i=1 ; i<items.size();i++) {
            array1[j] = items.get(i).Name;
            j++;
        }

        return array1;

    }

    String[] returnDescription() {
        Item item = new Item();
        List<Item> items = new ArrayList<Item>();
        items = item.getListItems();
        int i=0,x=0,j=0;

        for (i=1 ; i<items.size();i++) {
            x++;
        }
        String[] array1 = new String[x];

        for (i=1 ; i<items.size();i++) {
            array1[j] = items.get(i).Description;
            j++;
        }

        return array1;

    }

    String[] returnRegularPrice() {
        Item item = new Item();
        List<Item> items = new ArrayList<Item>();
        items = item.getListItems();
        int i=0,x=0,j=0;

        for (i=1 ; i<items.size();i++) {
            x++;
        }
        String[] array1 = new String[x];

        for (i=1 ; i<items.size();i++) {
            array1[j] = items.get(i).RegularPrice;
            j++;
        }

        return array1;

    }

    String[] returnPremiumPrice() {
        Item item = new Item();
        List<Item> items = new ArrayList<Item>();
        items = item.getListItems();
        int i=0,x=0,j=0;

        for (i=1 ; i<items.size();i++) {
            x++;
        }
        String[] array1 = new String[x];

        for (i=1 ; i<items.size();i++) {
            array1[j] = items.get(i).PremiumPrice;
            j++;
        }

        return array1;

    }
}