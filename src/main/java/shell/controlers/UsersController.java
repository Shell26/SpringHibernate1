package shell.controlers;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shell.model.User;
import shell.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UsersController {

    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(path = "/admin", method = RequestMethod.GET)
    public ModelAndView getAllUsers() {
        List<User> users = userService.getAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainPage");
        modelAndView.addObject("usersFromServer", users);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/delete/{*}", method = RequestMethod.POST)
    public String deleteUser(@PathVariable("*") Long userId) {
        userService.delete(userId);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) { //@PathVariable указывает на то, что данный параметр (int id) получается из адресной строки
        User user = userService.get((long) id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin"); //означает, что после выполнения данного метода мы будем перенаправлены на адрес "/"
        userService.edit(user);
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
    public ModelAndView getIndexPage (Authentication authentication, ModelMap model, HttpServletRequest request) {
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
        userService.save(user, "USER");
        return "redirect:/admin";
    }

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp (User user, String role) {
        userService.save(user, role);
        return "redirect:/login";
    }

    @RequestMapping(path = "/admin/save", method = RequestMethod.GET)
    public ModelAndView save() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("save");
        return modelAndView;
    }
}
