package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by omar_ on 08/05/2017.
 */
public class Add extends AbstractAction {

    private MainFrame frame;
    private AddPanel panel;

    public Add(String text, AddPanel panel, MainFrame frame) {
        super(text);
        this.panel = panel;
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
        String type = this.panel.getNameLabel().getText();
        if (Open.absolutePathProject == null) {
            JOptionPane.showMessageDialog(frame, "vous n'avez pas encore ouvert de projet laravel");
        } else {
            String name = this.panel.getName1().getText();
            String dir = this.panel.getnameDir().getText();
            if (name.equals("")) {
                if (type.contains("cont")) {
                    JOptionPane.showMessageDialog(frame, "Veuillez spécifier le nom du controleur");
                } else if (type.contains("mod")) {
                    JOptionPane.showMessageDialog(frame, "Veuillez spécifier le nom du modéle");
                } else {
                    JOptionPane.showMessageDialog(frame, "Veuillez spécifier le nom du middleware");
                }

            } else {
                Process p;
                String command = "cmd.exe /c " + "cd " + Open.absolutePathProject;
                try {
                    if (type.contains("cont")) {
                        if (dir.equals("")) {
                            command = command + " & php artisan make:controller " + name;
                        } else {
                            command = command + " & php artisan make:controller " + dir + "/" + name;
                        }
                    } else if (type.contains("mod")) {
                        if (dir.equals("")) {
                            command = command + " & php artisan make:model " + name;
                        } else {
                            command = command + " & php artisan make:model " + dir + "/" + name;
                        }
                    } else {
                        if (dir.equals("")) {
                            command = command + " & php artisan make:middleware " + name;
                        } else {
                            command = command + " & php artisan make:middleware " + dir + "/" + name;
                        }
                    }
                    p = Runtime.getRuntime().exec(command);
                    p.waitFor();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line = "";
                    line = reader.readLine();
                    String message;
                    if (line.contains("successfully")) {
                        if (type.contains("cont")) {
                            message = "le controleur a été crée avec succés";
                        } else if (type.contains("mod")) {
                            message = "le modéle a été crée avec succés";
                        } else {
                            message = "le middleware a été crée avec succés";
                        }
                        JOptionPane.showMessageDialog(frame, message);
                    } else {
                        if (type.contains("cont")) {
                            message = "une erreur s'est produite durant la génération du controleur";
                        } else if (type.contains("mod")) {
                            message = "une erreur s'est produite durant la génération du modéle";
                        } else {
                            message = "une erreur s'est produite durant la génération du middleware";
                        }
                        JOptionPane.showMessageDialog(frame, message, "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (InterruptedException | IOException e1) {
                    e1.printStackTrace();
                }


            }

        }

    }

}