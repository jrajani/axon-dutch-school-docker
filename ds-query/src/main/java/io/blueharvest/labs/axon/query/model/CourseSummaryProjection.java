package io.blueharvest.labs.axon.query.model;

import io.blueharvest.labs.axon.common.event.AddCourseEvent;
import io.blueharvest.labs.axon.common.event.StudentRegisteredEvent;
import io.blueharvest.labs.axon.common.model.*;
import io.blueharvest.labs.axon.common.query.CountCoursesQuery;
import io.blueharvest.labs.axon.common.query.CountCoursesResponse;
import io.blueharvest.labs.axon.common.query.FindCoursesQuery;
import io.blueharvest.labs.axon.common.query.FindCoursesResponse;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;


@Component
public class CourseSummaryProjection {

    private final static Logger LOG = LoggerFactory.getLogger(CourseSummaryProjection.class);

    private final EntityManager entityManager;

    public CourseSummaryProjection(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @QueryHandler
    public FindCoursesResponse handle(FindCoursesQuery query) {
        LOG.debug("handle FindCoursesResponse: [{}]", query);
        Query jpaQuery = entityManager.createQuery("SELECT c FROM CourseSummary c ORDER BY c.id", CourseSummary.class);
        jpaQuery.setFirstResult(query.getOffset());
        jpaQuery.setMaxResults(query.getLimit());
        FindCoursesResponse response = new FindCoursesResponse(jpaQuery.getResultList());
        return response;
    }

    @QueryHandler
    public CountCoursesResponse handle(CountCoursesQuery query) {
        LOG.debug("handle CountCoursesResponse: [{}]", query);

        Query jpaQuery = entityManager.createQuery("SELECT COUNT(c) FROM CourseSummary c", Long.class);
        CountCoursesResponse response = new CountCoursesResponse(((Long) jpaQuery.getSingleResult()).intValue());
        return response;
    }

    @EventHandler
    public void on(AddCourseEvent evt) {
        LOG.debug("on AddCourseEvent: [{}]", evt);
        entityManager.persist(new CourseSummary(evt.getId(), evt.getName(), 0));
    }

    @EventHandler
    public void on(StudentRegisteredEvent evt) {
        LOG.debug("on StudentRegisteredEvent: [{}]", evt);
        CourseSummary summary = entityManager.find(CourseSummary.class, evt.getId());
        summary.setTotalStudents(summary.getTotalStudents() + evt.getTotalStudents());
    }
}
