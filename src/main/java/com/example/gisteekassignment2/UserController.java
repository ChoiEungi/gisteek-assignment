package com.example.gisteekassignment2;

import com.example.gisteekassignment2.domain.User;
import com.example.gisteekassignment2.domain.UserResponseDto;
import com.example.gisteekassignment2.repository.UserRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    UserRepositroy userRepositroy;

    @GetMapping("/users")
    private ResponseEntity<Object> retrieveAllUser(){
        return ResponseEntity.ok().body(userRepositroy.findAll());
    }

    @GetMapping("/users/{name}")
    private ResponseEntity<Object> retrieveUser(@PathVariable String name ){
        List<UserResponseDto> userDtos = new ArrayList<>();
        for (User user: userRepositroy.findByName(name)){
            userDtos.add(UserResponseDto.from(user));
        }

        return ResponseEntity.ok().body(userDtos);
    }

    @GetMapping("/{name}/{age}")
    private ResponseEntity<Object> retrieveUserByURL(@PathVariable String name, @PathVariable int age){
        UserResponseDto user = new UserResponseDto(name, age, String.format("hello, %s year olds %s", age, name));
        return ResponseEntity.ok().body(user);

    }

}
