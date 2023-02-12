package com.mx.accenture.springmvc.example.dto;

import java.util.List;

public class StudentDTO {

    private Integer id;
    private String name;
    private List<CourseDTO> courses;

    public StudentDTO() {
    }

    public StudentDTO(Integer id, String name, List<CourseDTO> courses) {
        this.id = id;
        this.name = name;
        this.courses = courses;
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

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }
}
