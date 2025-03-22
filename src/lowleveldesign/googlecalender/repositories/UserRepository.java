package lowleveldesign.googlecalender.repositories;

import lowleveldesign.googlecalender.model.User;

public interface UserRepository {
    User createUser(User user);

    User getUserByEmail(String email);
}
