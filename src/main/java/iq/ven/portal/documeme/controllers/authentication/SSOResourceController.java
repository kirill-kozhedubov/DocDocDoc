package iq.ven.portal.documeme.controllers.authentication;

import iq.ven.portal.documeme.controllers.AbstractController;
import iq.ven.portal.documeme.database.user.model.Role;
import iq.ven.portal.documeme.database.user.model.User;
import iq.ven.portal.documeme.controllers.authentication.model.UserForRegistration;
import iq.ven.portal.documeme.services.RolesService;
import iq.ven.portal.documeme.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/sso")
public class SSOResourceController extends AbstractController {

    @Autowired
    private UserService userService;

    @Autowired
    private RolesService rolesService;

    @RequestMapping(path = "/login/request", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> loginRequest(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        Map<String, Object> result = new HashMap<>();
        UserDetails userDetails = userService.loadUserByUsername(email);
        if (userDetails != null) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            if (userDetails.getUsername().equals(email) && bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {
                result.put("success", true);
            } else {
                result.put("success", false);
            }
        } else {
            result.put("isError", true);
            result.put("errorMessage", "Email or password is incorrect");
        }
        return result;
    }


    @RequestMapping(value = "/registration/request", method = RequestMethod.POST)
    public Map<String, Object> createNewUser(@Valid UserForRegistration userData, BindingResult bindingResult,
                                             HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        User userExists = null;
        User user = null;
        if (userData != null) {
            userExists = userService.findUserByEmail(userData.getEmail());
        }
        if (userExists != null) {
            result.put("errorMessage", "There is error in your registration try again");
        } else {
            if (validateUserDataForRegistration(userData)) {
                user = new User();
                user.setName(userData.getFirstName());
                user.setLastName(userData.getLastName());
                user.setEmail(userData.getEmail());
                user.setPassword(userData.getPassword());
            }

        }
        if (bindingResult.hasErrors()) {
            result.put("errorMessage", "There is error in your registration, try again");
        } else {
            if (user != null) {
                try {
                    userService.saveUser(user);
                    result.put("redirectURL", "/sso/login");
                    result.put("successfulRegistrationMessage", "User has been registered successfully");
                } catch (Exception e) {
                    logger.error("Error in saving user", e);
                    result.put("userData", user);
                    result.put("failedRegistrationMessage", "User has not been registered");
                }
            }
        }
        return result;
    }

    private static boolean validateUserDataForRegistration(UserForRegistration user) {
        boolean validationResult = false;
        if (user != null &&
                user.getEmail() != null &&
                user.getFirstName() != null &&
                user.getLastName() != null &&
                user.getPassword() != null && user.getPassword().length() >= 6) {
            validationResult = true;
        }
        return validationResult;
    }

}
