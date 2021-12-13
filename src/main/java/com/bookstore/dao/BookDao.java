package com.bookstore.dao;

import com.bookstore.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookDao extends MongoRepository<Book, String> {

}
