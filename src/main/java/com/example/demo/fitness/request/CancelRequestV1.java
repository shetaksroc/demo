package com.example.demo.fitness.request;

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
public class CancelRequestV1 {
    String bookingId;
}
