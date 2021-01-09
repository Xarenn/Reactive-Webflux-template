package com.template.reactive.query;

import com.template.reactive.query.manager.QueryManager;
import com.template.reactive.service.filter.Filter;

import java.util.Set;
import java.util.stream.Collectors;

public class Query {

    private QueryManager queryManager;

    public Query(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

    public Set<String> execute() {
        return queryManager.getFilters().stream().map(Filter::apply).collect(Collectors.toSet());
    }

}
