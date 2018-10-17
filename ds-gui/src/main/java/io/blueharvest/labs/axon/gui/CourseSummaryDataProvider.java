package io.blueharvest.labs.axon.gui;

import com.vaadin.data.provider.CallbackDataProvider;
import io.blueharvest.labs.axon.common.model.*;
import io.blueharvest.labs.axon.common.query.*;
import org.axonframework.queryhandling.QueryGateway;

public class CourseSummaryDataProvider extends CallbackDataProvider<CourseSummary, Void> {
    public CourseSummaryDataProvider(QueryGateway queryGateway) {
        super(q -> {
            FindCoursesQuery query = new FindCoursesQuery(q.getOffset(), q.getLimit());
            FindCoursesResponse response = queryGateway.send(
                    query, FindCoursesResponse.class).join();
            return response.getData().stream();
        }, q -> {
            CountCoursesQuery query = new CountCoursesQuery();
            CountCoursesResponse response = queryGateway.send(
                    query, CountCoursesResponse.class).join();
            return response.getCount();
        });
    }
}
