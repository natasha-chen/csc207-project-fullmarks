<<<<<<< HEAD:src/main/java/entity/PathManager.java
package entity;
=======
package data_access;
import javax.swing.*;
import java.io.File;

>>>>>>> 262bd59af1dd338256666eed4270e9af69c859b6:src/main/java/data_access/PathManager.java

public class PathManager {

    public static String getOutputFolder() {
        String outputFolder;

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select a Folder");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = chooser.getSelectedFile();
            System.out.println("Selected folder: " + selectedFolder.getAbsolutePath());
            outputFolder = selectedFolder.getAbsolutePath() + File.separator;
        }
        else {
            outputFolder = chooser.getCurrentDirectory().getAbsolutePath()  + File.separator;
        }

        return outputFolder ;
    }
}
