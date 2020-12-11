package guru.learningjournal.examples.kafka.avroposgen;

import guru.learningjournal.examples.kafka.avroposgen.services.datagenerator.InvoiceGenerator;
import guru.learningjournal.examples.kafka.avroposgen.services.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AvroPosGeneratorApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(AvroPosGeneratorApplication.class, args);
	}

	@Autowired
	private KafkaProducerService producerService;

	@Autowired
	private InvoiceGenerator invoiceGenerator;

	@Value("${application.configs.invoice.count}")
	private int INVOICE_COUNT;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		for (int i = 0; i < INVOICE_COUNT; i++) {
			producerService.sendMessage(invoiceGenerator.getNextInvoice());
			Thread.sleep(1000);
		}
	}

}
