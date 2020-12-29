package guru.learningjournal.examples.kafka.xmlbranching.model;

import guru.learningjournal.examples.kafka.model.Order;
import lombok.Data;

@Data
public class OrderEnvelop {
    String xmlOrderKey;
    String xmlOrderValue;

    String orderTag;
    Order validOrder;
}
