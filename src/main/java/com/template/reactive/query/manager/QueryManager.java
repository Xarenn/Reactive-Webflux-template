package com.template.reactive.query.manager;

import com.template.reactive.service.filter.Filter;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class QueryManager {

    private List<Filter> filters = new ArrayList<>();

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public void addFilters(List<Filter> filters) {
        filters.addAll(filters);
    }

}
