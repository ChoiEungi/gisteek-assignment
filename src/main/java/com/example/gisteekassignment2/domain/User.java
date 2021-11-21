package com.example.gisteekassignment2.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@Document
public class User {
    @Id
    @Generated
    private String id;
    private String name;
    private int age;
}
