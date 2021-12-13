package com.bookstore.dao;

import com.bookstore.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerDao extends MongoRepository<Customer, String> {
}
