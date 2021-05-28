package com.example.demo.fitness.entities;

import com.example.demo.fitness.request.BookingRequestV1;
import lombok.*;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author akshay on 28/05/21
 */
@NoArgsConstructor
@Data
public class FitnessClass {
    private String classId;
    private Long startTime;
    private Long endTime;
    private Long arenaId;
    private FitnessClassMetadata fitnessClassMetadata;
    private int currentBookingCount;
    private Queue<BookingRequestV1> bookingQueue=new LinkedList<>();

    public FitnessClass(String classId, Long startTime, Long endTime, Long arenaId, FitnessClassMetadata fitnessClassMetadata) {
        this.classId = classId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.arenaId = arenaId;
        this.fitnessClassMetadata = fitnessClassMetadata;
    }

    public void incrBookingCount(){
        currentBookingCount++;
    }

    public void decrBookingCount(){
        currentBookingCount--;
    }

}
