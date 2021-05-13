import java.util.Random;

public class BankingSystem implements Runnable{

     void processCreditCard() throws Exception{
        Boolean creditCardNoFound = false;
        while(true) {
            while ((Main.buffer.messageBufferFull == false)) {
                if(Main.buffer.endCond == true){return;}
                Thread.sleep(1000);
            }
            System.out.println("Reached here");
            if(Main.buffer.endCond == true){return;}
            CreditCard receivedCreditCard = Main.buffer.messageBuffer.poll();
            Main.buffer.messageBufferFull = false;

            CreditCard temp = new CreditCard();
            CreditCard temp1 = new CreditCard();
            //boolean found;
            temp1 = temp.getCreditCard(receivedCreditCard.CreditCardNo);

            //System.out.println(receivedCreditCard);
            //System.out.println(found);

            if (temp1.CreditCardNo.equals(receivedCreditCard.CreditCardNo) && Float.valueOf(temp1.CreditLimit) > Float.valueOf(receivedCreditCard.CreditLimit)) {
                //System.out.println("Credit Card successfully Charged");
                Random random = new Random();
                int orderID = random.nextInt(9999 - 1000) + 1000;
                credit_charge new1 = new credit_charge();
                new1.credit_subtract(receivedCreditCard.CreditCardNo, receivedCreditCard.CreditLimit);
                Main.buffer.responseBuffer.add(orderID);
            } else {
                //System.out.println("Customer Credit Card is invalid");
                Main.buffer.responseBuffer.add(0);
            }
            Main.buffer.responseBufferFull = true;
            //notify();
        }

    }


    @Override
    public void run() {
        try {
            while (Main.buffer.endCond != true) {
                this.processCreditCard();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
