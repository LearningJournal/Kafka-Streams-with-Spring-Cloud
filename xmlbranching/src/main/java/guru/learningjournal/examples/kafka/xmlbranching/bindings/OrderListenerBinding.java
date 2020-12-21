package guru.learningjournal.examples.kafka.xmlbranching.bindings;


import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface OrderListenerBinding {

    @Input("xml-order-topic")
    KStream<String, String> notificationInputStream();

}
