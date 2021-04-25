import javafx.application.Application;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Buffer{
    Boolean messageBufferFull = false;
    Boolean responseBufferFull = false;
    Boolean endCond = false;

    Queue<String> messageBuffer = new LinkedList<>();
    Queue<Integer> responseBuffer = new LinkedList<>();/*

    public synchronized void endStuff() throws Exception{
        notify();
    }




    void processCreditCard() throws Exception{
        Boolean creditCardNoFound = false;

        while ((messageBufferFull == false)){
            if(this.endCond == true){return;}
            wait();
        }
        if(this.endCond == true){return;}
        String receivedCreditCardNo = messageBuffer.poll();
        messageBufferFull = false;

        CreditCard temp = new CreditCard();
        boolean found;
        found = temp.getCreditCard(receivedCreditCardNo);

        if(found == true){
            //System.out.println("Credit Card successfully Charged");
            Random random = new Random();
            int temp1 =  random.nextInt(9999 - 1000) + 1000;
            responseBuffer.add(temp1);
            notify();
        }
        else {
            //System.out.println("Customer Credit Card is invalid");
            responseBuffer.add(0);
            notify();
        }

    }

    public synchronized void reply(int n){
        responseBuffer.add(n);
        responseBufferFull = true;
        notify();
    }*/
}



public class Main {
    public static Buffer buffer = new Buffer();

    public static void main(String[] args){
        Buffer buffer = new Buffer();
        BankingSystem banking = new BankingSystem();
        Thread bankingSystem = new Thread(banking);
        bankingSystem.start();

        Application.launch(ApplicationClass.class,args);

    }
}

