package guru.learningjournal.examples.kafka.windowcount.bindings;

import guru.learningjournal.examples.kafka.windowcount.model.SimpleInvoice;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface InvoiceListenerBinding {

    @Input("invoice-input-channel")
    KStream<String, SimpleInvoice> invoiceInputStream();

}
