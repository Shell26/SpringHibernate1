package shell.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shell.model.User;
import shell.service.RestTemplateService;


import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private RestTemplateService restTemplateService;

    public AdminController(RestTemplateService restTemplateService){
        this.restTemplateService = restTemplateService;
    }

    @RequestMapping(path = "/admin", method = RequestMethod.GET)
    public ModelAndView getAllUsers() {
        ResponseEntity<List<User>> response = restTemplateService.getAllUsers();
        List<User> users = response.getBody();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainPage");
        modelAndView.addObject("usersFromServer", users);
        return modelAndView;
    }

    @RequestMapping(path = "/admin/save", method = RequestMethod.POST)
    public String saveUsers(User user, String role) {
        restTemplateService.saveUser(user, role);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/delete/{*}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("*") Long userId) {
        restTemplateService.deleteById(userId);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/edit/{*}", method = RequestMethod.PUT)
    public String editPage(@PathVariable("*") long id, User user) { //@PathVariable указывает на то, что данный параметр (int id) получается из адресной строки
        restTemplateService.editUser(user, id);
        return "redirect:/admin";
    }
//
//    @RequestMapping(value = "/admin/edit/{*}", method = RequestMethod.POST)
//    public ModelAndView editUser(@PathVariable("*") long id, User user) {
//        User userFromDB = userService.findOneById(id);
//        userFromDB.setLogin(user.getLogin());
//        userFromDB.setName(user.getName());
//        userFromDB.setPassword(user.getPassword());
//        userRepository.save(userFromDB);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("redirect:/admin"); //означает, что после выполнения данного метода мы будем перенаправлены на адрес "/"
//        return modelAndView;
//    }
//
//    @RequestMapping(path = "/admin/save", method = RequestMethod.POST)
//    public String saveUsers(User user) {
//        user.setRoles(roleService.getRoleByName("USER"));
//        userService.addUser(user);
//        return "redirect:/admin";
//    }

}
