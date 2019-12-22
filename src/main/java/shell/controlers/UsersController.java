package shell.controlers;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import shell.repositories.UserRepository;
import shell.service.RoleService;
import shell.service.UserService;


import javax.servlet.http.HttpServletRequest;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRepository userRepository;

    public UsersController(UserService userService, RoleService roleService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage ( HttpServletRequest request) {
        return "login";
    }

    @GetMapping("/user")
    public ModelAndView getIndexPage () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        modelAndView.addObject("user");
        return modelAndView;
    }

    @GetMapping("/")
    public String getLoginPage() {
        return "login";
    }
}
