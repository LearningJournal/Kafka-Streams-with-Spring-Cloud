package guru.learningjournal.kafka.examples.avroposgen;

import guru.learningjournal.kafka.examples.avroposgen.services.KafkaProducerService;
import guru.learningjournal.kafka.examples.avroposgen.services.datagenerator.InvoiceGenerator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
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
