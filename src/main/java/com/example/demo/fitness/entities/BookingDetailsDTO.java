package com.example.demo.fitness.entities;

import com.example.demo.fitness.response.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author akshay on 28/05/21
 */
@Data
@AllArgsConstructor
public class BookingDetailsDTO {
    private String bookingId;
    private Long userId;
    private String classId;
    private BookingStatus status;
}
