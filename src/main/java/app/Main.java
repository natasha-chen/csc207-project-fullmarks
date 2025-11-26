package app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        AppBuilder builder = new AppBuilder();
        JPanel mainPanel = builder.build();

        JFrame frame = new JFrame("Youtube to MP3/4");
        frame.setContentPane(mainPanel);
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        builder.viewManagerModel.setActiveView("signup_login_menu");
        builder.viewManagerModel.firePropertyChanged();
    }
}
