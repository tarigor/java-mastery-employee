package com.tarigor.employeeservice.service.impl;

import com.tarigor.employeeservice.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProducerServiceImpl {

    private final JmsTemplate jmsTemplate;
    @Value("${spring.activemq.topic}")
    private String topicName;

    public void sendMessage(EmployeeDTO employeeDTO) {
        jmsTemplate.convertAndSend(topicName, employeeDTO);
    }
}
