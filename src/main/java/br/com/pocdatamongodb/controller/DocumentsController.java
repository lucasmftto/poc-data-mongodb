package br.com.pocdatamongodb.controller;

import br.com.pocdatamongodb.entity.Customer;
import br.com.pocdatamongodb.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Customer customer) {
        jmsTemplate.convertAndSend("queue.sample", customer);
        jmsTemplateTopic.convertAndSend("topic.sample", customer);
    }
}
