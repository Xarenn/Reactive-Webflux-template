package com.template.reactive.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Test implements Serializable {

    private Long id;

    private String testName;

    private Long testNumber;

    public Test testName(String testName) {
        this.testName = testName;
        return this;
    }

    public Test testNumber(Long testNumber) {
        this.testNumber = testNumber;
        return this;
    }

}
