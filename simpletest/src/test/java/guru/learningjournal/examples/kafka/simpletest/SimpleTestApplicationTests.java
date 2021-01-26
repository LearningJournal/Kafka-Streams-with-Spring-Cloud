package guru.learningjournal.examples.kafka.simpletest;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = {"server.port=0"})
public class SimpleTestApplicationTests {


    @ClassRule
    public static EmbeddedKafkaRule embeddedKafkaRule = new EmbeddedKafkaRule(1, true, 1,
            "input-topic", "output-topic");
    private static EmbeddedKafkaBroker embeddedKafka = embeddedKafkaRule.getEmbeddedKafka();

    @Autowired
    StreamsBuilderFactoryBean streamsBuilderFactoryBean;

    private static Consumer<String, String> consumer;

    @BeforeClass
    public static void setUp() {

        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("group", "false", embeddedKafka);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        DefaultKafkaConsumerFactory<String, String> cf = new DefaultKafkaConsumerFactory<>(consumerProps);
        consumer = cf.createConsumer();
        embeddedKafka.consumeFromAnEmbeddedTopic(consumer, "output-topic");

        System.setProperty("spring.cloud.stream.kafka.streams.binder.brokers", embeddedKafka.getBrokersAsString());
    }

    @AfterClass
    public static void tearDown() {
        consumer.close();
        System.clearProperty("spring.cloud.stream.kafka.streams.binder.brokers");
    }

    @Test
    public void SimpleProcessorApplicationTest() {
        Set<String> actualResultSet = new HashSet<>();
        Set<String> expectedResultSet = new HashSet<>();
        expectedResultSet.add("HELLO1");
        expectedResultSet.add("HELLO2");

        Map<String, Object> senderProps = KafkaTestUtils.producerProps(embeddedKafka);
        DefaultKafkaProducerFactory<Integer, String> pf = new DefaultKafkaProducerFactory<>(senderProps);
        try {
            KafkaTemplate<Integer, String> template = new KafkaTemplate<>(pf, true);

            template.setDefaultTopic("input-topic");

            template.sendDefault("hello1");
            template.sendDefault("hello2");

            int receivedAll = 0;
            while(receivedAll<2) {
                ConsumerRecords<String, String> cr = KafkaTestUtils.getRecords(consumer);
                receivedAll = receivedAll + cr.count();
                cr.iterator().forEachRemaining(r -> actualResultSet.add(r.value()));
            }

            assertThat(actualResultSet.equals(expectedResultSet)).isTrue();
        }
        finally {
            pf.destroy();
        }
    }

}
