package com.example.demo.fitness.datastore;

import com.example.demo.fitness.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author akshay on 28/05/21
 */
@Component
public class UserRepo {
    Map<Long, User> map = new HashMap<>();

    public UserRepo() {
        init();
    }

    /**
     * Simulating init: as a read from data base and map as a cache for quick retrieval
     *
     */
    private void init() {
        User user1 = new User("akshay", 1L);
        User user2 = new User("harshit", 2L);
        User user3 = new User("akshay", 3L);

        map.put(user1.getUserId(), user1);
        map.put(user2.getUserId(), user2);
        map.put(user3.getUserId(), user3);
    }
}
