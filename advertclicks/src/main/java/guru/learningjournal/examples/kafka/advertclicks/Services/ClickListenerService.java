package guru.learningjournal.examples.kafka.advertclicks.Services;

import guru.learningjournal.examples.kafka.advertclicks.bindings.ClicksListenerBinding;
import guru.learningjournal.examples.kafka.advertclicks.models.AdClick;
import guru.learningjournal.examples.kafka.advertclicks.models.AdInventories;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@EnableBinding(ClicksListenerBinding.class)
public class ClickListenerService {

    @StreamListener
    public void process(@Input("inventories-channel") GlobalKTable<String, AdInventories> inventory,
                        @Input("clicks-channel") KStream<String, AdClick> click) {

        click.foreach((k, v) -> log.info("Click Key: {}, Value: {}",k, v));

        click.join(inventory,
                (clickKey, clickValue) -> clickKey,
                (clickValue, inventoryValue) -> inventoryValue)
                .groupBy((joinedKey, joinedValue) -> joinedValue.getNewsType(),
                        Grouped.with(Serdes.String(),
                                new JsonSerde<>(AdInventories.class)))
                .count()
                .toStream()
                .foreach((k, v) -> log.info("Click Key: {}, Value: {}",k, v));
    }
}
