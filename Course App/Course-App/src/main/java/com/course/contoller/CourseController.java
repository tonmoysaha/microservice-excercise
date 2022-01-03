package com.course.contoller;

import com.course.entity.Course;
import com.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    public Course getSpecificCourse(@PathVariable() Long id) {
        return courseRepository.getById(id);
    }

    @PostMapping()
    public Course saveCourse(@RequestBody  Course course) {
        return courseRepository.save(course);
    }

    @DeleteMapping("/{id}")
    public void deleteSpecificCourse(@PathVariable() Long id) {
         courseRepository.deleteById(id);
    }

    @PostConstruct
    public void insertDummyData() {
        for (int i = 1; i < 5; i++) {
            String courseName = "Hibernate " + i;
            String authorName = "opi " + i;
            Course course = new Course(courseName, authorName);
            courseRepository.save(course);
        }
    }
}
