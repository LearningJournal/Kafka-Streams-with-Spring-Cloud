package guru.learningjournal.examples.kafka.streamingtest.configs;

import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
@EnableAutoConfiguration
public class ListenerService {

    @Bean
    public Function<KStream<Bytes, String>, KStream<Bytes, String>> process() {

        return v -> v.mapValues(i -> i.toUpperCase());

    }
}
