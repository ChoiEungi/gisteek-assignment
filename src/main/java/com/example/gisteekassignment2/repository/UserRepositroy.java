package com.example.gisteekassignment2.repository;


import com.example.gisteekassignment2.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositroy extends MongoRepository<User, Long> {

    public List<User> findByName(String name);



}
