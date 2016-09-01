package com.bres.siodme.web.security;

import com.bres.siodme.web.model.Role;
import com.bres.siodme.web.model.User;
import com.bres.siodme.web.repository.RoleRepository;
import com.bres.siodme.web.repository.UserRepository;
import com.bres.siodme.web.service.UserService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Adam on 2016-07-30.
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    @Transactional
    /**
     * Newly registered users may receive only User Privileges.
     */
    public void save(User user) throws ConstraintViolationException {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            List<Role> list = new ArrayList<>();
            list.add(roleRepository.findByName("ROLE_USER"));
            user.setRoles(list);
            userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {

        return userRepository.findByUsername(username);
    }
}
