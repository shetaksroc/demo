package com.example.demo.fitness.response;

import com.example.demo.fitness.entities.FitnessClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author akshay on 28/05/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseV1 {
    public BookingResponseV1(String bookingId) {
        this.bookingId = bookingId;
    }

    BookingStatus status;
    String message;
    String bookingId;
    FitnessClass fitnessClass;
}
