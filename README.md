# gisteek-assignment


### Environment
- Java 11
- Spring Boot 2.5.6

### Dependencies
- Lombok
- Spring Web
- Spring-Data-mongo





Docker-compose를 이용해 mongodb client인 mongo expressf를 localhost:8081에 띄움

<img src="https://raw.githubusercontent.com/ChoiEungi/git-blog-image/upload/img/202111131437994.png"><img src="https://raw.githubusercontent.com/ChoiEungi/git-blog-image/upload/img/202111131432073.png">



### Code & Result

**DTO**

```java
@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {
    private String name;
    private String  age;
    private String message;

}

```



**Service and Controller**

```java
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

```



**Result**

<img src="https://raw.githubusercontent.com/ChoiEungi/git-blog-image/upload/img/202111131649736.png">