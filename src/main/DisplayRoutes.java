package main;

/**
 * Created by Moslah_Hamza on 27/03/2017.
 */

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import main.guielement.DynamicModel;
import main.guielement.RoutesTable;

public class DisplayRoutes extends AbstractAction {

    private MainFrame frame;

    public DisplayRoutes(String text, MainFrame frame) {
        super(text);
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {

        Process p ;
        ArrayList<Route> routes = new ArrayList<Route>();
        if (Open.absolutePathProject != null) {
            String command4 = "cmd.exe /c " + "cd " + Open.absolutePathProject + " & php artisan route:list";
            try {
                p = Runtime.getRuntime().exec(command4);
                p.waitFor();
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = "";
                String line1 = null;
                line = reader.readLine();
                line = reader.readLine();
                line = reader.readLine();

                while ((line = reader.readLine()) != null) {
                    if (line.charAt(0) == '+') {
                        break;
                    }

                    String[] list = line.split("\\|");
                    for (String s : list) {
                        System.out.println("memebre de list : " + s);
                    }
                    System.out.println("*************");
                    String method = list[list.length - 6] + "," + list[list.length - 5];
                    String uri = list[list.length - 4];
                    String action = list[list.length - 2];
                    String middleware = list[list.length - 1];

                    Route route = new Route(uri, method, action, middleware);
                    routes.add(route);
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }
            RoutesTable rt = new RoutesTable();
            DynamicModel dm = (DynamicModel) rt.getTableau().getModel();
            for (Route r : routes) {
                dm.addRoute(r);
            }
            rt.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(frame, "vous n'avez pas encore ouvert de projet laravel");
        }
    }

}
