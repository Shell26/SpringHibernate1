package shell.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import shell.model.User;
import shell.repositories.UserRepository;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> userCandidate = Optional.ofNullable(repository.findOneByLogin(login));
        if (userCandidate.isPresent()) {
            return userCandidate.get();
        } else throw new IllegalArgumentException("User not found");
    }

//    @Autowired
//    private UserServiceImpl userService;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        return userService.getByLogin(login);
//    }
}
