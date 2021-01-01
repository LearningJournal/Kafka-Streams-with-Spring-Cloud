package guru.learningjournal.examples.kafka.rewards.bindings;

import guru.learningjournal.examples.kafka.model.Notification;
import guru.learningjournal.examples.kafka.model.PosInvoice;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

public interface PosListenerBinding {

    @Input("invoice-input-channel")
    KStream<String, PosInvoice> invoiceInputStream();

    @Output("notification-output-channel")
    KStream<String, Notification> notificationOutputStream();

}
