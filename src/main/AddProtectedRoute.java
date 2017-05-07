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
public class AddProtectedRoute extends AbstractAction  {
    private MainFrame frame;
    private String projectPath = "";
    List<String> lines = new ArrayList<String>();
    String line = null;
    String route = "";



    public AddProtectedRoute(String text , MainFrame frame) {
        super(text) ;
        this.frame=frame;

    }



    public void actionPerformed(ActionEvent e) {
        if (Open.absolutePathProject == null) {
            JOptionPane.showMessageDialog(frame, "vous n'avez pas encore ouvert de projet laravel");
        } else {
            String route = this.frame.getRoute().getText();
            String view = OpenView.viewName;
            String method = this.frame.getMethodList().getSelectedItem().toString() ;
            if (route.equals("")) {
                JOptionPane.showMessageDialog(frame, "Veuillez spécifier l'adresse du route à ajouter");
            } else if (view.equals("")) {
                JOptionPane.showMessageDialog(frame, "Veuillez spécifier la vue Html associé au route");
            } else {
                try {
                    String routeCode = "\nRoute::"+ method.toLowerCase() + "('/" + route + "', function() {\n" +
                            "  return view('" + view + "');\n" +
                            "});" ;
                    System.out.println(Open.absolutePathProject + "/routes/web.php");
                    BufferedReader br = new BufferedReader(new FileReader(Open.absolutePathProject + "/routes/web.php"));
                    int i=1;
                    String line ;
                    while (((line = br.readLine()) != null) && (line.indexOf("Route::group(['middleware' => 'auth'], function (){") == -1))
                        i++;
                    Path path = Paths.get(Open.absolutePathProject + "/routes/web.php");
                    List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
                    lines.add(i,routeCode);
                    Files.write(path, lines, StandardCharsets.UTF_8);


                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }


    }



}
}
