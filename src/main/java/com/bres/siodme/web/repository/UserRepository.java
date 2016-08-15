package com.bres.siodme.web.repository;

import com.bres.siodme.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Adam on 2016-07-30.
 */

/* In JPA you don't have to write the implementation of a repository interface */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
