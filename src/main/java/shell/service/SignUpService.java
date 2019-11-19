package shell.service;


import shell.model.User;

public interface SignUpService {
    void signUp(User user, String role);
}
