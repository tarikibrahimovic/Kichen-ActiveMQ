package org.kuhinja;

import org.shared.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private final MessageProducer messageProducer;

    public MessageConsumer(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @JmsListener(destination = "ds1.queue")
    public void receiveMessage(Message message) {
        System.out.println("Message received: " + message.getText());
        messageProducer.sendMessage(message);
        // wait random time but no more than 4 seconds
        try {
            Thread.sleep((long) (Math.random() * 4000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Message messageDone = new Message("Kuhinja je zavrsila porudzbinu: " + message.getText());
        messageProducer.sendMessage(messageDone);
    }
}
