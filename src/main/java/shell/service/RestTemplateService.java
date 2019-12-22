package shell.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import shell.model.Role;
import shell.model.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class RestTemplateService {

    private static RestTemplateService restTemplateService;

    RestTemplate restTemplate = new RestTemplate();

    private  RestTemplateService(){}

    public static RestTemplateService getInstance(){
        if(restTemplateService == null){
            restTemplateService = new RestTemplateService();
        }
        return restTemplateService;
    }

    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(Arrays.asList(restTemplate.getForObject("http://localhost:8080/api/users", User[].class)),HttpStatus.OK);
    }

    public ResponseEntity saveUser(User user, String role){
//        HttpHeaders header = new HttpHeaders();
//        header.setContentType(MediaType.APPLICATION_JSON);
        if(role.equals("ADMIN")){
            user.setId(1L);
        }else {
            user.setId(2L);
        }

        restTemplate.postForEntity("http://localhost:8080/api/admin/save/", user, User.class);
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity deleteById(Long id) {
        restTemplate.delete("http://localhost:8080/api/admin/delete/" + id);
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity<User> editUser(User newUser, Long id) {
        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<User> entity = new HttpEntity<>(newUser, headers);
        headers.setContentType(MediaType.APPLICATION_JSON);

        restTemplate.put("http://localhost:8080/api/admin/edit/" + id, newUser, headers, id);
        return new ResponseEntity<>(newUser, HttpStatus.OK);

    }
}
