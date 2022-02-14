package com.user.repository;

import com.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByuserid(BigInteger id);
    List<User> findBycourseid(BigInteger id);
}
