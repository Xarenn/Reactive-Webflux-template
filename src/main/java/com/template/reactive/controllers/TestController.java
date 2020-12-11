package com.template.reactive.controllers;

import com.template.reactive.domain.Test;
import com.template.reactive.domain.dto.CredentialsDTO;
import com.template.reactive.domain.dto.TestDTO;
import com.template.reactive.exceptions.BadRequest;
import com.template.reactive.security.jwt.JwtUtil;
import com.template.reactive.security.properties.SecurityProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class TestController {

    private final SecurityProperties securityProperties;

    public static Mono<Set<Test>> saveTests(Mono<Set<TestDTO>> testDTOSet) {
        return testDTOSet.flatMap(test -> Mono.justOrEmpty(test.stream().map(testDTO -> new Test().testNumber(testDTO.getTestNumber()).testName(testDTO.getTestName())).collect(Collectors.toSet()))).filter(testSet -> testSet.size() > 2)
                .switchIfEmpty(Mono.error(new BadRequest("Not enough tests")));
    }

    public static Mono<String> checkAuthorized(Mono<? extends Principal> principal) {
        return principal.map(Principal::getName).switchIfEmpty(Mono.error(new BadRequest("Subject credential is empty")));
    }

    public Mono<CredentialsDTO> getJwt(String username, String role) {
        return Mono.justOrEmpty(CredentialsDTO.builder().jwtToken(new JwtUtil(securityProperties).generateToken(username, Arrays.asList(role))).build());
    }

}
