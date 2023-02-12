/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package primenumberproducer;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author sarun
 */
public class TextListener implements MessageListener {
    private MessageProducer replyProducer;
    private Session session;
    
//    public TextListener(Session session, Queue queue) {
//              
//        this.session = session;
//        try {
//            replyProducer = session.createProducer(queue);
//        } catch (JMSException ex) {
//            Logger.getLogger(TextListener.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
//    @Override
//    public void onMessage(Message message) {
//        TextMessage msg = null;
//
//        try {
//           if (message instanceof TextMessage) {
//                msg = (TextMessage) message;
//                System.out.println("Reading message: " + msg.getText());
//            } else {
//                System.err.println("Message is not a TextMessage");
//            }
//            // set up the reply message
////            Scanner input = new Scanner(System.in);
//            int prime;
////            if(!prime.equals("")){
////                break;
////            }
////                System.out.println("Enter two numbers. Usesr ',' to separate each number. To end the program press ENTER");
////                prime = input.nextLine();
//                prime = Integer.parseInt(msg.getText());
//                boolean isP = isPrime(prime);
//                System.out.println(prime + "is " + isP);
//                TextMessage response = session.createTextMessage(String.valueOf(isP));
//                response.setJMSCorrelationID(message.getJMSCorrelationID());
//                System.out.println("sending message " + response.getText());
//                replyProducer.send(message.getJMSReplyTo(), response);
//           
//            //processing prime number ???
//            
//            //send back??? 
//        } catch (JMSException e) {
//            System.err.println("JMSException in onMessage(): " + e.toString());
//        } catch (Throwable t) {
//            System.err.println("Exception in onMessage():" + t.getMessage());
//        }
//        
//    }
//    
//    private boolean isPrime(int n) {
//        int i;
//        for (i = 2; i*i <= n; i++) {
//            if ((n % i) == 0) {
//                return false;
//            }
//        }
//        return true;
//    }
    
        @Override
    public void onMessage(Message message) {
        TextMessage msg = null;

        try {
            //Read Message
            if (message instanceof TextMessage) {
                msg = (TextMessage) message;
                System.out.println("Reading message: " + msg.getText() + " " + 
                        msg.getJMSCorrelationID());
            } else {
                System.err.println("Message is not a TextMessage");
            }
            
            //Processing Message
//            int prime = Integer.parseInt(msg.getText());
//            boolean isP = isPrime(prime);
//            System.out.println(prime + "is " + isP);
//            
            //Create Sesssion To send back
            
            
        } catch (JMSException e) {
            System.err.println("JMSException in onMessage(): " + e.toString());
        } catch (Throwable t) {
            System.err.println("Exception in onMessage():" + t.getMessage());
        }
        
    }
}
