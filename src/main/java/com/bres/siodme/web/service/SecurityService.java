package com.bres.siodme.web.service;

import org.springframework.stereotype.Service;

/**
 * Created by Adam on 2016-08-01.
 */
@Service
public interface SecurityService {
    String findLoggedInUsername();
    void autologin(String username, String password);
}
