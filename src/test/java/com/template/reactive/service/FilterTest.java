package com.template.reactive.service;

import com.template.reactive.query.Query;
import com.template.reactive.query.manager.QueryManager;
import com.template.reactive.service.filter.test.StringFilter;
import com.template.reactive.service.filter.test.TestFilter;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebFluxTest
public class FilterTest {

    @Test
    public void testFilter() {
        QueryManager queryManager = new QueryManager();
        Query query = new Query(queryManager);

        TestFilter testFilter = new TestFilter();
        queryManager.addFilter(testFilter);
        assertThat(query.execute()).hasSize(1);

        StringFilter stringFilter = new StringFilter();
        queryManager.addFilter(stringFilter);
        assertThat(query.execute()).hasSize(2);
        assertThat(query.execute()).isEqualTo(new HashSet<>(Arrays.asList("filter test", "filter string")));
    }
}
