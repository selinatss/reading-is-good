package com.bookstore.service;

import com.bookstore.dao.CustomerDao;
import com.bookstore.dao.OrderDao;
import com.bookstore.model.Customer;
import com.bookstore.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerDao customerDao;

    @Autowired
    OrderDao orderDao;

    public Customer saveCustomer(Customer customer){
        customer.setRegisterDate(new Date());
        return customerDao.save(customer);
    }

    public List<Customer> list(){
       return customerDao.findAll();
    }

    public List<Order> listAllOrdersOfCustomer(String id){
        return orderDao.findByCustomer_Id(id);
    }
}
