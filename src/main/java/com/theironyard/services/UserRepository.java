package com.theironyard.services;

import com.theironyard.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by alexanderhughes on 3/9/16.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUserName(String name);
}
