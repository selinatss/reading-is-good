package com.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomUser {
    @Id
    private String id;
    private String userName;
    private String email;
    private String password;
    private String token;
 }