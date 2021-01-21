package guru.learningjournal.examples.kafka.otpvalidation.configs;

import guru.learningjournal.examples.kafka.otpvalidation.model.PaymentRequest;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class PaymentRequestTimeExtractor implements TimestampExtractor{

    @Override
    public long extract(ConsumerRecord<Object, Object> consumerRecord, long prevTime) {
        PaymentRequest request = (PaymentRequest) consumerRecord.value();
        return ((request.getCreatedTime() > 0) ? request.getCreatedTime() : prevTime);
    }

    @Bean
    public TimestampExtractor requestTimeExtractor() {
        return new PaymentRequestTimeExtractor();
    }
}
