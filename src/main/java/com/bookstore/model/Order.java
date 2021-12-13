package com.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "order")
public class Order {
    @Id
    private String id;
    @DBRef
    private Customer customer;
    @DBRef
    private List<Book> books;
    private Date orderDate;
    private Double orderPurchased;
    private int status;
}
