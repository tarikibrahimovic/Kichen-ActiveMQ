package org.porudzbina;

import org.shared.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    @Autowired
    private JmsTemplate jmsTemplate;


    public void sendMessage(Message message) {
        try {
            jmsTemplate.convertAndSend("ds1.queue", message);
        }
        catch (Exception e) {
            System.out.println("Error while sending message");
        }
    }
}