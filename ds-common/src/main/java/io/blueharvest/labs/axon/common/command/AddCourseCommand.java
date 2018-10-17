package io.blueharvest.labs.axon.common.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class AddCourseCommand {

    @TargetAggregateIdentifier
    private Integer id;
    private String name;

    public AddCourseCommand(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
