package com.course.catalog.controller;

import com.course.catalog.entity.Course;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class CourseCatalogController {

    @Autowired
    private EurekaClient eurekaClient;

    @GetMapping()
    public List<Course> getAllCourses(){
        InstanceInfo nextServerFromEureka = eurekaClient.getNextServerFromEureka("course-app", false);
        String homePageUrl = nextServerFromEureka.getHomePageUrl();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Course>> exchange = restTemplate.exchange(homePageUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Course>>() {
        });
//        ResponseEntity<Course[]> exchange = restTemplate.exchange(homePageUrl, HttpMethod.GET, requestEntity, Course[].class);
        return exchange.getBody();
    }

    @PostMapping()
    public Course createCourses(@RequestBody  Course course){
        InstanceInfo nextServerFromEureka = eurekaClient.getNextServerFromEureka("course-app", false);
        String homePageUrl = nextServerFromEureka.getHomePageUrl();
        RestTemplate restTemplate = new RestTemplate();
        Course course1 = restTemplate.postForObject(homePageUrl, course, Course.class);
        return course1;
    }

    @GetMapping("/{id}")
    public Course getSpecificCourse(@PathVariable() Long id){
        InstanceInfo nextServerFromEureka = eurekaClient.getNextServerFromEureka("course-app", false);
        String homePageUrl = nextServerFromEureka.getHomePageUrl();
        RestTemplate restTemplate = new RestTemplate();
        Course forObject = restTemplate.getForObject(homePageUrl + 1, Course.class);
        return forObject;
    }

    @DeleteMapping("/{id}")
    public String getDeleteCourse(@PathVariable() Long id){
        InstanceInfo nextServerFromEureka = eurekaClient.getNextServerFromEureka("course-app", false);
        String homePageUrl = nextServerFromEureka.getHomePageUrl();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(homePageUrl + 1, id);
        return "done";
    }
}
