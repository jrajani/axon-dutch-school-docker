package io.blueharvest.labs.axon.command;

import io.blueharvest.labs.axon.common.command.AddCourseCommand;
import io.blueharvest.labs.axon.common.command.RegisterStudentCommand;
import io.blueharvest.labs.axon.common.event.AddCourseEvent;
import io.blueharvest.labs.axon.common.event.StudentRegisteredEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class Course {

    private final static Logger LOG = LoggerFactory.getLogger(Course.class);

    @AggregateIdentifier
    private Integer id;
    private String name;
    private int totalStudents;

    public Course() {
        LOG.debug("Empty Constructor");
    }

    @CommandHandler
    public Course(AddCourseCommand command) {
        LOG.debug("Course : [{}]", command);
        if(command.getId() < 0) throw new IllegalArgumentException("id < 0");
        apply(new AddCourseEvent(command.getId(), command.getName()));
    }

    @CommandHandler
    public void registerStudent(RegisterStudentCommand command) {
        LOG.debug("registerStudent : [{}]", command);
        if(command.getId() < 0) throw new IllegalArgumentException("id < 0");
        apply(new StudentRegisteredEvent(id, command.getTotalStudents()));
    }

    @EventSourcingHandler
    public void on(AddCourseEvent event) {
        LOG.debug("AddCourseEvent : [{}]", event);
        id = event.getId();
        name = event.getName();
    }

    @EventSourcingHandler
    public void on(StudentRegisteredEvent evt) {
        LOG.debug("StudentRegisteredEvent : [{}]", evt);
        totalStudents += evt.getTotalStudents();
    }
}
