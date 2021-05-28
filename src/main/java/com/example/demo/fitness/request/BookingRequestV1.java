package com.example.demo.fitness.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author akshay on 28/05/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestV1 {
    private String classId;
    private Long userId;
    private String bookingId;
}
