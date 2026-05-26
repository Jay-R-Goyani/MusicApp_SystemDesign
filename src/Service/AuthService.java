package Service;

import Repository.UserRepository;
import domain.User;

public class AuthService {
    private static UserRepository userRepository;

    public AuthService() {
        this.userRepository = UserRepository.getInstance();
    }

    public void register(String name, String email, String password) {
        if(userRepository.findByEmail(email)!=null){
            throw new RuntimeException("User already exists");
        }
        User user = new User(name, email, password);
        userRepository.save(user);
    }

    public User login(String email, String password){
        User user = userRepository.findByEmail(email);
        if(user!=null && user.password.equals(password)){
            return user;
        }
        return null;
    }

}
