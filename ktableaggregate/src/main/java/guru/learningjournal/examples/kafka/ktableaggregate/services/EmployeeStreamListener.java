package guru.learningjournal.examples.kafka.ktableaggregate.services;

import guru.learningjournal.examples.kafka.ktableaggregate.bindings.EmployeeListenerBinding;
import guru.learningjournal.examples.kafka.model.Employee;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@EnableBinding(EmployeeListenerBinding.class)
public class EmployeeStreamListener {

    @Autowired
    RecordBuilder recordBuilder;

    @StreamListener("employee-input-channel")
    public void process(KStream<String, Employee> input) {

        input.map((k, v) -> KeyValue.pair(v.getId(), v))
                .peek((k, v) -> log.info("Key = " + k + " Value = " + v))
                .toTable()
                .groupBy((k, v) -> KeyValue.pair(v.getDepartment(), v))
                .aggregate(
                        () -> recordBuilder.init(),
                        (k, v, aggV) -> recordBuilder.aggregate(v, aggV),
                        (k, v, aggV) -> recordBuilder.subtract(v, aggV)
                ).toStream()
                .foreach((k, v) -> log.info("Key = " + k + " Value = " + v));
    }
}
