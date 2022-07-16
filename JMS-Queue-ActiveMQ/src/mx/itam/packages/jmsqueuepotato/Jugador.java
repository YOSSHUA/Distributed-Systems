package mx.itam.packages.jmsqueuepotato;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Jugador extends Thread{
    public int id;
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    // default broker URL is : tcp://localhost:61616"
    private static String mySubject;
    private static String adversarySubject;
    public Jugador(int id, int adversary){
        this.id = id;
        mySubject = "Player_"+id+"_queue";
        adversarySubject = "Player_"+id+"_queue";
    }
    public void produceMessages(Papa papa) {
        MessageProducer messageProducer;
        ObjectMessage objectMessage;
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            connectionFactory.setTrustAllPackages(true);
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(adversarySubject);
            messageProducer = session.createProducer(destination);
            objectMessage = session.createObjectMessage();

            objectMessage.setObject(papa);
            if(papa.getTimer() != -1)
                System.out.println("Player " + id + " sending the potato with time " + papa.getTimer());
            messageProducer.send(objectMessage);

            messageProducer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    public void getMessages() {

        boolean explotedPotato = false;

        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            connectionFactory.setTrustAllPackages(true);
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue(mySubject);

            MessageConsumer messageConsumer = session.createConsumer(destination);

            while (!explotedPotato) {
                ObjectMessage potato = (ObjectMessage) messageConsumer.receive();
                if (potato != null && ((Papa) potato.getObject()).getTimer() == -1) {
                    System.out.println("Game end. Player " + id + " won.");
                    break;
                }
                if (potato != null && ((Papa) potato.getObject()).getTimer() == 0) {
                    System.out.println("Oh, no! Player " + id + " received the potato with time 0.");
                    explotedPotato = true;
                }
                if (potato != null) {
                    Papa papa = (Papa) potato.getObject();
                    if(papa.getTimer() != 0)
                        System.out.println("Player " + id + " received the potato with time " + papa.getTimer());
                    papa.decrementTimer();
                    produceMessages(papa);
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
