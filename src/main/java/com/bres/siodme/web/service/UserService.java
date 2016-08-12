package com.bres.siodme.web.service;

import com.bres.siodme.web.model.User;
import org.springframework.stereotype.Service;


/**
 * Created by Adam on 2016-07-30.
 */
@Service
public interface UserService {
    void save(User user);
    User findByUsername(String username);
}
