package ru.yandex.taskserviceapp.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.rabbitmq")
public record RabbitProperties(

        String exchange,
        Queues queues,
        RoutingKeys routingKeys
) {

    public record Queues(
            String reminder,
            String overdue
    ) {
    }

    public record RoutingKeys(
            String reminder,
            String overdue
    ) {
    }
}