package com.course.catalog.controller;

import com.course.catalog.entity.Course;
import com.course.catalog.utility.APIS;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class CourseCatalogController {

    @GetMapping()
    public List<Course> getAllCourses(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Course>> exchange = restTemplate.exchange(APIS.COURSE_GET_ALL_COURSE, HttpMethod.GET, null, new ParameterizedTypeReference<List<Course>>() {
        });
        return exchange.getBody();
    }

    @PostMapping()
    public Course createCourses(@RequestBody  Course course){
        RestTemplate restTemplate = new RestTemplate();
        Course course1 = restTemplate.postForObject(APIS.COURSE_CREATE_COURSE, course, Course.class);
        return course1;
    }

    @GetMapping("/{id}")
    public Course getSpecificCourse(@PathVariable() Long id){
        RestTemplate restTemplate = new RestTemplate();
        Course forObject = restTemplate.getForObject(APIS.COURSE_GET_SPECIFIC_COURSE + 1, Course.class);
        return forObject;
    }

    @DeleteMapping("/{id}")
    public String getDeleteCourse(@PathVariable() Long id){
        RestTemplate restTemplate = new RestTemplate();
       restTemplate.delete(APIS.COURSE_DELETE_COURSE + 1, id);
        return "done";
    }
}
