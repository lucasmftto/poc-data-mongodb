package br.com.pocdatamongodb.listener;

import br.com.pocdatamongodb.entity.Customer;
import br.com.pocdatamongodb.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class DocumentsListener {

    @Autowired
    private CustomerRepository repository;

    Logger logger = LoggerFactory.getLogger(DocumentsListener.class);

    @JmsListener(destination = "queue.sample")
    public void onReceiverQueue(Customer customer) {
        logger.info("save queue: " + customer.toString());
        this.repository.save(customer);
    }

    @JmsListener(destination = "topic.sample", containerFactory = "jmsFactoryTopic")
    public void onReceiverTopic(Customer customer) {
        logger.info("save Topic: " + customer.toString());
        this.repository.save(customer);
    }

}
