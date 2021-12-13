package com.bookstore.controler;

import com.bookstore.model.Order;
import com.bookstore.response.RestResponse;
import com.bookstore.service.OrderService;
import com.bookstore.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value="/api/order/v1")
public class OrderController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OrderService orderService;

    @PostMapping(value = "/save")
    public ResponseEntity<RestResponse> create(@RequestBody Order order) {
        try {
            Order savedOrder = orderService.saveOrder(order);
            logger.info("Customer registered successfully");
            return ResponseEntity.ok().body(RestResponse.of("id = " + savedOrder.getId(), HttpStatus.OK, Constant.successMessage));
        } catch (Exception e) {
            logger.error("Error during customer register");
            return ResponseEntity.ok().body(RestResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, Constant.errorMessage));
        }
    }

    @GetMapping(value = "/get")
    public ResponseEntity<RestResponse> getOrderById(@RequestParam String id) {
        try {
            return ResponseEntity.ok().body(RestResponse.of(orderService.getOrderById(id), HttpStatus.OK, Constant.successMessage));
        } catch (Exception e) {
            logger.error("Error during process");
            return ResponseEntity.ok().body(RestResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, Constant.errorMessage));
        }
    }

    @GetMapping(value = "/getOrders")
    public ResponseEntity<RestResponse> getOrderById(@RequestParam(value="startDate")  @DateTimeFormat(pattern="MMddyyyy") Date startDate,
                                                     @RequestParam(value="endDate")   @DateTimeFormat(pattern="MMddyyyy") Date endDate) {
        try {
            List<Order> orders = orderService.getOrders(startDate, endDate);
            return ResponseEntity.ok().body(RestResponse.of(orders, HttpStatus.OK, Constant.successMessage));
        } catch (Exception e) {
            logger.error("Error during process");
            return ResponseEntity.ok().body(RestResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, Constant.errorMessage));
        }
    }

}
