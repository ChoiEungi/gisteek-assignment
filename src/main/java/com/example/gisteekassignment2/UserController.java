package com.example.gisteekassignment2;

import com.example.gisteekassignment2.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/{name}/{age}")
    private ResponseEntity<Object> retrieveUser(@PathVariable String name, @PathVariable int age){

        return ResponseEntity.ok().body();
    }
}
