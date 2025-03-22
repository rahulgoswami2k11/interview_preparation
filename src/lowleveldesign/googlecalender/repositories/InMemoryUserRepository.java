package lowleveldesign.googlecalender.repositories;

import lowleveldesign.googlecalender.model.User;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {

    Map<String, User> users;

    public InMemoryUserRepository() {
        users = new HashMap<>();
    }

    @Override
    public User createUser(User user) {
        users.put(user.getEmail(), user);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = users.getOrDefault(email, null);
        if(user == null) {
            throw new RuntimeException("User doesn't exist");
        }
        return user;
    }
}
