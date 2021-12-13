package com.bookstore.controler;

import com.bookstore.model.Customer;
import com.bookstore.response.RestResponse;
import com.bookstore.service.CustomerService;
import com.bookstore.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/api/customer/v1")
public class CustomerController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CustomerService customerService;

    @PostMapping(value = "/save")
    public ResponseEntity<RestResponse> create(@RequestBody Customer customer) {
        try {
            Customer savedCustomer = customerService.saveCustomer(customer);
            logger.info("Customer registered successfully");
            return ResponseEntity.ok().body(RestResponse.of("id = " + savedCustomer.getId(),HttpStatus.OK, Constant.successMessage));
        } catch (Exception e) {
            logger.error("Error during customer register");
            return ResponseEntity.ok().body(RestResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, Constant.errorMessage));
        }
    }

    @GetMapping(value= "/list")
    public ResponseEntity<RestResponse> getCustomers(){
        try {
            return ResponseEntity.ok().body(RestResponse.of(customerService.list(),
                    HttpStatus.OK, Constant.successMessage));}
        catch (Exception e){
            logger.error("Error during book saved");
            return ResponseEntity.ok().body(RestResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, Constant.errorMessage));
        }
    }

    @GetMapping(value = "orders")
    public ResponseEntity<RestResponse> getCustomerOrders(@RequestParam String customerId){
        try {
            return ResponseEntity.ok().body(RestResponse.of(customerService.listAllOrdersOfCustomer(customerId),
                    HttpStatus.OK, Constant.successMessage));}
        catch (Exception e){
            logger.error("Error during book saved");
            return ResponseEntity.ok().body(RestResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, Constant.errorMessage));
        }
    }
}
