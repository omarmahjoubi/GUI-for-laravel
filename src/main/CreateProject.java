package main; /**
 * Created by Moslah_Hamza on 27/03/2017.
 */

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class CreateProject extends AbstractAction {
    private MainFrame frame;
    private String projectPath = "";

    public CreateProject(String text, MainFrame frame) {
        super(text);
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
        Process p = null;
        try {

            projectPath = Open.projectPath;
            System.out.println("projectPath = " + projectPath);
            if(projectPath!=""){
                ProcessBuilder builder = new ProcessBuilder(
                        "cmd.exe", "/c", "cd "+projectPath +" && composer create-project --prefer-dist laravel/laravel "+this.frame.getField().getText());
                builder.redirectErrorStream(true);
                try {
                    p = builder.start();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = "";
                while (true) {
                    try {
                        line = r.readLine();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    if (line == null) {
                        break;
                    }
                    System.out.println(line);
                }
                JOptionPane.showMessageDialog(frame, "le projet " + this.frame.getField().getText() + "été crée avec succés");
                frame.changePanel();
            }
            else {
                JOptionPane.showMessageDialog(frame, "Vous devez spécifier le chemin de votre projet");
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
