package io.blueharvest.labs.axon.common.query;

public class CountCoursesResponse {
    private int count;

    public CountCoursesResponse() {

    }

    public CountCoursesResponse(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
