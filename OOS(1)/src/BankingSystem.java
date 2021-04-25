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
            String receivedCreditCardNo = Main.buffer.messageBuffer.poll();
            Main.buffer.messageBufferFull = false;

            CreditCard temp = new CreditCard();
            boolean found;
            found = temp.getCreditCard(receivedCreditCardNo);
            System.out.println(receivedCreditCardNo);
            System.out.println(found);

            if (found == true) {
                //System.out.println("Credit Card successfully Charged");
                Random random = new Random();
                int temp1 = random.nextInt(9999 - 1000) + 1000;
                Main.buffer.responseBuffer.add(temp1);
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
