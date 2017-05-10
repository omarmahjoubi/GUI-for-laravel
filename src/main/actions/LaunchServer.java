package main.actions;

import main.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by omar_ on 10/05/2017.
 */
public class LaunchServer extends AbstractAction {

    private MainFrame frame;

    public LaunchServer(String text, MainFrame frame) {
        super(text);
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {

        if (Open.absolutePathProject == null) {
            JOptionPane.showMessageDialog(frame, "vous n'avez pas encore ouvert de projet laravel");
        } else {
            String command = "cmd.exe /c " + " cd " + Open.absolutePathProject + " & php artisan serve";
            Process p;
            Process p1 ;
            try {
                p = Runtime.getRuntime().exec(command);
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = "";
                line = reader.readLine();
                if (line.contains("server started")) {
                    String url = line.substring(line.indexOf("<"),line.indexOf(">")) ;
                    JOptionPane.showMessageDialog(frame,"le serveur a été lancé sur l'adresse : " + url );
                } else {
                    JOptionPane.showMessageDialog(frame,"une erreur s'es produite durant le lancement du serveur",
                            "ERROR",JOptionPane.ERROR_MESSAGE);
                }
                String command1 = "cmd.exe /c " + "tasklist /FI \"IMAGENAME eq php.exe\"" ;
                p1 = Runtime.getRuntime().exec(command1);
                p1.waitFor();
                BufferedReader reader1 = new BufferedReader(new InputStreamReader(p1.getInputStream()));
                String line1 = "";
                while ((line1 = reader.readLine())!=null) {
                    if (line1.contains("php.exe")) {
                        System.out.println("php line ===> " + line1);
                    }
                }



            } catch (IOException | InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }
}