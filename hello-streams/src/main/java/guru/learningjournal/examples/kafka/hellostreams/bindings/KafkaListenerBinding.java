package guru.learningjournal.examples.kafka.hellostreams.bindings;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface KafkaListenerBinding {
    @Input("input-channel-1")
    KStream<String, String> inputStream();
}
