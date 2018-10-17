package io.blueharvest.labs.axon.common.event;

import java.io.Serializable;

public class StudentRegisteredEvent implements Serializable {
    private int id;
    private int totalStudents;

    public StudentRegisteredEvent(int id, int noOfStudents) {
        this.id = id;
        this.totalStudents = noOfStudents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }
}
