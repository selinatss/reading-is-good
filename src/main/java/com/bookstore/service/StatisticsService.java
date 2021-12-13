package com.bookstore.service;

import com.bookstore.dao.OrderDao;
import com.bookstore.model.Order;
import com.bookstore.response.StatisticsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormatSymbols;
import java.util.Date;
import java.util.List;

@Service
public class StatisticsService {
     @Autowired
     OrderDao orderDao;

     public StatisticsResponse getCustomersMonthlyStatistic(String id, int month){
         List<Order> orders = orderDao.findByCustomer_IdAndOrderDate_Month(id, month);
         int totalBookCount = 0;
         int totalOrderCount = 0;
         double totalPurchasedAmount = 0;
         for(Order order:orders){
             totalOrderCount++;
             totalBookCount += order.getBooks().size();
             totalPurchasedAmount += order.getOrderPurchased();
         }

         StatisticsResponse statisticsResponse = new StatisticsResponse();
         statisticsResponse.setMonth(new DateFormatSymbols().getMonths()[month]);
         statisticsResponse.setTotalBookCount(totalBookCount);
         statisticsResponse.setTotalOrderCount(totalOrderCount);
         statisticsResponse.setTotalPurchasedCount(totalPurchasedAmount);

         return statisticsResponse;
     }
}
