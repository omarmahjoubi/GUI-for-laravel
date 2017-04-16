package main;
/**
 * Created by Moslah_Hamza on 27/03/2017.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class MainFrame extends JFrame {


    public static int row = 2;
    public static int column=0 ;

    private JTextField field;
    private JTextField route;
    private JTextField view;
    private JButton Confirm;
    private JButton button;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel vueName;
    private JComboBox methodList;



    public MainFrame() {
        super();
        build();
    }

    public void build() {
        setTitle("test");
        setSize(400, 400);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(buildMainPane());
    }

    public JPanel buildContentPane() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);

        JLabel label = new JLabel("Nom du projet");
        panel.add(label);

        field = new JTextField();
        field.setColumns(10);
        panel.add(field);

        button = new JButton(new CreateProject("créer le projet", this));
        panel.add(button);

        button1 = new JButton(new Open("Spécifier le chemin de votre projet", this, ""));
        panel.add(button1);

        return panel;
    }



    void changePanel() {
        setContentPane(buildSecondPane());
        validate();
    }

    public JPanel buildSecondPane() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);


        button4 = new JButton(new Arborescence("Afficher l'arborescence", this));
        panel.add(button4);

        return panel;
    }

    public JPanel buildMainPane() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);
        JFrame frame = this;

        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("Projet");

        JMenuItem create = new JMenuItem(new OpenCreatePanel("Créer un projet", this));
        menu1.add(create);

        JMenuItem open = new JMenuItem(new Open("Ouvrir un projet", this, "old"));
        menu1.add(open);

        menuBar.add(menu1);

        JMenu menu2 = new JMenu("Routage");

        JMenuItem addRoute = new JMenuItem(new OpenAddRoutePanel("ajouter route", this));
        menu2.add(addRoute);

        JMenuItem displayRoute = new JMenuItem(new DisplayRoutes("afficher les routes", this));
        menu2.add(displayRoute);

        menuBar.add(menu2);

        JMenu menu3 = new JMenu("Migrations");

        JMenuItem addMigration = new JMenuItem(new OpenAddMigrationPanel("ajouter une migration", this));
        menu3.add(addMigration);

        menuBar.add(menu3);

        setJMenuBar(menuBar);


        button2 = new JButton(new Guide("Guide", this));
        panel.add(button2);

        return panel;
    }

    public JPanel buildAddRoutePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);

        JLabel label = new JLabel("adresse du route");
        panel.add(label);
        route = new JTextField();
        route.setColumns(20);
        panel.add(route);

        JLabel label1 = new JLabel("methode du route");
        panel.add(label1);

        String[] methods = {"GET", "POST", "PUT", "DELETE"};
        methodList = new JComboBox(methods);
        methodList.setPreferredSize(new Dimension(200, 20));
        methodList.setSelectedItem(0);
        panel.add(methodList);

        JButton button = new JButton(new OpenView("sélectionner la vue HTML", this));
        panel.add(button);

        vueName = new JLabel("aucune vue séléctionné");
        panel.add(vueName);


        JButton confirm = new JButton(new AddRoute("Confirmer", this));
        panel.add(confirm);

        return panel;

    }

    public JPanel buildAddMigrationPanel() {

        this.setSize(1000, 600);

        MigrationPanel panel = new MigrationPanel(this) ;

        return panel ;



    }

    public JTextField getRoute() {
        return route;
    }

    public JTextField getView() {
        return view;
    }

    public JTextField getField() {
        return field;
    }

    public JLabel getVueName() {
        return vueName;
    }

    public JComboBox getMethodList() {
        return methodList;
    }



}
