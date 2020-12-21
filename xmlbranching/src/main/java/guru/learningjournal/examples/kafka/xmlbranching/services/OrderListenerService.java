package guru.learningjournal.examples.kafka.xmlbranching.services;

import guru.learningjournal.examples.kafka.xmlbranching.Order;
import guru.learningjournal.examples.kafka.xmlbranching.bindings.OrderListenerBinding;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@Service
@Log4j2
@EnableBinding(OrderListenerBinding.class)
public class OrderListenerService {

    @StreamListener("xml-order-topic")
    public void process(KStream<String, String> input){

        KStream<String, Order> orderKStream = input.mapValues(v -> {
            Order order = null;
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Order.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                order = (Order) jaxbUnmarshaller.unmarshal(new StringReader(v));
            } catch (JAXBException e) {
                log.error("Failed to Unmarshal the incoming XML");
            }
            return order;
        });

        orderKStream.foreach((k, v) -> log.info(String.format("Unmarshal Completed for Order ID: %s", v.getOrderId())));
    }

}
