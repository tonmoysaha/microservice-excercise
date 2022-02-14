package com.course.catalog.controller;

import com.course.catalog.entity.Course;
import com.course.catalog.entity.User;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class CourseCatalogController {

    private static final String COURSE_CATALOG = "courseCatalogService";

    @Autowired
    private EurekaClient eurekaClient;

    @GetMapping("/home")
    public String Home(){
        return "exchange.getBody()";
    }

    /**
     * fallback back method and method retun type should be same
     * method signature should same except the exception parameter
     * COURSE_CATALOG are circuitBreaker instances
     * @return
     */
    @GetMapping()
    @CircuitBreaker(name = COURSE_CATALOG, fallbackMethod = "tryAfterSomeTime")
    public ResponseEntity<?> getAllCourses(){
        InstanceInfo nextServerFromEureka = eurekaClient.getNextServerFromEureka("course-app", false);
        String homePageUrl = nextServerFromEureka.getHomePageUrl();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Course>> exchange = restTemplate.exchange(homePageUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Course>>() {
        });
//        ResponseEntity<Course[]> exchange = restTemplate.exchange(homePageUrl, HttpMethod.GET, requestEntity, Course[].class);
//        return ResponseEntity.ok(exchange.getBody());
        return ResponseEntity.ok(exchange.getBody());
    }
    
    public ResponseEntity<?> tryAfterSomeTime( Exception e){
        return ResponseEntity.ok("no data found");
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
        Course forObject = restTemplate.getForObject(homePageUrl + id, Course.class);
        return forObject;
    }

    @GetMapping("user/course/{id}")
    public String getSpecificUserCourse(@PathVariable() Long id){
        InstanceInfo nextServerFromEureka = eurekaClient.getNextServerFromEureka("course-app", false);
        String homePageUrl = nextServerFromEureka.getHomePageUrl();
        RestTemplate restTemplate = new RestTemplate();
        Course course = restTemplate.getForObject(homePageUrl + id, Course.class);
        InstanceInfo nextServerFromEureka1 = eurekaClient.getNextServerFromEureka("USER-SERVICE", false);
        String userPageUrl = nextServerFromEureka1.getHomePageUrl();
        String userList = restTemplate.getForObject(userPageUrl  + course.getId(), String.class);
        return("Our first course is "+course.getCourseName() +"***** and Enrolled users are ***** "+ userList);
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
