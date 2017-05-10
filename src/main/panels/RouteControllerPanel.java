package main.panels;

import main.actions.AddProtectedRoute;
import main.actions.AddRouteController;
import main.MainFrame;
import main.actions.Open;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by omar_ on 22/04/2017.
 */
public class RouteControllerPanel extends JPanel {
    private MainFrame frame;
    private JTextField route;
    private JComboBox methodList;
    private JComboBox controllerList;
    private JTextField method ;


    public RouteControllerPanel(MainFrame frame) {

        super();

        this.frame = frame;
        setLayout(new GridLayout(5, 2));
        setBackground(Color.white);

        JPanel[][] emptyPanels = new JPanel[5][2];
        for (int m = 0; m < 5; m++) {
            for (int l = 0; l < 2; l++) {

                emptyPanels[m][l] = new JPanel();
                add(emptyPanels[m][l]);
            }
        }

        JLabel label = new JLabel("Adresse du route");
        emptyPanels[0][0].add(label);

        route = new JTextField();
        route.setColumns(20);
        emptyPanels[0][1].add(route);

        JLabel label1 = new JLabel("methode du route");
        emptyPanels[1][0].add(label1);

        String[] methods = {"GET", "POST", "PUT", "DELETE"};
        methodList = new JComboBox(methods);
        methodList.setPreferredSize(new Dimension(200, 20));
        methodList.setSelectedItem(0);
        emptyPanels[1][1].add(methodList);

        JLabel label2 = new JLabel("Controleur");
        emptyPanels[2][0].add(label2);

        ArrayList<String> list = new ArrayList<>() ;
        if (Open.absolutePathProject!= null) {
            String rootPath = Open.absolutePathProject + "\\app\\Http\\Controllers" ;
            ArrayList<File> files = new ArrayList<>();
            files = listf(rootPath,files) ;
            for (File f : files) {
                if (f.getName().contains(".php")) {
                    String[] list1 = f.getAbsolutePath().split("Controllers");
                    list.add(list1[1].substring(1,list1[1].length()-4)) ;

                }
            }
        }
        controllerList = new JComboBox(list.toArray());
        controllerList.setPreferredSize(new Dimension(200, 20));
        controllerList.setSelectedItem(0);
        emptyPanels[2][1].add(controllerList);

        JLabel label3 = new JLabel("methode du controleur");
        emptyPanels[3][0].add(label3);

        method = new JTextField();
        method.setColumns(20);
        emptyPanels[3][1].add(method);

        JButton confirm = new JButton (new AddRouteController("Ajouter",this,this.frame)) ;
        emptyPanels[4][0].add(confirm);

        JButton confirmProtected = new JButton (new AddProtectedRoute("Ajouter route protégé",this,this.frame,"cont")) ;
        emptyPanels[4][1].add(confirmProtected);


    }

    public ArrayList<File> listf(String directoryName, ArrayList<File> files) {
        File directory = new File(directoryName);

        // get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                files.add(file);
            } else if (file.isDirectory()) {
                listf(file.getAbsolutePath(), files);
            }
        }
        return files ;
    }


    public MainFrame getFrame() {
        return frame;
    }

    public JTextField getRoute() {
        return route;
    }

    public JComboBox getMethodList() {
        return methodList;
    }

    public JComboBox getControllerList() {
        return controllerList;
    }

    public JTextField getMethod() {
        return method;
    }
}