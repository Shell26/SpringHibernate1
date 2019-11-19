package shell.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {


    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public SignUpServiceImpl (UserService userService, RoleService roleService){
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void signUp(shell.model.User user, String role) {
        userService.save(user, role);
    }
}
