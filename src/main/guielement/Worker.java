package main.guielement;

import main.MainFrame;
import main.actions.LaunchServer;
import main.actions.Open;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by omar_ on 10/05/2017.
 */
public class Worker extends SwingWorker<Void, String> {
    private String msg = "";
    private MainFrame frame ;

    public Worker(MainFrame frame ) {
        super() ;
        this.frame =frame ;
    }

    @Override
    protected Void doInBackground() throws Exception {
        Process p;
        String command = "cmd.exe /c " + " cd " + Open.absolutePathProject + " & php artisan serve";
        try {
            p = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            line = reader.readLine();
            if (line.contains("server started")) {
                msg = line.substring(line.indexOf("<") + 1, line.indexOf(">"));
                LaunchServer.launched = true ;
                this.frame.setContentPane(this.frame.buildSecondPane());
                this.frame.validate();
            } else {
                msg = "une erreur s'es produite durant le lancement du serveur";

            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null ;
    }

    protected void done() {

        if (msg.contains("une")) {
            JOptionPane.showMessageDialog(frame, "une erreur s'es produite durant le lancement du serveur",
                    "ERROR", JOptionPane.ERROR_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(frame, "le serveur a été lancé sur l'adresse : " + msg);

        }
    }

    @Override
    protected void process(List<String> chunks) {
        //this is executed in EDT you can update a label for example

    }

    public String getMsg() {
        return msg;
    }
    //add setters for label and businessDelegate
}

