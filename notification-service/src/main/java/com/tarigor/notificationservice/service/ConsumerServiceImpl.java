package com.tarigor.notificationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarigor.notificationservice.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ConsumerServiceImpl {

    private final ObjectMapper objectMapper;

    @JmsListener(destination = "${spring.activemq.topic}")
    public void listenForMessage(String message) throws JsonProcessingException {
        EmployeeDTO employeeDTO;
        employeeDTO = objectMapper.readValue(message, EmployeeDTO.class);
        log.info("the following data of employee received -> " + employeeDTO.toString());
    }
}
