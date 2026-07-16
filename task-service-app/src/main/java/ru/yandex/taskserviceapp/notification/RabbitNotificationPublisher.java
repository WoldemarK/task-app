package ru.yandex.taskserviceapp.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.yandex.taskserviceapp.config.RabbitConfig;
import ru.yandex.taskserviceapp.dto.RabbitProperties;
import ru.yandex.taskserviceapp.notification.event.ReminderEvent;
import ru.yandex.taskserviceapp.notification.event.TaskOverdueEvent;

@Primary
@Component
@RequiredArgsConstructor
public class RabbitNotificationPublisher implements NotificationPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitProperties properties;

    @Override
    public void publishReminder(ReminderEvent event) {
        rabbitTemplate.convertAndSend(
                properties.exchange(),
                properties.routingKeys().reminder(),
                event
        );

    }

    @Override
    public void publishTaskOverdue(TaskOverdueEvent event) {
        rabbitTemplate.convertAndSend(
                properties.exchange(),
                properties.routingKeys().overdue(),
                event
        );

    }
}
