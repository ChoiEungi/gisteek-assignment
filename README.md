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

### Implementation

**GET** `/users`

Respone Type:  `List<User>`

200 OK



- 모든 유저의 정보를 받아옵니다.

<img src="https://raw.githubusercontent.com/ChoiEungi/git-blog-image/upload/img/202111211122028.png">





**GET:** `/users/{name}`

Respone Type:  ` List<User>`

200 OK

- name이라는 user의 정보들를 받아옵니다.



Example

`/users/eungi` 

<img src="https://raw.githubusercontent.com/ChoiEungi/git-blog-image/upload/img/202111211535381.png">



`/users/postman3`

<img src="https://raw.githubusercontent.com/ChoiEungi/git-blog-image/upload/img/202111211538185.png">

**POST**: `/users`

**Response Type**:`void` (HTTP Header에 Resource의 URI를 포함합니다.)

201 created



**Request Type** 

|      | field name |  Type  |
| :--: | :--------: | :----: |
|      |    name    | String |
|      |    age     |  int   |



**Request Example**

```json
{
    "name": "postman3",
    "age":40
}
```



Example

<img src="https://raw.githubusercontent.com/ChoiEungi/git-blog-image/upload/img/202111211538436.png">





**DB**

<img src="https://raw.githubusercontent.com/ChoiEungi/git-blog-image/upload/img/202111211119841.png">





### Code & Result

**DTO**

```java
@Getter
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
```



### Limiation

- Spring boot에서 MongoRepository의 save method를 사용할 때 _class field가 자동으로 주입된다. -> 이를 제거하지 못했음. 따라서 response dto를 제작해  user에게 age, name, message만 보냄.

  