package com.bookstore.controler;

import com.bookstore.response.RestResponse;
import com.bookstore.service.StatisticsService;
import com.bookstore.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/api/v1")
public class StatisticsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StatisticsService statisticsService;

    @GetMapping(value = "statistic")
    public ResponseEntity<RestResponse> getOrderById(@RequestParam String id, @RequestParam(value="startDate")  @DateTimeFormat(pattern="MMddyyyy") Date startDate,
                                                     @RequestParam(value="endDate")   @DateTimeFormat(pattern="MMddyyyy") Date endDate) {
        try {
            return ResponseEntity.ok().body(RestResponse.of(statisticsService.getCustomersMonthlyStatistic(id, startDate, endDate), HttpStatus.OK, Constant.successMessage));
        } catch (Exception e) {
            logger.error("Error during process");
            return ResponseEntity.ok().body(RestResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, Constant.errorMessage));
        }
    }

}

