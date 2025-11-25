package use_case.user;

import entity.User;

public interface UserDataAccessInterface {
    boolean existsByName(String username);

    User getUser(String username);

    void saveUser(User user);
}
