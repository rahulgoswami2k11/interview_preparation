package lowleveldesign.googlecalender.services;

import lowleveldesign.googlecalender.model.User;
import lowleveldesign.googlecalender.repositories.UserRepository;

public class DefaultUserService implements UserService {

    private UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String email) {
        User user = User.builder()
                .email(email)
                .build();
        return userRepository.createUser(user);
    }
}
