package business;

import javax.jms.*;
import javax.ejb.*;

import util.*;

@MessageDriven( 
        mappedName = "jms/AuctionQueue", //JNDI name of the destination from which the bean receives messages
        activationConfig = {
            @ActivationConfigProperty(propertyName = "destinationType",
                    propertyValue = "javax.jms.Queue"),
            @ActivationConfigProperty(propertyName = "acknowledgeMode",
                    propertyValue = "Auto-acknowledge"),
            @ActivationConfigProperty(propertyName = "destination",
                    propertyValue = "jms/AuctionQueue")
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
                //准备发送邮件
                SimpleMailSender sender = new SimpleMailSender();
                sender.setFrom("spring_test@sina.com");
                sender.setTo(mailTo);
                sender.setSubject("竞拍通知");
                sender.setContent("Dear "
                        + bidUser
                        + ", 谢谢你参与竞价，你的竞价的物品的是: "
                        + itemName);
                sender.send();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
