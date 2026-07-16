package ru.yandex.taskserviceapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.yandex.taskserviceapp.dto.RabbitProperties;


@Configuration
@RequiredArgsConstructor
public class RabbitConfig {

    private final RabbitProperties properties;

    @Bean
    public TopicExchange taskExchange() {
        return new TopicExchange(properties.exchange());
    }

    @Bean
    public Queue reminderQueue() {
        return new Queue
                (
                        properties.queues()
                                .reminder(),
                        true
                );
    }

    @Bean
    public Queue overdueQueue() {
        return new Queue
                (
                        properties.queues()
                                .overdue(),
                        true
                );
    }

    @Bean
    public Binding reminderBinding() {
        return BindingBuilder.bind(reminderQueue())
                .to(taskExchange())
                .with(properties.routingKeys().reminder());
    }

    @Bean
    public Binding overdueBinding() {
        return BindingBuilder.bind(overdueQueue())
                .to(taskExchange())
                .with(properties.routingKeys().overdue());
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
