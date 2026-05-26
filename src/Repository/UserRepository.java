package Repository;

import java.util.HashMap;
import java.util.Map;

import domain.User;

public class UserRepository {
    public Map<String, User> users = new HashMap<>();
    private static UserRepository instance = null;

    private UserRepository() {

    }

    public static UserRepository getInstance(){
        if(instance == null){
            instance = new UserRepository();;
        }
        return instance;
    }

    public void save(User user) {
        users.put(user.email, user);
    }

    public User findByEmail(String email) {
        return users.getOrDefault(email,null);
    }
}
