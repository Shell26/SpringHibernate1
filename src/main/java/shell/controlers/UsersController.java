package shell.controlers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shell.model.Role;
import shell.model.User;
import shell.service.RestTemplateService;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private RestTemplateService restTemplateService;

    public UsersController(RestTemplateService restTemplateService){
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/login")
    public String getLoginPage (HttpServletRequest request) {

        return "login";
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUser(){
        return restTemplateService.getAllUsers();
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
