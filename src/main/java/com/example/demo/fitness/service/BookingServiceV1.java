package com.example.demo.fitness.service;

import com.example.demo.fitness.request.BookingRequestV1;
import com.example.demo.fitness.request.CancelRequestV1;
import com.example.demo.fitness.response.BookingResponseV1;
import com.example.demo.fitness.response.CancelResponseV1;
import org.springframework.stereotype.Service;

/**
 * @author akshay on 28/05/21
 */
@Service
public interface BookingServiceV1 {
    BookingResponseV1 bookClass(BookingRequestV1 requestV1);

    CancelResponseV1 cancelBooking(CancelRequestV1 cancelRequest);
}
