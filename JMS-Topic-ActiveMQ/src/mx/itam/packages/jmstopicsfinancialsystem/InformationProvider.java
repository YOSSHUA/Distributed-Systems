package mx.itam.packages.jmstopicsfinancialsystem;

import mx.itam.packages.jmstopicamq.MessageSender;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.sound.sampled.Line;

public class InformationProvider {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    // default broker URL is : tcp://localhost:61616"
    private static String subject = "JOGG_TOPIC"; // Topic Name. You can create any/many topic names as per your requirement.

    public void produceMessages() {
        String subjects[] = {"Telecommunications", "Banks", "Transportation", "FoodSupply", "Education"};
        MessageProducer messageProducer[];
        Destination dest[];
        TextMessage textMessage;
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);

            int n_topics = subjects.length;
            dest =  new Destination[n_topics];
            messageProducer = new MessageProducer[n_topics];

            for(int i = 0; i < n_topics; i++) {
                dest[i] = session.createTopic(subjects[i]);
            }
            for(int i = 0; i < n_topics; i++) {
                messageProducer[i] = session.createProducer(dest[i]);
            }

            int n_messages = (int) (Math.random()*(15-5) + 5);
            System.out.println("Sending " + n_messages + " messages.");
            textMessage = session.createTextMessage();
            for(int i = 0; i < n_messages; i++) {

                int topic = (int) (Math.random()*5);
                int level = (int) (Math.random()*10);
                textMessage.setText("Sending terrible market news. Level: " + level +  " Category: " + subjects[topic]);
                //System.out.println("Sending the following message: " + textMessage.getText());
                messageProducer[topic].send(textMessage);

            }
            for(int i = 0; i < n_topics; i++) {
                textMessage.setText("Good bye!");
                messageProducer[i].send(textMessage);
                messageProducer[i].close();
            }
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new InformationProvider().produceMessages();
    }
}
