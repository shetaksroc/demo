package com.example.demo.fitness.entities;

import lombok.*;

import java.util.Map;

/**
 * @author akshay on 28/05/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FitnessClassMetadata {
    private String classTitle;
    private Map<String, Object> additionalDetails;
    private Integer capacity;
    private Integer duration;
    private FitnessClassType fitnessClassType;
    private Integer queueCapacity;
}
