package shell.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import shell.model.User;
import shell.repositories.UserRepository;
import shell.service.RoleService;
import shell.service.UserService;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRepository userRepository;

    public AdminController(UserService userService, RoleService roleService) {
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

    @RequestMapping(value = "/admin/edit/{*}", method = RequestMethod.POST)
    public ModelAndView editUser(@PathVariable("*") long id, User user) {
        User userFromDB = userService.findOneById(id);
        userFromDB.setLogin(user.getLogin());
        userFromDB.setName(user.getName());
        userFromDB.setPassword(user.getPassword());
        userRepository.save(userFromDB);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin"); //означает, что после выполнения данного метода мы будем перенаправлены на адрес "/"
        return modelAndView;
    }

    @RequestMapping(path = "/admin/save", method = RequestMethod.POST)
    public String saveUsers(User user) {
        user.setRoles(roleService.getRoleByName("USER"));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @RequestMapping(path = "/admin/save", method = RequestMethod.GET)
    public ModelAndView save() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("save");
        return modelAndView;
    }
}
