package com.template.reactive.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CredentialsDTO {

    @JsonProperty("jwt_token")
    private String jwtToken;

}
