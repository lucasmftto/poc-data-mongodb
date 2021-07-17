package br.com.pocdatamongodb.controller;

import br.com.pocdatamongodb.entity.Customer;
import br.com.pocdatamongodb.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/docs")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DocumentsController {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private JmsTemplate jmsTemplateTopic;

    Logger logger = LoggerFactory.getLogger(DocumentsController.class);


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Customer customer) {
        MDC.put("Object queue and topic: ", String.valueOf(UUID.randomUUID()));

        logger.info("Sending queue: " + customer.toString());
        jmsTemplate.convertAndSend("queue.sample", customer);

        logger.info("Sending topic: " + customer.toString());
        jmsTemplateTopic.convertAndSend("topic.sample", customer);

        MDC.clear();
    }
}
