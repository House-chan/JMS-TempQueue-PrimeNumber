/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package primenumberconsumer;

import java.util.Scanner;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author sarun
 */
public class Main {
    @Resource(mappedName = "jms/ConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/TempQueue")
    private static Queue queue;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection connection = null;
        TextListener listener = null;
                 
        try {
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(
                        false,
                        Session.AUTO_ACKNOWLEDGE);
            listener = new TextListener();
            //Create a temporary queue that this client will listen for responses on then create a consumer
            //that consumes message from this temporary queue...for a real application a client should reuse
            //the same temp queue for each message to the server...one temp queue per client
            Queue tempDest = session.createTemporaryQueue();
            MessageConsumer responseConsumer = session.createConsumer(tempDest);
            responseConsumer.setMessageListener(listener);
            MessageProducer producer = session.createProducer(queue);
            TextMessage message = session.createTextMessage();
            Scanner input = new Scanner(System.in);
            String prime = " ";
            while(!prime.equals("")){
                System.out.println("Enter two numbers, use ',' to separate each number. To end the program press enter");
                prime = input.nextLine();
                message.setText(prime);
                message.setJMSReplyTo(tempDest);
                //Set a correlation ID so when you get a response you know which sent message the response is for
                //If there is never more than one outstanding message to the server then the
                //same correlation ID can be used for all the messages...if there is more than one outstanding
                //message to the server you would presumably want to associate the correlation ID with this
                //message somehow...a Map works good
                String correlationId = "";
                message.setJMSCorrelationID(correlationId);
                connection.start();
                System.out.println("Sending message: " + message.getText());
                producer.send(message);
            }
            
        } catch (JMSException e) {
            System.err.println("Exception occurred: " + e.toString());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                }
            }
        }
    }
}
