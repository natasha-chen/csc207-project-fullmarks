package interface_adapter.user;

import entity.User;
import use_case.user.UserDataAccessInterface;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileUserDataAccessObject implements UserDataAccessInterface {

    private final File file;
    private final Map<String, User> users = new HashMap<>();

    public FileUserDataAccessObject(String filepath) {
        this.file = new File(filepath);

        // Create file if missing
        try {
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Failed to create user file.", e);
        }

        loadUsers();
    }

    private void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length != 2) continue;

                String username = parts[0];
                String password = parts[1];

                users.put(username, new User(username, password));
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to read user file.", e);
        }
    }

    private void saveUsers() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            for (User user : users.values()) {
                pw.println(user.getUsername() + "," + user.getPassword());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to write user file.", e);
        }
    }

    @Override
    public boolean existsByName(String username) {
        return users.containsKey(username);
    }

    @Override
    public User getUser(String username) {
        return users.get(username);
    }

    @Override
    public void saveUser(User user) {
        users.put(user.getUsername(), user);
        saveUsers();
    }
}
