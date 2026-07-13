package ru.yandex.authapp.rest;


import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.authapp.dto.UserMapper;
import ru.yandex.authapp.dto.UserResponse;
import ru.yandex.authapp.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;
    private final UserMapper userMapper;


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers() {
        return userService.getUsers()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @GetMapping("/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse getUser(@PathVariable String email) {
        return userMapper.toResponse(userService.getByEmail(email));
    }
}
