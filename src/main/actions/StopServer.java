package main.actions;

import main.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by omar_ on 11/05/2017.
 */
public class StopServer extends AbstractAction {

    private MainFrame frame;


    public StopServer(String text, MainFrame frame) {
        super(text);
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
        if (Open.absolutePathProject == null) {
            JOptionPane.showMessageDialog(frame, "vous n'avez pas encore ouvert de projet laravel");
        } else {
                Process p1 ;
                try {

                String command2 ="cmd.exe /c " + "taskkill /F /IM php.exe" ;
                p1 = Runtime.getRuntime().exec(command2);
                p1.waitFor();
                BufferedReader reader1 = new BufferedReader(new InputStreamReader(p1.getInputStream()));
                String line2 = "";
                while ((line2 = reader1.readLine()) != null) {
                    System.out.println("line2 ===> " + line2);
                }

                JOptionPane.showMessageDialog(this.frame,"le serveur a été arrété avec succés");
                LaunchServer.launched=false ;
                this.frame.setContentPane(this.frame.buildSecondPane());
                this.frame.validate();


            } catch (IOException | InterruptedException e1) {
                e1.printStackTrace();
            }
        }


    }
}


