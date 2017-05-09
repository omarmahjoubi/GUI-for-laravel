package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by omar_ on 27/04/2017.
 */
public class AddProtectedRoute extends AbstractAction {
    private MainFrame frame;
    private JPanel panel;
    private String type;
    private String projectPath = "";
    List<String> lines = new ArrayList<String>();
    String line = null;
    String route = "";


    public AddProtectedRoute(String text, JPanel panel, MainFrame fram, String type) {
        super(text);
        this.frame = frame;
        this.panel = panel;
        this.type = type;

    }


    public void actionPerformed(ActionEvent e) {
        if (Open.absolutePathProject == null) {
            JOptionPane.showMessageDialog(frame, "vous n'avez pas encore ouvert de projet laravel");
        } else {
            String routeCode;
            if (this.type.equals("simple")) {
                AddRoutePanel routePanel = (AddRoutePanel) this.panel;
                String route = routePanel.getRoute().getText();
                String view = OpenView.viewName;
                String method = routePanel.getMethodList().getSelectedItem().toString();
                if (route.equals("")) {
                    JOptionPane.showMessageDialog(frame, "Veuillez spécifier l'adresse du route à ajouter");
                } else if (view.equals("")) {
                    JOptionPane.showMessageDialog(frame, "Veuillez spécifier la vue Html associé au route");
                } else {
                    try {
                        routeCode = "\nRoute::" + method.toLowerCase() + "('/" + route + "', function() {\n" +
                                "  return view('" + view + "');\n" +
                                "});";
                        System.out.println(Open.absolutePathProject + "/routes/web.php");
                        BufferedReader br = new BufferedReader(new FileReader(Open.absolutePathProject + "/routes/web.php"));
                        int i = 1;
                        String line;
                        while (((line = br.readLine()) != null) && (line.indexOf("Route::group(['middleware' => 'auth'], function (){") == -1))
                            i++;
                        Path path = Paths.get(Open.absolutePathProject + "/routes/web.php");
                        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
                        lines.add(i, routeCode);
                        Files.write(path, lines, StandardCharsets.UTF_8);
                        OpenView.viewName = "";
                        routePanel.getVueName().setText("aucune vue séléctionné");
                        JOptionPane.showMessageDialog(frame, "votre route protégé a été ajouté avec succés");


                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            } else {

                RouteControllerPanel routeControllerPanel = (RouteControllerPanel) this.panel;
                String route = routeControllerPanel.getRoute().getText();
                String method = routeControllerPanel.getMethodList().getSelectedItem().toString();
                String contoller = routeControllerPanel.getControllerList().getSelectedItem().toString();
                String methodController = routeControllerPanel.getMethod().getText();
                if (route.equals("")) {
                    JOptionPane.showMessageDialog(frame, "Veuillez spécifier l'adresse du route à ajouter");
                } else if (methodController.equals("")) {
                    JOptionPane.showMessageDialog(frame, "Veuillez spécifier la méthode du controlleur à appeler");
                } else {
                    try {
                        routeCode = "\nRoute::" + method.toLowerCase() + "('/" + route + "','" + contoller + "@" + methodController + "');";
                        BufferedReader br = new BufferedReader(new FileReader(Open.absolutePathProject + "/routes/web.php"));
                        int i = 1;
                        String line;
                        while (((line = br.readLine()) != null) && (line.indexOf("Route::group(['middleware' => 'auth'], function (){") == -1))
                            i++;
                        Path path = Paths.get(Open.absolutePathProject + "/routes/web.php");
                        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
                        lines.add(i, routeCode);
                        Files.write(path, lines, StandardCharsets.UTF_8);
                        JOptionPane.showMessageDialog(frame, "votre route protégé a été ajouté avec succés");

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }


                }


            }
        }
    }
}