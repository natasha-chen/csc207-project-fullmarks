package app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //TODO: add TODOs
        AppBuilder appBuilder = new AppBuilder();
        JFrame application = appBuilder
                .addURLView()
                .build();
        application.pack();
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }
}
