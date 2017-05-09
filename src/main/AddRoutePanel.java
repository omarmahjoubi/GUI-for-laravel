package main;

import sun.applet.Main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by omar_ on 09/05/2017.
 */
public class AddRoutePanel extends JPanel{

    private MainFrame frame ;
    private JTextField route ;
    private JComboBox methodList ;
    private JLabel vueName ;

    public AddRoutePanel(MainFrame frame ) {

        this.frame=frame ;

        setLayout(new GridLayout(4, 2));
        setBackground(Color.white);


        // des panels vides pour representer les cellules du grid layout
        JPanel[][] emptyPanels = new JPanel[4][2];
        for (int m = 0; m < 4; m++) {
            for (int l = 0; l < 2; l++) {

                emptyPanels[m][l] = new JPanel();
                add(emptyPanels[m][l]);
            }
        }

        JLabel label = new JLabel("adresse du route");
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

        JButton button = new JButton(new OpenView("sélectionner la vue HTML", this,this.frame));
        emptyPanels[2][0].add(button) ;

        vueName = new JLabel("aucune vue séléctionné");
        emptyPanels[2][1].add(vueName) ;


        JButton confirm = new JButton(new AddRoute("Ajouter", this,this.frame));
        emptyPanels[3][0].add(confirm);

        JButton routeProtected = new JButton (new AddProtectedRoute("ajouter route protége",this,this.frame,"simple")) ;
        emptyPanels[3][1].add(routeProtected);


    }

    public JTextField getRoute() {
        return route;
    }

    public JComboBox getMethodList() {
        return methodList;
    }

    public JLabel getVueName() {
        return vueName;
    }
}
