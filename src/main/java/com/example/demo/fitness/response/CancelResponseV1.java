package com.example.demo.fitness.response;

import com.example.demo.fitness.entities.CancelStatus;
import com.example.demo.fitness.entities.FitnessClass;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author akshay on 28/05/21
 */
@Data
@AllArgsConstructor
public class CancelResponseV1 {
    String message;
    String bookingId;
    FitnessClass fitnessClass;
    CancelStatus cancelStatus;
}
