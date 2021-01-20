package guru.learningjournal.examples.kafka.sessionwindow.bindings;

import guru.learningjournal.examples.kafka.sessionwindow.models.UserClick;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface ClickListenerBinding {

    @Input("click-input-channel")
    KStream<String, UserClick> clickInputStream();

}
