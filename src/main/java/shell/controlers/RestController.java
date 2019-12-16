package shell.controlers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import shell.model.User;
import shell.repositories.UserRepository;
import shell.service.RoleService;
import shell.service.UserService;

import javax.validation.Valid;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    public RestController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<User> getAllUsers(){

    public ResponseEntity<List<User>> getAllUser(){
        List list = userRepository.findAll();
        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @RequestMapping(value = "/admin/save/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> saveUser(User user, String role){ //  @RequestBody - чтобы ввести тело HTTP-запроса в метод.
        if(user == null){                                                      // @ResponseBody - чтобы вернуть содержимое или объект в качестве тела HTTP-ответа.
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (role.equals("USER")) {
            user.setRoles(roleService.getRoleByName("USER"));
        }else{
            user.setRoles(roleService.getRoleByName("ADMIN"));
        }
        userRepository.save(user);
//        return new ResponseEntity<>(user, HttpStatus.OK);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long userId){
        if(userId == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = userRepository.getOne(userId);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        userRepository.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
        //////
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8081/api/admin/delete/" + userId;
//        restTemplate.getForObject(url, User.class);
//        return new ResponseEntity<>(HttpStatus.OK);

    }

//    @RequestMapping(value = "/admin/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<User> updateUser(@RequestBody User user, UriComponentsBuilder builder){
//        if(user == null){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        userRepository.save(user);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

    @PostMapping(path = "/admin/edit/{id}")
    public ResponseEntity editUser(User newUser, @PathVariable("id") Long userId) {
        User user = userRepository.getOne(userId);
        user.setName(newUser.getName());
        user.setLogin(newUser.getLogin());
        user.setPassword(newUser.getPassword());
        userRepository.save(user);
//        return new ResponseEntity<>(user, HttpStatus.OK);
        return ResponseEntity.ok().build();
    }


//    //ResponseEntity<T> используется, чтобы вернуть HTTP-ответ с пользовательским статусом или заголовками.
//    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<User>> getAllUser(){
//        List list = userRepository.findAll();
//        if(list.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId){
//        if(userId == null){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        User user = userRepository.getOne(userId);
//        if(user == null){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<User> saveUser(@RequestBody User user){ //  @RequestBody - чтобы ввести тело HTTP-запроса в метод.
//        if(user == null){                                                      // @ResponseBody - чтобы вернуть содержимое или объект в качестве тела HTTP-ответа.
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        userRepository.save(user);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<User> updateUser(@RequestBody User user, UriComponentsBuilder builder){
//        if(user == null){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        userRepository.save(user);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<User> deleteUser(@PathVariable("id") Long userId){
//        if(userId == null){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        User user = userRepository.getOne(userId);
//        if(user == null){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        userRepository.deleteById(userId);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
