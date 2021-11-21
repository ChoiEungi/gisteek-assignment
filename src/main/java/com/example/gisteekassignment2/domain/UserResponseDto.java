package com.example.gisteekassignment2.domain;


public class UserResponseDto {
    public final String name;
    public final int age;
    public final String message;

    public UserResponseDto(String name, int age, String message) {
        this.name = name;
        this.age = age;
        this.message = message;
    }

    public static UserResponseDto from(User user){
        int age = user.getAge();
        String name = user.getName();
        return new UserResponseDto(user.getName(), user.getAge(), String.format("hello, %s year olds %s", age, name));
    }



}
