package com.example.demo.fitness.datastore;

import com.example.demo.fitness.entities.BookingDetailsDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author akshay on 28/05/21
 */
@Data
@Component
public class BookingDetailsRepo {
    private Map<String, BookingDetailsDTO> bookingDetailsDTOMap  = new HashMap<>();
}
