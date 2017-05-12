package main.actions;

import main.MainFrame;
import main.panels.AddRoutePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by Moslah_Hamza on 03/04/2017.
 */
public class AddRoute extends AbstractAction {
    private MainFrame frame;
    private AddRoutePanel panel;
    private String projectPath = "";
    List<String> lines = new ArrayList<String>();
    String line = null;
    String route = "";

    public AddRoute(String s,AddRoutePanel panel, MainFrame mainFrame) {
        super(s);
        this.frame = mainFrame;
        this.panel=panel ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (Open.absolutePathProject == null) {
            JOptionPane.showMessageDialog(frame, "vous n'avez pas encore ouvert de projet laravel");
        } else {
            String route = this.panel.getRoute().getText();
            String view = OpenView.viewName;
            String method = this.panel.getMethodList().getSelectedItem().toString() ;
            if (route.equals("")) {
                JOptionPane.showMessageDialog(frame, "Veuillez spécifier l'adresse du route à ajouter");
            } else if (view.equals("")) {
                JOptionPane.showMessageDialog(frame, "Veuillez spécifier la vue Html associé au route");
            } else {
                try {
                    FileWriter fw = new FileWriter(Open.absolutePathProject + "/routes/web.php", true);
                    Writer writer = new BufferedWriter(fw);

                    writer.write("\nRoute::"+ method.toLowerCase() + "('/" + route + "', function() {\n" +
                            "  return view('" + view + "');\n" +
                            "});");
                    writer.close();
                    OpenView.viewName="" ;
                    this.panel.getVueName().setText("aucune vue séléctionné");
                    JOptionPane.showMessageDialog(frame, "votre route a été ajouté avec succés");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        }
    }
}



