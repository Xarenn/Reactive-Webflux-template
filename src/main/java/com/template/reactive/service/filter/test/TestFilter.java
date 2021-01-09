package com.template.reactive.service.filter.test;

import com.template.reactive.service.filter.Filter;

public class TestFilter implements Filter {

    @Override
    public String apply() {
        return "filter test";
    }
}
