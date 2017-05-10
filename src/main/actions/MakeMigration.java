package main.actions;

import main.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by omar_ on 18/04/2017.
 */
public class MakeMigration extends AbstractAction {

    private MainFrame frame ;

    public MakeMigration(String text, MainFrame frame) {
        super(text);
        this.frame=frame ;
    }


    public void actionPerformed(ActionEvent e) {
        if (Open.absolutePathProject!=null) {
            Process p ;
            String command4 = "cmd.exe /c " + "cd " + Open.absolutePathProject + " & php artisan migrate";
            try {
                String line = ""  ;
                p = Runtime.getRuntime().exec(command4);
                p.waitFor();
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                line = reader.readLine() ;
                if (line.equals("")) {
                    JOptionPane.showMessageDialog(frame, "une erreurs s'est produite! \nPeut etre que le serveur de la base de données n'a pas été lancé","ERROR",JOptionPane.ERROR_MESSAGE);
                } else if (line.contains("Nothing")) {
                    JOptionPane.showMessageDialog(frame, "vous n'avez aucun fichier de migration à migere","WARNING",JOptionPane.WARNING_MESSAGE);
                } else if (line.contains("Migrating")) {
                    JOptionPane.showMessageDialog(frame, "les migrations ont été effectué avec succés");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "vous n'avez pas encore ouvert de projet laravel");
        }

    }

}
