package moringstarSoft.jmsDemo.activemq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageReceiver implements Runnable {
    private String url;
    private String user;
    private String password;
    private final String QUEUE;
 
    public MessageReceiver(String queue, String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.QUEUE = queue;
    }
 
    public void run() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                user, password, url);
        Session session = null;
        Destination receiveQueue;
        try {
            Connection connection = connectionFactory.createConnection();
 
            session = connection
                    .createSession(true, Session.SESSION_TRANSACTED);
            receiveQueue = session.createQueue(QUEUE);
            MessageConsumer consumer = session.createConsumer(receiveQueue);
 
            connection.start();
 
            while (true) {
                Message message = consumer.receive();
 
                if (message instanceof TextMessage) {
                    TextMessage receiveMessage = (TextMessage) message;
                    System.out.println("我是Receiver,收到消息如下: \r\n"
                            + receiveMessage.getText());
                } else {
                    session.commit();
                    break;
                }
 
            }
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
 
    public String getUrl() {
        return url;
    }
 
    public void setUrl(String url) {
        this.url = url;
    }
 
    public String getUser() {
        return user;
    }
 
    public void setUser(String user) {
        this.user = user;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
 
}
