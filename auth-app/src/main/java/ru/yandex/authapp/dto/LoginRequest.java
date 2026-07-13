package ru.yandex.authapp.dto;

public record LoginRequest
        (
                String username,
                String password
        ) {
}
