package guru.learningjournal.kafka.examples.bindings;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface HelloStreamsBinding {
    @Input("input-topic")
    KStream inputStream();
}
