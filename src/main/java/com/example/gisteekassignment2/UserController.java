package com.example.gisteekassignment2;

import com.example.gisteekassignment2.domain.User;
import com.example.gisteekassignment2.domain.UserResponseDto;
import com.example.gisteekassignment2.repository.UserRepositroy;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    UserRepositroy userRepositroy;


    @GetMapping("/{name}/{age}")
    private ResponseEntity<Object> retrieveUser(@PathVariable String name, @PathVariable String age){
        UserResponseDto user = new UserResponseDto();
        user.setAge(age);
        user.setName(name);
        user.setMessage(String.format("hello, %s year olds %s", age, name));

        return ResponseEntity.ok().body(user);

    }
}
