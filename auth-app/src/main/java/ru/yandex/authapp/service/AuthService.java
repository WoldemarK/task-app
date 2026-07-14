package ru.yandex.authapp.service;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import ru.yandex.authapp.config.KeycloakProperties;
import ru.yandex.authapp.dto.KeycloakTokenResponse;
import ru.yandex.authapp.dto.LoginRequest;
import ru.yandex.authapp.dto.RegisterRequest;
import ru.yandex.authapp.dto.TokenResponse;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final Keycloak keycloak;
    private final RestClient restClient;
    private final KeycloakProperties properties;

    public void register(RegisterRequest request) {

        RealmResource realm = keycloak.realm(properties.getRealm());

        UserRepresentation user = getUser(request);

        Response response = realm.users().create(user);

        if (response.getStatus() != 201) {
            throw new RuntimeException("User already exists");
        }

        String userId = CreatedResponseUtil.getCreatedId(response);

        CredentialRepresentation credential = getCredential(request);


        realm.users()
                .get(userId)
                .resetPassword(credential);

        assignUserRole(realm, userId);

    }
    private void assignUserRole(RealmResource realm, String userId) {

        RoleRepresentation userRole = realm.roles()
                        .get("USER")
                        .toRepresentation();


        realm.users()
                .get(userId)
                .roles()
                .realmLevel()
                .add(List.of(userRole));
    }
    private CredentialRepresentation getCredential(RegisterRequest request) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(request.password());
        credential.setTemporary(false);
        return credential;
    }

    private UserRepresentation getUser(RegisterRequest request) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(request.email());
        user.setEmail(request.email());
        user.setLastName(request.lastname());
        user.setFirstName(request.firstname());
        user.setEnabled(true);
        user.setEmailVerified(true);
        user.setRequiredActions(Collections.emptyList());
        return user;
    }

    public TokenResponse login(LoginRequest request) {

        KeycloakTokenResponse response = restClient.post()
                .uri(properties.getServerUrl()
                        + "/realms/"
                        + properties.getRealm()
                        + "/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(createForm(request))
                .retrieve()
                .onStatus(
                        status -> status.value() >= 400,
                        (req, res) -> {
                            System.out.println(new String(res.getBody().readAllBytes()));
                        }
                )
                .body(KeycloakTokenResponse.class);
        if (response == null) {
            throw new RuntimeException("Login failed");
        }
        return TokenResponse.builder()
                .accessToken(response.accessToken())
                .refreshToken(response.refreshToken())
                .build();
    }

    private MultiValueMap<String, String> createForm(LoginRequest request) {

        var form = new LinkedMultiValueMap<String, String>();
        form.add("grant_type", "password");
        form.add("client_id", properties.getClientId());
        form.add("client_secret", properties.getClientSecret());
        form.add("username", request.username());
        form.add("password", request.password());

        return form;
    }
}
