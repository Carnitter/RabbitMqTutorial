package com.example.rotto.rabbitmqtutorial;

import android.util.Log;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv {

    private RecvListener recvListener;

    private final static String QUEUE_NAME = "hello";

    public Recv () throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        Log.d("wait for msg"," [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                Log.d("Recieved message"," [x] Received '" + message + "'");
                recvListener.onMsgReceive(message);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}