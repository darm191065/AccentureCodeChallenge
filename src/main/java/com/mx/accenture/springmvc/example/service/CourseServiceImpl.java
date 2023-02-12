package com.mx.accenture.springmvc.example.service;

import com.mx.accenture.springmvc.example.dao.CourseRepository;
import com.mx.accenture.springmvc.example.dto.CourseDTO;
import com.mx.accenture.springmvc.example.model.Course;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseDTO> listCourse() {
        List<Course> curses = courseRepository.findAll();
        return curses.stream()
                .map(CourseServiceImpl::mapCourseToCourseDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO getCourseById(Integer id) {
        return CourseServiceImpl.mapCourseToCourseDTO(courseRepository.findCourseByIdCourse(id));
    }

    @Override
    @Transactional
    public CourseDTO addCourse(CourseDTO courseDTO) {
        return mapCourseToCourseDTO(courseRepository.save(mapCourseDtoToCourse(courseDTO)));
    }

    @Override
    public CourseDTO updateCourse(CourseDTO courseDTO, Integer id) {
        Course c = courseRepository.findCourseByIdCourse(id);

        if(c == null) {
            //todo custom exception and httpsttus to 404
            throw new InvalidPropertyException(CourseDTO.class, "ID", "Provided id has no results");
        }

        c = mapCourseDtoToCourse(courseDTO);
        c.setIdCourse(id);

        return mapCourseToCourseDTO(courseRepository.save(c));
    }

    @Override
    public void deleteCourse(int idCourse) {
        Course c = courseRepository.findCourseByIdCourse(idCourse);

        if(c == null) {
            //todo custom exception and httpsttus to 404
            throw new InvalidPropertyException(CourseDTO.class, "ID", "Provided id has no results");
        }

        courseRepository.deleteByIdCourse(idCourse);
    }

    private static CourseDTO mapCourseToCourseDTO(Course course){
        return new CourseDTO(course.getIdCourse(), course.getNameCourse(), course.getTypeCourse(),
                course.getNameTeacher(), course.getNumberStudents(), course.getNumberLessons());
    }

    private static Course mapCourseDtoToCourse(CourseDTO dto) {
        Course course = new Course();
        course.setIdCourse(dto.getIdCourse());
        course.setNameCourse(dto.getNameCourse());
        course.setTypeCourse(dto.getTypeCourse());
        course.setNameTeacher(dto.getNameTeacher());
        course.setNumberStudents(course.getNumberStudents());
        course.setNumberLessons(dto.getNumberLessons());

        return course;
    }
}