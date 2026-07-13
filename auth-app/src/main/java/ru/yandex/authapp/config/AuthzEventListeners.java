package ru.yandex.authapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.security.authorization.event.AuthorizationGrantedEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthzEventListeners {

    private static final Logger logger = LoggerFactory.getLogger(AuthzEventListeners.class);

    @EventListener
    public void onAuthorizationDenied(AuthorizationDeniedEvent event) {
        var auth = event.getAuthentication().get();
        var resource = event.getObject();
        logger.warn("Отказ в доступе: пользователь \"{}\" не имеет прав доступа к ресурсу {}",
                auth.getName(), resource);
    }
    @EventListener
    public void onAuthorizationGranted(AuthorizationGrantedEvent event) {
        Authentication auth = event.getAuthentication().get();
        Object resource = event.getObject();
        logger.info("Успешная авторизация: пользователь \"{}\" получил доступ к ресурсу {}",
                auth.getName(), resource);

    }
}
