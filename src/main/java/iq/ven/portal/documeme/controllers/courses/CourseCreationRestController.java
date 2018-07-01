package iq.ven.portal.documeme.controllers.courses;

import iq.ven.portal.documeme.controllers.AbstractController;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseCreationRestController extends AbstractController {

    @RequestMapping(path = "/zulul", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> zulul(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        Map<String, Object> result = new HashMap<>();
        result.put("yayx", "yaix");
        return result;
    }

}
