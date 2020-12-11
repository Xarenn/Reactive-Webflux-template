package com.template.reactive.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("server")
public class SecurityProperties {

    private String signingKey;

    //IN SECONDS
    private Integer accessTokenValidity;

    private String adminLogin;
    private String adminPassword;

}