package use_case.user;

import entity.User;

/**
 * Data access interface for user entities.
 *
 * <p>This interface defines the operations required by the application layer
 * to query and persist user information. It abstracts away the underlying
 * storage mechanism so that the use cases do not depend on database or file
 * system details.
 */
public interface UserDataAccessInterface {
    boolean existsByName(String username);

    User getUser(String username);

    void saveUser(User user);
}
