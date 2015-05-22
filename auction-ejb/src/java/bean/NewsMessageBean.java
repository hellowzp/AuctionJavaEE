/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSDestinationDefinition;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author Administrator
 */
@JMSDestinationDefinition(name = "jms/mdb_news_destination", interfaceName = "javax.jms.Queue", resourceAdapter = "jmsra", destinationName = "mdb_news_destination")
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/mdb_news_destination"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class NewsMessageBean implements MessageListener {
    
    public NewsMessageBean() {
    }
    
    @Override
    public void onMessage(Message message) {
    }
    
}
