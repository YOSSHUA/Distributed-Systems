package mx.itam.packages.jmstopicsfinancialsystem;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class FloorBroker extends Thread{
    private String subject;
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private  int id;
    public FloorBroker(String subject, int id){
        this.subject =  subject;
        this.id = id;
        System.out.println("Broker " + id + " has subject "  + subject);
    }
    public void getMessages() {

        boolean goodByeReceived = false;

        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createTopic(subject);

            MessageConsumer messageConsumer = session.createConsumer(destination);

            while (!goodByeReceived) {
                TextMessage textMessage = (TextMessage) messageConsumer.receive();
                if (textMessage != null) {
                    System.out.println("Broker " + id + " with topic "+ subject +" received the following message: " + textMessage.getText());
                    System.out.println();
                }
                if (textMessage.getText() != null && textMessage.getText().equals("Good bye!")) {
                    goodByeReceived = true;
                }
            }

            messageConsumer.close();
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        getMessages();
    }
}
