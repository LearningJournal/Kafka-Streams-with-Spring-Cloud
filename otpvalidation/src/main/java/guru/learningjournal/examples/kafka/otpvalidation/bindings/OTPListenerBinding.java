package guru.learningjournal.examples.kafka.otpvalidation.bindings;

import guru.learningjournal.examples.kafka.otpvalidation.model.PaymentConfirmation;
import guru.learningjournal.examples.kafka.otpvalidation.model.PaymentRequest;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface OTPListenerBinding {

    @Input("payment-request-channel")
    KStream<String, PaymentRequest> requestInputStream();

    @Input("payment-confirmation-channel")
    KStream<String, PaymentConfirmation> confirmationInputStream();

}
