package br.com.pocdatamongodb;

import br.com.pocdatamongodb.entity.Customer;
import br.com.pocdatamongodb.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class PocDataMongodbApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private JmsTemplate jmsTemplateTopic;


	public static void main(String[] args) {

		SpringApplication.run(PocDataMongodbApplication.class, args);
	}

	@JmsListener(destination = "queue.sample")
	public void onReceiverQueue(String str) {
		System.out.println( str );
	}

	@Override
	public void run(String... args) throws Exception {

		jmsTemplate.convertAndSend("queue.sample", "{user: 'wolmir', usando: 'fila'}");
		jmsTemplateTopic.convertAndSend("topic.sample", "{user: 'wolmir', usando: 'tópico'}");

		repository.deleteAll();

		// save a couple of customers
		repository.save(new Customer("Alice", "Smith"));
		repository.save(new Customer("Bob", "Smith"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : repository.findByLastName("Smith")) {
			System.out.println(customer);
		}

	}

}
