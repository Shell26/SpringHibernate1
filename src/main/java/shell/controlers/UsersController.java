package shell.controlers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shell.model.User;

import shell.repositories.UserRepository;
import shell.service.RoleService;
import shell.service.UserService;
import shell.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UsersController {
//
//    private UserServiceImpl userService;
//
//    public UsersController(UserServiceImpl userService) {
//        this.userService = userService;
//    }

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

    @RequestMapping(path = "/admin", method = RequestMethod.GET)
    public ModelAndView getAllUsers() {
        List<User> users = userService.findAllUser();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainPage");
        modelAndView.addObject("usersFromServer", users);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/delete/{*}", method = RequestMethod.POST)
    public String deleteUser(@PathVariable("*") Long userId) {
        userService.deleteById(userId);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/edit/{*}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("*") long id) { //@PathVariable указывает на то, что данный параметр (int id) получается из адресной строки
        User user = userService.findOneById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        user.setId(id);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute("user") User user) {
        User userFromDB = userService.findOneById(user.getId());
        userFromDB.setLogin(user.getLogin());
        userFromDB.setName(user.getName());
        userFromDB.setPassword(user.getPassword());
        userRepository.save(userFromDB);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin"); //означает, что после выполнения данного метода мы будем перенаправлены на адрес "/"
        return modelAndView;
    }

    @GetMapping("/login")
    public String getLoginPage (Authentication authentication, ModelMap model, HttpServletRequest request) {
        if (authentication != null) {
            return "redirect: /user";
        }
        if (request.getParameterMap().containsKey("error")) {
            model.addAttribute("error", true);
        }
        return "login";
    }

    @GetMapping("/user")
    public ModelAndView getIndexPage (Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("user", authentication.getName());
        return modelAndView;
    }

    @GetMapping("/")
    public String getLoginPage(Authentication authentication) {
        if (authentication != null) {
            return "redirect: /user";
        }
        return "login";
    }

    @RequestMapping(path = "/admin/save", method = RequestMethod.POST)
    public String saveUsers(User user) {
        user.setRoles(roleService.getRoleByName("USER"));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp (User user, String role) {
        if (role.equals("USER")) {
            user.setRoles(roleService.getRoleByName(role));
        } else {
            user.setRoles(roleService.getRoleByName("ADMIN"));
        }
        userRepository.save(user);
        return "redirect:/login";
    }

    @RequestMapping(path = "/admin/save", method = RequestMethod.GET)
    public ModelAndView save() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("save");
        return modelAndView;
    }
}
