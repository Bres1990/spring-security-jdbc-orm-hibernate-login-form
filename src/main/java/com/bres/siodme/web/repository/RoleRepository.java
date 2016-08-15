package com.bres.siodme.web.repository;

import com.bres.siodme.web.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Adam on 2016-08-01.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
