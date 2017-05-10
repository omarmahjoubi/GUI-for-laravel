package main.actions;

import main.MainFrame;
import main.guielement.Worker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by omar_ on 10/05/2017.
 */
public class LaunchServer extends AbstractAction {

    private MainFrame frame;
    public static ArrayList<String> pidList = new ArrayList<>() ;

    public LaunchServer(String text, MainFrame frame) {
        super(text);
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {

        if (Open.absolutePathProject == null) {
            JOptionPane.showMessageDialog(frame, "vous n'avez pas encore ouvert de projet laravel");
        } else {

            Process p1;
            try {
                Worker worker = new Worker(this.frame);
                worker.execute();


                String command1 = "cmd.exe /c " + "tasklist /FO CSV /FI \"IMAGENAME eq php.exe\""; //récupérer du pid(s) de
                p1 = Runtime.getRuntime().exec(command1);                                      //thread du serveur
                p1.waitFor();
                BufferedReader reader1 = new BufferedReader(new InputStreamReader(p1.getInputStream()));
                String line1 = "";
                while ((line1 = reader1.readLine()) != null) {
                    if (line1.contains("php.exe")) {
                        System.out.println("php line ===> " + line1);
                        String[] list = line1.split(",") ;
                         pidList.add(list[1].substring(1,list[1].length()-1)) ;
                    }
                }
                for (String pid : pidList) {
                    System.out.println("pid ==> " + pid) ;
                }


            } catch (IOException | InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }
}