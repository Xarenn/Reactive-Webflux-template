package com.template.reactive;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.reactive.controllers.TestController;
import com.template.reactive.domain.dto.TestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.map;


@ExtendWith(SpringExtension.class)
@WebFluxTest
@AutoConfigureWebTestClient
public class TestControllerTest extends WebFluxTestConfigurer {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private TestController testController;

    @Test
    @WithMockUser
    public void savedTestsShouldFailedLackOfBody() {
        webTestClient.mutateWith(SecurityMockServerConfigurers.csrf())
                .post()
                .uri("/test/create")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @WithMockUser
    public void savedTestsShouldFailedNotEnoughTestsBody() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<TestDTO> testSet = Arrays.asList(TestDTO.builder().testName("T1").testNumber(1L).build(), TestDTO.builder().testName("T2").testNumber(2L).build());
        webTestClient.mutateWith(SecurityMockServerConfigurers.csrf())
                .post()
                .uri("/test/create")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(mapper.writeValueAsString(testSet))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @WithMockUser
    public void savedTestShouldBeValid() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<TestDTO> testSet = Arrays.asList(TestDTO.builder().testName("T1").testNumber(1L).build(), TestDTO.builder().testName("T2").testNumber(2L).build(), TestDTO.builder().testName("T3").testNumber(3L).build());
        EntityExchangeResult<List<TestDTO>> tests = webTestClient.mutateWith(SecurityMockServerConfigurers.csrf())
                .post()
                .uri("/test/create")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(mapper.writeValueAsString(testSet))
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<TestDTO>>() {}).returnResult();

        assertThat(tests.getResponseBody()).isEqualTo(testSet);
    }




}
