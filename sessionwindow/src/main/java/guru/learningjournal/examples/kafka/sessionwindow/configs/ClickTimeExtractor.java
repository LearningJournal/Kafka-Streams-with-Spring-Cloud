package guru.learningjournal.examples.kafka.sessionwindow.configs;

import guru.learningjournal.examples.kafka.sessionwindow.models.UserClick;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class ClickTimeExtractor implements TimestampExtractor{

    @Override
    public long extract(ConsumerRecord<Object, Object> consumerRecord, long prevTime) {
        UserClick click = (UserClick) consumerRecord.value();
        log.info("Click Time: {}", click.getCreatedTime());
        return ((click.getCreatedTime() > 0) ? click.getCreatedTime() : prevTime);
    }

    @Bean
    public TimestampExtractor userClickTimeExtractor() {
        return new ClickTimeExtractor();
    }
}
