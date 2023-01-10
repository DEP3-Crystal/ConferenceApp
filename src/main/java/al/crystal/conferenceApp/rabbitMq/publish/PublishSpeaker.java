package al.crystal.conferenceApp.rabbitMq.publish;

import al.crystal.conferenceApp.model.message_model.SessionMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class PublishSpeaker {
    private final Connection connection;

    private final static String QUEUE_NAME = "speaker";

    public PublishSpeaker(Connection connection) {
        this.connection = connection;
    }

    public boolean sendMessage(SessionMessage sessionMessage){
        try (Channel channel= connection.createChannel()){
            ObjectMapper objectMapper=new ObjectMapper();
            String message=objectMapper.writeValueAsString(sessionMessage);
            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                    .timestamp(Date.from(Instant.now()))
                    .build();
            channel.basicPublish("",QUEUE_NAME,properties,message.getBytes(StandardCharsets.UTF_8));
            return true;
        } catch (IOException | TimeoutException e) {
            return false;
        }
    }
}
