package guru.learningjournal.examples.kafka.otpvalidation.configs;

import guru.learningjournal.examples.kafka.otpvalidation.model.PaymentConfirmation;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class PaymentConfirmationTimeExtractor implements TimestampExtractor{

    @Override
    public long extract(ConsumerRecord<Object, Object> consumerRecord, long prevTime) {
        PaymentConfirmation confirmation = (PaymentConfirmation) consumerRecord.value();
        return ((confirmation.getCreatedTime() > 0) ? confirmation.getCreatedTime() : prevTime);
    }

    @Bean
    public TimestampExtractor confirmationTimeExtractor() {
        return new PaymentConfirmationTimeExtractor();
    }
}
