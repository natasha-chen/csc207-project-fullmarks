package use_case.signup;

import entity.User;
import entity.PasswordHasher;
import org.junit.jupiter.api.Test;
import use_case.user.UserDataAccessInterface;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for SignupInteractor.
 *
 * Covers:
 * - password mismatch
 * - username already exists
 * - successful signup
 */
class SignupInteractorTest {

    /**
     * Fake data access object for testing.
     */
    private static class FakeUserDAO implements UserDataAccessInterface {

        public User storedUser = null;
        public String lastSavedUsername = null;

        @Override
        public boolean existsByName(String username) {
            return storedUser != null && storedUser.getUsername().equals(username);
        }

        @Override
        public User getUser(String username) {
            if (storedUser != null && storedUser.getUsername().equals(username)) {
                return storedUser;
            }
            return null;
        }

        @Override
        public void saveUser(User user) {
            storedUser = user;
            lastSavedUsername = user.getUsername();
        }
    }

    /**
     * Fake presenter to capture outputs.
     */
    private static class FakePresenter implements SignupOutputBoundary {
        boolean successCalled = false;
        boolean failCalled = false;
        String failMessage = null;
        String failUsername = null;
        SignupOutputData successData = null;

        @Override
        public void prepareSuccessView(SignupOutputData data) {
            successCalled = true;
            successData = data;
        }

        @Override
        public void prepareFailView(String error, String username) {
            failCalled = true;
            failMessage = error;
            failUsername = username;
        }
    }

    // 1. PASSWORD MISMATCH
    @Test
    void testPasswordMismatch() {
        FakeUserDAO dao = new FakeUserDAO();
        FakePresenter presenter = new FakePresenter();
        SignupInteractor interactor = new SignupInteractor(dao, presenter);

        SignupInputData input =
                new SignupInputData("jason", "pass1", "pass2");

        interactor.execute(input);

        assertTrue(presenter.failCalled);
        assertFalse(presenter.successCalled);
        assertEquals("Passwords do not match.", presenter.failMessage);
        assertEquals("jason", presenter.failUsername);
    }

    // 2. USERNAME ALREADY EXISTS
    @Test
    void testUsernameAlreadyExists() {
        FakeUserDAO dao = new FakeUserDAO();
        FakePresenter presenter = new FakePresenter();

        // Fake an existing user
        dao.storedUser = new User("jason", PasswordHasher.hash("whatever"));

        SignupInteractor interactor = new SignupInteractor(dao, presenter);

        SignupInputData input =
                new SignupInputData("jason", "pass", "pass");

        interactor.execute(input);

        assertTrue(presenter.failCalled);
        assertFalse(presenter.successCalled);
        assertEquals("Username already exists.", presenter.failMessage);
        assertEquals("jason", presenter.failUsername);
    }

    // 3. SUCCESSFUL SIGNUP
    @Test
    void testSuccessfulSignup() {
        FakeUserDAO dao = new FakeUserDAO();
        FakePresenter presenter = new FakePresenter();
        SignupInteractor interactor = new SignupInteractor(dao, presenter);

        SignupInputData input =
                new SignupInputData("jason", "mypassword", "mypassword");

        interactor.execute(input);

        assertFalse(presenter.failCalled);
        assertTrue(presenter.successCalled);
        assertEquals("jason", presenter.successData.getUsername());

        // Verify user was saved with hashed password
        assertNotNull(dao.storedUser);
        assertEquals("jason", dao.lastSavedUsername);

        String expectedHash = PasswordHasher.hash("mypassword");
        assertEquals(expectedHash, dao.storedUser.getPassword());
    }
}
