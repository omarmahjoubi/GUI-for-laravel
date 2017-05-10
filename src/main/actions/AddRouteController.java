package main.actions;

import main.MainFrame;
import main.panels.RouteControllerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

/**
 * Created by omar_ on 22/04/2017.
 */
public class AddRouteController extends AbstractAction {

    private RouteControllerPanel panel;
    private MainFrame frame;

    public AddRouteController(String text, RouteControllerPanel panel, MainFrame frame) {
        super(text);
        this.panel = panel;
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
    if (Open.absolutePathProject == null) {
        JOptionPane.showMessageDialog(frame, "vous n'avez pas encore ouvert de projet laravel");
    } else {
        String route = this.panel.getRoute().getText() ;
        String method = this.panel.getMethodList().getSelectedItem().toString() ;
        String contoller = this.panel.getControllerList().getSelectedItem().toString() ;
        String methodController = this.panel.getMethod().getText() ;
        if (route.equals("")) {
            JOptionPane.showMessageDialog(frame, "Veuillez spécifier l'adresse du route à ajouter");
        } else if (methodController.equals("")) {
            JOptionPane.showMessageDialog(frame, "Veuillez spécifier la méthode du controlleur à appeler");
        } else {
            try {
                FileWriter fw = new FileWriter(Open.absolutePathProject + "/routes/web.php", true);
                Writer writer = new BufferedWriter(fw);
                writer.write("\nRoute::"+ method.toLowerCase() + "('/" + route + "','"+contoller+"@"+methodController+"');") ;

                writer.close();
                JOptionPane.showMessageDialog(frame, "votre route a été ajouté avec succés");
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
    }
}



        }
