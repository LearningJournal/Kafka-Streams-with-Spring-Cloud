package guru.learningjournal.kafka.examples.kafkaproducer.model;

import lombok.Data;

@Data
public class IncomingMessage {
    private String topic;
    private String key;
    private String value;
}
