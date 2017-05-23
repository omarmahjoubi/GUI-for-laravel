package main.panels;

import main.actions.*;
import main.MainFrame;

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
    private JButton home ;

    public AddRoutePanel(MainFrame frame ) {

        this.frame=frame ;

        setLayout(new GridLayout(5, 2));
        setBackground(Color.white);


        // des panels vides pour representer les cellules du grid layout
        JPanel[][] emptyPanels = new JPanel[5][2];
        for (int m = 0; m < 5; m++) {
            for (int l = 0; l < 2; l++) {

                emptyPanels[m][l] = new JPanel();
                add(emptyPanels[m][l]);
            }
        }

        if (Open.absolutePathProject!=null) {
            home =  new JButton( new OpenHome("Acceuil",this.frame,"opened")) ;
        } else {
            home =  new JButton( new OpenHome("Acceuil",this.frame,"")) ;
        }

        emptyPanels[0][1].add(home) ;


        JLabel label = new JLabel("adresse du route");
        emptyPanels[1][0].add(label);

        route = new JTextField();
        route.setColumns(20);
        emptyPanels[1][1].add(route);

        JLabel label1 = new JLabel("methode du route");
        emptyPanels[2][0].add(label1);

        String[] methods = {"GET", "POST", "PUT", "DELETE"};
        methodList = new JComboBox(methods);
        methodList.setPreferredSize(new Dimension(200, 20));
        methodList.setSelectedItem(0);
        emptyPanels[2][1].add(methodList);

        JButton button = new JButton(new OpenView("sélectionner la vue HTML", this,this.frame));
        emptyPanels[3][0].add(button) ;

        vueName = new JLabel("aucune vue séléctionné");
        emptyPanels[3][1].add(vueName) ;


        JButton confirm = new JButton(new AddRoute("Ajouter", this,this.frame));
        emptyPanels[4][0].add(confirm);

        JButton routeProtected = new JButton (new AddProtectedRoute("ajouter route protége",this,this.frame,"simple")) ;
        emptyPanels[4][1].add(routeProtected);


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
