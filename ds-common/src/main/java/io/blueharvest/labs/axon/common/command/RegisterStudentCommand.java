package io.blueharvest.labs.axon.common.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class RegisterStudentCommand {

    @TargetAggregateIdentifier
    private Integer id;
    private int totalStudents;

    public RegisterStudentCommand(int id, int noOfStudents) {
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
