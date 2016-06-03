package com.kerkyra.repository;

import com.kerkyra.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Andras.Timar on 5/30/2016.
 */
public interface UserRepository extends CrudRepository<User,Long> {
    List<User> findByName(String name);
}
