package com.kerkyra.repository;

import com.kerkyra.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Petar.Stojanovski on 6/2/2016.
 */
public interface UserRepository extends CrudRepository<User,Long> {
}
