package data_access;
import javax.swing.*;
import java.io.File;


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
