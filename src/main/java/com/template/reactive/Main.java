package com.template.reactive;

import com.template.reactive.controllers.TestController;
import com.template.reactive.domain.Test;
import com.template.reactive.domain.dto.CredentialsDTO;
import com.template.reactive.domain.dto.TestDTO;
import com.template.reactive.exceptions.handler.ExceptionHandler;
import com.template.reactive.security.properties.SecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
@EnableConfigurationProperties(SecurityProperties.class)
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> start(TestController testController) {
        return route()
                .GET("/test/{id}", accept(APPLICATION_JSON), request -> ok().contentType(APPLICATION_JSON).body(Mono.just(request.pathVariable("id")), String.class))

                .GET("/authorized/endpoint", accept(APPLICATION_JSON), request -> new ExceptionHandler<String>().handle(TestController.checkAuthorized(request.principal())))

                .GET("/auth/create-jwt/{username}/{role}", accept(APPLICATION_JSON), request -> new ExceptionHandler<CredentialsDTO>().
                        handle(testController.getJwt(request.pathVariable("username"), request.pathVariable("role"))))

                .POST("/test/create", accept(APPLICATION_JSON), request -> new ExceptionHandler<Set<Test>>().handle(TestController
                        .saveTests(request.bodyToMono(new ParameterizedTypeReference<Set<TestDTO>>() {}))) )

                .POST("/test", accept(APPLICATION_JSON), request -> ok().contentType(APPLICATION_JSON).body(request.bodyToMono(String.class), String.class))

                .GET("/test", accept(APPLICATION_JSON), request -> ok().contentType(APPLICATION_JSON).body(Mono.just("Test"), String.class)).build();
    }

}
