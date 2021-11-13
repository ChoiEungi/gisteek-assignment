package com.example.gisteekassignment2.repository;


import com.example.gisteekassignment2.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepositroy extends MongoRepository<User, Long> {

    @Query("{name:'?0'}")
    User findUserByName(String name);

}
