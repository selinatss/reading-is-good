package com.bookstore.dao;

import com.bookstore.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface OrderDao extends MongoRepository<Order, String> {
    List<Order> findByOrderDateBetween(Date startDate, Date endDate);

    List<Order> findByCustomer_Id(String id);

    List<Order> findByCustomer_IdAndOrderDate_Month(String id, int month);
}
