package com.tarigor.employeeservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
@EnableJms
@RequiredArgsConstructor
public class JmsConfiguration {

    @Bean
    public JmsListenerContainerFactory<?> factory(CachingConnectionFactory connectionFactory,
                                                  DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
//        factory.setPubSubDomain(true);
//        factory.setClientId("producer");
//        factory.setSubscriptionDurable(true);
//		factory.setSubscriptionShared(true);
//		factory.setConcurrency("1");
        connectionFactory.setClientId("producer");
        return factory;
    }

    @Bean
    public MessageConverter JmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
