package com.example.demo.fitness.datastore;

import com.example.demo.fitness.entities.FitnessClass;
import com.example.demo.fitness.entities.FitnessClassMetadata;
import com.example.demo.fitness.entities.FitnessClassType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author akshay on 28/05/21
 */
@Getter
@Setter
@Component
@Slf4j
public class FitnessClassRepo {

    private Map<String, Map<String, Map<Date, List<FitnessClass>>>> classDetails  = new HashMap<>();
    private Map<String, FitnessClass> classIdClassDetailsMap  = new HashMap<>();

    public FitnessClassRepo() {
        init();
    }

    private void init() {
        FitnessClassMetadata yoga = new FitnessClassMetadata("Yoga", null, 1, 60, FitnessClassType.YOGA, 1);
        FitnessClassMetadata dance = new FitnessClassMetadata("Dance", null, 1, 60, FitnessClassType.DANCE, 1);
        FitnessClassMetadata gym = new FitnessClassMetadata("Gym", null, 1, 60, FitnessClassType.GYM, 1);
        FitnessClass yogaFitnessClass = new FitnessClass("c_id1", 1622207755094L, 1622207755094L, 1L, yoga);
        FitnessClass  danceFitnessClass = new FitnessClass("c_id2", 1622207755094L, 1622207755094L, 1L, dance);
        FitnessClass gymFitnessClass = new FitnessClass("c_id3", 1622207755094L, 1622207755094L, 1L, gym);

        classIdClassDetailsMap.put(yogaFitnessClass.getClassId(), yogaFitnessClass);
        classIdClassDetailsMap.put(danceFitnessClass.getClassId(), danceFitnessClass);
        classIdClassDetailsMap.put(gymFitnessClass.getClassId(), gymFitnessClass);

        Map<Date, List<FitnessClass>> dateWiseClassDetails = new HashMap<>();
        dateWiseClassDetails.put(new Date(), Arrays.asList(yogaFitnessClass, danceFitnessClass));

        Map<String, Map<Date, List<FitnessClass>>> zipCodeBasedClasses = new HashMap<>();
        zipCodeBasedClasses.put("560030", dateWiseClassDetails);

        dateWiseClassDetails = new HashMap<>();
        dateWiseClassDetails.put(new Date(), Arrays.asList(gymFitnessClass));

        zipCodeBasedClasses.put("560031", dateWiseClassDetails);

        classDetails.put("bangalore", zipCodeBasedClasses);
        log.info("{}",classDetails);
    }
}
