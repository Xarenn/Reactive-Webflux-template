package com.template.reactive.service.filter.test;

import com.template.reactive.service.filter.Filter;

public class StringFilter implements Filter {

    @Override
    public String apply() {
        return "filter string";
    }
}
