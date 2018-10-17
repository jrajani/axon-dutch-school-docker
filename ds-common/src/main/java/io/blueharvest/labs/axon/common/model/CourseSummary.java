package io.blueharvest.labs.axon.common.model;


public class CourseSummary {

    private Integer id;
    private String name;
    private int totalStudents;

    public CourseSummary() {

    }

    public CourseSummary(Integer id, String name, int totalStudents) {
        this.id = id;
        this.name = name;
        this.totalStudents = totalStudents;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }

    @Override
    public String toString() {
        return "CourseSummary{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", totalStudents=" + totalStudents +
                '}';
    }
}
