package com.template.reactive.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Builder
public class TestDTO {

    private String testName;

    private Long testNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestDTO testDTO = (TestDTO) o;
        return Objects.equals(testName, testDTO.testName) &&
                Objects.equals(testNumber, testDTO.testNumber);
    }
}
