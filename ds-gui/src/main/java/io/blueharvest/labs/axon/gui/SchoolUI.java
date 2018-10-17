package io.blueharvest.labs.axon.gui;

import com.vaadin.annotations.Push;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import io.blueharvest.labs.axon.common.command.AddCourseCommand;
import io.blueharvest.labs.axon.common.model.CourseSummary;
import io.blueharvest.labs.axon.common.command.RegisterStudentCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;

@SpringUI
@Push
public class SchoolUI extends UI {

    private final QueryGateway queryGateway;

    private final CommandGateway commandGateway;

    private CourseSummaryDataProvider courseSummaryDataProvider;

    public SchoolUI(QueryGateway queryGateway, CommandGateway commandGateway) {
        this.queryGateway = queryGateway;
        this.commandGateway = commandGateway;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponents(createCoursePanel(), registerStudentsPanel());
        horizontalLayout.setSizeFull();

        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(horizontalLayout);
        layout.addComponents(summaryGrid());
        layout.setHeight(95, Unit.PERCENTAGE);

        setContent(layout);

        UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
            @Override
            public void error(com.vaadin.server.ErrorEvent event) {
                Throwable cause = event.getThrowable();
                while(cause.getCause() != null) cause = cause.getCause();
                Notification.show("Error", cause.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
        });
    }

    private Component registerStudentsPanel() {
        TextField id = new TextField("Course ID");
        TextField noOfStudents = new TextField("Number of Students");
        Button submit = new Button("Submit");

        submit.addClickListener(evt -> {
            commandGateway.sendAndWait(new RegisterStudentCommand(Integer.parseInt(id.getValue()), Integer.parseInt(noOfStudents.getValue())));
            Notification.show("Success", Notification.Type.HUMANIZED_MESSAGE)
                    .addCloseListener(e -> courseSummaryDataProvider.refreshAll());
        });

        FormLayout form = new FormLayout();
        form.addComponents(id, noOfStudents, submit);
        form.setMargin(true);

        Panel panel = new Panel("Register Course");
        panel.setContent(form);
        return panel;
    }

    private Component createCoursePanel() {
        TextField id = new TextField("Course ID");
        TextField name = new TextField("Name");
        Button submit = new Button("Submit");

        submit.addClickListener(evt -> {
            commandGateway.sendAndWait(new AddCourseCommand(Integer.parseInt(id.getValue()), name.getValue()));
            Notification.show("Success", Notification.Type.HUMANIZED_MESSAGE)
                    .addCloseListener(e -> courseSummaryDataProvider.refreshAll());
        });

        FormLayout form = new FormLayout();
        form.addComponents(id, name, submit);
        form.setMargin(true);

        Panel panel = new Panel("Add New Course");
        panel.setContent(form);
        return panel;
    }

    private Grid summaryGrid() {
        courseSummaryDataProvider = new CourseSummaryDataProvider(queryGateway);
        Grid<CourseSummary> grid = new Grid<>();
        grid.addColumn(CourseSummary::getId).setCaption("Course ID");
        grid.addColumn(CourseSummary::getName).setCaption("Course Name");
        grid.addColumn(CourseSummary::getTotalStudents).setCaption("Total Students");
        grid.setSizeFull();
        grid.setDataProvider(courseSummaryDataProvider);
        return grid;
    }
}
