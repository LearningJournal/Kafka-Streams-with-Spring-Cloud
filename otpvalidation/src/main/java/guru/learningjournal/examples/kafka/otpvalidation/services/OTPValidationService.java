package guru.learningjournal.examples.kafka.otpvalidation.services;

import guru.learningjournal.examples.kafka.otpvalidation.bindings.OTPListenerBinding;
import guru.learningjournal.examples.kafka.otpvalidation.model.PaymentConfirmation;
import guru.learningjournal.examples.kafka.otpvalidation.model.PaymentRequest;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.StreamJoined;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;


@Log4j2
@Service
@EnableBinding(OTPListenerBinding.class)
public class OTPValidationService {

    @Autowired
    private RecordBuilder recordBuilder;

    @StreamListener
    public void process(@Input("payment-request-channel") KStream<String, PaymentRequest> request,
                        @Input("payment-confirmation-channel") KStream<String, PaymentConfirmation> confirmation) {

        request.foreach((k, v) -> log.info("Request Key = " + k + " Created Time = "
                + Instant.ofEpochMilli(v.getCreatedTime()).atOffset(ZoneOffset.UTC)));

        confirmation.foreach((k, v) -> log.info("Confirmation Key = " + k + " Created Time = "
                + Instant.ofEpochMilli(v.getCreatedTime()).atOffset(ZoneOffset.UTC)));


        request.join(confirmation,
                (r, c) -> recordBuilder.getTransactionStatus(r, c),
                JoinWindows.of(Duration.ofMinutes(5)),
                StreamJoined.with(Serdes.String(),
                        new JsonSerde<>(PaymentRequest.class),
                        new JsonSerde<>(PaymentConfirmation.class)))
                .foreach((k, v) -> log.info("Transaction ID = " + k + " Status = " + v.getStatus()));

    }
}
