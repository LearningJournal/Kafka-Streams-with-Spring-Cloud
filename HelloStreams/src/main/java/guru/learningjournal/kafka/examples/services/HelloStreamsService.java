package guru.learningjournal.kafka.examples.services;

import guru.learningjournal.kafka.examples.bindings.HelloStreamsBinding;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@EnableBinding(HelloStreamsBinding.class)
public class HelloStreamsService {

    @StreamListener
    public void process(@Input("input-topic")KStream<String, String> input_stream){

        input_stream.foreach((k,v) -> log.info(String.format("Key: %s, Value: %s",k,v)));
    }
}
