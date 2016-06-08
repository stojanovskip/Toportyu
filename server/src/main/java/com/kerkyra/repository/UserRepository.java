package com.kerkyra.repository;

import com.kerkyra.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Andras.Timar on 6/3/2016.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByUsername(String username);
}
