package com.bookstore.service;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.OrderDao;
import com.bookstore.model.Book;
import com.bookstore.model.Order;
import com.bookstore.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    BookDao bookDao;

    public Order saveOrder(Order order) {
        List<Book> books = order.getBooks();

        Map<String, Long> bookCountMap = books.stream().collect(Collectors.groupingBy(Book::getId,
                Collectors.counting()));

        for (Map.Entry<String, Long> entry : bookCountMap.entrySet()) {
            Optional<Book> book = bookDao.findById(entry.getKey());
            if (book.isPresent()) {
                Book book1 = book.get();
                int stockCount = book1.getStockCount();
                if (stockCount > 0) {
                    book1.setStockCount(stockCount - entry.getValue().intValue());
                    order.setStatus(Status.Active.code);
                } else {
                    order.setStatus(Status.Canceled.code);
                    break;
                }
                bookDao.save(book1);
            }
        }
        order.setOrderDate(new Date());
        return orderDao.save(order);
    }

    public Optional<Order> getOrderById(String id) {
        Optional<Order> order = orderDao.findById(id);
        return order;
    }

    public List<Order> getOrders(Date startDate, Date endDate){
        return orderDao.findByOrderDateBetween(startDate, endDate);
    }
}
