package io.blueharvest.labs.axon.common.query;

public class FindCoursesQuery {

    private int offset;
    private int limit;

    public FindCoursesQuery() {

    }

    public FindCoursesQuery(int offset, int limit) {

    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
