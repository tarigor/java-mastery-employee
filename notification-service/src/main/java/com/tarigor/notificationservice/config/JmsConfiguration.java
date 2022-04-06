package com.tarigor.notificationservice.config;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;

@Configuration
@EnableJms
public class JmsConfiguration {

    @Bean
    public JmsListenerContainerFactory<?> factory(CachingConnectionFactory connectionFactory,
                                                  DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
//        factory.setPubSubDomain(true);
//        factory.setClientId("consumer");
//        factory.setSubscriptionDurable(true);
        connectionFactory.setClientId("consumer");
        return factory;
    }
}
