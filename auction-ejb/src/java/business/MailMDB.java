package business;

import javax.jms.*;
import javax.ejb.*;

import util.*;

@MessageDriven( 
        mappedName = "auction_mdb_queue", //JNDI name of the destination from which the bean receives messages, forward slash is invalid
        activationConfig = {
            @ActivationConfigProperty(propertyName = "destinationType",
                    propertyValue = "javax.jms.Queue"),
            @ActivationConfigProperty(propertyName = "acknowledgeMode",
                    propertyValue = "Auto-acknowledge"),
            @ActivationConfigProperty(propertyName = "destination",
                    propertyValue = "auction_mdb_queue")
        }, 
        messageListenerInterface = javax.jms.MessageListener.class       
)
public class MailMDB {

    public void onMessage(Message rawMsg) {
        try {
            if (rawMsg instanceof MapMessage) {
                MapMessage msg = (MapMessage) rawMsg;
                String mailTo = msg.getString("mailTo");
                String bidUser = msg.getString("bidUser");
                String itemName = msg.getString("itemName");
                
                SimpleMailSender sender = new SimpleMailSender();
                sender.setFrom("zpkx.wang@gmail.com");
                sender.setTo(mailTo);
                sender.setSubject("Auction Mail Notification");
                sender.setContent("Dear " + bidUser + ", thanks for bidding, your item is: " + itemName);
                sender.send();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
