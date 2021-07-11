package br.com.pocdatamongodb.listener;

import br.com.pocdatamongodb.entity.Customer;
import br.com.pocdatamongodb.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class DocumentsListener {

    @Autowired
    private CustomerRepository repository;

    @JmsListener(destination = "queue.sample")
    public void onReceiverQueue(Customer customer) {
        this.repository.save(customer);
        System.out.println("Sample: " + customer.toString() );
    }

    @JmsListener(destination = "topic.sample", containerFactory = "jmsFactoryTopic")
    public void onReceiverTopic(Customer customer) {
        this.repository.save(customer);
        System.out.println("Topic: " + customer.toString() );
    }

}
