//package ru.yandex.gateway.route;
//
//import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.function.*;
//
//import java.util.UUID;
//
//@Configuration
//public class GatewayRoutes {
//
//    @Bean
//    public RouterFunction<ServerResponse> routes() {
//
//        return RouterFunctions.route()
//                .GET("/api/tasks/**", HandlerFunctions.http("http://localhost:8082"))
//                .filter(correlationIdFilter())
//                .build();
//    }
//    private HandlerFilterFunction<ServerResponse, ServerResponse> correlationIdFilter() {
//
//        return (request, next) -> {
//
//            String correlationId = request.headers().firstHeader("X-Correlation-ID");
//
//            if (correlationId == null || correlationId.isBlank()) {
//                correlationId = UUID.randomUUID().toString();
//            }
//
//            ServerRequest modifiedRequest = ServerRequest.from(request).
//                    header("X-Correlation-ID", correlationId)
//                    .build();
//
//            return next.handle(modifiedRequest);
//        };
//    }
//}
