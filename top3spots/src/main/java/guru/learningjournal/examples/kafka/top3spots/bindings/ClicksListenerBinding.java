package guru.learningjournal.examples.kafka.top3spots.bindings;

import guru.learningjournal.examples.kafka.top3spots.models.AdClick;
import guru.learningjournal.examples.kafka.top3spots.models.AdInventories;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface ClicksListenerBinding {

    @Input("inventories-channel")
    GlobalKTable<String, AdInventories> inventoryInputStream();

    @Input("clicks-channel")
    KStream<String, AdClick> clickInputStream();

}
