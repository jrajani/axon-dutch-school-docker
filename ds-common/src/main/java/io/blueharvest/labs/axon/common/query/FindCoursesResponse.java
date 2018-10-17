package io.blueharvest.labs.axon.common.query;

import io.blueharvest.labs.axon.common.model.CourseSummary;

import java.util.List;

public class FindCoursesResponse {

    List<CourseSummary> courseSummaries;

    public FindCoursesResponse(List resultList) {
        courseSummaries = resultList;
    }

    public List<CourseSummary> getData() {
        return this.courseSummaries;
    }
}
