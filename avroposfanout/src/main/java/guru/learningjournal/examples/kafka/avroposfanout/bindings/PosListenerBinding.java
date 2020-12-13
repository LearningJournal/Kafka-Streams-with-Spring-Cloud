package guru.learningjournal.examples.kafka.avroposfanout.bindings;


import guru.learningjournal.examples.kafka.avroposfanout.model.HadoopRecord;
import guru.learningjournal.examples.kafka.avroposfanout.model.Notification;
import guru.learningjournal.examples.kafka.model.PosInvoice;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

public interface PosListenerBinding {

    @Input("notification-input-channel")
    KStream<String, PosInvoice> notificationInputStream();

    @Output("notification-output-channel")
    KStream<String, Notification> notificationOutputStream();

    @Input("hadoop-input-channel")
    KStream<String, PosInvoice> hadoopInputStream();

    @Output("hadoop-output-channel")
    KStream<String, HadoopRecord> hadoopOutputStream();

}
