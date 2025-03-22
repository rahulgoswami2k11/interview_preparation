package lowleveldesign.googlecalender.services;

import lowleveldesign.googlecalender.model.User;

public interface UserService {
    User createUser(String email);
}
