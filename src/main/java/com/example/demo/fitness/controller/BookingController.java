package com.example.demo.fitness.controller;

import com.example.demo.fitness.request.BookingRequestV1;
import com.example.demo.fitness.request.CancelRequestV1;
import com.example.demo.fitness.response.BookingResponseV1;
import com.example.demo.fitness.response.CancelResponseV1;
import com.example.demo.fitness.service.BookingServiceV1;
import com.example.demo.fitness.service.impl.BookingServiceV1Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author akshay on 28/05/21
 */
@RestController
@RequestMapping("v1/class")
public class BookingController {

    @Autowired
    private BookingServiceV1Impl bookingServiceV1;

    /**
     * API to book a class
     * @param request
     * @return
     */
    @PostMapping(path = "/book")
    @ResponseStatus(HttpStatus.OK)
    public BookingResponseV1 bookClass(@RequestBody BookingRequestV1 request){
        request.setBookingId(UUID.randomUUID().toString());
        return bookingServiceV1.bookClass(request);
    }

    /**
     * API to delete a booked class
     * @param request
     * @return
     */
    @DeleteMapping(path = "/cancel")
    @ResponseStatus(HttpStatus.OK)
    public CancelResponseV1 cancelClass(@RequestBody CancelRequestV1 request){
        return bookingServiceV1.cancelBooking(request);
    }
}
