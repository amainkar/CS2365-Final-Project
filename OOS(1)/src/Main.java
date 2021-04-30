import javafx.application.Application;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Buffer{
    Boolean messageBufferFull = false;
    Boolean responseBufferFull = false;
    Boolean endCond = false;

    Queue<CreditCard> messageBuffer = new LinkedList<>();
    Queue<Integer> responseBuffer = new LinkedList<>();


}



public class Main {
    public static Buffer buffer = new Buffer();

    public static void main1(String[] args){
        Buffer buffer = new Buffer();
        BankingSystem banking = new BankingSystem();
        Thread bankingSystem = new Thread(banking);
        bankingSystem.start();

        Application.launch(ApplicationClass.class,args);

    }
}

