package use_case.login;

import entity.User;
import org.junit.jupiter.api.Test;
import use_case.user.UserDataAccessInterface;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for LoginInteractor.
 *
 * Verifies success path, username-not-found path,
 * and incorrect password path.
 */
class LoginInteractorTest {

    /**
     * Fake DAO for testing.
     * Lets us control the returned user.
     */
    private static class FakeUserDAO implements UserDataAccessInterface {

        public User storedUser = null;
        public String lastRequestedUsername = null;

        @Override
        public boolean existsByName(String username) {
            return storedUser != null && storedUser.getUsername().equals(username);
        }

        @Override
        public User getUser(String username) {
            lastRequestedUsername = username;
            if (storedUser != null && storedUser.getUsername().equals(username)) {
                return storedUser;
            }
            return null;
        }

        @Override
        public void saveUser(User user) {
            storedUser = user;
        }
    }


    /**
     * Fake presenter to capture outputs.
     */
    private static class FakePresenter implements LoginOutputBoundary {
        boolean successCalled = false;
        boolean failCalled = false;
        LoginOutputData successData = null;
        String failMessage = null;
        String failUsername = null;

        @Override
        public void prepareSuccessView(LoginOutputData outputData) {
            successCalled = true;
            successData = outputData;
        }

        @Override
        public void prepareFailView(String error, String username) {
            failCalled = true;
            failMessage = error;
            failUsername = username;
        }
    }

    /**
     * SUCCESSFUL LOGIN TEST
     */
    @Test
    void testSuccessfulLogin() {
        FakeUserDAO dao = new FakeUserDAO();
        FakePresenter presenter = new FakePresenter();

        // Stored user has hashed password.
        String hashedPassword = entity.PasswordHasher.hash("mypassword");
        dao.storedUser = new User("jason", hashedPassword);

        LoginInteractor interactor = new LoginInteractor(dao, presenter);

        LoginInputData input = new LoginInputData("jason", "mypassword");
        interactor.execute(input);

        assertTrue(presenter.successCalled);
        assertFalse(presenter.failCalled);
        assertEquals("jason", presenter.successData.getUsername());
        assertEquals("jason", dao.lastRequestedUsername);
    }

    /**
     * USERNAME NOT FOUND TEST
     */
    @Test
    void testUserNotFound() {
        FakeUserDAO dao = new FakeUserDAO();
        FakePresenter presenter = new FakePresenter();

        LoginInteractor interactor = new LoginInteractor(dao, presenter);

        LoginInputData input = new LoginInputData("unknown", "irrelevant");
        interactor.execute(input);

        assertTrue(presenter.failCalled);
        assertFalse(presenter.successCalled);
        assertEquals("Username not found.", presenter.failMessage);
        assertEquals("unknown", presenter.failUsername);
    }

    /**
     * INCORRECT PASSWORD TEST
     */
    @Test
    void testIncorrectPassword() {
        FakeUserDAO dao = new FakeUserDAO();
        FakePresenter presenter = new FakePresenter();

        // Store user with correct hashed password
        dao.storedUser = new User("jason", entity.PasswordHasher.hash("correct"));

        LoginInteractor interactor = new LoginInteractor(dao, presenter);

        LoginInputData input = new LoginInputData("jason", "wrongpassword");
        interactor.execute(input);

        assertTrue(presenter.failCalled);
        assertFalse(presenter.successCalled);
        assertEquals("Incorrect password.", presenter.failMessage);
        assertEquals("jason", presenter.failUsername);
    }
}
