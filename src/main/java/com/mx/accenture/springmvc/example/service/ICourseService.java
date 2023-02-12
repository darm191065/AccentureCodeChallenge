package com.mx.accenture.springmvc.example.service;

import com.mx.accenture.springmvc.example.dto.CourseDTO;

import java.util.List;

public interface ICourseService {

    public List<CourseDTO> listCourse();

    CourseDTO getCourseById(Integer id);

    CourseDTO addCourse(CourseDTO courseDTO);

    CourseDTO updateCourse(CourseDTO courseDTO, Integer id);

    public void deleteCourse(int idCourse);

}