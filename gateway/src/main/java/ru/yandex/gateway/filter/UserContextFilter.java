//package ru.yandex.gateway.filter;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
//import org.springframework.web.servlet.function.HandlerFilterFunction;
//import org.springframework.web.servlet.function.ServerRequest;
//import org.springframework.web.servlet.function.ServerResponse;
//
//import java.util.stream.Collectors;
//
//@Configuration
//public class UserContextFilter {
//
//
//    @Bean
//    public HandlerFilterFunction<ServerResponse, ServerResponse> userContext()
//    {
//        return (request, next) ->
//        {
//
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//            if (authentication instanceof JwtAuthenticationToken jwtAuth) {
//                String userId = jwtAuth.getToken().getSubject();
//                String username = jwtAuth.getToken().getClaimAsString("preferred_username").trim();
//                String roles = authentication.getAuthorities()
//                        .stream()
//                        .map(Object::toString)
//                        .collect(Collectors.joining(","));
//
//                ServerRequest modifiedRequest = ServerRequest.from(request)
//                        .header("X-User-Id", userId)
//                        .header("X-Username", username)
//                        .header("X-User-Roles", roles)
//                        .build();
//
//                return next.handle(modifiedRequest);
//            }
//            return next.handle(request);
//        };
//
//    }
//}
