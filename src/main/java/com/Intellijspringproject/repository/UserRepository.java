package com.Intellijspringproject.repository;

import com.Intellijspringproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);//it search record on the basis of email
    Optional<User> findByUsernameOrEmail(String username, String email);//It finds data on basis
    //username and email
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);//it just chect recod is present and
    //return boolean value true or false
    Boolean existsByEmail(String email);

}
