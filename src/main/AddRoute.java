package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;

/**
 * Created by Moslah_Hamza on 03/04/2017.
 */
public class AddRoute extends AbstractAction {
    private MainFrame frame;
    private String projectPath = "";
    List<String> lines = new ArrayList<String>();
    String line = null;
    String route = "";

    public AddRoute(String s, MainFrame mainFrame) {
        super(s);
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        route = JOptionPane.showInputDialog(frame, "veuillez entrer la route à ajouter", "Ajout d'une route", JOptionPane.PLAIN_MESSAGE);
        doIt();
    }

    public void doIt() {
        try {
            System.out.println(Open.absolutePathProject + "\\routes\\web.php");
            File f1 = new File(Open.absolutePathProject + "\\routes\\web.php");
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                if (line.contains("java"))
                    line = line.replace("java", " ");
                lines.add(line);
            }
            lines.add(route);

            fr.close();
            br.close();

            FileWriter fw = new FileWriter(f1);
            BufferedWriter out = new BufferedWriter(fw);
            for (String s : lines)
                out.write(s);
            out.flush();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

