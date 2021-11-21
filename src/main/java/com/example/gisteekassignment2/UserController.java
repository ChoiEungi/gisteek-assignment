package com.example.gisteekassignment2;

import com.example.gisteekassignment2.domain.User;
import com.example.gisteekassignment2.domain.UserRequestDto;
import com.example.gisteekassignment2.domain.UserResponseDto;
import com.example.gisteekassignment2.repository.UserRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    UserRepositroy userRepositroy;

    @GetMapping("/users")
    private ResponseEntity<Object> retrieveAllUser(){
        List<UserResponseDto> userDtos = new ArrayList<>();
        for (User user : userRepositroy.findAll()) {
            userDtos.add(UserResponseDto.from(user));
        }
        return ResponseEntity.ok().body(userDtos);
    }

    @GetMapping("/users/{name}")
    private ResponseEntity<Object> retrieveUser(@PathVariable String name ){
        List<UserResponseDto> userDtos = new ArrayList<>();
        for (User user: userRepositroy.findByName(name)){
            userDtos.add(UserResponseDto.from(user));
        }

        return ResponseEntity.ok().body(userDtos);
    }

    @PostMapping("/users")
    private ResponseEntity<Object> createUser(@RequestBody UserRequestDto userRequestDto){

        String name =userRepositroy.save(
                User.builder()
                        .age(userRequestDto.getAge())
                        .name(userRequestDto.getName())
                        .build()
        ).getName();

        return ResponseEntity.created(URI.create("/users/"+name)).build();

    }

    @GetMapping("/{name}/{age}")
    private ResponseEntity<Object> retrieveUserByURL(@PathVariable String name, @PathVariable int age){
        UserResponseDto user = new UserResponseDto(name, age, String.format("hello, %s year olds %s", age, name));
        return ResponseEntity.ok().body(user);

    }

}
