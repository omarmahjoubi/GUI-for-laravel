package main;

/**
 * Created by Moslah_Hamza on 27/03/2017.
 */
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

public class Open extends AbstractAction {

    private MainFrame frame;
    public static String absolutePathProject = null;
    public static String projectPath = "";

    public Open(String text, MainFrame frame) {
        super(text);
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setApproveButtonText("Selectionne le ficher");
        fileChooser.showOpenDialog(this.frame);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);

        if (fileChooser.showOpenDialog(this.frame) == JFileChooser.APPROVE_OPTION) {
            Process p;
            Open.absolutePathProject = fileChooser.getSelectedFile().getAbsolutePath();
            projectPath = String.valueOf(fileChooser.getCurrentDirectory());
            System.out.println("getSelectedDirectory(): " + fileChooser.getCurrentDirectory());
            System.out.println("getSelectedFile(): " + fileChooser.getSelectedFile());
            String command4 = "cmd.exe /c " + "cd " + Open.absolutePathProject;
            try {
                p = Runtime.getRuntime().exec(command4);
                p.waitFor();
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

                String line = "";
                while ((line = reader.readLine()) != null) {
                    System.out.println(line + "\n");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else {
            System.out.println("No Selection ");
        }
    }
}
