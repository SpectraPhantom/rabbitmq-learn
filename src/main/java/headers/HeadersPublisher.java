package headers;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class HeadersPublisher {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        String message = "Message for Mobile and TV";

        Map<String, Object> headersMap = new HashMap<>();
        headersMap.put("item1", "mobile");
        headersMap.put("item2", "television");

        AMQP.BasicProperties br = new AMQP.BasicProperties();
        br = br.builder().headers(headersMap).build();

        channel.basicPublish("Headers-Exchange", "", br, message.getBytes());

        channel.close();
        connection.close();
    }
}
