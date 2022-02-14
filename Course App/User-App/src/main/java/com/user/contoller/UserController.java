package com.user.contoller;

import com.user.entity.User;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public List<User> getUser() {
        return userRepository.findAll();
    }

    @RequestMapping("/{id}")
    public List<User> getUsersForACours(@PathVariable("id") BigInteger id) {
        return userRepository.findBycourseid(id);
    }

    @PostConstruct
    public void  addDummyCourses(){
        for (int i =1; i<=3; i++) {
            User user = new User();
            user.setCourseid(BigInteger.valueOf(i));
            user.setUserid(BigInteger.valueOf(i));
            user.setUsename("g tech" + i);
            userRepository.save(user);

        }
    }

}
