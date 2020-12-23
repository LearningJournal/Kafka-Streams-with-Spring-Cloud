package guru.learningjournal.examples.kafka.xmlbranching.Configs;

import guru.learningjournal.examples.kafka.xmlbranching.model.OrderEnvelop;
import io.confluent.kafka.streams.serdes.json.KafkaJsonSchemaSerde;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class AppSerdes extends Serdes {

    private static final String schema_registry_url = "http://localhost:8081";

    private final static Map<String, String> serdeConfig = Collections.singletonMap(
            "schema.registry.url", schema_registry_url);

    public static Serde<OrderEnvelop> OrderEnvelop() {
        final Serde<OrderEnvelop> specificJsonSerde = new KafkaJsonSchemaSerde<>();
        specificJsonSerde.configure(serdeConfig, false);
        return specificJsonSerde;
    }
}
