package ru.yandex.authapp.service;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import ru.yandex.authapp.config.KeycloakProperties;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Keycloak keycloak;
    private final KeycloakProperties properties;

    public List<UserRepresentation> getUsers() {
        return keycloak.realm(properties.getRealm())
                .users()
                .list();
    }

    public UserRepresentation getByEmail(String email) {
        return keycloak.realm(properties.getRealm())
                .users()
                .searchByEmail(email, true)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found " + email));
    }
}
