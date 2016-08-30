package com.bres.siodme.web.controller;

import com.bres.siodme.web.model.User;
import com.bres.siodme.web.repository.UserRepository;
import com.bres.siodme.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


/**
 * Created by Adam on 2016-07-04.
 */

@Controller
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class LoginController {
    @Autowired private UserService userService;
    @Autowired SecurityService securityService;
    @Autowired UserRepository userRepository;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model, @RequestParam(value="usernameOccupied", required=false) String userOcc) {
        model.addAttribute("userForm", new User());

        if ("1".equals(userOcc))
            model.addAttribute("message", "The username you have chosen is already taken. Try a different one.");

        return "registration";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "registration";

        if (userRepository.findByUsername(userForm.getUsername()) != null)
            return "redirect:/registration?usernameOccupied=1";

        userService.save(userForm);
        return "redirect:/login?registered=1";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value="registered", required=false) String registered,
                        Model model, String error, String logout) {

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        if ("1".equals(registered))
            model.addAttribute("message", "The registration process was sucessful.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome() {
        return "welcome";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin() {
        return "admin";
    }
}
